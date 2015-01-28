/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import java.util.ArrayList;

/**
 * @author happ546
 *
 */
public class NodeContainer extends AbstractNs3Object {
	/**
	 * The number of nodes in this NodeContainer
	 */
	private int numNodes;
	/**
	 * An array containing the Nodes in this NodeContainer
	 */
	private ArrayList<Node> nodes;
	
	/**
	 * Initializes an empty NodeContainer
	 */
	public NodeContainer() {
		this.numNodes = 0;
		this.nodes = new ArrayList<Node>();
	}

	/**
	 * Creates specified number of nodes in this NodeContainer
	 * @param numNodes
	 */
	public void create(int numNodes) {
		this.numNodes = numNodes;
		for (int i = 0; i < numNodes; i++) {
			this.addNode(new Node());
		}
		appendPrintObj(this.getName() + ".Create(" + this.numNodes + ");\n");
	}

	/**
	 * Appends a new node to the end of the nodes array
	 */
	public void addNode(Node node) {
		this.numNodes++;
		this.nodes.add(node);
	}
	
	/**
	 * 
	 * @param container the NodeContainer containing the Node to add to this NodeContainer
	 * @param index the index of the Node to be added
	 */
	public void addNode(NodeContainer container, int index) {
		this.addNode(container.getNode(index));
		appendPrintObj(this.getName() + " = " + container.getName() + ".Get(0);\n");
	}

	/**
	 * @return the number of Nodes in this NodeContainer
	 */
	public int getNumNodes() {
		return numNodes;
	}

	/**
	 * 
	 * @param index the integer index of the Node to retrieve from the NodeContainer
	 * @return the Node at the given index or null if there is no node at that index
	 */
	public Node getNode(int index) {
		if (index > this.nodes.size() || this.nodes.get(index) == null) {
			return null;
		}
		return nodes.get(index);
	}
	
}