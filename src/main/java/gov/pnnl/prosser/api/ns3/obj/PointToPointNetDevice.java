/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

/**
 * The PointToPointNetDevice is used to communicate over a 
 * point-to-point channel.
 * 
 * @author happ546
 *
 */
public class PointToPointNetDevice extends NetDevice {
	
	/**
	 * Creates a new PointToPointNetDevice with the given name
	 * 
	 * @param name
	 */
	public PointToPointNetDevice(String name) {
		this.setName(name);
	}

}
