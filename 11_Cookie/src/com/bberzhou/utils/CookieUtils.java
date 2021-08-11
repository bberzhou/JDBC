package com.bberzhou.utils;

import javax.servlet.http.Cookie;

/**
 * @description:    用于遍历和获取Cookie的工具类方法
 * @author: bberzhou@gmail.com
 * @date: 8/11/2021
 * Create By Intellij IDEA
 */
public class CookieUtils {
    /**
     *  查找指定名称的Cookie对象
     * @param name  需要的Cookie name值
     * @param cookies 获取到的cookies数组
     * @return
     */
    public static Cookie findCookie(String name, Cookie[] cookies){
        //  首先做一个非空判断
        if (name == null || cookies.length == 0 || cookies == null){
            return null;
        }

        //  如果找到了对应的Cooke直接返回
        for (Cookie cookie: cookies){
            if (name.equals(cookie.getName())){
                return  cookie;
            }

        }
        //  如果没找到就直接返回null
        return  null;
    }

}
