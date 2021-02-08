package net.gregfriis.moduleconfig.node.mapping;

import net.gregfriis.moduleconfig.node.BooleanNode;
import net.gregfriis.moduleconfig.node.NumberNode;
import org.junit.Test;

import static net.gregfriis.moduleconfig.node.ModuleNodeTestUtils.STRING_A;
import static net.gregfriis.moduleconfig.node.mapping.ModuleNodeObjectMapper.*;
import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ModuleNodeObjectMapperTest {

    @Test
    public void testMapBoolean() {
        assertEquals(Boolean.TRUE, map(BooleanNode.TRUE, Boolean.class));
        assertEquals(Boolean.FALSE, map(BooleanNode.FALSE, Boolean.class));
    }

    @Test
    public void testMapString() {
        assertEquals("a", map(STRING_A, String.class));
    }

    @Test
    public void testMapInteger() {
        assertEquals(Integer.valueOf(10), map(NumberNode.create(10), Integer.class));
        assertEquals(Integer.valueOf(10), map(NumberNode.create(10.3), Integer.class));
    }

    @Test
    public void testMapDouble() {
        assertEquals(10.0, map(NumberNode.create(10), Double.class), 0.001);
        assertEquals(10.3, map(NumberNode.create(10.3), Double.class), 0.001);
    }
}