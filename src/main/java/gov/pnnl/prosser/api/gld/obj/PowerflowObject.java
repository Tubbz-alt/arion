/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.GldUtils;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;

import java.util.EnumSet;
import java.util.Objects;

/**
 * Generic Powerflow Object
 *
 * @author nord229
 */
public abstract class PowerflowObject extends AbstractGldObject {

    /**
     * Nominal Voltage
     */
    private Double nominalVoltage;

    /**
     * Phases this object is using
     */
    private EnumSet<PhaseCode> phases;

    /**
     * Get the Nominal Voltage
     * 
     * @return the nominalVoltage
     */
    public Double getNominalVoltage() {
        return nominalVoltage;
    }

    /**
     * Set the Nominal Voltage
     * 
     * @param nominalVoltage
     *            the nominalVoltage to set
     */
    public void setNominalVoltage(final Double nominalVoltage) {
        this.nominalVoltage = nominalVoltage;
    }

    /**
     * Get the Phases this object is using
     * 
     * @return the phases
     */
    public EnumSet<PhaseCode> getPhases() {
        return phases;
    }

    /**
     * Set the Phases this object is using
     * 
     * @param phases
     *            the phases to set
     */
    public void setPhases(final EnumSet<PhaseCode> phases) {
        this.phases = phases;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.nominalVoltage, this.phases);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PowerflowObject other = (PowerflowObject) obj;
        return Objects.equals(this.nominalVoltage, other.nominalVoltage)
                && Objects.equals(this.phases, other.phases);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        final StringBuilder phaseBuilder = new StringBuilder();
        final boolean hasAllOfS;
        if (this.phases.containsAll(PhaseCode.S)) {
            hasAllOfS = true;
        } else {
            hasAllOfS = false;
        }
        for (final PhaseCode code : this.phases) {
            if (hasAllOfS) {
                switch (code) {
                    case S1:
                    case S2:
                    case SN:
                        continue;
                    default:
                        break;

                }
            }
            phaseBuilder.append(code.name());
        }
        if (hasAllOfS) {
            phaseBuilder.append("S");
        }

        GldUtils.writeProperty(sb, "phases", phaseBuilder.toString());
        GldUtils.writeProperty(sb, "nominal_voltage", this.nominalVoltage);
    }
}
