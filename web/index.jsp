<%--
  Created by IntelliJ IDEA.
  User: 24304
  Date: 2019/8/4
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <p>管理员登陆面板</p>
            <form id="form_sub_a" action="<%=request.getContextPath()%>/admin" method="post">
                <p>账号:&nbsp;&nbsp;<input type="text" name="adminName" /></p>
                <p>密码:&nbsp;&nbsp;<input type="password" name="adminPassword" /></p>
                <input type="submit" value="登陆" />
                <input type="reset" value="取消" />
            </form>
            <a href="message-board.jsp">留言板</a>
            <a href="register.jsp">注册</a>
            <a href="login.jsp">用户登录</a>
        </div>
    </div>
</div>

</body>
</html>
