package com.bberzhou.servlet;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/6/2021
 * Create By Intellij IDEA
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取要下载的文件名
        String downloadFileName  = "2.png";
        //  2、读取要下载的文件内容（通过ServletContext对象可以读取）
        ServletContext servletContext = getServletContext();
        /**
         *  / 斜杠被服务器解析表示地址为 http://ip:port/工程名/ 映射到代码的web目录
         *  提供下载的服务时，服务器作为输入流上传数据，然后响应客户端的时候就使用输出流输出数据
         */
        //  4、在回传前，通过响应头告诉客户端返回的数据类型
            //  获取要下载的文件类型，并传入到响应内容类型
        String mimeType = servletContext.getMimeType("/picFile/" + downloadFileName);
        System.out.println("下载的类型是："+mimeType);
        resp.setContentType(mimeType);

        //  5、还要告诉客户端收到的数据是用于下载使用（还是使用响应头）
                //  Content-Disposition响应头，表示收到的数据怎么处理
                //  attachment表示附件，表示下载使用
                //  fileName 指定要下载的文件名，并且可以设置这个fileName的值来指定下载时候的名字
                //  URL编码是把汉字转换成为 %xx%xx的格式

        //  resp.setHeader("Content-Disposition","attachment;fileName=222.png"); 下载的时候就是 222.png，可以跟源文件名不同

        //  如果将下载的文件名更改为中文的文件名，那么下载的时候请求头里面会出现乱码的情况， Content-Disposition: attachment;fileName=??.png
        //  就需要对中文进行 url 编码，才能进行传输操作。
        // resp.setHeader("Content-Disposition","attachment;fileName=中国.png");

        //  经过URL 编码之后 Content-Disposition: attachment;fileName=%E4%B8%AD%E5%9B%BD.jpg

        //  针对chrome和IE
        // resp.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode("中国.png","UTF-8"));

        //  针对火狐浏览器
        resp.setHeader("Content-Disposition","attachment;fileName=?UTF-8?B?"+new BASE64Encoder().encode("中国.png".getBytes(StandardCharsets.UTF_8)));

        InputStream resourceAsStream = servletContext.getResourceAsStream("/picFile/"+downloadFileName);
        // byte[] buffer = new byte[1024];

        //  获取响应的输出流
        ServletOutputStream outputStream = resp.getOutputStream();

        //  3、把下载的文件内容回传给客户端
        //  使用 io 工具类里面的数据，将输入的数据复制到输出的数据
        //  读取输入流中全部的数据，复制给输出流，输出给客户端
        IOUtils.copy(resourceAsStream,outputStream);



    }
}
