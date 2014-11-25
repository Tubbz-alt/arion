/**
 *
 */
package gov.pnnl.prosser.api;

import java.util.Objects;

/**
 * Object to encompass shared properties for all objects
 *
 * @author nord229
 */
public abstract class AbstractProsserObject implements GLDSerializable {

    /**
     * Object name for referencing in files
     */
    private String name;

    public AbstractProsserObject() {
    }

    public AbstractProsserObject(final String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
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
        final AbstractProsserObject other = (AbstractProsserObject) obj;
        return Objects.equals(this.name, other.name);
    }

    public abstract String getGLDObjectType();

    @Override
    public void writeGLDString(final StringBuilder sb) {
        sb.append("object ").append(getGLDObjectType()).append(" {\n");
        GLDUtils.appendProperty(sb, "name", this.name);
        this.writeGLDProperties(sb);
        sb.append("}\n");
    }

    protected abstract void writeGLDProperties(final StringBuilder sb);
}
