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
import java.sql.ResultSet;

//@WebServlet("/message")
public class Message extends HttpServlet {
    DbDao mysql = new DbDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection conn = mysql.getConnection();
        ResultSet rs = mysql.resultSet(conn);
        String json = mysql.resultSetToJson(rs);
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println(json);
    }
}
