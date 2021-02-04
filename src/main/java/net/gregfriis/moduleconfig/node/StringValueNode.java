package net.gregfriis.moduleconfig.node;

/**
 *
 */
public class StringValueNode implements ValueNode {

    private final String value;

    private StringValueNode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }

    public static StringValueNode create(String value) {
        return new StringValueNode(value);
    }
}
