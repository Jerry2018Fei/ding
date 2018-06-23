package com.saas;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Arrays;

public class DemoTest {
    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
        System.out.println(int[].class.getName());
    }
    private static void test2(){
        int n=40;
        int[] numbers=new int[n];
        for(int i=0;i<numbers.length;i++){
            numbers[i]=i+1;
        }
        int k=6;
        int result[]=new int[k];
        for(int i=0;i<result.length;i++){
            int r= (int) (Math.random()*n);
            result[i]=numbers[r];
            numbers[r]=numbers[n-1];
            n--;
        }
        System.out.println(Arrays.toString(result));
        Arrays.sort(result);
        System.out.println(Arrays.toString(result));
    }
    private static void test1() {
        int i=0;
        hello:
        while(true){
            if(i==200) break ;
            i++;
            for(int k=10;k>0;k--){
                for(int j=10;j>0;j--){
                    if(j==5){
                        System.out.println("======================");
                        break hello;
                    }else {
                        System.out.println(i+":"+k+":"+j);

                    }
                }
            }

        }
    }

}

