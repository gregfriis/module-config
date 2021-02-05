package net.gregfriis.moduleconfig.node;

/**
 *
 */
public interface ModuleNode {
    ModuleNodeType getType();
    ObjectNode asObjectNode();
    StringNode asStringNode();
}
