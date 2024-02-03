package com.ssafy.bestalgo.code.entity;

public enum CodeType {
    BEST("BEST"),
    NOVEL("NOVEL"),
    GOOD("GOOD"),
    CAN_BE_BETTER("CAN_BE_BETTER");

    private final String name;

    CodeType(String name) {
        this.name = name;
    }
}
