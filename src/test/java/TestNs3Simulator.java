/**
 * 
 */

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.ns3.module.Namespace;
import gov.pnnl.prosser.api.ns3.obj.Ns3Network;
import java.util.ArrayList;
import java.util.List;

/**
 * @author happ546
 *
 */
public class TestNs3Simulator {
	
	private Ns3Network network;
	
	public void setup() {
		
		// User inputs basic params (Network type, addr base & mask, # of nodes [or infer from gldList?])
		network = new Ns3Network();

	}
	
	public List<Module> getModules() {
		// enum? of all commonly used modules that network can select from based on params
		return network.getModules();
	}

	public List<Namespace> getNamespaces() {
		List<Namespace> namespaces = new ArrayList<Namespace>();
		namespaces.add(new Namespace("ns3"));
		//namespaces.add(new Namespace("std")); //TODO need std?
		
		return namespaces;
	}

	public List<AbstractNs3Object> getObjects() {
		
		final List<AbstractNs3Object> objects = null; //network.build(); // Not a real builder pattern; after necessary params, use network type for type-specific method to construct nodes, install devices/applications, etc.
		
		return objects;
	}


	
}
