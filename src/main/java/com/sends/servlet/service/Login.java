package com.sends.servlet.service;

import com.sends.servlet.dao.DbDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

//@WebServlet("/login")
public class Login extends HttpServlet {
    public static String username;
    public static String password;
    DbDao mysql = new DbDao();

    public static String getUsername() {
        return username;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        //获得表单提交的数据
        username=request.getParameter("user");
        password=request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        System.out.println("得到request请求参数成功");
        Connection conn = mysql.getConnection();

        boolean priv1;
        priv1 = mysql.checkUser(conn,username,password);
        System.out.println(priv1);
        PrintWriter out = response.getWriter();
//        如果成功弹出登陆成功并跳到下一个页面
        if(priv1 == true){

            // 创建cookie
            Cookie userCookie = new Cookie("username", username);
            Cookie passCookie =new Cookie("password",password);
            userCookie.setMaxAge(60); //时间
            passCookie.setMaxAge(60);
            response.addCookie(userCookie); // 服务器返回给浏览器cookie
            response.addCookie(passCookie);

            out.println("<script>alert('登陆成功!');window.location.href='SubMessage.jsp';</script>");
        }
//        如果失败弹出登陆失败并跳到下一个页面
        else{
            out.println("<script>alert('登陆失败');window.location.href='login.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
