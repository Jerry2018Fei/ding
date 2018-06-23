package com.saas.ding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LotterySimpleResult {
    private String id;
    private String name;
    private String result;
    private Boolean status;

    public LotterySimpleResult(String id, String name, Boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
