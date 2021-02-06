package net.gregfriis.moduleconfig.node;

/**
 *
 */
public class ModuleNodeTestUtils {
    public static final StringNode STRING_A = StringNode.create("a");
    public static final StringNode STRING_B = StringNode.create("b");
    public static final StringNode STRING_C = StringNode.create("c");

    public static final NumberNode NUMBER_0 = NumberNode.create(0);

    public static final ListNode LIST_EMPTY = ListNode.create();
    public static final ListNode LIST_ABC = ListNode.create(STRING_A, STRING_B, STRING_C);

    public static final ObjectNode OBJECT_ALL_TYPES = ObjectNode.create()
            .withField("s", STRING_A)
            .withField("n", NUMBER_0)
            .withField("l", LIST_ABC)
            .withField("b", BooleanNode.TRUE)
            .build();
}
