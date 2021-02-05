package net.gregfriis.moduleconfig.node;

import net.gregfriis.moduleconfig.node.exception.NodeCastException;

/**
 *
 */
public interface ModuleNode {
    ModuleNodeType getType();
    default ObjectNode asObject() {
        throw new NodeCastException();
    }
    default StringNode asString() {
        throw new NodeCastException();
    }
    default ListNode asList() {
        throw new NodeCastException();
    }
    ModuleNode mergeOnto(ModuleNode base);
}
