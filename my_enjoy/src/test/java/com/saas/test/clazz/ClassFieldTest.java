package com.saas.test.clazz;

public class ClassFieldTest {
    public static void main(String[] args){
        Integer i1=new Integer(1);
        Integer i2=new Integer(1);
        Integer i3=128;
        Integer i4=128;
        System.out.println(i1 == i2);
        System.out.println(i1.intValue() == i2.intValue());

        System.out.println(i3 == i4);
        System.out.println(i1.getClass());
        System.out.println(i3.getClass());
    }


    private static void testA() {
        Integer i=1;
        ClassA a1 = new ClassA(i);
        a1.test();
        ClassA a2 = new ClassA(i);
        a2.test();
        i=3;
        a1.setId(i);
        a1.test();
        a2.test();
//        ClassA.setNextId(3);
//        ClassA a3 = new ClassA(3);
//        a3.test();
//        ClassA a4 = new ClassA(4);
//        a4.test();
//        ClassA.setNextId(5);
//        ClassA a5 = new ClassA(5);
//        a5.test();
//        ClassA a6 = new ClassA(6);
//        a6.test();
//        a1.test();
//        a2.test();
//        ClassA.setNextId(7);
//        a3.test();
//        a4.test();
//        ClassA.setNextId(9);
//        a5.test();
//        a6.test();
    }
}

class ClassA {
    private static Integer nextId = 1;
    private Integer id;

    public ClassA(Integer id) {
        this.id = id;
    }

    public ClassA() {
    }

    public void test() {
        System.out.println(String.format("%s + %s = %s", id, nextId, (id + nextId)));
    }

    public static Integer getNextId() {
        return nextId;
    }

    public static void setNextId(Integer nextId) {
        ClassA.nextId = nextId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

class B{
    public void test(){}
}

class C extends B{
    public void test(){}
}