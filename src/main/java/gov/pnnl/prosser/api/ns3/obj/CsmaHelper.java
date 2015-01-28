/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;

import java.util.HashMap;
import java.util.Map;

/**
 * @author happ546
 *
 */
public class CsmaHelper extends AbstractNs3Object {
	
	private Map<String, String> channelAttributes;
	private Map<String, String> deviceAttributes;
	
	public CsmaHelper() {
		this.channelAttributes = new HashMap<String, String>();
		this.deviceAttributes = new HashMap<String, String>();
	}
	
	public void install(NodeContainer sourceNodes, NetDeviceContainer destinationContainer) {
		destinationContainer.setNodes(sourceNodes);
		appendPrintObj(destinationContainer.getName() + " = " + this.getName() + ".Install(" + sourceNodes.getName() + ");\n");
	}
	
	/**
	 * Set attributes for this CSMA channel
	 * @param attr the attribute to set the value to
	 * @param value
	 */
	public void setChannelAttribute(String attr, String value) {
		channelAttributes.put(attr, value);
		appendPrintObj(this.getName() + ".SetChannelAttribute(\"" + attr + "\", StringValue(\"" + value + "\"));\n");
	}
	
	/**
	 * Set attributes for this CSMA device
	 * @param attr the attribute to set the value to
	 * @param value
	 */
	public void setDeviceAttribute(String attr, String value) {
		deviceAttributes.put(attr, value);
		appendPrintObj(this.getName() + ".SetDeviceAttribute(\"" + attr + "\", StringValue(\"" + value + "\"));\n");
	}
	
}
