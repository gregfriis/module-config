package net.gregfriis.moduleconfig.node;

import net.gregfriis.moduleconfig.node.exception.NodeCastException;

/**
 *
 */
public class StringNode implements ModuleNode {

    private final String value;

    private StringNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }

    public static StringNode create(String value) {
        return new StringNode(value);
    }

    @Override
    public ModuleNodeType getType() {
        return ModuleNodeType.STRING;
    }

    @Override
    public ObjectNode asObjectNode() {
        throw new NodeCastException();
    }

    @Override
    public StringNode asStringNode() {
        return this;
    }
}
