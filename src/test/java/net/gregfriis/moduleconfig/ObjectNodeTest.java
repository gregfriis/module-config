package net.gregfriis.moduleconfig;

import net.gregfriis.moduleconfig.node.ObjectNode;
import net.gregfriis.moduleconfig.node.StringValueNode;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class ObjectNodeTest {

    @Test
    public void testToString() {
        ObjectNode objectNode = ObjectNode.create()
                .withField("0", StringValueNode.create("a"))
                .withField("1", StringValueNode.create("b"))
                .build();
        Assert.assertEquals("{0:\"a\",1:\"b\"}", objectNode.toString());
    }
}
