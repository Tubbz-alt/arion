/**
 *
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.ns3.module.Namespace;
import gov.pnnl.prosser.api.ns3.obj.*;
import gov.pnnl.prosser.api.ns3.obj.csma.CsmaChannel;
import gov.pnnl.prosser.api.ns3.obj.p2p.PointToPointChannel;
import gov.pnnl.prosser.api.ns3.obj.wifi.WifiMacType;
import gov.pnnl.prosser.api.ns3.obj.wifi.YansWifiChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Stub for NS-3 Simulator configurations
 *
 * @author nord229
 */
public class Ns3Simulator {

	private Ns3Network network;
	private String name;
	private List<Namespace> namespaces;
	private static List<AbstractNs3Object> ns3Objects;
	
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
	 * @param object the AbstractNs3Object to add to this Ns3Simulator's
	 *               list of ns-3 objects
	 */
	public static void addObject(AbstractNs3Object object) {
		ns3Objects.add(object);
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
	 * 
     * @return the generated GridlabD market network interface prefix
	 */
	private String generateAuctionPrefix() {
		
		UUID myUUID = UUID.randomUUID();
		String s = myUUID.toString();
		return s;
	}
	
	
	/**
	 * Initializes the modules, namespaces, and objects used in this network based on 
	 * the user specified parameters
	 * @param numAuctions
     *          the number of GridlabD auctions (aka markets) that will be created
	 *          in this network
	 */
	public void setup(final int numAuctions) {
		
		network = new Ns3Network();
		
		namespaces.add(new Namespace("ns3"));
		namespaces.add(new Namespace("std"));

		for (int i = 0; i < numAuctions; i++) {
			network.addAuctionName(generateAuctionPrefix());
		}
		
		network.setupFncsSimulator();
	}

	/**
	 * Creates the FncsApplicationHelper used to connect the GLD and ns-3 simulators
	 */
	public void setupFncsApplicationHelper() {
		network.setupFncsApplicationHelper();
	}


    /**
     * Populates global routing tables on each Router in the network
     */
    public void setupGlobalRouting() {
        network.setupGlobalRouting();
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
	 * @param type
	 * 			the NetworkType of this Channel
	 * @return an instance of the specified subclass of Channel
	 */
	public Channel channel(NetworkType type) {
		if (type.equals(NetworkType.CSMA)) {
			return new CsmaChannel();
		} else if (type.equals(NetworkType.P2P)) {
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
	 * @return the list of house Channels for the Ns3Network
	 */
	public List<Channel> getHouseChannels() {
		return this.network.getHouseChannels();
	}

	/**
	 * @return the list of auction Channels for the Ns3Network
	 */
	public List<Channel> getAuctionChannels() {
		return network.getAuctionChannels();
	}

	/**
	 * Adds this Channel to the network
	 * @param channel
	 */
	public void addChannel(Channel channel) {
		this.network.addChannel(channel);
	}

	/**
	 * Adds this House Channel to the network
	 * @param houseChannel
	 */
	public void addHouseChannel(Channel houseChannel) {
		this.network.addHouseChannel(houseChannel);
	}

	public void addFncsNode(Router router) {
		this.network.addFncsNode(router.getNode());
	}

	/**
	 * @param auctionChannel
	 * @return a Router connected to this auctionChannel
	 */
	public Router auctionRouter(Channel auctionChannel) {
		return router(auctionChannel, "auction", false, null);
	}

	/**
	 * @param auctionChannel
	 * @param debug
	 * 			set true to enable PCAP and ASCII files for this Router
	 * @return a Router connected to this auctionChannel
	 */
	public Router auctionRouter(Channel auctionChannel, boolean debug) {
		return router(auctionChannel, "auction", debug, null);
	}

	/**
	 * @param houseChannel
	 * @return a Router connected to this houseChannel
	 */
	public Router houseRouter(Channel houseChannel) {
		return router(houseChannel, "house", false, null);
	}

	/**
	 * @param houseChannel
	 * @param debug
	 * 			set true to enable PCAP and ASCII files for this Router
	 * @return a Router connected to this houseChannel
	 */
	public Router houseRouter(Channel houseChannel, boolean debug) {
		return router(houseChannel, "house", debug, null);
	}

	public Router houseRouter(Channel houseChannel, WifiMacType macType) {
		return router(houseChannel, "house", false, macType);
	}

	/**
	 * @param backboneChannel
	 * @return a Router connected to this backboneChannel
	 */
	public Router backboneRouter(Channel backboneChannel) {
		return router(backboneChannel, "backbone", false, null);
	}

	/**
	 * @param backboneChannel
	 * @param debug
	 * 			set true to enable PCAP and ASCII trace files for this Router
	 * @return a Router connected to this backboneChannel
	 */
	public Router backboneRouter(Channel backboneChannel, boolean debug) {
		return router(backboneChannel, "backbone", debug, null);
	}

	public Router backboneRouter(Channel backboneChannel, WifiMacType macType) {
		return router(backboneChannel, "backbone", false, macType);
	}

	private Router router(Channel chan, String routerType, boolean debug, WifiMacType macType) {
		NetworkType type = chan.getType();
		String name = routerType + "Rtr_" + chan.getName();
		// Add chan to appropriate list of Channels
		if (routerType.equals("house")) {
			this.network.addHouseChannel(chan);
		} else if (routerType.equals("auction")) {
			this.network.addAuctionChannel(chan);
		}
		this.network.addChannel(chan);
		Router router = new Router(name);
		router.setPcap(debug);
		router.setAscii(debug);
		router.setChannel(chan);
		router.setMacType(macType);
		if (type.equals(NetworkType.P2P)) {
			((PointToPointChannel) chan).setRouterA(router);
		}
		// Add router to node container needed for FNCSApplicationHelper stuff if
		//  router is not a backbone node
		if (!routerType.equals("backbone")) {
			addFncsNode(router);
		}
		return router;
	}

    /**
     * Adds the GLD Controller names to the ns3 global list of names
     * for the FncsApplicationHelper setup
     */
    public void addControllerNames() { this.network.addControllerNames();	}

	/**
	 * @return a List of Strings of auction names
	 */
	public List<String> getAuctionNames() {
		return network.getAuctionNames();
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
}
