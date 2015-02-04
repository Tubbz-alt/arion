/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.pwr.obj.ControllerNetworkInterface;
import gov.pnnl.prosser.api.pwr.obj.MarketNetworkInterface;

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
	
	/**
	 * Type of networks available
	 * 
	 * @author happ546
	 *
	 */
	public enum NetworkType {
		CSMA, LTE, P2P, WIFI
	}
	
	/**
	 * Quality of Service Class Indicator types, used by LTE network EPS Bearers, a 
	 * radio link between User Equipment (UE) devices and Evolved Node B (eNB) base station devices
	 * 
	 * @author happ546
	 *
	 */
	public enum Qci {		
		GBR_CONV_VOICE,
		GBR_CONV_VIDEO,
		GBR_GAMING,
		GBR_NON_CONV_VIDEO,
		NGBR_IMS,
		NGBR_VIDEO_TCP_OPERATOR,
		NGBR_VOICE_VIDEO_GAMING,
		NGBR_VIDEO_TCP_PREMIUM,
		NGBR_VIDEO_TCP_DEFAULT;
		
		private String name;
		
		/**
		 * 
		 * @return the name of this QoS Class Indicator
		 */
		public String getName() {
			return this.name;
		}
		
		/**
		 * 
		 * @param name sets the name of this QoS Class Indicator
		 */
		public void setName(String name) {
			this.name = name;
		}
	}
	
	private NetworkType type;
	private String addrBase, addrMask;
	private int numNodes;
	private List<Module> modules; // List of ns-3 Modules used in this network
	private List<Node> nodes;
	private List<AbstractProsserObject> gldObjects;
	private double startTime, stopTime;
	private MarketNetworkInterface marketNI;
	private List<ControllerNetworkInterface> controllerNIs;
	
	/**
	 * Create a new Ns3Network object, used to set up an ns-3 network for use in Prosser simulation
	 */
	public Ns3Network() {
		this.type = null;
		this.modules = new ArrayList<Module>();
		this.nodes = new ArrayList<Node>();
		this.setGldObjects(new ArrayList<AbstractProsserObject>());
	}
	
	/**
	 * @return the type
	 */
	public NetworkType getType() {
		return type;
	}
	
	/**
	 * 
	 * @param type the NetworkType for this Ns3Network
	 */
	public void setType(NetworkType type) {
		this.type = type;
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
	 * @return the numNodes
	 */
	public int getNumNodes() {
		return numNodes;
	}

	/**
	 * @param numNodes the numNodes to set
	 */
	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
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
	private void addModule(String name) {
		modules.add(new Module(name));
	}	

	/**
	 * 
	 * @return nodes the list of all nodes in this network
	 */
	public List<Node> getNodes() {
		return nodes;
	}
	
	/**
	 * @return the list of gldObjects used in this simulator
	 */
	public List<AbstractProsserObject> getGldObjects() {
		return gldObjects;
	}

	/**
	 * @param gldObjects the list of GridLab-D objects to set
	 */
	public void setGldObjects(List<AbstractProsserObject> gldObjects) {
		this.gldObjects = gldObjects;
	}
	
	/**
	 * Sets the ns-3 simulator's time to start running (0.0 is typical)
	 * @param time in seconds
	 */
	public void setStartTime(double time) {
		this.startTime = time;
	}
	
	/**
	 * Sets the ns-3 simulator's time to stop running
	 * @param time in seconds
	 */
	public void setStopTime(double time) {
		this.stopTime = time;
	}
	
	/**
	 * 
	 * @param marketNI the MarketNetwrokInterface to use for the 
	 * market Nodes in this network
	 */
	public void setMarketNI(MarketNetworkInterface marketNI) {
		this.marketNI = marketNI;
	}

	/**
	 * 
	 * @param controllerNIs the ControllerNetworkInterface to use for the 
	 * GLD House connected Nodes in this network
	 */
	public void setControllerNIs(List<ControllerNetworkInterface> controllerNIs) {
		this.controllerNIs = controllerNIs;
	}

	/**
	 * 
	 * @param stack the InternetStackHelper used to set up the IP routing tables
	 */
	private void setupIp(InternetStackHelper stack) {
		Ipv4NixVectorHelper nixRouting = new Ipv4NixVectorHelper();
		nixRouting.setName("nixRouting");
		
		Ipv4StaticRoutingHelper staticRouting = new Ipv4StaticRoutingHelper();
		staticRouting.setName("staticRouting");
		
		Ipv4ListRoutingHelper list = new Ipv4ListRoutingHelper();
		list.setName("list");
		list.add(staticRouting, 0);
		list.add(nixRouting, 10);
		
		stack.setRoutingHelper(list);
	}
	
	public void setupMarket(InternetStackHelper stack, Ipv4AddressHelper addresses, NodeContainer[] baseNodes) {
		
		this.addModule("point-to-point-module"); // To use PointToPoint network type
		
		int numMarkets = 1; // TODO get real number of markets
		int numConts = numNodes/20 + 1; // Number of NodeContainers to create
		
		NodeContainer marketCon = new NodeContainer();
		marketCon.setName("marketCon");
		marketCon.create(numMarkets);
		
		// Installs an IP protocol stack on the Nodes in the Market NodeContainer
		stack.install(marketCon);
		
		// Creates a point to point channel to connect the Market and network nodes
		PointToPointHelper p2pHelper = new PointToPointHelper();
		p2pHelper.setName("p2pHelper");
		p2pHelper.setDeviceAttribute("DataRate", "4Mbps");
		p2pHelper.setChannelAttribute("Delay", "2ms");
		
		for (int i = 0; i < numMarkets; i++) {
			for (int j = 0; j < numConts; j++) {
				String addr = "11." + j + "." + i + ".0";
				
				NetDeviceContainer marketToNetwork = new NetDeviceContainer();
				marketToNetwork.setName("marketToNetwork_" + j);
				
				NodeContainer p2pInstall = new NodeContainer();
				p2pInstall.setName("p2pInstall_" + j);
				p2pInstall.addNode(marketCon, i);
				p2pInstall.addNode(baseNodes[j], 0);
				
				p2pHelper.install(p2pInstall, marketToNetwork);
				
				addresses.setBase(addr, "255.255.255.0");
				addresses.assign(marketToNetwork);
			}
		}

		
	}

	/**
	 * Creates the nodes and installs devices/applications to create a network of the 
	 * desired type and size to fit the gldObjects
	 * TODO need to evaluate how to create network with more than 1 type (WiFi <--> CSMA <--> LTE; etc)
	 * @return 
	 */
	public List<AbstractNs3Object> build() {
		
		List<AbstractNs3Object> objects = new ArrayList<AbstractNs3Object>();
		
		
		this.addModule("core-module");
		this.addModule("network-module");
		this.addModule("internet-module");
		this.addModule("applications-module");
		
		// For FNCS; these are the header files that can't be found
//		this.addModule("fncs");
//		this.addModule("fncsapplication-helper");
		
		switch (this.type) {
				
			case CSMA:
				
				this.addModule("csma-module");
				this.addModule("nix-vector-routing-module");
				
				// IP setup
				Ipv4AddressHelper addresses = new Ipv4AddressHelper();
				addresses.setName("addresses");
				objects.add(addresses);
				InternetStackHelper stack = new InternetStackHelper();
				stack.setName("stack");
				setupIp(stack);
				objects.add(stack);
				
				// max of 20 nodes per NodeContainer to prevent poor performance, according to researchers
				int numConts = numNodes/20 + 1;
				
				//TODO get dataRate and delay from user
				String dataRate = "10Mbps";
				String delay = "3ms";
				
				// CSMA channel/device setup
				CsmaHelper csmaHelper = new CsmaHelper();
				csmaHelper.setName("csmaHelper");
				csmaHelper.setChannelAttribute("DataRate", dataRate);
				csmaHelper.setChannelAttribute("Delay", delay);
				objects.add(csmaHelper);
				
				NodeContainer[] csmaNodeConts = new NodeContainer[numConts];
				NetDeviceContainer[] netDeviceConts = new NetDeviceContainer[numConts];
				
				for (int i = 0; i < numConts; i++) {
					NodeContainer csmaNodes = new NodeContainer();
					csmaNodes.setName("csmaNodes_" + i);
					csmaNodes.create(20); // Create 20 Nodes in this NodeContainer
					
					csmaNodeConts[i] = csmaNodes;
					objects.add(csmaNodes);
					
					String ipBase = this.addrBase + i + ".0"; // Sets IP address base to use for devices in this NetDeviceContainer
					NetDeviceContainer temp = new NetDeviceContainer();
					temp.setName("netDevices_" + i);
					
					csmaHelper.install(csmaNodes, temp); // Install the CSMA devices & channel onto the temp NodeContainer
					stack.install(csmaNodes); // Install the IP stack protocols on the CSMA Nodes
					
					addresses.setBase(ipBase, "255.255.255.0"); // IPbase, mask
					addresses.assign(temp);
					
					netDeviceConts[i] = temp;
					objects.add(temp);
					
					// Adds each CSMA Node to the global list of nodes
					for (int j = 0; j < 21; j++) {
						nodes.add(csmaNodes.getNode(j));
					}
				}
				
				// Creates and connects the Market nodes to these backbone Nodes
				setupMarket(stack, addresses, csmaNodeConts);
				
				break;
				
			case LTE:
				
				//TODO may need to add Mobility in order to compile under ns-3. See error message below
/*				[sean@siftworkstation ns-3.21]$ ./waf --run scratch/ns3
				Waf: Entering directory `/home/sean/workspace/ns-allinone-3.21/ns-3.21/build'
				Waf: Leaving directory `/home/sean/workspace/ns-allinone-3.21/ns-3.21/build'
				'build' finished successfully (1.764s)
				assert failed. cond="uid <= m_information.size () && uid != 0", file=../src/core/model/type-id.cc, line=230
				terminate called without an active exception
				Command ['/home/sean/workspace/ns-allinone-3.21/ns-3.21/build/scratch/ns3'] terminated with signal SIGIOT. Run it under a debugger to get more information (./waf --run <program> --command-template="gdb --args %s <args>").*/

				
				this.addModule("lte-module");
				this.addModule("mobility-module");
				
				LteHelper lte = new LteHelper();
				lte.setName("lte");
				objects.add(lte);
				
				List<NetDeviceContainer> lteDeviceContainers = new ArrayList<NetDeviceContainer>();
				
				NodeContainer enbNodes = new NodeContainer();
				enbNodes.setName("enbNodes");
				objects.add(enbNodes);

				
				int numUeNodes = 200; //TODO get real value of LTE user equipment nodes
				int numEnbNodes = numUeNodes / 50; //TODO get real value of LTE base (towers) nodes
				
				NetDeviceContainer enbDevices = new NetDeviceContainer();
				enbDevices.setName("enbDevices");
				enbNodes.create(numEnbNodes);
				objects.add(enbDevices);
				
				MobilityHelper mobilityHelper = new MobilityHelper();
				mobilityHelper.setName("mobilityHelper");
				mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); // TODO ConstantPositionMobilityModel sets all nodes at origin (0,0,0)
				mobilityHelper.install(enbNodes);
				
				lte.installEnbDevice(enbNodes, enbDevices); // Install the LTE protocol stack on the eNB nodes
				
				lteDeviceContainers.add(enbDevices);
				
				// Add all Nodes from this NodeContainer to Nodes global list of nodes
				for (int i = 0; i < numUeNodes; i++) {
					nodes.add(enbNodes.getNode(i));
				}
				
				// Setup QoS Class Indicator 
				Qci q = Qci.GBR_CONV_VOICE;
				q.setName("q");
				
				EpsBearer bearer = new EpsBearer();
				bearer.setName("bearer");
				bearer.setQci(q);
				objects.add(bearer);
				
				for (int i = 0; i < numEnbNodes; i++) {
					NodeContainer ueNodes = new NodeContainer();
					ueNodes.setName("ueNodes_" + i);
					ueNodes.create(numUeNodes);
					objects.add(ueNodes);
					
					mobilityHelper.setMobilityModel("ns3::ConstantPositionMobilityModel"); // TODO ConstantPositionMobilityModel sets all nodes at origin (0,0,0)
					mobilityHelper.install(ueNodes);
					
					NetDeviceContainer ueDevices = new NetDeviceContainer();
					ueDevices.setName("ueDevices_" + i);
					lte.installUeDevice(ueNodes, ueDevices); // Install the LTE protocol stack on the UE nodes
					lte.attach(ueDevices, enbDevices, i); // Attach the newly created UE devices to an eNB device
					lte.activateDataRadioBearer(ueDevices, bearer);
					objects.add(ueDevices);
					
					lteDeviceContainers.add(ueDevices);
					
					// Add all Nodes from this NodeContainer to Nodes global list of nodes
					for (int j = 0; j < numUeNodes; j++) {
						nodes.add(ueNodes.getNode(j));
					}
				}
				
				break;
				
			case P2P:
				
				this.addModule("point-to-point-module");
				
				NodeContainer p2pNodes = new NodeContainer();
				//TODO finish this
				
				// Add all Nodes from this NodeContainer to Nodes global list of nodes
				for (int i = 0; i < p2pNodes.getNumNodes(); i++) {
					nodes.add(p2pNodes.getNode(i));
				}
				
				break;
				
			case WIFI:
				
				this.addModule("wifi-module");
				
				NodeContainer wifiStaNodes = new NodeContainer();
				wifiStaNodes.setName("wifiStaNodes");
				wifiStaNodes.create(this.numNodes); //TODO get appropriate number of nodes for WiFi devices
				objects.add(wifiStaNodes);
				
				NodeContainer wifiApNodes = new NodeContainer();
				wifiApNodes.setName("wifiApNodes");
				wifiApNodes.create(this.numNodes/20 + 1); //TODO get appropriate # of AP nodes
/*				if (p2pNodes != null) {
					wifiApNodes.addNode(p2pNodes, 0); //TODO get node from non-WiFi network to connect AP to
				}*/
				objects.add(wifiApNodes);
				
				YansWifiChannelHelper channel = new YansWifiChannelHelper();
				channel.setName("channel");
				objects.add(channel);
				
				YansWifiPhyHelper phy = new YansWifiPhyHelper();
				phy.setName("phy");
				phy.defaultParams();
				phy.setChannel(channel);
				objects.add(phy);
				
				WifiHelper wifi = new WifiHelper();
				wifi.setName("wifi");
				wifi.defaultParams();
				wifi.setRemoteStationManager("ns3::AarfWifiManager");
				objects.add(wifi);
				
				NqosWifiMacHelper mac = new NqosWifiMacHelper();
				mac.setName("mac");
				mac.defaultParams();
				objects.add(mac);
				
				Ssid ssid = new Ssid();
				ssid.setSsid("wifi1"); //TODO have user-entered param for this or auto generate?
				objects.add(ssid);
				
				// MAC helper for station nodes
				mac.setType("ns3::StaWifiMac", ssid, false); //Type, ssid, active probing
				
				NetDeviceContainer staDevices = new NetDeviceContainer();
				staDevices.setName("staDevices");
				objects.add(staDevices);
				
				wifi.install(phy, mac, wifiStaNodes, staDevices);
				
				// MAC helper for access-point node
				mac.setType("ns3::ApWifiMac", ssid);
				
				NetDeviceContainer apDevices = new NetDeviceContainer();
				apDevices.setName("apDevices");
				objects.add(apDevices);
				
				wifi.install(phy, mac, wifiApNodes, apDevices);
				
				// End of WiFi setup unless simulated Mobility (random movement of staNodes) is required (likely not for most GLD objects)
				
				// Add all Nodes from each NodeContainer to Nodes global list of nodes
				for (int i = 0; i < wifiStaNodes.getNumNodes(); i++) {
					nodes.add(wifiStaNodes.getNode(i));
				}
				for (int i = 0; i < wifiApNodes.getNumNodes(); i++) {
					nodes.add(wifiApNodes.getNode(i));
				}				
				
				break;
				
			default:
				
				break;

		}
		
		NodeContainer gldNodes = new NodeContainer();
		gldNodes.setName("gldNodes");
		// TODO populate all GLD Nodes (houses & markets)
		
//		FNCSApplicationHelper fncsHelper = new FNCSApplicationHelper();
//		fncsHelper.setName("fncsHelper");
//		
//		ApplicationContainer fncsAps = new ApplicationContainer();
//		fncsAps.setName("fncsAps");
//		
//		fncsHelper.setApps("names", gldNodes, "marketToControllerMap", fncsAps);
//		fncsAps.start(0.0);
//		fncsAps.stop(259200.0);
		
		AbstractNs3Object ns3 = nodes.get(0);
		
		ns3.appendPrintObj("\n");
		// Output simulator stop, run, & destroy
		//ns3.appendPrintObj("Simulator::Stop(Seconds(" + this.stopTime + "));\n");
        // This stuff doesn't seem to vary from sim to sim
		ns3.appendPrintObj("Simulator::Run();\n");
		ns3.appendPrintObj("Simulator::Destroy();\n");
		ns3.appendPrintObj("return 0;\n");
		
		return objects;
	}
	
}
