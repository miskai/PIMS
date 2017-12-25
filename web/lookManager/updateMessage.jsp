<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="lookMessage.LookMessageBean"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理系统——查看</title>
</head>
<body bgcolor="#E9EBE9">
	<hr noshade>
	<div align="center">
		<table border="0" cellspacing="0" cellpadding="0" width="100%"
			align="center">
			<tr>
				<td width="33%">修改个人信息</td>
				<td width="33%"><a
					href="lookMessage.jsp">查看个人信息</a>
				</td>
				<td width="33%"><a
					href="updatePassword.jsp">修改密码</a>
				</td>
			</tr>
		</table>
	</div>
	<hr noshade>
	<br>
	<br>
	<form action="http://localhost:8080/UpdateMessageServlet"
		method="post">
		<table border="2" cellspacing="0" cellpadding="0" bgcolor="#E9EBE9"
			width="60%" align="center">
			<%
				ArrayList wordlist = (ArrayList) session.getAttribute("wordlist");
				System.out.println(wordlist);
				if (wordlist == null || wordlist.size() == 0) {
					response.sendRedirect("../main/bottom.jsp");
				} else {
					for (int i = wordlist.size() - 1; i >= 0; i--) {
						LookMessageBean mm = (LookMessageBean) wordlist.get(i);
			%>
			<tr>
				<td height="30">用户姓名</td>
				<td><%=mm.getName()%></td>
			</tr>

			<tr>
				<td height="30">用户性别</td>
				<td><%=mm.getSex()%></td>
			</tr>

			<tr>
				<td height="30">出生日期</td>
				<td><%=mm.getBirth()%></td>
			</tr>
			
			<tr>
				<td height="30">用户民族</td>
				<td><%=mm.getNation()%></td>
			</tr>
			<tr>
				<td height="30">用户学历</td>
				<td><select name="edu" size="1">
						<%
							System.out.println(mm.getEdu()+""+mm.getWork());
							if (("博士").equals(mm.getEdu())) {
						%>
						<option value="博士" selected>博士</option>
						<%
							} else {
						%>
						<option value="博士">博士</option>
						<%
							}
						%> -
						<%
							if (("硕士").equals(mm.getEdu())) {
						%>
						<option value="硕士" selected>硕士</option>
						<%
							} else {
						%>
						<option value="硕士">硕士</option>
						<%
							}
						%>

						<%
							if (("本科").equals(mm.getEdu())) {
						%>
						<option value="本科" selected>本科</option>
						<%
							} else {
						%>
						<option value="本科">本科</option>
						<%
							}
						%>

						<%
							if (("专科").equals(mm.getEdu())) {
						%>
						<option value="专科" selected>专科</option>
						<%
							} else {
						%>
						<option value="专科">专科</option>
						<%
							}
						%>

						<%
							if (("高中").equals(mm.getEdu())) {
						%>
						<option value="高中" selected>高中</option>
						<%
							} else {
						%>
						<option value="高中">高中</option>
						<%
							}
						%>

						<%
							if (("初中").equals(mm.getEdu())) {
						%>
						<option value="初中" selected>初中</option>
						<%
							} else {
						%>
						<option value="初中">初中</option>
						<%
							}
						%>

						<%
							if (("小学").equals(mm.getEdu())) {
						%>
						<option value="小学" selected>小学</option>
						<%
							} else {
						%>
						<option value="小学">小学</option>
						<%
							}
						%>

						<%
							if (("其他").equals(mm.getEdu())) {
						%>
						<option value="其他" selected>其他</option>
						<%
							} else {
						%>
						<option value="其他">其他</option>
						<%
							}
						%>
				</select></td>
			</tr>
			
			<tr>
				<td height="30">用户类型</td>
				<td><select name="work" size="1">
						<%
							if (("软件开发工程师").equals(mm.getWork())) {
						%>
						<option value="软件开发工程师" selected>软件开发工程师</option>
						<%
							} else {
						%>
						<option value="软件开发工程师">软件开发工程师</option>
						<%
							}
						%>

						<%
							if (("软件测试工程师").equals(mm.getWork())) {
						%>
						<option value="软件测试工程师" selected>软件测试工程师</option>
						<%
							} else {
						%>
						<option value="软件测试工程师">软件测试工程师</option>
						<%
							}
						%>

						<%
							if (("教师").equals(mm.getWork())) {
						%>
						<option value="教师" selected>教师</option>
						<%
							} else {
						%>
						<option value="教师">教师</option>
						<%
							}
						%>

						<%
							if (("学生").equals(mm.getWork())) {
						%>
						<option value="学生" selected>学生</option>
						<%
							} else {
						%>
						<option value="学生">学生</option>
						<%
							}
						%>

						<%
							if (("职员").equals(mm.getWork())) {
						%>
						<option value="职员" selected>职员</option>
						<%
							} else {
						%>
						<option value="职员">职员</option>
						<%
							}
						%>

						<%
							if (("经理").equals(mm.getWork())) {
						%>
						<option value="经理" selected>经理</option>
						<%
							} else {
						%>
						<option value="经理">经理</option>
						<%
							}
						%>

						<%
							if (("老板").equals(mm.getWork())) {
						%>
						<option value="老板" selected>老板</option>
						<%
							} else {
						%>
						<option value="老板">老板</option>
						<%
							}
						%>

						<%
							if (("公务员").equals(mm.getWork())) {
						%>
						<option value="公务员" selected>公务员</option>
						<%
							} else {
						%>
						<option value="公务员">公务员</option>
						<%
							}
						%>
						<%
							if (("其他").equals(mm.getWork())) {
						%>
						<option value="其他" selected>其他</option>
						<%
							} else {
						%>
						<option value="其他">其他</option>
						<%
							}
						%>
				</select></td>
			</tr>

			<tr>
				<td height="30">用户电话</td>
				<td><input type="text" name="phone" value="<%=mm.getPhone()%>">
				</td>
			</tr>

			<tr>
				<td height=="30">家庭住址</td>
				<td><%=mm.getPlace()%></td>
			</tr>

			<tr>
				<td height="30">邮箱地址</td>
				<td><input type="text" name="email" value="<%=mm.getEmail()%>">
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit" value="确定"
					size="12"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="reset" value="清除" size="12"></td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</form>
</body>
</html>