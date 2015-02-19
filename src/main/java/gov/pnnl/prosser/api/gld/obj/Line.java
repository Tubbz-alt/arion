/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldUtils;
import gov.pnnl.prosser.api.gld.lib.Conductor;
import gov.pnnl.prosser.api.gld.lib.LineConfiguration;

/**
 * Generic Power Line
 *
 * @author nord229
 */
public abstract class Line<C extends Conductor, Q extends LineConfiguration<C>> extends LinkObject {

    /**
     * length of line in feet
     */
    private double length;

    /**
     * Line Configuration
     */
    private Q configuration;

    /**
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * @param length
     *            the length to set
     */
    public void setLength(final double length) {
        this.length = length;
    }

    /**
     * @return the configuration
     */
    public Q getConfiguration() {
        return configuration;
    }

    /**
     * @param configuration
     *            the configuration to set
     */
    public void setConfiguration(final Q configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        GldUtils.writeProperty(sb, "length", this.length, "ft");
        GldUtils.writeProperty(sb, "configuration", this.configuration);
    }

}
