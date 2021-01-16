<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

</head>
<body>

    <form action="${pageContext.request.contextPath}/login">
        用户名: <input type="text" name="username">
        密码: <input type="text" name="pwd">
        <input type="submit" value="提交">
    </form>

</body>

</html>