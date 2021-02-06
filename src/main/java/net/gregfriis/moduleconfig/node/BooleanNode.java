package net.gregfriis.moduleconfig.node;

import java.util.Objects;

/**
 *
 */
public class BooleanNode implements ModuleNode {

    private final boolean value;

    private BooleanNode(boolean value) {
        this.value = value;
    }

    public static final BooleanNode TRUE = new BooleanNode(true);
    public static final BooleanNode FALSE = new BooleanNode(false);

    public static BooleanNode create(boolean value) {
        return value ? TRUE : FALSE;
    }

    public boolean value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BooleanNode that = (BooleanNode) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public BooleanNode asBoolean() {
        return this;
    }

    @Override
    public ModuleNodeType getType() {
        return ModuleNodeType.BOOLEAN;
    }

    @Override
    public ModuleNode mergeOnto(ModuleNode base) {
        return this;
    }
}
