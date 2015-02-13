/**
 *
 */

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.Ns3Simulator;
import gov.pnnl.prosser.api.gld.obj.AuctionObject;
import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.ns3.module.Namespace;
import gov.pnnl.prosser.api.ns3.obj.Ns3Network;
import gov.pnnl.prosser.api.pwr.obj.Controller;
import gov.pnnl.prosser.api.pwr.obj.ControllerNetworkInterface;
import gov.pnnl.prosser.api.pwr.obj.MarketNetworkInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author happ546
 *
 */
public class TestExperimentNs3Simulator implements Ns3Simulator {

    private Ns3Network network;

    private List<MarketNetworkInterface> marketNIs;

    private List<ControllerNetworkInterface> controllerNIs;

    private String gldNodePrefix;

    private List<AuctionObject> auctions;

    private List<Controller> controllers;

    @Override
    public void setup() {
        // Get list of end devices from Peter
        // List<..AbstractProsserObject?..> gldList = peter'sList;

        // User inputs basic params (Network type, addr base & mask, # of nodes)
        network = new Ns3Network();
        network.setNumNodes(200); // TODO Infer this from gldList or user specification
        // network.setMarketNIs(this.marketNIs);
        // network.setControllerNIs(this.controllerNIs);
        network.setAuctions(auctions);
        network.setControllers(controllers);

        // TODO get stop time from user
        network.setStopTime(0.5);
    }

    @Override
    public List<Module> getModules() {
        return network.getModules();
    }

    @Override
    public List<Namespace> getNamespaces() {
        final List<Namespace> namespaces = new ArrayList<Namespace>();
        namespaces.add(new Namespace("ns3"));
        // namespaces.add(new Namespace("std")); // TODO only use std for data structures/file IO

        return namespaces;
    }

    @Override
    public List<AbstractNs3Object> getObjects() {

        // Number of Nodes representing GLD Houses (each with attached ControllerNetworkInterfaces)
        final int numEndpointNodes = this.getControllers().size();
        // Number of Nodes for network backbone (WiFi APs, CSMA/Ethernet "routers", LTE towers)
        int numBackboneNodes = numEndpointNodes / 20;
        if (numEndpointNodes % 20 > 0) {
            numBackboneNodes++;
        }

        // Not a real builder pattern; after necessary params, use network type for type-specific method to construct nodes, install devices/applications, etc.
        final List<AbstractNs3Object> objects = network.createWifi(numBackboneNodes, numEndpointNodes, "10.0.0.0", "4ms");

        // List of ns-3 Nodes to keep track of specific Nodes
        // final List<Node> nodes = network.getNodes();

        return objects;
    }

    /**
     * @return the marketNI
     */
    public List<MarketNetworkInterface> getMarketNIs() {
        return marketNIs;
    }

    /**
     * @return the auctions
     */
    public List<AuctionObject> getAuctions() {
        return auctions;
    }

    /**
     * @param auctions
     *            the Auctions to set
     */
    public void setAuctions(final List<AuctionObject> auctions) {
        this.auctions = auctions;
    }

    /**
     * @return the controllers
     */
    public List<Controller> getControllers() {
        return controllers;
    }

    /**
     * @param controllers
     *            the Controllers to set
     */
    public void setControllers(final List<Controller> controllers) {
        this.controllers = controllers;
    }

    /**
     * @param marketNI
     *            the marketNI to set
     */
    public void setMarketNIs(final List<MarketNetworkInterface> marketNIs) {
        this.marketNIs = marketNIs;
    }

    /**
     * @return the controllerNIs
     */
    public List<ControllerNetworkInterface> getControllerNIs() {
        return controllerNIs;
    }

    /**
     * @param controllerNIs
     *            the controllerNIs to set
     */
    public void setControllerNIs(final List<ControllerNetworkInterface> controllerNIs) {
        this.controllerNIs = controllerNIs;
    }

    /**
     * @return the gldNodePrefix
     */
    public String getGldNodePrefix() {
        return gldNodePrefix;
    }

    /**
     * @param gldNodePrefix
     *            the gldNodePrefix to set
     */
    public void setGldNodePrefix(final String gldNodePrefix) {
        this.gldNodePrefix = gldNodePrefix;
    }

}
