/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.gld.GldUtils;

/**
 * Load objects represent static loads and export both voltages and current
 * 
 * @author nord229
 */
public class Load extends Node {

    /**
     * the parent node to apply this load to
     */
    private Node parent;

    /**
     * constant power load on phase A, real only, specified as W
     */
    private String phaseAConstantReal;

    /**
     * constant power load on phase B, real only, specified as W
     */
    private String phaseBConstantReal;

    /**
     * constant power load on phase C, real only, specified as W
     */
    private String phaseCConstantReal;

    /**
     * Get the parent node to apply this load to
     * 
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Set the parent node to apply this load to
     * 
     * @param parent
     *            the parent to set
     */
    public void setParent(final Node parent) {
        this.parent = parent;
    }

    /**
     * Get the constant power load on phase A, real only, specified as W
     * 
     * @return the phaseAConstantReal
     */
    public String getPhaseAConstantReal() {
        return phaseAConstantReal;
    }

    /**
     * Set the constant power load on phase A, real only, specified as W
     * 
     * @param phaseAConstantReal
     *            the phaseAConstantReal to set
     */
    public void setPhaseAConstantReal(final String phaseAConstantReal) {
        this.phaseAConstantReal = phaseAConstantReal;
    }

    /**
     * Get the constant power load on phase B, real only, specified as W
     * 
     * @return the phaseBConstantReal
     */
    public String getPhaseBConstantReal() {
        return phaseBConstantReal;
    }

    /**
     * Set the constant power load on phase B, real only, specified as W
     * 
     * @param phaseBConstantReal
     *            the phaseBConstantReal to set
     */
    public void setPhaseBConstantReal(final String phaseBConstantReal) {
        this.phaseBConstantReal = phaseBConstantReal;
    }

    /**
     * Get the constant power load on phase C, real only, specified as W
     * 
     * @return the phaseCConstantReal
     */
    public String getPhaseCConstantReal() {
        return phaseCConstantReal;
    }

    /**
     * Set the constant power load on phase C, real only, specified as W
     * 
     * @param phaseCConstantReal
     *            the phaseCConstantReal to set
     */
    public void setPhaseCConstantReal(final String phaseCConstantReal) {
        this.phaseCConstantReal = phaseCConstantReal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "load";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        GldUtils.writeProperty(sb, "parent", this.parent);
        GldUtils.writeProperty(sb, "constant_power_A_real", this.phaseAConstantReal);
        GldUtils.writeProperty(sb, "constant_power_B_real", this.phaseBConstantReal);
        GldUtils.writeProperty(sb, "constant_power_C_real", this.phaseCConstantReal);
    }

}
