package net.gregfriis.moduleconfig.node;

/**
 *
 */
public interface ModuleNode {
    ModuleNodeType getType();
    ObjectNode asObjectNode();
    StringNode asStringNode();
    ListNode asListNode();
    ModuleNode mergeOnto(ModuleNode base);
}
