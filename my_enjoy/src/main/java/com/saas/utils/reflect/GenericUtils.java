package com.saas.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型工具类
 * author dingpengfei
 * date    2017/9/7 上午9:45:54
 */
public class GenericUtils {
    /**
     * 功能:通过反射,获得指定类的父类的泛型参数的实际类型
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @param index 泛型参数所在索引,从0开始
     * @return 泛型参数的实际参数, 如果没有实现ParameterizedType接口, 即不支持泛型,
     * 所以直接返回Object.class
     * 2013-4-8 上午9:47:32
     */
    public static Class<?> getSuperClassGenericType(Class<?> clazz, int index) {
        /* 得到泛型父类*/
        Type genType = clazz.getGenericSuperclass();
        /* 如果没有实现ParameterizedTyper接口,即不支持泛型,直接返回Object.class*/
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class<?>) params[index];
    }

    /**
     * 功能:通过反射,获取指定类的父类的第一个泛型参数的实际类型
     *
     * @param clazz clazz 需要反射的类,该类必须继承泛型父类
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口, 即不支持泛型,
     * 所以直接返回Object.class
     * 2013-4-8 上午10:09:15
     */
    public static Class<?> getSuperClassGenericType(Class<?> clazz) {
        return getSuperClassGenericType(clazz, 0);
    }

    /**
     * 功能:通过反射,获得方法返回值泛型参数的实际类型
     *
     * @param method 方法
     * @param index  泛型参数所在索引,从0开始
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口, 即不支持泛型,
     * 所以直接返回Object.class
     * 2013-4-8 上午10:26:24
     */
    public static Class<?> getMethodGenericReturnType(Method method, int index) {
        Type returnType = method.getGenericReturnType();
        if (returnType instanceof ParameterizedType) {
            Type[] typeArguments = ((ParameterizedType) returnType).getActualTypeArguments();
            if (index >= typeArguments.length || index < 0) {
                throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
            }
            if (!(typeArguments[index] instanceof Class)) {
                return Object.class;
            }
            return (Class<?>) typeArguments[index];
        }
        return Object.class;
    }

    /**
     * 功能:通过反射,获得方法返回值第一个泛型参数的实际类型
     *
     * @param method 方法
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口,
     * 即不支持泛型,所以直接返回Object.class
     * 2013-4-8 上午11:00:07
     */
    public static Class<?> getMethodGenericReturnType(Method method) {
        return getMethodGenericReturnType(method, 0);
    }

    /**
     * 功能:通过反射,获得方法输入参数第index个输入参数的所有泛型参数的实际类型
     *
     * @param method 方法
     * @param index  第几个输入参数
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口,
     * 即不支持泛型,所以直接返回空集合
     * 2013-4-8 上午11:19:31
     */
    public static List<Class<?>> getMethodGenericParameterTypes(Method method, int index) {

        List<Class<?>> results = new ArrayList<>();
        Type[] genericParamTypes = method.getGenericParameterTypes();
        if (index >= genericParamTypes.length || index < 0) {
            throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
        }
        Type genericParamType = genericParamTypes[index];
        if (genericParamType instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) genericParamType;
            Type[] paramArgTypes = paramType.getActualTypeArguments();
            Class<?> temp;
            for (Type paramArgType : paramArgTypes) {
                if (!(paramArgType instanceof Class)) {
                    temp = Object.class;
                } else {
                    temp = (Class<?>) paramArgType;
                }
                results.add(temp);
            }
        }
        return results;
    }

    /**
     * 功能:通过反射,获得方法输入参数第1个输入参数的所有泛型参数的实际类型
     * @param method 方法
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口,
     * 即不支持泛型,所以直接返回空集合
     * 2013-4-8 上午11:19:31
     */
    public static List<Class<?>> getMethodGenericParameterTypes(Method method) {
        return getMethodGenericParameterTypes(method, 0);
    }

    /**
     * 功能:通过反射,获得Field泛型参数的实际类型
     *
     * @param field 字段
     * @param index 泛型参数所在索引,从0开始
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口, 即不支持泛型,
     * 所以返回Object.class
     * 2013-4-8 上午11:52:21
     */
    public static Class<?> getFieldGenericType(Field field, int index) {

        Type genericFieldType = field.getGenericType();
        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) genericFieldType;
            Type[] paramArgTypes = paramType.getActualTypeArguments();
            if (index >= paramArgTypes.length || index < 0) {
                throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
            }
            if (!(paramArgTypes[index] instanceof Class)) {
                return Object.class;
            }
            return (Class<?>) paramArgTypes[index];
        }
        return Object.class;
    }

    /**
     * 功能:通过反射,获得Field第一个泛型参数的实际类型
     * @param field 字段
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口, 即不支持泛型,
     * 所以返回Object.class
     * 2013-4-8 上午11:52:21
     */
    public static Class<?> getFieldGenericType(Field field) {
        return getFieldGenericType(field, 0);
    }
}
