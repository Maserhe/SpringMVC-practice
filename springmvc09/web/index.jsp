<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2021/1/16
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <h1>上传文件</h1>
    <%--二进制文件流的形式进行上传文件--%>
    <form enctype="multipart/form-data" method="post" action="/upload">
      <input type="file" name="file">
      <input type="submit" value="上传">
    </form>

  </body>
</html>
