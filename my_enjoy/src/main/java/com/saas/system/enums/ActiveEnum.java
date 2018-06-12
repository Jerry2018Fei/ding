package com.saas.system.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor
public enum ActiveEnum {
    ACTIVE("活跃","H"),
    NO_ACTIVE("不活跃","NH")
    ;
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
