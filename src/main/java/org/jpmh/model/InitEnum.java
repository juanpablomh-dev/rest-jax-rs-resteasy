package org.jpmh.model;

public enum InitEnum {
    ALL(0), PERSONS(1);
    private int type;

    InitEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
