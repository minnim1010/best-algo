package com.ssafy.bestalgo.code.entity;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CodeType {
    BEST("best"),
    NOVEL("novel"),
    GOOD("good"),
    CAN_BE_BETTER("can be better");

    private static final Map<String, CodeType> convertor =
            Arrays.stream(CodeType.values()).collect(Collectors.toMap(CodeType::getName, type -> type));
    private final String name;

    CodeType(String name) {
        this.name = name;
    }

    public static boolean exists(String name) {
        return convertor.containsKey(name);
    }

    public static CodeType get(String name) {
        return convertor.get(name);
    }

    public String getName() {
        return name;
    }
}
