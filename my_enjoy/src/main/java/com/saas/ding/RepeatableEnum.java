package com.saas.ding;

/**
 * 重复性enum
 */
public enum RepeatableEnum {
    REPEAT("可重复的",1),
    NO_REPEAT("不可以重复的",0)
    ;

    private String name;
    private Integer value;

    RepeatableEnum(String name, Integer value) {
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
