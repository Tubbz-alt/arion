/**
 *
 */
package gov.pnnl.prosser.api.gld.module;

import gov.pnnl.prosser.api.GLDUtils;

import java.util.Objects;

/**
 * GridLabD Powerflow Module
 *
 * @author nord229
 */
public class PowerflowModule extends Module {

    public enum SolverMethod {
        FBS,
        GS,
        NR;
    }

    private SolverMethod solverMethod;

    public PowerflowModule() {
    }

    public PowerflowModule(final SolverMethod solverMethod) {
        this.solverMethod = solverMethod;
    }

    /**
     * @return the solverMethod
     */
    public SolverMethod getSolverMethod() {
        return solverMethod;
    }

    /**
     * @param solverMethod
     *            the solverMethod to set
     */
    public void setSolverMethod(final SolverMethod solverMethod) {
        this.solverMethod = solverMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(solverMethod);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PowerflowModule other = (PowerflowModule) obj;
        return Objects.equals(this.solverMethod, other.solverMethod);
    }

    @Override
    public String getGLDObjectType() {
        return "powerflow";
    }

    @Override
    public boolean hasProperties() {
        return true;
    }

    @Override
    protected void writeGLDProperties(final StringBuilder sb) {
        GLDUtils.writeProperty(sb, "solver_method", this.solverMethod);
    }

}
