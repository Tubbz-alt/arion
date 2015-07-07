/**
 *
 */
package gov.pnnl.prosser.api;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.apache.commons.io.FilenameUtils;

import gov.pnnl.prosser.api.heat.HeatTemplateWriter;

/**
 * Main class referenced by the manifest for this Package
 * This class will take a java file and an output directory and compile the java file to the output directory
 * It will then reference that compiled file and output the simulator files to the output directory
 * 
 * @author nord229
 */
public abstract class ExperimentMain {

    /**
     * Main method, takes two args, a java file and a output directory
     * This compiles the java file and then references it to write the experiment to the output directory
     * 
     * @param args
     *            args[0] is the input java file and args[1] is the output directory
     * @throws Exception
     *             when any exception occurs
     */
    public static void main(final String... args) throws Exception {
        if (args.length != 2) {
            throw new Exception("Requires two arguments");
        }
        final String input = args[0];
        final String output = args[1];

        final Path inPath = Paths.get(input).toRealPath();
        final Path outPath = Paths.get(output).toAbsolutePath();
        if (!Files.exists(outPath)) {
            Files.createDirectories(outPath);
        }

        final Path jarPath = Paths.get(ExperimentMain.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toRealPath();
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new Exception("Please run with a JDK");
        }

        final int result = compiler.run(null, null, null, "-classpath", jarPath.toString(), inPath.toString(), "-d", outPath.toString());
        if (result != 0) {
            throw new Exception("Unable to compile");
        }

        System.out.println("Compiled!");
        final String name = FilenameUtils.getBaseName(inPath.toString());
        // final Path compiledClassPath = inPath.resolveSibling(name + ".class");
        final URLClassLoader child = new URLClassLoader(new URL[] { outPath.toUri().toURL() }, ExperimentMain.class.getClassLoader());

        final Class<?> compiledClass = Class.forName(name, true, child);
        final Class<? extends Experiment> experimentClass = compiledClass.asSubclass(Experiment.class);
        final Experiment experiment = experimentClass.getConstructor().newInstance();
        experiment.experiment();

        for (final GldSimulator sim : experiment.getGldSimulators()) {
            GldSimulatorWriter.writeGldSimulator(outPath, sim);
        }

        final String ns3Name = experiment.getNs3Simulator().getName() + ".cc";
        Ns3SimulatorWriter.writeNs3Simulator(outPath.resolve(ns3Name), experiment.getNs3Simulator());
        experiment.getExtraExperimentFiles().forEach((f) -> {
            try {
                Files.copy(f, outPath.resolve(f.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Unable to copy extra files", e);
            }
        });
        if (experiment.getFncsSimulator() != null) {
            FncsSimulatorWriter.writeSimulator(outPath, experiment.getFncsSimulator(), experiment.getGldSimulators().size());
        }
        if(experiment.getHeatTemplate() != null) {
            HeatTemplateWriter.writeHeatTemplate(outPath, experiment.getHeatTemplate());
        }
        // TODO FNCS simulator writer
        System.out.println("Written!");
    }
}
