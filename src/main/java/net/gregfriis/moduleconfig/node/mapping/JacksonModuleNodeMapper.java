package net.gregfriis.moduleconfig.node.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.gregfriis.moduleconfig.node.ListNode;
import net.gregfriis.moduleconfig.node.ModuleNode;
import net.gregfriis.moduleconfig.node.NumberNode;
import net.gregfriis.moduleconfig.node.StringNode;
import net.gregfriis.moduleconfig.node.exception.NodeMappingException;

import java.util.ArrayList;

/**
 *
 */
public class JacksonModuleNodeMapper {

    private JacksonModuleNodeMapper() {}

    public static ModuleNode mapJsonNode(JsonNode jsonNode) {
        if (jsonNode.isValueNode()) {
            if (jsonNode.isNumber()) {
                return NumberNode.create(jsonNode.numberValue());
            }
            if (jsonNode.isTextual()) {
                return StringNode.create(jsonNode.textValue());
            }
        }
        if (jsonNode.isObject()) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            net.gregfriis.moduleconfig.node.ObjectNode.Builder builder = net.gregfriis.moduleconfig.node.ObjectNode.create();
            objectNode.fields().forEachRemaining(field -> builder.withField(field.getKey(), mapJsonNode(field.getValue())));
            return builder.build();
        }
        if (jsonNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            ArrayList<ModuleNode> elements = new ArrayList<>(arrayNode.size());
            arrayNode.forEach(e -> elements.add(mapJsonNode(e)));
            return ListNode.create(elements);
        }
        throw new NodeMappingException("Unsupported JSON node type: " + jsonNode.getNodeType());
    }
}
