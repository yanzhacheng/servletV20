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

//@WebServlet("/auditing")
public class Auditing extends HttpServlet {
    DbDao mysql = new DbDao();
    public static String num;
    public static String username;
    public static String details;
    public static String visable;
    public static String time;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //获得表单提交的数据
        num = request.getParameter("num");
        username = request.getParameter("user");
        details = request.getParameter("details");
        time = request.getParameter("time");
        visable = request.getParameter("visable");
        System.out.println(num);
        System.out.println(username);
        System.out.println(details);
        System.out.println(time);
        System.out.println(visable);
        System.out.println("得到request请求参数成功");
        Connection conn = mysql.getConnection();


        boolean auditing1 = mysql.auditingUserName(conn, num, username);
        boolean auditing2 = mysql.auditingDetails(conn, num, details);
        boolean auditing3 = mysql.auditingVisable(conn, num, visable);
        PrintWriter out = response.getWriter();
//        提示成功并刷新
        if(auditing1 == true && auditing2 == true && auditing3 == true){
            out.println("<script>alert('修改成功!');window.location.href='admin.jsp';</script>");
        }
//        提示失败刷新
        else{
            out.println("<script>alert('修改失败!');window.location.href='admin.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
