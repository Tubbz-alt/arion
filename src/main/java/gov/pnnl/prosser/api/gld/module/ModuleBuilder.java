package gov.pnnl.prosser.api.gld.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleBuilder {
    protected final List<Module> modules = new ArrayList<>();

    public ModuleBuilder addClimate() {
        this.modules.add(new ClimateModule());
        return this;
    }

    public ModuleBuilder addTape() {
        this.modules.add(new Tape());
        return this;
    }

    public ModuleBuilder.PowerflowModuleBuilder addPowerflow() {
        final ModuleBuilder.PowerflowModuleBuilder builder = new PowerflowModuleBuilder(this);
        return builder;
    }

    public ModuleBuilder.ResidentialModuleBuilder addResidential() {
        final ModuleBuilder.ResidentialModuleBuilder builder = new ResidentialModuleBuilder(this);
        return builder;
    }

    protected void addModule(final Module module) {
        this.modules.add(module);
    }

    public List<Module> build() {
        return this.modules;
    }

    public static class PowerflowModuleBuilder {

        private final ModuleBuilder parent;

        private SolverMethod solverMethod;

        private Long nrIterationLimit;

        protected PowerflowModuleBuilder(final ModuleBuilder parent) {
            this.parent = parent;
        }

        public ModuleBuilder.PowerflowModuleBuilder solverMethod(final SolverMethod solverMethod) {
            this.solverMethod = solverMethod;
            return this;
        }

        public ModuleBuilder.PowerflowModuleBuilder nrIterationLimit(final Long nrIterationLimit) {
            this.nrIterationLimit = nrIterationLimit;
            return this;
        }

        public ModuleBuilder and() {
            this.parent.addModule(new PowerflowModule(solverMethod, nrIterationLimit));
            return this.parent;
        }
    }

    public static class ResidentialModuleBuilder {
        private final ModuleBuilder parent;

        private String implicitEnduses;

        protected ResidentialModuleBuilder(final ModuleBuilder parent) {
            this.parent = parent;
        }

        public ModuleBuilder.ResidentialModuleBuilder implicitEnduses(final String implicitEnduses) {
            this.implicitEnduses = implicitEnduses;
            return this;
        }

        public ModuleBuilder and() {
            this.parent.addModule(new Residential(implicitEnduses));
            return this.parent;
        }
    }
}