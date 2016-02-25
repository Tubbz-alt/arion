/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.enums.PhaseCode;
import gov.pnnl.prosser.api.gld.enums.SwitchStatus;

import java.util.EnumSet;

public class Switch extends LinkObject {
	private EnumSet<PhaseCode> phases;
	private SwitchStatus status;
	
	/**
	 * @param simulator
	 */
	public Switch(GldSimulator simulator) {
		super(simulator);
	}
	
	/**
	 * @return the phases
	 */
	public EnumSet<PhaseCode> getPhases() {
		return phases;
	}

	/**
	 * @param phases the phases to set
	 */
	public void setPhases(EnumSet<PhaseCode> phases) {
		this.phases = phases;
	}

	/**
	 * @return the status
	 */
	public SwitchStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(SwitchStatus status) {
		this.status = status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getGldObjectType() {
		return "switch";
	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        
        writeProperty(sb, "phases", PhaseCode.writeGldProperties(this.phases));
        writeProperty(sb, "status", this.status);
    }
}