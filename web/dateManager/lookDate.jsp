<%@page import="dateManager.LookDateBean" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息管理系统——查看日程</title>
</head>
<body bgcolor="#E9EBE9">
	<hr noshade>
	<div align="center">
		<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
			<tr>

				<td width="20%"><a href="addDate.jsp">添加日程</a></td>
				<td width="20%">查看日程</td>
				<td width="20%"><a href="updateDate.jsp">修改日程</a></td>
				<td width="20%"><a href="deleteDate.jsp">删除日程</a></td>
			</tr>
		</table>
	</div>
	<hr noshade><br><br>
		<table border="5" cellpadding="0" cellspacing="0" width="60%" align="center">
			<tr>
				<th width="40%">日程时间</th>
				<th width="60%">日程内容</th>
			</tr>
			<%
				ArrayList datelist=(ArrayList)session.getAttribute("datelist");
				if(datelist==null||datelist.size()==0){
			%>
			<div align="center">
				<h1>您还没有日程安排</h1>
			</div>
			<%
				}else{
					for(int i=datelist.size()-1;i>=0;i--){
						LookDateBean dd=(LookDateBean)datelist.get(i);
			%>
			<tr>
				<td><%=dd.getDate() %></td>
				<td><%=dd.getThing() %></td>
			</tr>
			<%
				}
			}
			%>
		</table>

</body>
</html>