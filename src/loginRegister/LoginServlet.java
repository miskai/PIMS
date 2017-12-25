package loginRegister;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	public void wrong1() {
		String msg = "用户名不能为空";
		int type = JOptionPane.YES_NO_CANCEL_OPTION;
		String title = "信息提示";
		JOptionPane.showMessageDialog(null, msg, title, type);
		System.out.println("wrong1执行了");
	}

	public void wrong2() {
		String msg = "用户名不能为空，登陆失败!";
		int type = JOptionPane.YES_NO_CANCEL_OPTION;
		String title = "信息提示";
		JOptionPane.showMessageDialog(null, msg, title, type);
	}

	public void wrong3() {
		String msg = "用户尚未注册,登陆失败!";
		int type = JOptionPane.YES_NO_CANCEL_OPTION;
		String title = "信息提示";
		JOptionPane.showMessageDialog(null, msg, title, type);
	}

	public void wrong4() {
		String msg = "用户密码不正确,登陆失败!";
		int type = JOptionPane.YES_NO_CANCEL_OPTION;
		String title = "信息提示";
		JOptionPane.showMessageDialog(null, msg, title, type);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;UTF-8");
		//必须加这一句转码 不然js乱码
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		String userName=new
				String(request.getParameter("userName").getBytes("ISO-8859-1"),"UTF-8");
		String password=new
				String(request.getParameter("password").getBytes("ISo-8859-1"),"UTF-8");
		System.out.println(userName+"ad"+password);
		if(userName.equals("") ){
			out.print("<script language='javascript'> alert('用户名为空!');window.history.back(-1);</script>");
			//wrong1();
			//request.getRequestDispatcher("login.jsp").forward(request, response);
			//response.sendRedirect ("../login.jsp") ;
		}else if(password.equals("") ){
			//wrong2();
			out.print("<script language='javascript'> alert('密码为空!');window.history.back(-1);</script>");
		}else{
			try{
				Connection con=null;
				Statement stmt=null;
				ResultSet rs=null;
				Class.forName("com.mysql.jdbc.Driver") ;
				System.out.println("成功加载MySQL驱动程序");
				String url="jdbc:mysql://localhost:3306/information?useUnicode=true&characterEncoding=utf-8";
				con= DriverManager.getConnection(url,"root","123456") ;
				stmt=con.createStatement ();
				rs=stmt.executeQuery("select*from myuser where userName='"+userName+"'");
				int N=0;
				int P= 0;
				while(rs.next()){
					if(userName.equals (rs.getString("userName") )) {
						N=1001;
						if(password.equals(rs.getString("password") )) {
							P=1001;
							LoginBean nn=new LoginBean ();
							nn.setUserName (userName) ;
							nn.setPassword (password) ;
							//获取session 对象
							HttpSession session=request.getSession();
							ArrayList login = new ArrayList();
							login.add(nn);
							session.setAttribute("login", login);
							response.sendRedirect("./main/main.jsp");
						}else{

						}
					}else {
						N++;
					}
				}
				if (N<1001) {
					//wrong3();
					out.print("<script language='javascript'> alert('用户尚未注册!');window.history.back(-1);</script>");
				}else if(P<1001){
					//wrong4();
					out.print("<script language='javascript'> alert('密码错误!');window.history.back(-1);</script>");
				}
				rs.close();
				stmt.close();;
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
