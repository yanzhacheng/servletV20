package com.sends.servlet.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbDao {
    static public Connection conn;

    //连接数据库
    static public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            return null;
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "0032");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("连接数据库成功");
        return conn;
    }

    //检查是否为管理员 返回值true/false
    static public boolean check(Connection conn, String name, String password) {
        try {
            Statement stmt = conn.createStatement();
            String sqlForSelect = "SELECT *  from admin where AdminName='" + name + "'";
            ResultSet rs = stmt.executeQuery(sqlForSelect);
            while (rs.next()) {
                if (rs.getString("AdminPassword").equals(password)) {
                    System.out.println(rs.getString("AdminPassword"));
                    return true;
                }
            }
        }
        catch (SQLException e) {
            return false;
        }
        return false;
    }

    //检查是否为用户 返回值true/false
    static public boolean checkUser(Connection conn, String name, String password) {
        try {
            Statement stmt = conn.createStatement();
            String sqlForSelect = "SELECT *  from user where UserName='" + name + "'";
            ResultSet rs = stmt.executeQuery(sqlForSelect);
            while (rs.next()) {
                if (rs.getString("UserPassword").equals(password)) {
                    System.out.println(rs.getString("UserPassword"));
                    return true;
                }
            }
        }
        catch (SQLException e) {
            return false;
        }
        return false;
    }


    //获取ResultSet
    public static ResultSet resultSet(Connection conn) {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            String sqlForSelect = "SELECT *  from message";
            rs = stmt.executeQuery(sqlForSelect);
        }
        catch (SQLException e) {
        }
        return rs;
    }


    //ResultSet转json
    public static String resultSetToJson(ResultSet rs) {
        String json = null;
        json = "[";
        try {
            while (rs.next()) {
                json = json + "{\"Num\":\"";
                json = json + rs.getString("Num");
                json = json + "\",\"UserName\":\"";
                json = json + rs.getString("UserName");
                json = json + "\",\"Details\":\"";
                json = json + rs.getString("Details");
                json = json + "\",\"SubTime\":\"";
                json = json + rs.getString("SubTime");
                json = json + "\",\"Visable\":\"";
                json = json + rs.getString("Visable");
                json = json + "\"},";
            }
        }
        catch (SQLException e) {
        }
        json = json.substring(0, json.length() - 1);
        json = json + "]";
        System.out.println(json);
        return json;
    }


    //获得当前时间
    public static String time() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        return df.format(new Date());
    }


    //提交留言
    public static boolean subMessage(Connection conn, String UserName, String Details, String Time){
        try {
            Statement sta = conn.createStatement(); // 创建语句对象
            // 执行SQL语句
            String strSql = "SELECT MAX(CAST(NUM AS SIGNED INTEGER )) FROM message";
            // 获取最大值
            ResultSet rs = sta.executeQuery(strSql);
            // 使结果集光标向前移动一行
            rs.next();
            // 输出结果结果中的第一个元素
            String Num = Long.toString(rs.getLong(1) + 1);

            PreparedStatement pst = conn.prepareStatement("INSERT INTO message VALUES ( ?,?,?,?,'1')");
            pst.setString(1, Num);
            pst.setString(2, UserName);
            pst.setString(3, Details);
            pst.setString(4, Time);
            pst.executeUpdate();
            System.out.println("添加留言成功");
            sta.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //    修改留言姓名
    public static boolean auditingUserName(Connection conn, String Num, String UserName){
        try {
            PreparedStatement pst = conn.prepareStatement("update message set UserName = ? where Num = ?");
            pst.setString(1, UserName);
            pst.setString(2, Num);
            pst.executeUpdate();
            pst.close();
            // 关闭所有已经打开的资源;
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }


    //    修改留言内容
    public static boolean auditingDetails(Connection conn, String Num, String Details){
        try {
            PreparedStatement pst = conn.prepareStatement("update message set Details = ? where Num = ?");
            pst.setString(1, Details);
            pst.setString(2, Num);
            pst.executeUpdate();
            pst.close();
            // 关闭所有已经打开的资源;
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }


    //    修改留言可见性
    public static boolean auditingVisable(Connection conn, String Num, String Visable){
        try {
            PreparedStatement pst = conn.prepareStatement("update message set Visable = ? where Num = ?");
            pst.setString(1, Visable);
            pst.setString(2, Num);
            pst.executeUpdate();
            pst.close();
            // 关闭所有已经打开的资源;
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }


    public static boolean registerUser(Connection conn,String UserName,String UserPassword){
        try {
            if (UserName.length()>12 || UserName.length()<1 || UserPassword.length()>12 || UserPassword.length()<6){
                return false;
            }
            Statement sta = conn.createStatement(); // 创建语句对象
            // 执行SQL语句
            String strSql = "SELECT MAX(id) FROM user";
            // 获取最大值
            ResultSet rs = sta.executeQuery(strSql);
            // 使结果集光标向前移动一行
            rs.next();
            // 输出结果结果中的第一个元素
            String id = Long.toString(rs.getLong(1) + 1);

            PreparedStatement pst = conn.prepareStatement("INSERT INTO user VALUES ( ?,?,? )");
            pst.setString(1, id);
            pst.setString(2, UserName);
            pst.setString(3, UserPassword);
            pst.executeUpdate();
            System.out.println("注册成功");
            sta.close();
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }


//    测试main函数
//    public static void main(String args[]){
//        Connection conn = getConnection();
//        ResultSet rs = resultSet(conn);
//        resultSetToJson(rs);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
////          Connection conn = getConnection();
//          String UserName = "asd";
//          String Num = "1";
//          try{
//              PreparedStatement pst = conn.prepareStatement("update message set UserName = ? where Num = ?");
//              pst.setString(1, UserName);
//              pst.setString(2, Num);
//              pst.executeUpdate();
//              System.out.println("添加留言成功  等待审核");
//          }
//          catch (SQLException e){
//              System.out.println("failed");
//          }
//    }
}
