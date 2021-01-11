package com.example.samplebatch.enums;

public enum BatchResult {

    //@formatter:off
    SUCCESS(0),
    FAILURE(1),
    ;
    //@formatter:on

    private int code;

    private BatchResult(int code) {

        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
