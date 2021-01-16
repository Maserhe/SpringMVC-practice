<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2021/1/15
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>

    <script src="${pageContext.request.contextPath}/static/jquery-3.5.1.js"></script>

    <script>
      function a() {
        $.post({
          url:"${pageContext.request.contextPath}/a",
          data:{"name":$("#username").val()},
          success: function (data) {
            alert(data)
          }
        })
      }
    </script>
  </head>
  <body>

  <%--失去焦点的 时候， 发起一个请求到后台--%>
  <input type="text" id="username" onblur="a()">


  </body>
</html>
