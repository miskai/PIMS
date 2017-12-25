package dateManager;

import loginRegister.LoginBean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LookDateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/information?useUnicode=true&characterEncoding=UTF8";
			con=DriverManager.getConnection(url, "root", "123456");
			stmt=con.createStatement();
			String userName="";
			HttpSession session=request.getSession();
			ArrayList login=(ArrayList)session.getAttribute("login");
			if(login==null||login.size()==0){
				response.sendRedirect("./login.jsp");
			}else{
				for(int i=login.size()-1;i>=0;i--){
					LoginBean nn=(LoginBean)login.get(i);
					userName=nn.getUserName();
				}
			}
			String sql="select*from date where userName='"+userName+"'";
			rs=stmt.executeQuery(sql);
			ArrayList datelist=null;
			datelist=new ArrayList();
			while(rs.next()){
				LookDateBean dd=new LookDateBean();
				dd.setDate(rs.getString("date"));
				dd.setThing(rs.getString("things"));
				datelist.add(dd);
				session.setAttribute("datelist", datelist);
				
			}
			rs.close();
			stmt.close();
			con.close();
			response.sendRedirect("./dateManager/lookDate.jsp");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
