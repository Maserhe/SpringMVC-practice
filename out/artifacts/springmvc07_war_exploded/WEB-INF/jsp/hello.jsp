<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2021/1/15
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script>
        function go() {
            var url = document.getElementById("url").value;
            document.getElementById("iframe").src=url;
        }
    </script>

    <title>Title</title>
</head>
<body>

    <div>
        <p>输入地址</p>
        <p>
            <input type="text" id="url">
            <input type="button" value="提交" onclick="go()">
        </p>
    </div>


    <div>
        <iframe id="iframe" style="width: 100%;height: 500px"></iframe>
    </div>

</body>
</html>
