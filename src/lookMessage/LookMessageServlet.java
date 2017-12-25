package lookMessage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LookMessageServlet
 */

public class LookMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LookMessageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		try {
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/information? useUnicode=true&characterEncoding=utf-8";
			con = DriverManager.getConnection(url, "root", "123456");
			stmt = con.createStatement();
			String sql = "select*from myuser where userName='" + userName + "'";
			rs = stmt.executeQuery(sql);
			LookMessageBean mm = new LookMessageBean();
			while (rs.next()) {
				mm.setName(rs.getString("name"));
				mm.setSex(rs.getString("sex"));
				mm.setBirth(rs.getString("birth"));
				mm.setNation(rs.getString("nation"));
				mm.setEdu(rs.getString("edu"));
				mm.setWork(rs.getString("work"));
				mm.setPhone(rs.getString("phone"));
				mm.setPlace(rs.getString("place"));
				mm.setEmail(rs.getString("email"));
			}
			HttpSession session = request.getSession();
			ArrayList wordlist = wordlist = new ArrayList();
			wordlist.add(mm);
			session.setAttribute("wordlist", wordlist);
			rs.close();
			stmt.close();
			con.close();
			response.sendRedirect("../lookManager/lookMessage.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
