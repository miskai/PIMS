package lookMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import loginRegister.LoginBean;

/**
 * Servlet implementation class UpdatePasswordServlet
 */

public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String password1=new
		String (request.getParameter("password1").getBytes("ISO-8859-1"),"Utf-8");
		String password2=new
		String (request.getParameter("password2").getBytes("ISO-8859-1"),"Utf-8");
		if (password1.length()==0||password2.length()==0){
			out.print("<script language='javascript'> alert('不允许有空，修改失败!');window.history.back(-1);</script>");

		}else if(!(password1.equals(password2))){
			out.print("<script language='javascript'> alert('密码不一致，修改失败!');window.history.back(-1);</script>");

		}else{
				try{
					Connection con=null;
					Statement stmt=null;
					ResultSet rs=null;
					Class.forName("com.mysql.jdbc.Driver");

					String url="jdbc:mysql://localhost:3306/information?useUnicode=true&characterEncoding=utf-8";
					con=DriverManager.getConnection(url,"root","123456");
					stmt=con.createStatement() ;
					String userName= "";
					
					HttpSession session=request.getSession ();
					 ArrayList login= (ArrayList)session.getAttribute("login");
					if(login==null||login.size()==0){
						response.sendRedirect("login.jsp");
					}else{
						for(int i=login.size()-1;i>=0;i--) {
							LoginBean nn=(LoginBean)login.get(i);
							userName= nn.getUserName();
							}
							}
					String sql1="Update myuser set password='"+password1+"'where userName='"+userName+"'";
					stmt.executeUpdate(sql1);
					String sql2="select * from myuser where userName='"+userName+"'";
					rs=stmt.executeQuery(sql2);
					LoginBean nn=new LoginBean();
					nn.setPassword(password1);
					ArrayList wordlist=null;
					wordlist=new ArrayList();
					wordlist.add(nn);
					session.setAttribute("login", login);
					rs.close();
					stmt.close();
					con.close();
					out.print("<script language='javascript'> alert('修改成功!');window.open('login.jsp','_top')</script>");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
