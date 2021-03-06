package net.gregfriis.moduleconfig.node;

import java.util.Objects;

/**
 *
 */
public class StringNode implements ModuleNode {

    private final String value;

    private StringNode(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StringNode that = (StringNode) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static StringNode create(String value) {
        return new StringNode(value);
    }

    @Override
    public ModuleNodeType getType() {
        return ModuleNodeType.STRING;
    }

    @Override
    public StringNode asString() {
        return this;
    }

    @Override
    public StringNode mergeOnto(ModuleNode base) {
        // Literal nodes simply overwrite whatever they are being merged into
        return this;
    }
}
