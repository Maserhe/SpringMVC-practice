<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script src="${pageContext.request.contextPath}/static/jquery-3.5.1.js"></script>

</head>

<body>
    <input type="button" value="加载数据" id="btn">
    <table>
        <tr>
            <td>姓名</td>
            <td>年龄</td>
            <td>性别</td>
        </tr>

        <tbody id="content"></tbody>
    </table>

</body>
<script>

    $("#btn").click(function (){

        $.post("${pageContext.request.contextPath}/user", function (data){
            var html = "";

            for (let i = 0; i < data.length; i ++) {
                html += "<tr>" +
                    "<td>" + data[i].name + "</td>" +
                    "<td>" + data[i].age + "</td>" +
                    "<td>" + data[i].sex + "</td>" + "</tr>";
            }

            $("#content").html(html);
        });
    });


</script>

</html>

