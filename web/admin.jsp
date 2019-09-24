<%--
  Created by IntelliJ IDEA.
  User: 10938
  Date: 2018/8/25
  Time: 2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>留言板后台管理</title>
</head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
    $(function(){
        $.ajax({
            type : "get",
            url : "message",
            dataType : "json",
            success: function (jsonData) {
                jsonData = JSON.stringify(jsonData);
                console.log(jsonData);
                jsonData = eval("(" + jsonData + ")");
                console.log(jsonData);
                show(jsonData);
            }
        })
    });


    function show(jsonData) {
        document.write("<p>0为不可见 1为可见</p>\n" );
        for (var i in jsonData) {
            document.write("<form id=\"form_sub_a\" action=\"<%=request.getContextPath()%>/auditing\" method=\"post\">");
            document.write("<li>");
            document.write("<p>序号:&nbsp;&nbsp;\n" +
                "        <input type=\"text\" name=\"num\" value=\"" + jsonData[i]["Num"] + "\">    </p>");
            document.write("<p>留言用户名:&nbsp;&nbsp;\n" +
                "        <input type=\"text\" name=\"user\" value=\"" + jsonData[i]["UserName"] + "\">    </p>");
            document.write("<p>留言内容:&nbsp;&nbsp;\n" +
                "        <input type=\"text\" name=\"details\" value=\"" + jsonData[i]["Details"] + "\">    </p>");
            document.write("<p>留言时间:&nbsp;&nbsp;\n" + jsonData[i]["SubTime"] + "</p>");
            document.write("<p>可见性:&nbsp;&nbsp;\n" +
                "        <input type=\"text\" name=\"visable\" value=\"" + jsonData[i]["Visable"] + "\">    </p>");
            document.write("    <input type=\"submit\" value=\"修改\" />")
            document.write("</li>");
            document.write("</form>")
//            form记得结束
        }
    }
</script>
<body>
</body>
</html>