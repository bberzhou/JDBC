package com.bberzhou.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/6/2021
 * Create By Intellij IDEA
 */
public class Base64Test {
    public static void main(String[] args) throws IOException {

        String base = "这是base64编码设置";
        //  创建一个Base64编码器
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //  执行Base64编码操作
        String encode = base64Encoder.encode(base.getBytes(StandardCharsets.UTF_8));
        System.out.println("编码为："+encode); //  6L+Z5pivYmFzZTY057yW56CB6K6+572u

        //  创建一个Base64解码器
        BASE64Decoder base64Decoder = new BASE64Decoder();
        //  解码操作，解码成为一个字符数组
        byte[] bytes = base64Decoder.decodeBuffer(encode);

        //  字符数组还原成为一个字符串
        String str = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("解码为："+str);
    }
}
