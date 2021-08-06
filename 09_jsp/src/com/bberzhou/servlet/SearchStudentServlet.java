package com.bberzhou.servlet;

import com.bberzhou.pojo.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @description:    JSP请求转发的使用
 * @author: bberzhou@gmail.com
 * @date: 8/5/2021
 * Create By Intellij IDEA
 */
public class SearchStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数

        //  2、发送sql语句查询学生的信息
        //  使用for循环查询到的数据做模拟
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i+1,"name"+i, 15+i,"phone"+i));
        }
        //  3、保存查询到的结果（学生信息），保存到request 域对象中，
        req.setAttribute("stuList",students);
        //  4、请求转发到showStudent.jsp 页面，因为请求转发是一次请求，所以可以得到request域中的数据
        req.getRequestDispatcher("/showStudent.jsp").forward(req,resp);
    }
}
