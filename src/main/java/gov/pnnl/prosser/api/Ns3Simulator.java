/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.c.obj.Pointer;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.ns3.module.Namespace;
import gov.pnnl.prosser.api.ns3.obj.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Stub for NS-3 Simulator configurations
 *
 * @author nord229
 */
public class Ns3Simulator {
	
	private Ns3Network network;
	private String name;
	private List<Namespace> namespaces;
	private List<AbstractNs3Object> ns3Objects;
	
	/**
	 * Create a new Ns3Simulator
	 */
	public Ns3Simulator(String name) {
		this.network = null;
		this.name = name;
		this.namespaces = new ArrayList<>();
		this.ns3Objects = new ArrayList<>();
	}

	/**
	 *
	 * @return name
	 * 				the name of this Ns3Simulator
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Initializes the modules, namespaces, and objects used in this network based on 
	 * the user specified parameters
	 * @param addressBase
	 * @param addressMask 
	 * @param backboneDataRate 
	 * @param backboneDelay 
	 * @param stopTime 
	 */
	public void setup(final String addressBase, 
						final String addressMask, final String backboneDataRate, 
						final String backboneDelay, final double stopTime,
					  	final String marketNIPrefix, final String controllerNIPrefix) {
		
		network = new Ns3Network();
		
		namespaces.add(new Namespace("ns3"));
		namespaces.add(new Namespace("std"));

		network.setAddrBase(addressBase);
		network.setAddrMask(addressMask);
		
		network.setBackboneDataRate(backboneDataRate);
		network.setBackboneDelay(backboneDelay);
		
		network.setStopTime(stopTime);
		
		network.setupFncsSimulator(marketNIPrefix, controllerNIPrefix);


		
		//network.buildBackbone();
	}
	
	/**
	 * Creates the FncsApplicationHelper used to connect the GLD and ns-3 simulators
	 */
	public void setupFncsApplicationHelper() {
		network.setupFncsApplicationHelper();
	}
	
    /**
     * Gets the Modules used in this simulation
     * @return the list of Modules
     */
	public List<Module> getModules() {
		return network.getModules();
	}
	
    /**
     * Gets the Namespaces used in this simulation
     * @return the list of Namespaces
     */
	public List<Namespace> getNamespaces() {
		return this.namespaces;
	}
	
    /**
     * Gets the ns-3 objects used in this simulation
     * @return the list of AbstractNs3Objects
     */
	public List<AbstractNs3Object> getObjects() {		
		return ns3Objects;
	}

	/**
	 * @param auctions
	 */
	public void setAuctions(List<AuctionObject> auctions) {
		network.setAuctions(auctions);
	}

	/**
	 * @param controllers
	 */
	public void setControllers(List<Controller> controllers) {
		network.setControllers(controllers);
	}

	/**
	 * @param controllerPrefix
	 */
	public void setGldNodePrefix(String controllerPrefix) {
		network.setGldNodePrefix(controllerPrefix);
	}
	
	/**
	 * @param type
	 * @return an instance of the specified subclass of Channel
	 */
	public Channel channel(NetworkType type) {
		if (type.name().equalsIgnoreCase("csma")) {
			return new CsmaChannel();
		} else if (type.name().equalsIgnoreCase("p2p")) {
			return new PointToPointChannel();
		} else {
			return new YansWifiChannel();
		}
	}

	/**
	 * @return the list of Channels for the Ns3Network
	 */
	public List<Channel> getChannels() {
		return this.network.getChannels();
	}

	/**
	 *
	 * @return the list of house Channels for the Ns3Network
	 */
	public List<Channel> getHouseChannels() {
		return this.network.getHouseChannels();
	}

	/**
	 * Build the Controller and Auction nodes and connect to backbone network
	 */
	public void buildFrontend() {
		this.ns3Objects = network.buildFrontend();
	}

	/**
	 * Adds this Channel to the network
	 * @param channel
	 */
	public void addChannel(Channel channel) {
		this.network.addChannel(channel);
		this.ns3Objects.add(channel);
	}

	/**
	 * Adds this House Channel to the network
	 * @param houseChannel
	 */
	public void addHouseChannel(CsmaChannel houseChannel) {
		this.network.addHouseChannel(houseChannel);
	}

	/**
	 * Adds the GLD Controller names to the ns3 global list of names
	 * for the FncsApplicationHelper setup
	 */
	public void addControllerNames() { this.network.addControllerNames();	}

	/**
	 * Prints the GLD Controller names and the ns-3 Node names to
	 * the output ns-3 .cc file
	 * @return a string
	 */
	public String printControllerNames() {
		return this.network.printControllerNames();
	}

	/**
	 *
	 * @return the base IP address of the network
	 */
	public String getIPBase() {
		return network.getAddrBase();
	}

	/**
	 *
	 * @return the IP address subnet mask of the network
	 */
	public String getIPMask() {
		return network.getAddrMask();
	}

	/**
	 * Populates global routing tables on each Router in the network
	 */
	public void setupGlobalRouting() {
		network.setupGlobalRouting();
	}
}
