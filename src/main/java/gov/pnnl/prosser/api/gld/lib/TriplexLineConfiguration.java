/**
 *
 */
package gov.pnnl.prosser.api.gld.lib;

import java.util.Objects;

import gov.pnnl.prosser.api.GldSimulator;

/**
 * Triplex Line Configuration
 *
 * @author nord229
 */
public class TriplexLineConfiguration extends LineConfiguration<TriplexLineConductor> {

    /**
     * Conductor for Phase 1
     */
    private TriplexLineConductor phase1Conductor;

    /**
     * Conductor for Phase 2
     */
    private TriplexLineConductor phase2Conductor;

    /**
     * Conductor for Neutral Phase
     */
    private TriplexLineConductor phaseNConductor;

    /**
     * thickness of insulation around cabling in inches
     */
    private double insulationThickness;

    /**
     * total diameter of cable in inches
     */
    private double diameter;

    public TriplexLineConfiguration(final GldSimulator simulator) {
        super(simulator);
    }

    /**
     * Get the Conductor for Phase 1
     * 
     * @return the phase1Conductor
     */
    public TriplexLineConductor getPhase1Conductor() {
        return phase1Conductor;
    }

    /**
     * Set the Conductor for Phase 1
     * 
     * @param phase1Conductor
     *            the phase1Conductor to set
     */
    public void setPhase1Conductor(final TriplexLineConductor phase1Conductor) {
        this.phase1Conductor = phase1Conductor;
    }

    /**
     * Get the Conductor for Phase 2
     * 
     * @return the phase2Conductor
     */
    public TriplexLineConductor getPhase2Conductor() {
        return phase2Conductor;
    }

    /**
     * Set the Conductor for Phase 2
     * 
     * @param phase2Conductor
     *            the phase2Conductor to set
     */
    public void setPhase2Conductor(final TriplexLineConductor phase2Conductor) {
        this.phase2Conductor = phase2Conductor;
    }

    /**
     * Get the Conductor for Neutral Phase
     * 
     * @return the phaseNConductor
     */
    public TriplexLineConductor getPhaseNConductor() {
        return phaseNConductor;
    }

    /**
     * Set the Conductor for Neutral Phase
     * 
     * @param phaseNConductor
     *            the phaseNConductor to set
     */
    public void setPhaseNConductor(final TriplexLineConductor phaseNConductor) {
        this.phaseNConductor = phaseNConductor;
    }

    /**
     * Get the thickness of insulation around cabling in inches
     * 
     * @return the insulationThickness
     */
    public double getInsulationThickness() {
        return insulationThickness;
    }

    /**
     * Set the thickness of insulation around cabling in inches
     * 
     * @param insulationThickness
     *            the insulationThickness to set
     */
    public void setInsulationThickness(final double insulationThickness) {
        this.insulationThickness = insulationThickness;
    }

    /**
     * Get the total diameter of cable in inches
     * 
     * @return the diameter
     */
    public double getDiameter() {
        return diameter;
    }

    /**
     * Set the total diameter of cable in inches
     * 
     * @param diameter
     *            the diameter to set
     */
    public void setDiameter(final double diameter) {
        this.diameter = diameter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "triplex_line_configuration";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "conductor_1", this.phase1Conductor);
        writeProperty(sb, "conductor_2", this.phase2Conductor);
        writeProperty(sb, "conductor_N", this.phaseNConductor);
        writeProperty(sb, "insulation_thickness", this.insulationThickness);
        writeProperty(sb, "diameter", this.diameter);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phase1Conductor, phase2Conductor, phaseNConductor, insulationThickness, diameter);
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
        final TriplexLineConfiguration other = (TriplexLineConfiguration) obj;
        return Objects.equals(this.phase1Conductor, other.phase1Conductor)
                && Objects.equals(this.phase2Conductor, other.phase2Conductor)
                && Objects.equals(this.phaseNConductor, other.phaseNConductor)
                && Objects.equals(this.insulationThickness, other.insulationThickness)
                && Objects.equals(this.diameter, other.diameter);
    }

}
