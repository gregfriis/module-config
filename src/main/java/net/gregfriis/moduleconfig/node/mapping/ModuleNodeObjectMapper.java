package net.gregfriis.moduleconfig.node.mapping;

import net.gregfriis.moduleconfig.node.ModuleNode;

/**
 *
 */
public class ModuleNodeObjectMapper {

    @SuppressWarnings("unchecked")
    public static <T> T map(ModuleNode node, Class<T> type) {
        if (Boolean.class.isAssignableFrom(type)) {
            return (T) Boolean.valueOf(node.asBoolean().value());
        }
        if (String.class.isAssignableFrom(type)) {
            return (T) node.asString().value();
        }
        if (Integer.class.isAssignableFrom(type)) {
            return (T) Integer.valueOf(node.asNumber().intValue());
        }
        if (Double.class.isAssignableFrom(type)) {
            return (T) Double.valueOf(node.asNumber().doubleValue());
        }
        throw new RuntimeException("Unable to map module node to type " + type.getSimpleName());
    }
}
