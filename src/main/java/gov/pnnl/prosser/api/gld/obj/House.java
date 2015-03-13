/**
 *
 */
package gov.pnnl.prosser.api.gld.obj;

import java.util.ArrayList;
import java.util.List;

import gov.pnnl.prosser.api.gld.GldUtils;
import gov.pnnl.prosser.api.gld.enums.CoolingSystemType;
import gov.pnnl.prosser.api.gld.enums.FanType;
import gov.pnnl.prosser.api.gld.enums.HeatingSystemType;
import gov.pnnl.prosser.api.gld.enums.MotorEfficiency;
import gov.pnnl.prosser.api.gld.enums.MotorModel;

/**
 * House Object
 *
 * @author nord229
 */
public class House extends ResidentialEnduse {

    /**
     * parent node
     */
    private Node parent;

    /**
     * roof R-value (degF.sf.h/Btu)
     */
    private Double Rroof;

    /**
     * wall R-value (degF.sf.h/Btu)
     */
    private Double Rwall;

    /**
     * floor R-value (degF.sf.h/Btu)
     */
    private Double Rfloor;

    /**
     * door R-value (degF.sf.h/Btu)
     */
    private Double Rdoors;

    /**
     * window R-value (degF.sf.h/Btu)
     */
    private Double Rwindows;

    /**
     * number of air-changes per hour
     */
    private Double airchangePerHour;

    /**
     * power factor of hvac (unit)
     */
    private Double hvacPowerFactor;

    /**
     * cooling system type
     */
    private CoolingSystemType coolingSystemType;

    /**
     * heating system type
     */
    private HeatingSystemType heatingSystemType;

    /**
     * fan type
     */
    private FanType fanType;

    /**
     * determines the amount of current the HVAC circuit breaker can handle (A)
     */
    private Double hvacBreakerRating;

    /**
     * total thermal mass per floor area (Btu/degF.sf)
     */
    private Double totalThermalMassPerFloorArea;

    /**
     * motor efficiency
     */
    private MotorEfficiency motorEfficiency;

    /**
     * motor model
     */
    private MotorModel motorModel;

    /**
     * system cooling performance coefficient (Btu/kWh)
     */
    private Double coolingCop;

    /**
     * home conditioned floor area (sf)
     */
    private Double floorArea;

    /**
     * ratio of door area to wall area
     */
    private Double numberOfDoors;

    /**
     * thermostat heating setpoint (degF)
     */
    private Double heatingSetpoint;

    /**
     * thermostat heating setpoint (degF) function
     * if the heating setpoint is a function of another property set that here
     */
    private String heatingSetpointFn;

    /**
     * FNCS controller
     */
    private Controller controller;

    /**
     * the loads on the house (lights, etc.)
     */
    private final List<ZIPLoad> loads = new ArrayList<>();

    /**
     * Get the parent node
     * 
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Set the parent node
     * 
     * @param parent
     *            the parent to set
     */
    public void setParent(final Node parent) {
        this.parent = parent;
    }

    /**
     * Get the roof R-value (degF.sf.h/Btu)
     * 
     * @return the rroof
     */
    public Double getRroof() {
        return Rroof;
    }

    /**
     * Set the roof R-value (degF.sf.h/Btu)
     * 
     * @param rroof
     *            the rroof to set
     */
    public void setRroof(final Double rroof) {
        Rroof = rroof;
    }

    /**
     * Get the wall R-value (degF.sf.h/Btu)
     * 
     * @return the rwall
     */
    public Double getRwall() {
        return Rwall;
    }

    /**
     * Set the wall R-value (degF.sf.h/Btu)
     * 
     * @param rwall
     *            the rwall to set
     */
    public void setRwall(final Double rwall) {
        Rwall = rwall;
    }

    /**
     * Get the floor R-value (degF.sf.h/Btu)
     * 
     * @return the rfloor
     */
    public Double getRfloor() {
        return Rfloor;
    }

    /**
     * Set the floor R-value (degF.sf.h/Btu)
     * 
     * @param rfloor
     *            the rfloor to set
     */
    public void setRfloor(final Double rfloor) {
        Rfloor = rfloor;
    }

    /**
     * Get the door R-value (degF.sf.h/Btu)
     * 
     * @return the rdoors
     */
    public Double getRdoors() {
        return Rdoors;
    }

    /**
     * Set the door R-value (degF.sf.h/Btu)
     * 
     * @param rdoors
     *            the rdoors to set
     */
    public void setRdoors(final Double rdoors) {
        Rdoors = rdoors;
    }

    /**
     * Get the window R-value (degF.sf.h/Btu)
     * 
     * @return the rwindows
     */
    public Double getRwindows() {
        return Rwindows;
    }

    /**
     * Set the window R-value (degF.sf.h/Btu)
     * 
     * @param rwindows
     *            the rwindows to set
     */
    public void setRwindows(final Double rwindows) {
        Rwindows = rwindows;
    }

    /**
     * Get the number of air-changes per hour
     * 
     * @return the airchangePerHour
     */
    public Double getAirchangePerHour() {
        return airchangePerHour;
    }

    /**
     * Set the number of air-changes per hour
     * 
     * @param airchangePerHour
     *            the airchangePerHour to set
     */
    public void setAirchangePerHour(final Double airchangePerHour) {
        this.airchangePerHour = airchangePerHour;
    }

    /**
     * Get the power factor of hvac (unit)
     * 
     * @return the hvacPowerFactor
     */
    public Double getHvacPowerFactor() {
        return hvacPowerFactor;
    }

    /**
     * Set the power factor of hvac (unit)
     * 
     * @param hvacPowerFactor
     *            the hvacPowerFactor to set
     */
    public void setHvacPowerFactor(final Double hvacPowerFactor) {
        this.hvacPowerFactor = hvacPowerFactor;
    }

    /**
     * Get the cooling system type
     * 
     * @return the coolingSystemType
     */
    public CoolingSystemType getCoolingSystemType() {
        return coolingSystemType;
    }

    /**
     * Set the cooling system type
     * 
     * @param coolingSystemType
     *            the coolingSystemType to set
     */
    public void setCoolingSystemType(final CoolingSystemType coolingSystemType) {
        this.coolingSystemType = coolingSystemType;
    }

    /**
     * Get the heating system type
     * 
     * @return the heatingSystemType
     */
    public HeatingSystemType getHeatingSystemType() {
        return heatingSystemType;
    }

    /**
     * Set the heating system type
     * 
     * @param heatingSystemType
     *            the heatingSystemType to set
     */
    public void setHeatingSystemType(final HeatingSystemType heatingSystemType) {
        this.heatingSystemType = heatingSystemType;
    }

    /**
     * Get the fan type
     * 
     * @return the fanType
     */
    public FanType getFanType() {
        return fanType;
    }

    /**
     * Set the fan type
     * 
     * @param fanType
     *            the fanType to set
     */
    public void setFanType(final FanType fanType) {
        this.fanType = fanType;
    }

    /**
     * Get the amount of current the HVAC circuit breaker can handle (A)
     * 
     * @return the hvacBreakerRating
     */
    public Double getHvacBreakerRating() {
        return hvacBreakerRating;
    }

    /**
     * Set the amount of current the HVAC circuit breaker can handle (A)
     * 
     * @param hvacBreakerRating
     *            the hvacBreakerRating to set
     */
    public void setHvacBreakerRating(final Double hvacBreakerRating) {
        this.hvacBreakerRating = hvacBreakerRating;
    }

    /**
     * Get the total thermal mass per floor area (Btu/degF.sf)
     * 
     * @return the totalThermalMassPerFloorArea
     */
    public Double getTotalThermalMassPerFloorArea() {
        return totalThermalMassPerFloorArea;
    }

    /**
     * Set the total thermal mass per floor area (Btu/degF.sf)
     * 
     * @param totalThermalMassPerFloorArea
     *            the totalThermalMassPerFloorArea to set
     */
    public void setTotalThermalMassPerFloorArea(final Double totalThermalMassPerFloorArea) {
        this.totalThermalMassPerFloorArea = totalThermalMassPerFloorArea;
    }

    /**
     * Get the motor efficiency
     * 
     * @return the motorEfficiency
     */
    public MotorEfficiency getMotorEfficiency() {
        return motorEfficiency;
    }

    /**
     * Set the motor efficiency
     * 
     * @param motorEfficiency
     *            the motorEfficiency to set
     */
    public void setMotorEfficiency(final MotorEfficiency motorEfficiency) {
        this.motorEfficiency = motorEfficiency;
    }

    /**
     * Get the motor model
     * 
     * @return the motorModel
     */
    public MotorModel getMotorModel() {
        return motorModel;
    }

    /**
     * Set the motor model
     * 
     * @param motorModel
     *            the motorModel to set
     */
    public void setMotorModel(final MotorModel motorModel) {
        this.motorModel = motorModel;
    }

    /**
     * Get the system cooling performance coefficient (Btu/kWh)
     * 
     * @return the coolingCop
     */
    public Double getCoolingCop() {
        return coolingCop;
    }

    /**
     * Set the system cooling performance coefficient (Btu/kWh)
     * 
     * @param coolingCop
     *            the coolingCop to set
     */
    public void setCoolingCop(final Double coolingCop) {
        this.coolingCop = coolingCop;
    }

    /**
     * Get the home conditioned floor area (sf)
     * 
     * @return the floorArea
     */
    public Double getFloorArea() {
        return floorArea;
    }

    /**
     * Set the home conditioned floor area (sf)
     * 
     * @param floorArea
     *            the floorArea to set
     */
    public void setFloorArea(final Double floorArea) {
        this.floorArea = floorArea;
    }

    /**
     * Get the ratio of door area to wall area
     * 
     * @return the numberOfDoors
     */
    public Double getNumberOfDoors() {
        return numberOfDoors;
    }

    /**
     * Set the ratio of door area to wall area
     * 
     * @param numberOfDoors
     *            the numberOfDoors to set
     */
    public void setNumberOfDoors(final Double numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    /**
     * Get the thermostat heating setpoint (degF)
     * 
     * @return the heatingSetpoint
     */
    public Double getHeatingSetpoint() {
        return heatingSetpoint;
    }

    /**
     * Set the thermostat heating setpoint (degF)
     * 
     * @param heatingSetpoint
     *            the heatingSetpoint to set
     */
    public void setHeatingSetpoint(final Double heatingSetpoint) {
        this.heatingSetpoint = heatingSetpoint;
    }

    /**
     * Get the thermostat heating setpoint (degF) function
     * if the heating setpoint is a function of another property it will be set here
     * 
     * @return the heatingSetpointFn
     */
    public String getHeatingSetpointFn() {
        return heatingSetpointFn;
    }

    /**
     * Set the thermostat heating setpoint (degF) function
     * if the heating setpoint is a function of another property set that here
     * 
     * @param heatingSetpointFn
     *            the heatingSetpointFn to set
     */
    public void setHeatingSetpointFn(final String heatingSetpointFn) {
        this.heatingSetpointFn = heatingSetpointFn;
    }

    /**
     * Get the FNCS controller
     * 
     * @return the controller
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Set the FNCS controller
     * 
     * @param controller
     *            the controller to set
     */
    public void setController(final Controller controller) {
        this.controller = controller;
    }

    /**
     * Add a ZIPLoad to this House
     * 
     * @param load
     *            the load to add
     */
    public void addLoad(final ZIPLoad load) {
        this.loads.add(load);
    }

    /**
     * Create and Add a Zip load to this house
     * 
     * @return the Zip load
     */
    public ZIPLoad addLoad() {
        final ZIPLoad load = new ZIPLoad();
        this.loads.add(load);
        return load;
    }

    /**
     * Create and set the controller on this house
     * 
     * @param name
     *            the controller name
     * @return the controller
     */
    public Controller controller(final String name) {
        this.controller = new Controller();
        controller.setName(name);
        return controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "house";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        super.writeGldProperties(sb);
        GldUtils.writeProperty(sb, "parent", this.parent);
        GldUtils.writeProperty(sb, "Rroof", this.Rroof);
        GldUtils.writeProperty(sb, "Rwall", this.Rwall);
        GldUtils.writeProperty(sb, "Rfloor", this.Rfloor);
        GldUtils.writeProperty(sb, "Rdoors", this.Rdoors);
        GldUtils.writeProperty(sb, "Rwindows", this.Rwindows);
        GldUtils.writeProperty(sb, "airchange_per_hour", this.airchangePerHour);
        GldUtils.writeProperty(sb, "hvac_power_factor", this.hvacPowerFactor);
        GldUtils.writeProperty(sb, "cooling_system_type", this.coolingSystemType);
        GldUtils.writeProperty(sb, "heating_system_type", this.heatingSystemType);
        GldUtils.writeProperty(sb, "fan_type", this.fanType);
        GldUtils.writeProperty(sb, "hvac_breaker_rating", this.hvacBreakerRating);
        GldUtils.writeProperty(sb, "total_thermal_mass_per_floor_area", this.totalThermalMassPerFloorArea);
        GldUtils.writeProperty(sb, "motor_efficiency", this.motorEfficiency);
        GldUtils.writeProperty(sb, "motor_model", this.motorModel);
        GldUtils.writeProperty(sb, "cooling_COP", this.coolingCop);
        GldUtils.writeProperty(sb, "floor_area", this.floorArea);
        GldUtils.writeProperty(sb, "number_of_doors", this.numberOfDoors);
        if (heatingSetpointFn != null) {
            GldUtils.writeProperty(sb, "heating_setpoint", this.heatingSetpointFn);
        } else {
            GldUtils.writeProperty(sb, "heating_setpoint", this.heatingSetpoint);
        }
        controller.writeGldString(sb);
        // Handle special case since we need a semicolon here
        sb.insert(sb.length() - 1, ';');
        for (final ZIPLoad load : loads) {
            load.writeGldString(sb);
            // Handle special case since we need a semicolon here
            sb.insert(sb.length() - 1, ';');
        }
    }

}
