package net.gregfriis.moduleconfig.node.loading;

import net.gregfriis.moduleconfig.node.ModuleNode;
import org.junit.Test;

import static net.gregfriis.moduleconfig.TestUtils.testResource;
import static net.gregfriis.moduleconfig.node.ModuleNodeTestUtils.OBJECT_ALL_TYPES;
import static org.junit.Assert.assertEquals;

/**
 *
 */
public class YAMLModuleNodeLoaderTest {

    @Test
    public void testLoad() {
        ModuleNode actual = YAMLModuleNodeLoader.load(testResource("/yaml/object-all-types.yaml"));
        assertEquals(OBJECT_ALL_TYPES, actual);
    }
}