package net.gregfriis.moduleconfig.node;

import net.gregfriis.moduleconfig.node.exception.MissingFieldException;
import net.gregfriis.moduleconfig.node.exception.NodeCastException;
import net.gregfriis.moduleconfig.util.MapUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class ObjectNode implements ModuleNode {

    private final Map<String, ModuleNode> fields;

    private ObjectNode(Map<String, ModuleNode> fields) {
        // Defensively shallow copy the fields in case of builder re-use
        this.fields = MapUtils.shallowCopy(fields);
    }

    @Override
    public String toString() {
        return "{" +
                fields.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(e -> e.getKey() + ":" + e.getValue().toString()).collect(Collectors.joining(",")) +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ObjectNode) {
            return fields.equals(((ObjectNode) obj).fields);
        }
        return false;
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

    public ModuleNode expectField(String key) {
        if (fields.containsKey(key)) {
            return fields.get(key);
        }
        throw new MissingFieldException();
    }

    public Set<String> getFieldNames() {
        return fields.keySet();
    }

    @Override
    public ObjectNode mergeOnto(ModuleNode base) {
        // If base node is an object, merge fields
        if (base.getType() == ModuleNodeType.OBJECT) {
            ObjectNode baseObject = base.asObjectNode();
            return new ObjectNode(
                    Stream.concat(baseObject.fields.entrySet().stream(), this.fields.entrySet().stream())
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b.mergeOnto(a)))
            );
        }
        // Otherwise, overwrite the base node
        return this;
    }

    public static class Builder {
        private final Map<String, ModuleNode> fields;

        private Builder() {
            this.fields = new HashMap<>();
        }

        public Builder withField(String name, ModuleNode value) {
            fields.put(name, value);
            return this;
        }

        public ObjectNode build() {
            return new ObjectNode(fields);
        }
    }
}
