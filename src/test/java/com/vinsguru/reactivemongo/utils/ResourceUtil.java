package com.vinsguru.reactivemongo.utils;

import org.springframework.core.io.Resource;
import org.testcontainers.shaded.com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ResourceUtil {

    public static String read(Resource resource) throws IOException {
            var input = resource.getInputStream();
            String json = null;
            try (Reader reader = new InputStreamReader(input)) {
                json = CharStreams.toString(reader);
            }

            return json;
    }
}
