/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.GldUtils;

/**
 * Marker for Residential Enduse objects
 *
 * @author nord229
 */
public abstract class ResidentialEnduse extends AbstractGldObject {

    private Long scheduleSkew;

    /**
     * @return the scheduleSkew
     */
    public Long getScheduleSkew() {
        return scheduleSkew;
    }

    /**
     * @param scheduleSkew
     *            the scheduleSkew to set
     */
    public void setScheduleSkew(final Long scheduleSkew) {
        this.scheduleSkew = scheduleSkew;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        GldUtils.writeProperty(sb, "schedule_skew", this.scheduleSkew);
    }

}
