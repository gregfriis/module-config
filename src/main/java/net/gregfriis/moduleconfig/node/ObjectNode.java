package net.gregfriis.moduleconfig.node;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class ObjectNode implements ValueNode {

    private final Map<String, ValueNode> fields;

    private ObjectNode(Map<String, ValueNode> fields) {
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

    public static class Builder {
        private final Map<String, ValueNode> fields = new HashMap<>();

        private Builder() {}

        public Builder withField(String name, ValueNode value) {
            fields.put(name, value);
            return this;
        }

        public ObjectNode build() {
            return new ObjectNode(fields);
        }
    }
}
