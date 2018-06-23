package com.saas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "text")
@PropertySource(value = "classpath:application.yml")
public class A {

    private  String name;
    {
        System.out.println("--------5--------");
    }
    {
        System.out.println("--------5--------");
    }
    static {

            System.out.println("--------6--------");

    }
    static {

        System.out.println("--------6--------");

    }
    public A(String name) {
        System.out.println("--------1--------");
        this.name = name;
        System.out.println("--------2--------");
    }

    public A() {
        this.name="";
        System.out.println("--------0-------");
    }

    public String getName() {
        System.out.println("----3-----");
        return name;
    }

    @Value("${test.name}")
    public void setName(String name) {
        System.out.println("----4-----");
        this.name = name;
    }
    public static int t(){
//        this
        return 0;
    }
}
