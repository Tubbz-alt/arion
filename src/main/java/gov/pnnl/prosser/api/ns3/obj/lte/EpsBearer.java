/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj.lte;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * This class is used in the setup of LTE wireless networks.
 * 
 * @author happ546
 *
 */
public class EpsBearer extends AbstractNs3Object {
	
	/**
	 * Creates a new EpsBearer with the given name
	 * @param name
	 */
	public EpsBearer(String name) {
		this.setName(name);
	}

	/**
	 * Sets the name of this EpsBearer without saving any output
	 */
	@Override
	public void setName(String name) {
		super.setNameString(name);
	}

	/**
	 * Sets the Qci to use
	 * @param q the Qci (Quality of service Class Indicator) used by this EpsBearer
	 */
	public void setQci(Qci q) {
		// Write the Qci constructor string
		this.appendPrintInfo("\n  enum EpsBearer::Qci " + q.getName() + " = EpsBearer::" + q.name() + ";\n");
		// Create an EpsBearer with given Qci
		this.appendPrintInfo(this.getClass().getSimpleName() + " " + this.getName() + "(" + q.getName() + ");\n");
	}
	
}
