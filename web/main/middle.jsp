<%--
  Created by IntelliJ IDEA.
  User: misaki
  Date: 2017/12/20
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>midlle页面</title>
</head>
<body style="height: 30px;background-color: #E9EBE9">
<%
    String userName=request.getParameter("userName");
%>
<table width="100%"  align="right" bgcolor="black">
<tr height="20" bgcolor="#E9EBE9" align="center">
    <td>
        <a href="http://localhost:8080/LookMessageServlet?userName=<%=userName%>" target="main">个人信息管理</a>
    </td>
    <td>
        <a href="http://localhost:8080/friendManager/lookFriend.jsp" target="main">通讯管理器</a>
    </td>
    <td>
        <a href="http://localhost:8080/LookDateServlet" target="main">日程管理器</a>
    </td>
    <td>
        <a href="http://localhost:8080/index2_bak.jsp" target="main">个人文件管理器</a>
    </td>
    <td>
        <a href="http://localhost:8080/login.jsp" target="_top">退出系统</a>
    </td>
    <td>欢迎，<%=userName%>登录系统</td>
</tr>
</table>
</body>
</html>
