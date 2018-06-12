package com.saas.ding;

/**
 * 是否分割前后区enum
 */
public enum SplitEnum {
    YES("是",1),
    NO("否",0)
    ;

    private String name;
    private Integer value;

    SplitEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
