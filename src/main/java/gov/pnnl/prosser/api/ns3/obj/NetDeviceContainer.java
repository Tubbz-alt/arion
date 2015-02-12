/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

/**
 * @author happ546
 *
 */
public class NetDeviceContainer extends AbstractNs3Object {
	
	private NodeContainer nodes;
	
	/**
	 * Create an empty NetDeviceContainer
	 */
	public NetDeviceContainer() {
		this.nodes = new NodeContainer();
	}
	
	/**
	 * 
	 * @param nodes the NodeContainer holding the Node to add to this NetDeviceContainer
	 * @param index the index of the Node to add to this NetDeviceContainer
	 */
	public void addDevice(NetDeviceContainer nodes, int index) {
		this.nodes.addNode(nodes.getDevice(index));
		appendPrintObj(this.getName() + ".Add(" + nodes.getName() + ".Get(" + index + "));\n");
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer to add to this NetDeviceContainer's 
	 * 			NodeContainer
	 */
	public void addNodes(NodeContainer sourceNodes) {
		this.nodes.addNodeContainerNoPrint(sourceNodes);
	}
	
	/**
	 * 
	 * @param sourceNodes the NodeContainer to append to this NetDeviceContainer
	 */
	public void addDevices(NetDeviceContainer sourceDevices) {
		this.nodes.addNetDeviceContainerNoPrint(sourceDevices);
	}

	/**
	 * 
	 * @param sourceNodes the NodeContainer to store in this NetDeviceContainer
	 */
	public void setNodes(NodeContainer sourceNodes) {
		this.nodes = sourceNodes;
	}

	/**
	 * 
	 * @param index
	 * @return the device Node in this net device at the given index
	 */
	public Node getDevice(int index) {
		return this.nodes.getNode(index);
	}
	
	/**
	 * 
	 * @return the number of devices in this NetDeviceContainer
	 */
	public int getNumDevices() {
		return this.nodes.getNumNodes();
	}
	
}
