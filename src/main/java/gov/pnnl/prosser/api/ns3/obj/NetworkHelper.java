/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;

/**
 * A base class for the specific network helpers (Wi-Fi, LTE, CSMA, etc.)
 * 
 * @author happ546
 *
 */
public class NetworkHelper extends AbstractNs3Object {


	/**
	 * @param prefix the filename prefix
	 */
	public void enablePcapAll(String prefix) {
		appendPrintObj(this.getName() + ".EnablePcapAll (\"" + prefix + "\", true);\n");
	}

	/**
	 *
	 * @param prefix the filename prefix
	 * @param devs the NetDeviceContainer
	 * @param index the index of the device in devs to do a PCAP on
	 */
	public void enablePcap(String prefix, NetDeviceContainer devs, int index) {
		appendPrintObj(this.getName() + ".EnablePcap (\"" + prefix + "\", " + devs.getName() +
				".Get (" + index + "));\n");
	}


	/**
	 * @param prefix the filename prefix
	 */
	public void enableAsciiAll(String prefix) {
		appendPrintObj(this.getName() + ".EnableAsciiAll (\"" + prefix + "\");\n");
	}

	/**
	 *
	 * @param prefix the filename prefix
	 * @param devs the NetDeviceContainer
	 * @param index the index of the device in devs to do an Ascii trace of
	 */
	public void enableAscii(String prefix, NetDeviceContainer devs, int index) {
		appendPrintObj(this.getName() + ".EnableAscii (\"" + prefix + "\", " + devs.getName() +
				".Get (" + index + "));\n");
	}
	
	/**
	 * 
	 * @param nodes the NodeContainer to install this p2p channel on
	 * @param destination the NetDeviceContainer to install the p2p nodes on
	 * Equivalent statement: destination = this.install(nodes);
	 */
	public void install(NodeContainer nodes, NetDeviceContainer destination) {
		appendPrintObj(destination.getName() + " = " + this.getName() + ".Install(" + nodes.getName() + ");\n");
	}
	
	/**
	 * @param type
	 * @return the NetworkHelper of the given NetworkType
	 */
	public NetworkHelper getHelper(NetworkType type) {
		
		if (type.name().equalsIgnoreCase("csma")) {
			return new CsmaHelper(this.getName());
		} else if (type.name().equalsIgnoreCase("lte")) {
			return new LteHelper(this.getName());
		} else if (type.name().equalsIgnoreCase("p2p")) {
			return new PointToPointHelper(this.getName());
		} else {
			return new WifiHelper(this.getName());
		}

	}
	
}
