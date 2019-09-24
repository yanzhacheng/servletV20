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
//
//@WebServlet("/login")
public class Admin extends HttpServlet {
    public static String adminName;
    public static String adminPassword;
    DbDao mysql = new DbDao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //获得表单提交的数据
        adminName=request.getParameter("adminName");
        adminPassword=request.getParameter("adminPassword");
        System.out.println(adminName);
        System.out.println(adminPassword);
        System.out.println("得到request请求参数成功");
        Connection conn = mysql.getConnection();

        boolean priv;
        priv = mysql.check(conn, adminName, adminPassword);
        System.out.println(priv);
        PrintWriter out = response.getWriter();
//        如果成功弹出登陆成功并跳到下一个页面
        if(priv == true ){
            out.println("<script>alert('登陆成功!');window.location.href='admin.jsp';</script>");
        }
//        如果失败弹出登陆失败
        else{
            out.println("<script>alert('登陆失败');window.location.href='admin.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
