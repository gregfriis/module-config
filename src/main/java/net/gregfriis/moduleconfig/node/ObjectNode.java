package net.gregfriis.moduleconfig.node;

import net.gregfriis.moduleconfig.node.exception.NodeCastException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class ObjectNode implements ModuleNode {

    private final Map<String, ModuleNode> fields;

    private ObjectNode(Map<String, ModuleNode> fields) {
        // Defensively shallow copy the fields in case of builder re-use
        this.fields = fields.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public String toString() {
        return "{" +
                fields.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(e -> e.getKey() + ":" + e.getValue().toString()).collect(Collectors.joining(",")) +
                "}";
    }

    public static Builder create() {
        return new Builder();
    }

    @Override
    public ModuleNodeType getType() {
        return ModuleNodeType.OBJECT;
    }

    @Override
    public ObjectNode asObjectNode() {
        return this;
    }

    @Override
    public StringNode asStringNode() {
        throw new NodeCastException();
    }

    public static class Builder {
        private final Map<String, ModuleNode> fields = new HashMap<>();

        private Builder() {}

        public Builder withField(String name, ModuleNode value) {
            fields.put(name, value);
            return this;
        }

        public ObjectNode build() {
            return new ObjectNode(fields);
        }
    }
}
