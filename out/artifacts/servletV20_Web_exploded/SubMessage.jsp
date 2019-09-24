<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: 10938
  Date: 2018/8/25
  Time: 5:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>提交留言</title>
</head>
<body>


<form id="form_sub_a" action="<%=request.getContextPath()%>/MyServlet" method="post">
<%--    <p>您的名字:&nbsp;&nbsp;<input type="text" name="user" value=""/></p>--%>
    <p>留言内容:&nbsp;&nbsp;<input type="text" name="details" /></p>
    <input type="submit" value="留言" />
    <input type="reset" value="取消" />
</form>
<a href="message-board.jsp">留言板</a>
</body>
</html>

