package net.gregfriis.moduleconfig.node;

import org.junit.Test;

import static net.gregfriis.moduleconfig.node.ModuleNodeTestUtils.*;
import static org.junit.Assert.assertEquals;

public class ListNodeTest {

    @Test
    public void testToString() {
        {
            // Basic list
            assertEquals("[\"a\",\"b\",\"c\"]", LIST_ABC.toString());
        }
        {
            // Empty list
            assertEquals("[]", LIST_EMPTY.toString());
        }
    }

    @Test
    public void testMergeOnto() {
        {
            // List overwrites other list
            assertEquals(LIST_EMPTY, LIST_EMPTY.mergeOnto(LIST_ABC));
        }
        {
            // List overwrites object
            assertEquals(LIST_ABC, LIST_ABC.mergeOnto(ObjectNode.create().build()));
        }
    }
}
