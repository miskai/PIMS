<%@ page import="java.util.ArrayList" %>
<%@ page import="loginRegister.LoginBean" %><%--
  Created by IntelliJ IDEA.
  User: misaki
  Date: 2017/12/20
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>个人信息管理系统————主页面</title>
</head>
<%
    String userName=null;
    //获取在loginservlet.java中保存在session对象中的数据
    ArrayList login=(ArrayList)session.getAttribute("login");
    if(login==null||login.size()==0){
        response.sendRedirect("../login.jsp");
    }else{
        for(int i=login.size()-1;i>=0;i--){
            LoginBean nn=(LoginBean)login.get(i);
            userName=nn.getUserName();
        }
    }
%>

    <frameset rows="20%,50px,*">
        <frame src="top.jsp" name="top" scrolling="no" >
        <frame src="middle.jsp?userName=<%=userName%>" name="toop" scrolling="no">
        <frame src="bottom.jsp" name="main">
    </frameset>
</html>
