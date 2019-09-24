package com.sends.servlet.service;

import com.sends.servlet.dao.DbDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

//@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
    DbDao mysql = new DbDao();
    public static String username;
    public static String details;
    public static String time;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //获得表单提交的数据

        Cookie[] cookies=request.getCookies();
        for (int i=0;i<cookies.length;i++){
            if (cookies[i].getName().compareTo("username")==0)username=cookies[i].getValue();
        }


        details = request.getParameter("details");
        System.out.println(username);
        System.out.println(details);
        System.out.println("得到request请求参数成功");
        Connection conn = mysql.getConnection();
        time = mysql.time();

        boolean subMessage;
        subMessage = mysql.subMessage(conn, username, details, time);
        System.out.println(username);
        System.out.println(subMessage);
        PrintWriter out = response.getWriter();
//        如果成功,跳到留言板
        if(subMessage == true){
            out.println("<script>alert('留言成功!');window.location.href='message-board.jsp';</script>");
        }
//        如果失败弹出失败，返回原界面
        else{
            out.println("<script>alert('留言失败');window.location.href='SubMessage.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
