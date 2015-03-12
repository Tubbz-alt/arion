/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.c.obj.Pointer;
import gov.pnnl.prosser.api.c.obj.StringMap;
import gov.pnnl.prosser.api.c.obj.Vector;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.gld.obj.Controller;
import gov.pnnl.prosser.api.ns3.enums.NetworkType;
import gov.pnnl.prosser.api.ns3.enums.Qci;
import gov.pnnl.prosser.api.ns3.module.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class takes the basic network parameters from the user and constructs all of the 
 * ns-3 backbone to make it work.
 * 
 * @author happ546
 *
 */
public class Ns3Network {
	
	private NetworkType backboneType, auctionType;
	private String addrBase, addrMask;
	private String gldNodePrefix;
	private String backboneDataRate, backboneDelay;
	private int numBackboneNodes, numChannels;
	private double stopTime;
	private NodeContainer backboneNodes, nodes;
	private InternetStackHelper iStackHelper;
	private List<Module> modules;
	private List<AuctionObject> auctions;
	private List<Controller> controllers;
	private List<AbstractNs3Object> ns3Objects;
	private List<AbstractGldObject> gldObjects;
	private List<Channel> channels;
	private Vector<String> names;

	
	/**
	 * Create a new Ns3Network object, used to set up an ns-3 network for use in Prosser simulation
	 */
	public Ns3Network() {
		this.backboneType = null;
		this.auctionType = null;
		this.addrBase = "10.1.0.0";
		this.addrMask = "255.255.255.0";
		this.stopTime = Double.MAX_VALUE;
		this.backboneNodes = new NodeContainer();
		this.nodes = new NodeContainer();
		this.iStackHelper = new InternetStackHelper();
		this.modules = new ArrayList<Module>();
		this.auctions = new ArrayList<>();
		this.controllers = new ArrayList<>();
		this.ns3Objects = new ArrayList<>();
		this.gldObjects = new ArrayList<>();
		this.channels = new ArrayList<>();
	}
	
	/**
	 * @return the NetworkType of the Backbone infrastructure
	 */
	public NetworkType getBackboneType() {
		return backboneType;
	}
	
	/**
	 * 
	 * @param type the NetworkType to set for the Backbone infrastructure
	 */
	public void setBackboneType(NetworkType backboneType) {
		this.backboneType = backboneType;
	}
	
	/**
	 * @return the NetworkType of the Auction market
	 */
	public NetworkType getAuctionType() {
		return auctionType;
	}
	
	/**
	 * 
	 * @param type the NetworkType to set for the Auction market
	 */
	//TODO: Not sure this type of information should be stored here; Need convincing to allow simulator specific variables in NS3 objects
	public void setAuctionType(NetworkType auctionType) {
		this.auctionType = auctionType;
	}

	/**
	 * @return the addrBase
	 */
	public String getAddrBase() {
		return addrBase;
	}

	/**
	 * @param addrBase the addrBase to set
	 */
	public void setAddrBase(String addrBase) {
		this.addrBase = addrBase;
	}

	/**
	 * @return the addrMask
	 */
	public String getAddrMask() {
		return addrMask;
	}

	/**
	 * @param addrMask the addrMask to set
	 */
	public void setAddrMask(String addrMask) {
		this.addrMask = addrMask;
	}

	/**
	 * @return the gldNodePrefix
	 */
	public String getGldNodePrefix() {
		return gldNodePrefix;
	}

	/**
	 * @param gldNodePrefix the gldNodePrefix to set
	 */
	public void setGldNodePrefix(String gldNodePrefix) {
		this.gldNodePrefix = gldNodePrefix;
	}
	
	/**
	 * @return the backboneDataRate
	 */
	public String getBackboneDataRate() {
		return backboneDataRate;
	}

	/**
	 * @param backboneDataRate the backboneDataRate to set
	 */
	public void setBackboneDataRate(String backboneDataRate) {
		this.backboneDataRate = backboneDataRate;
	}

	/**
	 * @return the backboneDelay
	 */
	public String getBackboneDelay() {
		return backboneDelay;
	}

	/**
	 * @param backboneDelay the backboneDelay to set
	 */
	public void setBackboneDelay(String backboneDelay) {
		this.backboneDelay = backboneDelay;
	}
	
	/**
	 * @return the list of Channels
	 */
	public List<Channel> getChannels() {
		return this.channels;
	}

	/**
	 * @return the numChannels
	 */
	public int getNumChannels() {
		return numChannels;
	}

	/**
	 * @param numChannels the numChannels to set
	 */
	public void setNumChannels(int numChannels) {
		this.numChannels = numChannels;
	}

	/**
	 * @return the list of Modules used by this network
	 */
	public List<Module> getModules() {
		return modules;
	}

	/**
	 * 
	 * @param name of the Module to add to the Modules list
	 */
	private void addModule(Module mod) {
		modules.add(mod);
	}	

	/**
	 * 
	 * @return nodes the list of all nodes in this network
	 */
	public NodeContainer getNodes() {
		return nodes;
	}
	
	/**
	 * @param node the Node to add to the list of Nodes for this network
	 */
	public void addNode(Node node) {
		this.nodes.addNode(node);
	}
	
	/**
	 * Sets the ns-3 simulator's time to stop running
	 * @param time in seconds
	 */
	public void setStopTime(double time) {
		this.stopTime = time;
	}

	/**
	 * @return the backboneNodes
	 */
	public NodeContainer getBackboneNodes() {
		return backboneNodes;
	}

	/**
	 * @param backboneNodes the backboneNodes to set
	 */
	public void addBackboneNode(Node node) {
		this.backboneNodes.addNode(node);
	}

	/**
	 * @return the AuctionObjects
	 */
	public List<AuctionObject> getAuctions() {
		return auctions;
	}

	/**
	 * @param auctions the List of AuctionObjects to set
	 */
	public void setAuctions(List<AuctionObject> auctions) {
		this.auctions = auctions;
	}

	/**
	 * @return the controllers
	 */
	public List<Controller> getControllers() {
		return controllers;
	}

	/**
	 * @param controllers the List of Controllers to set
	 */
	public void setControllers(List<Controller> controllers) {
		this.controllers = controllers;
		int numBackboneNodes = this.controllers.size() / 20;
		if (this.controllers.size() % 20 != 0) {
			numBackboneNodes++;
		}
		this.setNumBackboneNodes(numBackboneNodes);
	}

	/**
	 * @return the number of backbone Nodes
	 */
	public int getNumBackboneNodes() {
		return this.numBackboneNodes;
	}
	
	/**
	 * @param numBackboneNodes the number of backbone Nodes to set
	 */
	public void setNumBackboneNodes(int numBackboneNodes) {
		this.numBackboneNodes = numBackboneNodes;
	}
	
	/**
	 * @param obj the AbstractProsserObject to add to this Ns3Network
	 */
	// TODO use only this or addChannel method, once ns3.add(...) is coded out
	public void addGldObject(AbstractGldObject obj) {
		this.gldObjects.add(obj);
	}
	
	/**
	 * @param chan the Channel to add to this Ns3Network
	 */
	// TODO see above todo
	public void addChannel(Channel chan) {
		this.channels.add(chan);
	}
	
	
	/**
	 * @param i
	 * @return the Channel at index i
	 */
	public Channel getChannel(int i) {
		return this.channels.get(i);
	}
	
	/**
	 * Connect the Controllers to the appropriate Channels
	 * TODO first channel is for Auction; any beyond that have up to 20 controllers on them
	 */
	public void connectControllerChannels() {
		
		int numChannels = this.channels.size();
		
		ns3Objects.add(iStackHelper);
		Ipv4AddressHelper ipv4AddrHelper = new Ipv4AddressHelper("ipv4AddrHelper");
		
		// 0th channel is reserved for Auction
		for (int i = 1; i < numChannels; i++) {
			
			Channel channel = getChannel(i);
			
			int ipThird = (i == 1) ? (i % 254) : (i % 254 + 1);
			// Sets IP address base to use for devices in this NetDeviceContainer
			String ipFirstTwo = (i / 64832 + 192) + "." + (i / 254 + 1) + ".";
			String ipBase = ipFirstTwo + ipThird + ".0";
			
			// Set the base address and subnet mask for the IPv4 addresses
			ipv4AddrHelper.setBase(ipBase, "255.255.255.0");
			
			List<Controller> controllers = channel.getControllers();
			// TODO structure methods in build() so that backbone network & channels are created first
			// 	then given back to me after GLD stuff adds controllers to the channels
			
			if (channel.getClass().getSimpleName().equalsIgnoreCase("csmachannel")) {
				Pointer<CsmaChannel> chanPtr  = new Pointer<CsmaChannel>("chanPtr_" + i, new CsmaChannel());
				chanPtr.assign(channel.getPointer());
				
				CsmaHelper csmaHelper = new CsmaHelper("csmaHelper_" + i);
				NodeContainer csmaNodes = new NodeContainer("csmaNodes_" + i);
				NetDeviceContainer csmaDevices = new NetDeviceContainer("csmaDevices_" + i);
								
				// Create new CSMA Node for each Controller
				for (int j = 0; j < controllers.size(); j++) {
					
					Controller controller = controllers.get(j);					
			    	// Adds the name of the Controller to the names Vector
			    	names.pushBack(controller);
			    	
			    	Pointer<Node> csmaNodePtr = new Pointer<Node>("csmaControllerNodePtr_" + i + "_" + j);
			    	csmaNodePtr.createObject(new Node());

					// Add Node to NodeContainer for IP stuff later
					csmaNodes.addNode(csmaNodePtr);

				}

				// Adds csmaNodes to global NodeContainer for FNCSApplicationHelper
				nodes.addNodeContainer(csmaNodes);
								
				// Install CSMA protocol stack on csmaNodes & connect to ith Channel
				csmaHelper.install(csmaNodes, chanPtr, csmaDevices);
								
				// Sets up IP routing tables, installs IP stack on nodes, and assigns IP addresses
				//setupIp(ipv4AddrHelper, csmaNodes, csmaDevices);
				iStackHelper.install(csmaNodes);
				ipv4AddrHelper.assign(csmaDevices);
				
			} else if (channel.getClass().getSimpleName().equalsIgnoreCase("pointtopointchannel")) {
				Pointer<PointToPointChannel> chanPtr  = new Pointer<PointToPointChannel>("chanPtr_" + i);
				chanPtr.encapsulate(channel);
				
				PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper_" + i);
				// P2PHelper doesn't take Channel param, so need to set attributes manually
				p2pHelper.setChannelAttribute("DataRate", channel.getAttribute("DataRate"));
				p2pHelper.setDeviceAttribute("Delay", channel.getAttribute("Delay"));
				
				NodeContainer p2pNodes = new NodeContainer("p2pNodes_" + i);
				NetDeviceContainer p2pDevices = new NetDeviceContainer("p2pDevices_" + i);
				
				Pointer<Node> p2pNodePtr = new Pointer<Node>("p2pNode_" + i);
				
				for (int j = 0; j < controllers.size(); j++) {
					Node node = new Node("node_" + i + "_" + j);
					
					Controller controller = controllers.get(j);
					
					node.setController(controller);
					
					// Wrap node in a Pointer
					p2pNodePtr.encapsulate(node);

					// Add Node to NodeContainer for IP stuff later
					p2pNodes.addNode(p2pNodePtr);
					
			    	// Adds the name of the Controller to the names Vector
			    	names.pushBack(controller);
			    	
				}
				
				// Adds p2pNodes to NodeContainer of all Nodes in this network
		    	nodes.addNodeContainer(p2pNodes);
				
				// Install P2P protocol stack on p2pNodes
				p2pHelper.install(p2pNodes, p2pDevices);
				
				// Sets up IP routing tables, installs IP stack on nodes, and assigns IP addresses
				setupIp(ipv4AddrHelper, p2pNodes, p2pDevices);
				
			} else if (channel.getClass().getSimpleName().equalsIgnoreCase("wifichannel_" + i)) {
				Pointer<YansWifiChannel> chanPtr  = new Pointer<YansWifiChannel>("chanPtr_" + i);
				chanPtr.encapsulate(channel);
				
				WifiHelper wifiHelper = new WifiHelper("wifiHelper_" + i);
				NodeContainer wifiNodes = new NodeContainer("wifiNodes_" + i);
				NetDeviceContainer wifiDevices = new NetDeviceContainer("wifiDevices_" + i);
				
				YansWifiPhyHelper phy = new YansWifiPhyHelper("phy_" + i);
				NqosWifiMacHelper mac = new NqosWifiMacHelper("mac");
				
				Pointer<Node> wifiNodePtr = new Pointer<Node>("wifiNode_" + i);
				//wifiNode.encapsulate(node);

				// Add Node to NodeContainer for IP stuff later
				wifiNodes.addNode(wifiNodePtr);
				
				// TODO add other nodes created to names Vector
				names.pushBack(wifiNodePtr);
				
				// Adds wifiNodes to global NodeContainer for FncsApplicationHelper.Install
				nodes.addNodeContainer(wifiNodes);
				
				// Configure WiFi physical link and install protocol stack on wifiNodes
				wifiHelper.install(phy, mac, wifiNodes, wifiDevices);
				
				// Sets up IP routing tables, installs IP stack on nodes, and assigns IP addresses
				setupIp(ipv4AddrHelper, wifiNodes, wifiDevices);
			}
		}
		
	}
	
	/**
	 * Connect the Auctions to the Auction Channel
	 * @return a map of Auction name to Controller prefix
	 */
	public StringMap<String, String> connectAuctionChannels() {
		// Call after controllers connected to network
		// Create node for each auction
		Channel auctionChannel = channels.get(0);
		List<AuctionObject> auctions = auctionChannel.getAuctions();
		
		// A map<string, string> mapping AuctionObject name to a Controller name
		StringMap<String, String> marketToControllerMap = new StringMap<String, String>("marketToControllerMap");
		
		NodeContainer auctionNodes = new NodeContainer("auctionNodes");
		NetDeviceContainer auctionDevices = new NetDeviceContainer("auctionDevices");
		
		for (int i = 0; i < auctions.size(); i++) {
			AuctionObject auction = auctions.get(i);
			
			Node node = new Node("auctionNode_" + i);
			Pointer<Node> nodePtr = new Pointer<Node>("auctionNodePtr_" + i);
			nodePtr.createObject(node);
			
		    node.setAuction(auction);
		    
		    // Adds the nodePtr to auctionNodes for IP stack install
		    auctionNodes.addNode(nodePtr);
		    
		    // Adds node to global list of nodes
		    nodes.addNode(nodePtr);
		    
			// IP setup
			Ipv4AddressHelper addresses = new Ipv4AddressHelper("auctionAddresses");
			addresses.setBase(auctionChannel.getAddressBase(), "255.255.255.0");
		    
		    if (auctionChannel.getClass().getSimpleName().equalsIgnoreCase("csmachannel")) {
		    	
		    	// Wraps auctionChannel in a CsmaChannel Pointer
		    	Pointer<CsmaChannel> csmaChannelPtr = new Pointer<CsmaChannel>("csmaChannelPtr_" + i);
		    	csmaChannelPtr.encapsulate(auctionChannel);
		    	
		    	// Installs CSMA stack and channel on Node
		    	CsmaHelper csmaHelper = new CsmaHelper("csmaHelper_" + i);
		    	NetDeviceContainer csmaDevices = new NetDeviceContainer("csmaDevices_" + i);
		    	csmaHelper.install(nodePtr, csmaChannelPtr, csmaDevices);
		    	auctionDevices.addDevices(csmaDevices);

		    } else if (auctionChannel.getClass().getSimpleName().equalsIgnoreCase("pointtopointchannel")) {
		    	
		    	Pointer<PointToPointChannel> p2pChannelPtr = new Pointer<PointToPointChannel>("p2pChannelPtr_" + i);
		    	p2pChannelPtr.createObject((PointToPointChannel) auctionChannel);
		    	
		    	PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper_" + i);
		    	p2pHelper.setDeviceAttribute("DataRate", auctionChannel.getAttribute("DataRate"));
		    	p2pHelper.setChannelAttribute("Delay", auctionChannel.getAttribute("Delay"));
		    	NetDeviceContainer p2pDevices = new NetDeviceContainer("p2pDevices_" + i);
		    	
		    	// Adds the nodePtr as second node for p2p auctionChannel
		    	((PointToPointChannel) auctionChannel).setNodeB(nodePtr);
		    	
		    	// Adds the p2p channel's other node to auctionNodes for IP stack install
		    	//auctionNodes.addNode(((PointToPointChannel) auctionChannel).getNodeA());
		    			    	
		    	p2pHelper.install(((PointToPointChannel) auctionChannel).getNodeA(), ((PointToPointChannel) auctionChannel).getNodeB(), p2pDevices);
		    	
		    	auctionDevices.addDevices(p2pDevices);
		    	
		    } else if (auctionChannel.getClass().getSimpleName().equalsIgnoreCase("yanswifichannel")) {
		    	// TODO WiFi auction
		    }
		    
		    // Install the IP stack on the auctioNodes
		    iStackHelper.install(auctionNodes);
		    
		    // Assigns IPv4 address to devices
	    	addresses.assign(auctionDevices);
		    
		    // Adds Auction name to vector of names
		    names.pushBack(auction);
			// Maps the Auction NetworkInterfaceName to the GldNodePrefix
			marketToControllerMap.put(auction.getNetworkInterfaceName(), auction.getFncsControllerPrefix());
		}
		
		return marketToControllerMap;
	}

	/**
	 * 
	 * @param addr the Ipv4AddressHelper
	 * @param nodes the NodeContainer to enable for IP communication
	 * @param devices the NetDeviceContainer to hold the IP-enabled devices
	 */
	private void setupIp(Ipv4AddressHelper addr, NodeContainer nodes, 
						NetDeviceContainer devices) {
				
		// Install the IP stack protocols on the nodes
		iStackHelper.install(nodes);
		
		// Assign IPv4 addresses to devices
		addr.assign(devices);
	}

	/**
	 * Sets up static routing on the global InternetStackHelper
	 * @param stack the InternetStackHelper used to set up the IP routing tables
	 */
	private void setupInternetStackAndRouting() {
		
		Ipv4StaticRoutingHelper staticRouting = new Ipv4StaticRoutingHelper("staticRoutingHelper");
		
		Ipv4ListRoutingHelper list = new Ipv4ListRoutingHelper("listRoutingHelper");
		list.add(staticRouting, 0);
		
		iStackHelper.setRoutingHelper(list);

	}
	
	/**
	 * 
	 * @param stack
	 * @param addresses
	 * @param baseNodes
	 * @param gldNodes a NodeContainer of GLD market and baseNodes to 
	 * 			pass to FNCSApplicationHelper.SetApps(...)
	 * @param names a StringVector<String> of all ControllerNetworkInterface names
	 * @param marketToControllerMap a StringMap<String, String> from MarketNetworkInterface 
	 * 			name to NetworkControllerInterface name
	 */
	private void setupMarket(InternetStackHelper stack, Ipv4AddressHelper addresses, 
								NodeContainer[] baseNodes, NodeContainer gldNodes, 
								Vector<String> names,
								StringMap<String, String> marketToControllerMap) {
		
		this.addModule(new PointToPoint()); // To use PointToPoint network type
		
		int numAuctions = this.getAuctions().size();
		//int numConts = numNodes/20 + 1; // Number of NodeContainers to create
		int numConts = baseNodes.length;
		
		NodeContainer auctionCon = new NodeContainer("marketCon");
		auctionCon.create(numAuctions);
		
		// Installs an IP protocol stack on the Nodes in the Market NodeContainer
		stack.install(auctionCon);
		
		// Add GLD market Nodes to container of GLD Nodes
		gldNodes.addNodeContainer(auctionCon);
		
		// Creates a point to point channel to connect the Market and network nodes
		PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper");
		p2pHelper.setDeviceAttribute("DataRate", "4Mbps");
		p2pHelper.setChannelAttribute("Delay", "2ms");
		
		for (int i = 0; i < numAuctions; i++) {
			
			// Select ith AuctionObject
			AuctionObject auction = this.getAuctions().get(i);
			// Set ith Node's MarketNI equal to mni
			auctionCon.getNodeNoPrint(i).setAuction(auction);
			
			for (int j = 0; j < numConts; j++) {
				
				NodeContainer tempCont = baseNodes[j];
				
				String addr = "11." + j + "." + i + ".0";
				
				NetDeviceContainer marketToNetwork = new NetDeviceContainer("marketToNetwork_" + j);
				
				NodeContainer p2pInstall = new NodeContainer("p2pInstall_" + j);
				p2pInstall.addNode(auctionCon, i);
				p2pInstall.addNode(tempCont, 0);
				
				p2pHelper.install(p2pInstall, marketToNetwork);
				
				addresses.setBase(addr, "255.255.255.0");
				addresses.assign(marketToNetwork);
				
				// Add a ControllerNetworkInterface to each Node in baseNodes[j]
				for (int k = 0; k < tempCont.getNumNodes(); k++) {
					Controller controller = this.getControllers().get(k);
					Node temp = tempCont.getNodeNoPrint(k);
					temp.setController(controller);
					// Add baseNodes to container of GLD Nodes
					gldNodes.addNode(temp);
					// Add controller prefix name to names vector
					names.pushBack(controller);
					marketToControllerMap.put(this.getAuctions().get(i).getNetworkInterfaceName(), controller.getNetworkInterfaceName());
				}
			}
		}
		
	}
	
	/**
	 * @param numApNodes equal to number of MarketNetworkInterfaces in the mni List
	 * @param numStaNodes total number of station nodes to create; 
	 * 			equal to number of ControllerNetworkInterfaces (GLD houses) in the cni List
	 * @param latency
	 * @return objects a List of all AbstractNs3Objects created for this WiFi network
	 */
	public List<AbstractNs3Object> createWifi(String latency) {
		
		// Add the necessary modules for WiFi
		this.addModule(new Mobility());
		this.addModule(new Wifi());
		this.addModule(new Network());
		this.addModule(new Csma());
		this.addModule(new Internet());
		this.addModule(new Bridge());
		
		// A map<string, string> mapping AuctionObject name to a Controller name
		StringMap<String, String> marketToControllerMap = new StringMap<String, String>("marketToControllerMap");
		
		// Things for FNCSApplicationHelper.SetApps(...) method at end of network setup
		// A NodeContainer to hold the GLD market and house nodes
		NodeContainer gldNodes = new NodeContainer("gldNodes");
		
		// Setup the Internet protocols
		Ipv4InterfaceContainer apInterfaces = new Ipv4InterfaceContainer("apInterfaces");
		ns3Objects.add(apInterfaces);
		Ipv4InterfaceContainer staInterfaces = new Ipv4InterfaceContainer("staInterfaces");
		ns3Objects.add(staInterfaces);
		InternetStackHelper stack = new InternetStackHelper("stack");
		ns3Objects.add(stack);
		Ipv4AddressHelper addressHelper = new Ipv4AddressHelper("addressHelper");
		ns3Objects.add(addressHelper);
		
		// Generic helper for installing the selected network protocol on the backbone Nodes
		NetworkHelper backboneHelper = new NetworkHelper();		
		
		YansWifiPhyHelper wifiPhy = new YansWifiPhyHelper("wifiPhy");
		wifiPhy.defaultParams();
		wifiPhy.setPcapDataLinkType("YansWifiPhyHelper::DLT_IEEE802_11_RADIO");
		ns3Objects.add(wifiPhy);
		
		// TODO make these as actual Vector objects if vectors needed for more than testing
//		String vectors = "std::vector<NodeContainer> staNodeVector;\n" +
//						  "std::vector<NetDeviceContainer> staDeviceVector;\n" +
//						  "std::vector<NetDeviceContainer> apDeviceVector;\n" +
//						  "std::vector<Ipv4InterfaceContainer> staInterfaceVector;\n" +
//						  "std::vector<Ipv4InterfaceContainer> apInterfaceVector;\n";
		
//		stack.appendPrintObj(vectors);
		
		// Used by MobilityHelper's positionAllocator and setMobilityModel
		double wifiX = 0.0;
		
		// Create p2p channel for Auction
		// TODO generalize this to allow CSMA
		PointToPointChannel auctionChannel = new PointToPointChannel("auctionChannel");
		auctionChannel.setAttribute("DataRate", "1Gbps");
		auctionChannel.setAttribute("Delay", "500ns");
		addChannel(auctionChannel);
		
		final int numChannels = getNumChannels();
		
		PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper");
		
		// Create backbone router
		NodeContainer backboneRouter = new NodeContainer("backboneRouter");
		backboneRouter.create(1);
		// NodeContainer for AP nodes
		NodeContainer apNodes = new NodeContainer("apNodes");
		apNodes.create(numChannels);
		
		WifiHelper wifiHelper = new WifiHelper("wifiHelper");
		wifiHelper.defaultParams();
		
		NqosWifiMacHelper wifiMacHelper = new NqosWifiMacHelper("wifiMacHelper");
		wifiMacHelper.defaultParams();
		
		YansWifiChannelHelper wifiChannelHelper = new YansWifiChannelHelper("wifiChannelHelper");
		wifiChannelHelper.defaultParams();
		wifiPhy.setChannel(wifiChannelHelper.create());
		
		MobilityHelper mobility = new MobilityHelper("mobilityHelper");

		
		// TODO look at whiteboard and set up WiFi ap nodes w/ p2p device & separate p2p router
		// TODO figure out how to implement own wifihelper.install method to be able to get specific channels out of wifi
		
		for (int i = 1; i < numChannels; i++) {
			
			
				String ssidBase = "wifi_" + i;
				Ssid ssid = new Ssid("ssid_" + i);
				ssid.setSsid(ssidBase);
				
				NetDeviceContainer apDevs = new NetDeviceContainer("apDev_" + i);
				ns3Objects.add(apDevs);
				
				Ipv4InterfaceContainer staInterface = new Ipv4InterfaceContainer("staInterface_" + i);
				ns3Objects.add(staInterface);
				Ipv4InterfaceContainer apInterface = new Ipv4InterfaceContainer("apInterface_" + i);
				ns3Objects.add(apInterface);
				
			    mobility.setPositionAllocator("ns3::GridPositionAllocator",
			    								wifiX, 0.0, 5.0, 5.0, 1,
			                                    "RowFirst");
			    // Setup the AP
			    mobility.setMobilityModel("ns3::ConstantPositionMobilityModel");
			    mobility.install(apNodes, i);
			    wifiMacHelper.setType("ns3::ApWifiMac", ssid);
			    
			    // Installs a WiFi device on the ith backbone Node to make the AP WiFi device
			    wifiHelper.install(wifiPhy, wifiMacHelper, apNodes, i, apDevs);
			   
			    
			    // TODO use this for sta node setup
//			    // Setup the Mobility model
//			    mobility.setMobilityModel("ns3::RandomWalk2dMobilityModel",
//			                               "Time", "2s", 
//			                               "ns3::ConstantRandomVariable[Constant=1.0]",
//			                               "RectangleValue(Rectangle(" + 
//			                               wifiX + ", " + wifiX + " + 5.0, 0.0, (" 
//			                               + numStaNodesPerApNode + " + 1) * 5.0))");
			    
			    // Saves everything in containers.
			    String stuff = "\n\tstaNodeVector.push_back(staNodes_" + i + ");\n" +
					    "\tapDeviceVector.push_back(apDev_" + i + ");\n" +
					    "\tapInterfaceVector.push_back(apInterface_" + i + ");\n" +
					    "\tstaDeviceVector.push_back(staDev_" + i + ");\n" +
					    "\tstaInterfaceVector.push_back(staInterface_" + i + ");\n";
			    
			    addressHelper.appendPrintObj(stuff);
	
			    wifiX += 20.0;
		}
			
		
//		AbstractNs3Object ns3 = ns3Objects.get(0);
//		
//		String stuff = "\n   Address dest;\n" +
//		  "   std::string protocol;\n" +
//		 "   if (false)\n" +
//		  "   {\n" +
//		 "    dest = InetSocketAddress(staInterfaceVector.at(0).GetAddress(1), 1025);\n" +
//		  "    protocol = \"ns3::UdpSocketFactory\";\n" +
//		  "   }\n" +
//		 "   else\n" +
//		  "   {\n" +
//		   "   PacketSocketAddress tmp;\n" +
//		   "   tmp.SetSingleDevice (staDeviceVector[0].Get(0)->GetIfIndex ());\n" +
//		   "   tmp.SetPhysicalAddress (staDeviceVector[0].Get(0)->GetAddress ());\n" +
//		   "   tmp.SetProtocol (0x807);\n" +
//		   "   dest = tmp;\n" +
//		   "   protocol = \"ns3::PacketSocketFactory\";\n" +
//		   "   }\n" + 
//
//		  "   OnOffHelper onoff = OnOffHelper (protocol, dest);\n" +
//		  "   onoff.SetConstantRate (DataRate (\"500kb/s\"));\n" +
//		  "   ApplicationContainer apps = onoff.Install (staNodeVector[0].Get (0));\n" +
//		  "   apps.Start (Seconds (0.5));\n" +
//		  "   apps.Stop (Seconds (3.0));\n" +
//		  "   wifiPhy.EnablePcap (\"wifi-wired-bridging\", apDeviceVector[0]);\n" +
//
//		  "   if (true)\n" +
//		    "   {\n" +
//		      "   AsciiTraceHelper ascii;\n" +
//		      "   MobilityHelper::EnableAsciiAll (ascii.CreateFileStream (\"wifi-wired-bridging.mob\"));\n" +
//		    "   }\n";
		
//		ns3.appendPrintObj(stuff + "\n");
		
		return ns3Objects;
		
	}
	
	/**
	 * Creates the FNCS simulator for ns-3 to use
	 */
	private void setupFncsSimulator() {
		// Setup FNCS simulator
		FncsSimulator fncsSim = new FncsSimulator("fncsSim");
		
		Pointer<FncsSimulator> hb2 = new Pointer<>("hb2");
		hb2.encapsulate(fncsSim);
		
		fncsSim.unref();
		fncsSim.setImplementation(hb2);
		
		// Add modules required for all simulations
		this.addModule(new Core());
		this.addModule(new Applications());
		
	}

	/**
	 * 
	 * @param nodeCont the Nodes to set an Auction and Controllers
	 * @param auction the AuctionObject to set on the first Node of nodeCont
	 * @param auctionIndex the current Auction count
	 * @param names a StringVector holding names of all Controllers for FNCSApplicationHelper
	 * @param gldNodes a NodeContainer holding all GLD (House) Nodes for FNCSApplicationHelper
	 * @return a StringVector of all Controller names
	 */
	private Vector<String> installAuctionsAndControllers(NodeContainer nodeCont, 
			AuctionObject auction, int auctionIndex, Vector<String> names, NodeContainer gldNodes) {
		
		final int nodeContSize = nodeCont.getNumNodes();
		
	    // Installs market on 1st (0th) node of nodeCont
	    Node firstNode = nodeCont.getNodeNoPrint(0);
	    firstNode.setAuction(auction);
	    gldNodes.addNodeContainer(nodeCont);
	    names.pushBack(auction);
		
		for (int i = 0; i < nodeContSize; i++) {
			// Adds each node to global List of Nodes
			addNode(nodeCont.getNodeNoPrint(i));
			// Gets the Controller at the index i offset by the current auctionIndex multiplied by the number of nodes in nodeCont
	    	Controller tempCont = this.getControllers().get(auctionIndex*nodeContSize + i);
	    	// Assigns the Controller at index i offset by the index of the Auction times the # of Nodes per Auction
	    	nodeCont.getNodeNoPrint(i).setController(tempCont);
	    	// Adds the name of the Controller to the Names StringVector
	    	names.pushBack(tempCont);
	    	// Adds this Node to the NodeContainer of all GLD Nodes
	    	gldNodes.addNode(nodeCont, i);
		}
		
		return names;
	}
	
	/**
	 * Sets up a FNCSApplicationHelper and ApplicationContainer for the FNCS simulator and 
	 * starts the ns-3 simulator
	 */
	private void setupFncsApplicationHelper(StringMap<String, String> marketToControllerMap) {
		this.addModule(new Fncs());
		this.addModule(new FncsApplication());
		
		FNCSApplicationHelper fncsHelper = new FNCSApplicationHelper("fncsHelper");
		
		ApplicationContainer fncsAps = new ApplicationContainer("fncsAps");
		
		fncsHelper.setApps(this.names, this.nodes, marketToControllerMap, fncsAps);
		fncsAps.start(0.0);
		fncsAps.stop(259200.0);
		
		// Run Simulator then clean up after it's done (according to FncsAps.stop(...))
		fncsAps.appendPrintObj("Simulator::Run();\n");
		fncsAps.appendPrintObj("Simulator::Destroy();\n");
		fncsAps.appendPrintObj("return 0;\n");
	}
	
	/**
	 * 
	 * @param backboneHelper
	 * @param backboneNodes
	 * @param backboneDevices
	 * @param index used for naming to prevent name conflicts when called in a loop
	 */
	private void createBackbone(NetworkHelper backboneHelper, NodeContainer backboneNodes,
			NetDeviceContainer backboneDevices, int index) {
		
		if (this.backboneType.name().equalsIgnoreCase("csma")) {
			backboneHelper = new CsmaHelper("csmaHelper_" + index);
			((CsmaHelper) backboneHelper).install(backboneNodes, backboneDevices);
			
		} else if (this.backboneType.name().equalsIgnoreCase("lte")) {
			backboneHelper = new LteHelper("lteHelper_" + index);
			((LteHelper) backboneHelper).installUeDevice(backboneNodes, backboneDevices);
			NodeContainer enbNodes = new NodeContainer("enbNodes_" + index);
			
			// TODO finish LTE backbone; ask if we need this (LTE backbone?)
			
		} else if (this.backboneType.name().equalsIgnoreCase("p2p")) {
			backboneHelper = new PointToPointHelper("pointopointHelper_" + index);
			((PointToPointHelper) backboneHelper).install(backboneNodes, backboneDevices);

		} else {
			backboneHelper = new WifiHelper("wifiHelper_" + index);
			
			// TODO finish WiFi backbone; ask if we need this (WiFi backbone?)
			// If so, need to pass WifiPhyHelper
		}
	}

	/**
	 * @return a List of all objects created for this network
	 * 
	 */
	public List<AbstractNs3Object> createLte() {
		
		this.addModule(new Core());
		this.addModule(new Mobility());
		this.addModule(new Applications());
		this.addModule(new Wifi());
		this.addModule(new Network());
		this.addModule(new Csma());
		this.addModule(new Internet());
		this.addModule(new Bridge());
		this.addModule(new PointToPoint());
		// LTE specific
		this.addModule(new Lte());
		this.addModule(new Mobility());
		
		// A map<string, string> mapping AuctionObject name to a Controller name
		StringMap<String, String> marketToControllerMap = new StringMap<String, String>("marketToControllerMap");
		
		// Things for FNCSApplicationHelper.SetApps(...) method at end of network setup
		// A NodeContainer to hold the GLD market and house nodes
		NodeContainer gldNodes = new NodeContainer("gldNodes");
		
		LteHelper lteHelper = new LteHelper("lteHelper");
		ns3Objects.add(lteHelper);
		
		PointToPointEpcHelper epcHelper = new PointToPointEpcHelper();
		epcHelper.setNameString("epcHelper");
		ns3Objects.add(epcHelper);
		
		Pointer<PointToPointEpcHelper> epcHelperPointer = new Pointer<>("epcHelperPointer");
		epcHelperPointer.createObject(epcHelper);
		
		lteHelper.setEpcHelper(epcHelperPointer);
		
		Pointer<Node> pgw = new Pointer<Node>("pgw");
		pgw.setType(new Node());
		epcHelper.getPgwNode(pgw);

		//TODO fix backbonetype so don't have to do this
		this.setBackboneType(NetworkType.P2P);
		PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper");
		p2pHelper.setDeviceAttribute("DataRate", "100Gb/s");
		p2pHelper.setDeviceAttribute("Mtu", 1500); // TODO may be able to use string for this as well
		p2pHelper.setChannelAttribute("Delay", "10ms");
		ns3Objects.add(p2pHelper);
		
		// Internet helpers for IP setup
		InternetStackHelper iStackHelper = new InternetStackHelper("iStackHelper");
		ns3Objects.add(iStackHelper);
		
		Ipv4AddressHelper ipv4AddrHelper = new Ipv4AddressHelper("ipv4AddrHelper");
		ns3Objects.add(ipv4AddrHelper);
		
		Ipv4InterfaceContainer ipv4Interfaces = new Ipv4InterfaceContainer("ipv4Interfaces");
		ns3Objects.add(ipv4Interfaces);
		
		List<NetDeviceContainer> lteDeviceContainers = new ArrayList<NetDeviceContainer>();
		
		int numAuctions = this.getAuctions().size();
		// NumAuctioNodes is total for whole simulation, so calculate AP Nodes per Market
		int numEnbNodesPerAuction = this.getNumBackboneNodes() / numAuctions;
		// Then calculate station Nodes per AP Node
		int numUeNodesPerAuction = numAuctions;
		int numUeNodesPerEnbNode = numUeNodesPerAuction / numEnbNodesPerAuction;
		
		MobilityHelper mobilityHelper = new MobilityHelper("mobilityHelper");
		// TODO ConstantPositionMobilityModel sets all nodes at origin (0,0,0)
		mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); 
		
		// Setup QoS Class Indicator 
		Qci q = Qci.GBR_CONV_VOICE;
		q.setName("q");
		
		EpsBearer bearer = new EpsBearer("bearer");
		bearer.setQci(q);
		ns3Objects.add(bearer);
		
		for (int i = 0; i < numAuctions; i++) {
			
			AuctionObject auction = this.getAuctions().get(i);
			
			NodeContainer enbNodes = new NodeContainer("enbNodes_" + i);
			enbNodes.create(numEnbNodesPerAuction);
			ns3Objects.add(enbNodes);
			
			NetDeviceContainer enbDevices = new NetDeviceContainer("enbDevices_" + i);
			ns3Objects.add(enbDevices);
			
			lteHelper.installEnbDevice(enbNodes, enbDevices);
			
			// TODO ConstantPositionMobilityModel sets all nodes at origin (0,0,0)
			mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); 
			mobilityHelper.install(enbNodes);
			
			// Install the LTE protocol stack on the eNB nodes; not for EPC
			//lteHelper.installEnbDevice(enbNodes, enbDevices); 
			
			lteDeviceContainers.add(enbDevices);
			
			// Create a point-to-point network between the enbNodes
			//createBackbone(p2pHelper, enbNodes, enbDevices, i);
			
			ipv4AddrHelper.setBase(getAddrBase() + i + ".0", getAddrMask());
			// Assign IP addresses to NetDevices in enbDevices
			ipv4AddrHelper.assign(enbDevices, ipv4Interfaces);
			
			for (int j = 0; j < numEnbNodesPerAuction; j++) {
				
				// Add all Nodes from this NodeContainer to Nodes global list of nodes
				addNode(enbNodes.getNodeNoPrint(j));
				
				NodeContainer ueNodes = new NodeContainer("ueNodes_" + j);
				ueNodes.create(numUeNodesPerEnbNode);
				ns3Objects.add(ueNodes);
				
				// TODO ConstantPositionMobilityModel sets all nodes at origin (0,0,0)
				mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); 
				mobilityHelper.install(ueNodes);
				
				// Install IP stack on UEs
				iStackHelper.install(ueNodes);
				
				NetDeviceContainer ueDevices = new NetDeviceContainer("ueDevices_" + j);
				lteHelper.installUeDevice(ueNodes, ueDevices); // Install the LTE protocol stack on the UE nodes
				lteHelper.attach(ueDevices, enbDevices, j); // Attach the newly created UE devices to an eNB device
				//lteHelper.activateDataRadioBearer(ueDevices, bearer); // not used for EPC
				ns3Objects.add(ueDevices);
				
				lteDeviceContainers.add(ueDevices);
				
				Ipv4InterfaceContainer ueIpInterface = new Ipv4InterfaceContainer("ueIpInterface");
				ns3Objects.add(ueIpInterface);
				
				for (int k = 0; k < numUeNodesPerEnbNode; k++) {
					((PointToPointEpcHelper) epcHelperPointer.getObject()).assignUeIpv4Address(ueDevices, ueIpInterface);
				}

				
			    // Installs the Auctions and Controllers on each Node in ueNodes, 
			    //	adds names to Names StringVector and Nodes to gldNodes for fncsHelper below
				installAuctionsAndControllers(ueNodes, auction, j, names, gldNodes);
	
			}
			
			marketToControllerMap.put(this.getAuctions().get(i).getNetworkInterfaceName(), this.getGldNodePrefix());
		}
		
		// Sets up the FNCS and ns-3 simulators, runs them, and cleans up
		setupFncsApplicationHelper(marketToControllerMap);
		
		return ns3Objects;

	}
	
	/**
	 * @param dataRate
	 * @param delay
	 */
	public void createCsma(String dataRate, String delay) {
		
		this.addModule(new Core());
		this.addModule(new Applications());
		this.addModule(new Network());
		this.addModule(new Csma());
		this.addModule(new PointToPoint());
		this.addModule(new Internet());
		this.addModule(new NixVectorRouting());

		final int numChannels = getNumChannels();
		
		// IP setup
		Ipv4AddressHelper addresses = new Ipv4AddressHelper("backboneAddresses");
		
		// CSMA channel/device setup
		CsmaHelper csmaHelper = new CsmaHelper("csmaHelper");
		
		// Point to point channel/device setup
		PointToPointHelper p2pHelper = new PointToPointHelper("p2pHelper");
		
		// Creates a channel for the Auctions & add it to list of channels
		// TODO allow user to select channel type
		// FIXME p2p channels can only have 2 devices on them; for 2+ auctions, need reference to backbonerouter
		PointToPointChannel auctionChannel = new PointToPointChannel("p2pAuctionChannel");
		auctionChannel.setAttribute("DataRate", dataRate);
		auctionChannel.setAttribute("Delay", delay);
		auctionChannel.setAddressBase("1.1.1.0"); // TODO implement user-settable Auction addrBase
		addChannel(auctionChannel);
		
		// Creates main backbone router
		NodeContainer backboneRouter = new NodeContainer("backboneRouter");
		backboneRouter.create(1);
		// Stores backboneRouter in Pointer for p2pHelper.install
		Pointer<Node> backboneRouterPtr = new Pointer<Node>("backboneRouterPtr", new Node());
		backboneRouter.getNode(0, backboneRouterPtr);
		
		// Install IP stack on backboneRouterPtr
		iStackHelper.install(backboneRouterPtr);
		
		// Adds the backbone router node to p2p auction channel
		auctionChannel.setNodeA(backboneRouterPtr);
		
		// Creates access point routers
		NodeContainer apNodes = new NodeContainer("apNodes_backbone");
		apNodes.create(numChannels - 1);
		
		// Adds backbone nodes to global NodeContainer for FNCSApplicationHelper
		nodes.addNodeContainer(backboneRouter);
		nodes.addNodeContainer(apNodes);
		
		// Adds node names to global Vector of names for FNCSApplicationHelper
		names.pushBack(backboneRouterPtr);
		names.pushBack(apNodes);

		
		for (int i = 0; i < numChannels - 1; i++) {
			
			int ipThird = ((2 * i) % 254 + 1);
			// Sets IP address base to use for devices in this NetDeviceContainer
			String ipFirstTwo = (i / 65014 + 10) + "." + (i / 254 + 1) + ".";
			String ipBase = ipFirstTwo + ipThird + ".0";
			
			// Create the CSMA Channel & add it to list of channels
			CsmaChannel channel = new CsmaChannel("csmaChannel_backbone_" + i);
			channel.setAttribute("DataRate", dataRate);
			channel.setAttribute("Delay", delay);
			// Add IP address base to Channel for use later in Market/Controller integration
			channel.setAddressBase(ipBase); // TODO check that this works later
			addChannel(channel);
			
			// Wrap channel in pointer for CsmaHelper.Install
			Pointer<CsmaChannel> channelPtr = new Pointer<CsmaChannel>("csmaChannelPtr_backbone_" + i);
			channelPtr.createObject(channel);
			
			// Attach this Pointer<CsmaChannel> to Channel
			channel.setPointer(channelPtr);
					
			Pointer<Node> apNodePtr = new Pointer<Node>("csmaNode_backbone_" + i, new Node());
			apNodes.getNode(i, apNodePtr);
			
			NetDeviceContainer csmaDevices = new NetDeviceContainer("csmaDevices_backbone_" + i);
			
			// Installs the CSMA protocols on the devices using the given channel
			csmaHelper.install(apNodePtr, channelPtr, csmaDevices);
			// Installs the IP stack protocols on the CSMA nodes
			iStackHelper.install(apNodePtr);
			
			addresses.setBase(ipBase, "255.255.255.0"); // IPbase, mask
			addresses.assign(csmaDevices);
			
			NetDeviceContainer p2pDevices = new NetDeviceContainer("p2pDevices_backbone_" + i);
			
			// Install p2p devices on ap node and backbone router & connect via p2p channel
			p2pHelper.install(apNodePtr, backboneRouterPtr, p2pDevices);
			
			ipThird = ((2 * i) % 254 + 2);
			
			ipBase = ipFirstTwo + ipThird + ".0";
			
			addresses.setBase(ipBase, "255.255.255.0");
			addresses.assign(p2pDevices);
			
		}
		
	}

	/**
	 * @return ns3Objects a list of all objects created in this network
	 * 
	 */
	// TODO add all objects to ns3Objects or just add 1 (only need 1 to print all ns3 setup text)
	// TODO give params to Ns3Network, then call this to call all other necessary methods
	public List<AbstractNs3Object> buildBackbone() {
		
		setupFncsSimulator();
		
		// Sets name for global NodeContainer for use in FNCSApplicationHelper.setApps(...)
		nodes.setName("allNodes");
		// Sets name for global InternetStackHelper (to utilize NixVectorRouting)
		iStackHelper.setName("iStackHelper");
		// Sets up iStackHelper with static and nix vector routing
		setupInternetStackAndRouting();
		
		// Instantiates global Vector names for use in FNCSApplicationHelper.setApps(...)
		names = new Vector<String>("allNames", String.class);
		
		// Creates backbone network
		// TODO build appropriate network(s) type
		createCsma(this.getBackboneDataRate(), this.getBackboneDelay());
		
		return ns3Objects;
	}
	
	/**
	 * @return ns3Objects a list of all objects created in this network
	 */
	public List<AbstractNs3Object> buildFrontend() {
		
		// Connect the Controllers and Auction to the backbone network
		connectControllerChannels();
		
		StringMap<String, String> marketToControllerMap = connectAuctionChannels();
		
		// Sets up the FNCS and ns-3 simulators, runs them, and cleans up
		setupFncsApplicationHelper(marketToControllerMap);
		
		return ns3Objects;
		
		// TODO reduce objects added to names and allNodes to bare min.
		
		
	}
	
}
