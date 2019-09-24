package com.sends.servlet.service;

import com.sends.servlet.dao.DbDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

//@WebServlet("/register")
public class Register extends HttpServlet {
    public  String username;
    public  String password;
    DbDao mysql = new DbDao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //获得表单提交的数据
        username=request.getParameter("username");
        password=request.getParameter("userPassword");
        System.out.println(username);
        System.out.println(password);
        System.out.println("得到request请求参数成功");
        Connection conn = mysql.getConnection();

        boolean register;
        register = mysql.registerUser(conn, username, password);
        System.out.println(register);
        PrintWriter out = response.getWriter();
//        如果成功,弹出登陆成功,跳到下一个页面
        if(register == true){
            out.println("<script>alert('注册成功');window.location.href='login.jsp';</script>");
        }
//        如果失败,返回原页面
        else{
            out.println("<script>alert('注册失败');window.location.href='register.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}