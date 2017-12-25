package dateManager;

import loginRegister.LoginBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

public class UpdateDateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String year = new String(request.getParameter("year").getBytes("ISO-8859-1"), "UTF-8");
		String month = new String(request.getParameter("month").getBytes("ISO-8859-1"), "UTF-8");
		String day = new String(request.getParameter("day").getBytes("ISO-8859-1"), "UTF-8");
		String thing = new String(request.getParameter("thing").getBytes("ISO-8859-1"), "UTF-8");
		String date = "20" + year + "-" + month + "-" + day;
		System.out.println(date);
		if (year.length() == 0 || month.length() == 0 || day.length() == 0) {
			out.print("<script language='javascript'> alert('不允许有空，修改失败!');window.history.back(-1);</script>");

		} else if (year.length() != 2 || Integer.parseInt(year) < 11 || Integer.parseInt(month) < 1
				|| Integer.parseInt(month) > 12 || Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
			out.print("<script language='javascript'> alert('请填写正确的日期格式，修改失败!');window.history.back(-1);</script>");

		} else if (thing.length() == 0) {
			out.print("<script language='javascript'> alert('请填写日程内容，修改失败!');window.history.back(-1);</script>");

		} else {
			try {
				Connection con = null;
				Statement stmt = null;
				ResultSet rs = null;
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/information?userUnicode=true&characterEncoding=UTF8";
				con = DriverManager.getConnection(url, "root", "123456");
				stmt = con.createStatement();

				String userName = "";
				HttpSession session = request.getSession();
				ArrayList login = (ArrayList) session.getAttribute("login");
				if (login == null || login.size() == 0) {
					response.sendRedirect("./login.jsp");
				} else {
					for (int i = login.size() - 1; i >= 0; i--) {
						LoginBean nn = (LoginBean) login.get(i);
						userName = nn.getUserName();
					}
				}

				String sql1 = "select * from date where date='" + date + "'and userName= '" + userName + "'";
				rs = stmt.executeQuery(sql1);
				rs.last();
				int k;
				k = rs.getRow();
				rs.beforeFirst();
				if (k < 1) {
					out.print("<script language='javascript'> alert('该日程不存在，修改失败!');window.history.back(-1);</script>");
				/*	wrong4();
					response.sendRedirect("http://localhost:8080/dateManager/updateDate.jsp");*/
				} else {
					String sql2 = "update date set things= '" + thing + "' where date='" + date + "'and userName='"
							+ userName + "'";
					stmt.executeUpdate(sql2);
					String sql3 = "select * from date where userName='" + userName + "'";
					rs = stmt.executeQuery(sql3);
					ArrayList datelist = new ArrayList();
					while (rs.next()) {
						LookDateBean dd = new LookDateBean();
						dd.setDate(rs.getString("date"));
						dd.setThing(rs.getString("things"));
						datelist.add(dd);
						session.setAttribute("datelist", datelist);
					}
					out.print("<script language='javascript'> alert('修改成功!');self.location='./dateManager/lookDate.jsp';</script>");
					/*response.sendRedirect("http://localhost:8080/dateManager/lookDate.jsp");*/
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}