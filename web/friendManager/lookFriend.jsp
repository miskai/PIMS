<%@ page import="java.util.ArrayList" %>
<%@ page import="friendManager.LookFriendBean"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="loginRegister.LoginBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理系统——查看通讯录</title>
</head>
<body bgcolor="#E9EBE9">
	<hr noshade>
	<div align="center">
	<table border="0" cellspacing="0" cellpadding="0"
	width="100%" align="center">
	<tr>
		<td width="20%">
		<a href="addFriend.jsp">增加联系人</a>
		</td>
		<td width="20%">
		查看通讯录
		</td>
		<td width="20%">
		<a href="updateFriend.jsp">修改联系人</a>
		</td>
		<td width="20%">
		<a href="deleteFriend.jsp">删除联系人</a>
		</td>
	</tr>
	</table>
	</div>
	<hr noshade>
	<br><br>
	<table border="2" cellspacing="0" cellpadding="0"
		width="60%" align="center">
		<tr>
			<th height="30">用户姓名</th>
			<th height="30">用户电话</th>
			<th height="30">邮箱地址</th>
			<th height="30">用户单位</th>
			<th height="30">家庭住址</th>
			<th height="30">用户QQ</th>
		</tr>
		<%
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			String userName= "";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/information?useUnicode=true&characterEncoding=UTF8","root","123456");
				stmt=con.createStatement();

				ArrayList login= (ArrayList)session.getAttribute ("login");
				if(login==null||login.size()==0) {
					response.sendRedirect ("./login.jsp") ;
				}else{
					for(int i=login.size()-1;i>=0;i--){
						LoginBean nn=(LoginBean)login.get(i);
						userName=nn.getUserName();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String sq13="select * from friends where userName= '"+userName+"'";
			rs=stmt.executeQuery(sq13);
			ArrayList friendslist=new ArrayList ();
			while(rs.next() ) {
				LookFriendBean ff = new LookFriendBean();
				ff.setName(rs.getString("name"));
				ff.setPhone(rs.getString("phone"));
				ff.setEmail(rs.getString("email"));
				ff.setWorkPlace(rs.getString("workPlace"));
				ff.setPlace(rs.getString("place"));
				ff.setQQ(rs.getString("QQ"));
				friendslist.add(ff);
			}
		if(friendslist==null||friendslist.size()==0){
		%>
		<div align="center">
		<h1>您还没有任何联系人</h1>
		</div>
		<%
			}else{
				for(int i=friendslist.size()-1;i>=0;i--){
					LookFriendBean ff =(LookFriendBean)friendslist.get(i);
		%>
		<tr>
			<td><%=ff.getName() %></td>
			<td><%=ff.getPhone() %></td>
			<td><%=ff.getEmail() %></td>
			<td><%=ff.getWorkPlace() %></td>
			<td><%=ff.getPlace() %></td>
			<td><%=ff.getQQ() %></td>
		</tr>
		<%
				}
			}
		%>
	</table>
</body>
</html>