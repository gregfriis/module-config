package net.gregfriis.moduleconfig.node;

/**
 *
 */
public interface ModuleNode {
    ModuleNodeType getType();
    ObjectNode asObject();
    StringNode asString();
    ListNode asList();
    ModuleNode mergeOnto(ModuleNode base);
}
