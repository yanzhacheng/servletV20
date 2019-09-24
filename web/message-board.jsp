<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>留言板</title>
</head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
    $(function(){
        $.ajax({
            type : "get",
            url : "message",
            dataType : "json",
            success: function (jsonData) {
                document.write("<a href=\"SubMessage.jsp\">提交留言   </a>");
                document.write("<a href=\"login.jsp\">退出</a>");
                jsonData = JSON.stringify(jsonData);
                console.log(jsonData);
                jsonData = eval("(" + jsonData + ")");
                console.log(jsonData);
                show(jsonData);
            }
        })
    });


    function show(jsonData) {
        for (var i in jsonData) {
            if(jsonData[i]["Visable"] == 1){
                document.write("<li>");
                document.write("</li>");
                document.write("用户ID:  " + jsonData[i]["UserName"]+"<br/>");
                document.write("留言时间:  " + jsonData[i]["SubTime"] + "<br/>");
                document.write("留言内容:  " + jsonData[i]["Details"] + "<br/>");
                document.write("</li>");
                document.write("</li>");
            }
        }
    }
</script>
<body>
</body>
</html>
















