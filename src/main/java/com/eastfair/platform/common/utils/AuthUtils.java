package com.eastfair.platform.common.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class AuthUtils {
    private final static ThreadLocal<String> AUTH = new ThreadLocal<>();

    public static void add(String username) {
        AUTH.set(username);
    }

    public static String get() {
        return AUTH.get();
    }

    public static void remove() {
        AUTH.remove();
    }
}
