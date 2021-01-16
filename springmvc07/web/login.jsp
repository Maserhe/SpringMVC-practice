<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <script src="${pageContext.request.contextPath}/static/jquery-3.5.1.js"></script>
    </head>
    <body>

        用户名: <input type="text" onblur="identity()" id="username">
        密码: <input type="text" >

    </body>

<script>+

    function identity(){
        $.post({
            url:"${pageContext.request.contextPath}/login",
            data:{username: $("#username").val()},
            success: function (data){
                if (data != "") alert(data);
            }
        });
    }

</script>

</html>