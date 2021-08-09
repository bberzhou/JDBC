package com.bberzhou.servlet;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @description: 用来处理上传的文件数据
 * @author: bberzhou@gmail.com
 * @date: 8/6/2021
 * Create By Intellij IDEA
 */
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("文件上传过来了");

        //  因为使用的是流的方式上传数据，所以服务器要先创建一个输入流来获取流入的的数据
        // ServletInputStream inputStream = req.getInputStream();
        // //  使用缓冲流，创建一个字节数组
        // byte[] buffer = new byte[1024000];
        // int read = inputStream.read(buffer);
        // System.out.println(new String(buffer,0,read));

        //  设置字符集，防止乱码

        req.setCharacterEncoding("UTF-8");

        // 1、先判断上传的数据是否是多段数据（只有是多段的数据，才是文件上传的）
        if (ServletFileUpload.isMultipartContent(req)) {
            //  2、创建 FileItemFactory 接口的 实现类对象
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //  3、创建用于解析上传数据的工具类 ServletFileUpload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

            try {
                //  4、解析上传的数据，得到一个表单项集合FileItems
                List<FileItem> fileItems = servletFileUpload.parseRequest(req);
                //  循环判断每一个表单项，是普通类型，还是上传的文件
                for (FileItem fileItem : fileItems) {

                    if (fileItem.isFormField()) {
                        //  普通表单项
                        //  如果是普通的表单就直接获取name属性值和value值
                        System.out.println("表单项的name属性值：" + fileItem.getFieldName());
                        // 使用 getString()方法，传入参数，防止乱码
                        System.out.println("表单项的value值：" + fileItem.getString("UTF-8"));

                    } else {
                        //  上传的文件内容
                        System.out.println("表单项的name属性值：" + fileItem.getFieldName());
                        System.out.println("上传的文件名是：" + fileItem.getName());
                        //  将文件写入到磁盘
                        fileItem.write(new File("D:\\" + fileItem.getName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
