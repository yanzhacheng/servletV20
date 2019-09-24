<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: 24304
  Date: 2019/8/5
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登陆</title>
</head>
<body>


<div>
    <div>
        <div>
            <p>用户登陆面板</p>
            <form id="form_sub_a" action="<%=request.getContextPath()%>/login" method="post">
                <p>用户名:&nbsp;&nbsp;<input type="text" name="user" /></p>
                <p>密码:&nbsp;&nbsp;<input type="password" name="password" /></p>
                <input type="submit" value="登陆" />
                <input type="reset" value="取消" />
            </form>
            <a href="message-board.jsp">留言板</a>
            <a href="register.jsp">注册</a>
        </div>
    </div>
</div>

</body>
</html>
