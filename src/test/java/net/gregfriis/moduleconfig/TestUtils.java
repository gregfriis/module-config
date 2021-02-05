package net.gregfriis.moduleconfig;

import java.io.InputStream;

/**
 *
 */
public class TestUtils {

    public static InputStream testResource(String path) {
        return TestUtils.class.getResourceAsStream(path);
    }
}
