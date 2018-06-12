package com.saas;

public class DemoTest {
    public static void main(String[] args) {
        Long count=33*32*31*30*29*28*16L;
        System.out.println(count);
        System.out.println(System.currentTimeMillis()/count);
        System.out.println(System.currentTimeMillis()% count);
    }
}
