package com.atypon.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private static Map<String, Object> map = new ConcurrentHashMap<>();

    public static void setAttribute(String name, Object object) {
        map.put(name, object);
    }

    public static Object getAttribute(String name) {
        return map.get(name);
    }
}
