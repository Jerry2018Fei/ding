package com.saas.utils.math;

import java.math.BigDecimal;

/**
 * Auth: dingpengfei
 * Date: 2017/10/23 10:29
 * Title:
 * Describer:
 **/
public class Maths {
    private static final Long DEFAULT_MULTIPLY=1000L;
    private static final Integer DEFAULT_SCALE=3;
    public static BigDecimal getBigDecimal(double price,Long number){
        BigDecimal priceBigDecimal=new BigDecimal(price).multiply(new BigDecimal(DEFAULT_MULTIPLY));
        return priceBigDecimal.multiply(new BigDecimal(number)).divide(new BigDecimal(DEFAULT_MULTIPLY),DEFAULT_SCALE,BigDecimal.ROUND_HALF_UP);
    }

    public static Double addCost(Double... prices){
        if(prices.length==0) return 0.0;
        BigDecimal priceBigDecimal=new BigDecimal(0);
        for(Double price:prices){
            priceBigDecimal=priceBigDecimal.add(new BigDecimal(price));
        }
        return priceBigDecimal.setScale(DEFAULT_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static Double getCost(double price, Long number){
        return getBigDecimal(price, number).doubleValue();
    }
    public static Boolean checkBalance(double price,double balance){
        return new BigDecimal(price).doubleValue()>new BigDecimal(balance).doubleValue();
    }
    public static void main(String[] args) {
//        BigDecimal price =getCost(0.002,2134L);
        System.out.println(addCost(1.009,2.770));
    }
}
