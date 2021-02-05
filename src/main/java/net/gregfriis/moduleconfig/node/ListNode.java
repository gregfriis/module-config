package net.gregfriis.moduleconfig.node;

import net.gregfriis.moduleconfig.node.exception.NodeCastException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class ListNode implements ModuleNode {

    private final List<ModuleNode> elements;

    private ListNode(List<ModuleNode> elements) {
        this.elements = elements;
    }

    public static ListNode create(ModuleNode... elements) {
        return new ListNode(Arrays.asList(elements));
    }

    public Stream<ModuleNode> stream() {
        return elements.stream();
    }

    public int size() {
        return elements.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListNode listNode = (ListNode) o;
        return elements.equals(listNode.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }

    @Override
    public String toString() {
        return "[" +
                elements.stream().map(Object::toString).collect(Collectors.joining(",")) +
                "]";
    }

    @Override
    public ModuleNodeType getType() {
        return ModuleNodeType.LIST;
    }

    @Override
    public ObjectNode asObjectNode() {
        throw new NodeCastException();
    }

    @Override
    public StringNode asStringNode() {
        throw new NodeCastException();
    }

    @Override
    public ListNode asListNode() {
        return this;
    }

    @Override
    public ListNode mergeOnto(ModuleNode base) {
        return this;
    }
}
