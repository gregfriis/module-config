package net.gregfriis.moduleconfig.node.loading;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.gregfriis.moduleconfig.node.ModuleNode;
import net.gregfriis.moduleconfig.node.mapping.JacksonModuleNodeMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public class YAMLModuleNodeLoader {

    private static final ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    private YAMLModuleNodeLoader() {}

    public static ModuleNode load(InputStream file) {
        return JacksonModuleNodeMapper.mapJsonNode(readTree(file));
    }

    private static JsonNode readTree(InputStream file) {
        try {
            return MAPPER.readTree(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
