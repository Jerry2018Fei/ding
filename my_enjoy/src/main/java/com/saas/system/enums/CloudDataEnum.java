package com.saas.system.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CloudDataEnum {
    IMEI_DATA("IEMI","IEMI")
    ,IDFA_DATA("IDFA","IDFA")
    ,MAC_DATA("MAC","MAC")
    ,MDN_DATA("MDN","MDN")
    ,LABEL_DATA("LABEL","LABEL")
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
