<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>个人信息管理系统</title>
  <style>
    body{
      padding-top: 5%;
    background: url("./images/lv.jpg") no-repeat;

      background-size:100% 1080px;
    }

    p1 {
      font-family: 华文行楷;
      font-size: 20pt;
      color: blue;
    }

    h1 {
      font-family: 华文行楷;
      font-size: 40pt;
      color: red;
      padding-bottom: 3%;
    }
  </style>
</head>
<body>
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center">
      <h1>个人信息管理系统</h1>
    </td>
  <tr>
    <td align="center" width="50%">
      <form action="LoginServlet" method="post">
        <table border="2" cellspacing="0" cellpadding="0"
               width="350">
          <tr align="center">
            <td align="center" height="130">请输入用户姓名：<input
                    type="text" name="userName" size="18" /><br>
              <p></p>
              请输入用户密码：<input type="password" name="password" size="18" /><br>
            </td>
          </tr>
          <td align="center"> <input type="submit" value="确  定" size="12">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset"
                                                        value="重   置" size="12"></td>
          </tr>
          <tr>
            <td>
              <p align="center">
                <a href="http://localhost:8080/register/register.jsp">注册</a>
              </p>
            </td>
          </tr>
        </table>
      </form>
    </td>
  </tr>
</table>
</body>
</html>