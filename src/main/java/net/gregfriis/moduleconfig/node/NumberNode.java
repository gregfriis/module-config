package net.gregfriis.moduleconfig.node;

import java.util.Objects;

/**
 *
 */
public class NumberNode implements ModuleNode {

    private final Number value;

    private NumberNode(Number value) {
        this.value = value;
    }

    public static NumberNode create(Number value) {
        return new NumberNode(value);
    }

    public int intValue() {
        return value.intValue();
    }

    public double doubleValue() {
        return value.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NumberNode that = (NumberNode) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public ModuleNodeType getType() {
        return ModuleNodeType.NUMBER;
    }

    @Override
    public NumberNode asNumber() {
        return this;
    }

    @Override
    public ModuleNode mergeOnto(ModuleNode base) {
        return this;
    }
}
