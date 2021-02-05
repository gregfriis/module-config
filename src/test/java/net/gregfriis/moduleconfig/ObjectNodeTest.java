package net.gregfriis.moduleconfig;

import net.gregfriis.moduleconfig.node.ObjectNode;
import net.gregfriis.moduleconfig.node.StringNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ObjectNodeTest {

    private static final StringNode STRING_A = StringNode.create("a");
    private static final StringNode STRING_B = StringNode.create("b");
    private static final StringNode STRING_C = StringNode.create("c");

    @Test
    public void testToString() {
        ObjectNode objectNode = ObjectNode.create()
                .withField("0", STRING_A)
                .withField("1", STRING_B)
                .build();
        assertEquals("{0:\"a\",1:\"b\"}", objectNode.toString());
    }

    @Test
    public void testMergeInto() {
        {
            // Simple merge with no overlaps
            ObjectNode a = ObjectNode.create()
                    .withField("0", STRING_A)
                    .build();
            ObjectNode b = ObjectNode.create()
                    .withField("1", STRING_B)
                    .build();
            ObjectNode expected = ObjectNode.create()
                    .withField("0", STRING_A)
                    .withField("1", STRING_B)
                    .build();
            assertEquals(expected, b.mergeOnto(a));
        }
        {
            // Overwrite string field
            ObjectNode a = ObjectNode.create()
                    .withField("0", STRING_A)
                    .withField("1", STRING_C)
                    .build();
            ObjectNode b = ObjectNode.create()
                    .withField("0", STRING_B)
                    .build();
            ObjectNode expected = ObjectNode.create()
                    .withField("0", STRING_B)
                    .withField("1", STRING_C)
                    .build();
            assertEquals(expected, b.mergeOnto(a));
        }
        {
            // If types are different, overwrite with object node
            ObjectNode b = ObjectNode.create()
                    .withField("0", STRING_B)
                    .build();
            assertEquals(b, b.mergeOnto(STRING_A));
        }
        {
            // Merge nested object
            ObjectNode a = ObjectNode.create()
                    .withField("0", ObjectNode.create()
                            .withField("0a", STRING_A)
                            .withField("0c", STRING_A)
                            .build())
                    .build();
            ObjectNode b = ObjectNode.create()
                    .withField("0", ObjectNode.create()
                            .withField("0b", STRING_B)
                            .withField("0c", STRING_C)
                            .build())
                    .build();
            ObjectNode expected = ObjectNode.create()
                    .withField("0", ObjectNode.create()
                            .withField("0a", STRING_A)
                            .withField("0b", STRING_B)
                            .withField("0c", STRING_C)
                            .build())
                    .build();
            assertEquals(expected, b.mergeOnto(a));
        }
    }
}
