package com.bberzhou.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description: 这个工具类，用来将请求参数封装到Bean中
 * @author: bberzhou@gmail.com
 * @date: 8/7/2021
 * Create By Intellij IDEA
 */
public class WebUtils {
    //  这样写有一个缺点，就是耦合性太高了，依赖性太强了， web层耦合度过高   版本2
    //  因为要传入的参数是HttpServletRequest类型的参数，
    // public static void copyParamToBean(HttpServletRequest request,Object bean){
    //     try {
    //         System.out.println("注入之前："+bean);
    //         /**
    //          *  把所有请求的参数都注入到user对象中
    //          */
    //         BeanUtils.populate(bean,request.getParameterMap());
    //         System.out.println("注入之后："+bean);
    //     }catch (Exception e){
    //         e.printStackTrace();
    //     }
    //
    // }

    //  对copyParamToBean 做改进            版本3

    // /**
    //  *  把Map中的值注入到对应的JavaBean属性中，这里不局限于HttpServletRequest的请求参数
    //  * @param value
    //  * @param bean
    //  */
    // public static Object copyParamToBean(Map value, Object bean){
    //     try {
    //         System.out.println("注入之前："+bean);
    //         /**
    //          *  把所有请求的参数都注入到user对象中
    //          */
    //         BeanUtils.populate(bean,value);
    //         System.out.println("注入之后："+bean);
    //     }catch (Exception e){
    //         e.printStackTrace();
    //     }
    //     //  参数注入完成之后，将JavaBean返回
    //     return bean;
    // }

    //  对版本3改进，增加泛型自动转换对应的返回类型

    /**
     *  泛型方法 ，将返回值类型自动转换
     * @param value
     * @param bean
     * @param <T>
     * @return
     */
    public static <T>  T copyParamToBean(Map value, T bean){
        try {
            System.out.println("注入之前："+bean);
            /**
             *  把所有请求的参数都注入到user对象中
             */
            BeanUtils.populate(bean,value);
            System.out.println("注入之后："+bean);
        }catch (Exception e){
            e.printStackTrace();
        }
        //  参数注入完成之后，将JavaBean返回
        return bean;
    }


    /**
     *  此静态方法主要是负责将字符串转换成为int类型的数据
     * @param strInt    要转换的字符串
     * @param defaultValue  默认值
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){
        if (strInt != null){
            return Integer.parseInt(strInt);
        }
        return defaultValue;
    }
}
