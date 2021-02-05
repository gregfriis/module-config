package net.gregfriis.moduleconfig.node;

import net.gregfriis.moduleconfig.node.exception.MissingFieldException;
import net.gregfriis.moduleconfig.node.exception.NodeCastException;
import net.gregfriis.moduleconfig.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
        this.fields = CollectionUtils.shallowCopy(fields);
    }

    @Override
    public String toString() {
        return "{" +
                fields.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(e -> e.getKey() + ":" + e.getValue().toString()).collect(Collectors.joining(",")) +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjectNode that = (ObjectNode) o;
        return fields.equals(that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fields);
    }

    public static Builder create() {
        return new Builder();
    }

    @Override
    public ModuleNodeType getType() {
        return ModuleNodeType.OBJECT;
    }

    @Override
    public ObjectNode asObject() {
        return this;
    }

    @Override
    public StringNode asString() {
        throw new NodeCastException();
    }

    @Override
    public ListNode asList() {
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
            ObjectNode baseObject = base.asObject();
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
