package com.saas.system.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor
public enum NetTypeEnum {
    ALL("所有网络","A")
    ,MOBILE_NETWORK("移动网络","M")
    ,FIXED_NETWORK("固网","W")
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
