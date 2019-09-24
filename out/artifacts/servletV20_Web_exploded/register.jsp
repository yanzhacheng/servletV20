<%--
  Created by IntelliJ IDEA.
  User: 24304
  Date: 2019/7/29
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员登陆</title>
</head>
<body>
<div>
    <div>
        <div>
            <p>注册面板</p>
            <form id="form_sub_a" action="<%=request.getContextPath()%>/register" method="post">
                <p>用户名:&nbsp;&nbsp;<input type="text" name="username" />（用户名允许1-12位）</p>
                <p>密码:&nbsp;&nbsp;<input type="password" name="userPassword" />（密码允许6-12位）</p>
                <input type="submit" value="注册" />
                <input type="reset" value="取消" />
                <input type="submit" value="登陆" />
            </form>

        </div>
    </div>
</div>

</body>
</html>