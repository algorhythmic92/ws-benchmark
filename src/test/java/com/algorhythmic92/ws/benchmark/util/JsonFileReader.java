package com.algorhythmic92.ws.benchmark.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileReader {
    public static String readJsonFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
