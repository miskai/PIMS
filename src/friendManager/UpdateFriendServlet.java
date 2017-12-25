package friendManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import static java.lang.Class.forName;

/**
 * Servlet implementation class UpdateFriendServlet
 */

public class UpdateFriendServlet extends HttpServlet {
		public void wrong1() {
		String msg="请输入要修改人的姓名";
		int type=JOptionPane.YES_NO_CANCEL_OPTION;
		String title="信息提示";
		JOptionPane.showMessageDialog(null,msg,title,type) ;
		}
		public void wrong2 () {
		String msg="此姓名不存在，无法修改";
		int type=JOptionPane.YES_NO_CANCEL_OPTION;
		String title="信息提示";
		JOptionPane.showMessageDialog(null,msg,title,type);
		}
		protected void doGet (HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
			response.setContentType("text/html;UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out=response.getWriter();
		String friendName=new
		String(request.getParameter ("friendName").getBytes ("ISO-8859-1"),"UTF-8") ;
		if(friendName.length()==0) {
			out.print("<script language='javascript'> alert('姓名不能为空!');window.history.back(-1);</script>");
		}else{
		try{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/information?useUnicode=true&characterEncoding=UTF8";
		con= DriverManager.getConnection (url,"root","123456") ;
		stmt=con.createStatement () ;
		String sqll="select * from friends where name= '"+friendName+"'";
		rs=stmt.executeQuery(sqll) ;
		rs.last();
		int k=rs.getRow () ;
		rs.beforeFirst () ;
		if(k<1){
			out.print("<script language='javascript'> alert('没有此联系人!');window.history.back(-1);</script>");
		}else{
		HttpSession session=request.getSession ();
		ArrayList friendslist2=null;
		friendslist2=new ArrayList () ;
		while(rs.next()) {
			LookFriendBean ff= new LookFriendBean () ;
			ff.setName (rs.getString ("name") );
			ff.setPhone (rs.getString ("phone") );
			ff.setEmail (rs.getString("email") );
			ff.setWorkPlace (rs.getString ("workPlace") );
			ff.setPlace (rs.getString ("place") );
			ff.setQQ(rs.getString("QQ") );
			friendslist2.add(ff);
			session.setAttribute ("friendslist2",friendslist2) ;
		}
			ArrayList friendslist3=null;
			UpdateFriendBean nn=new UpdateFriendBean () ;
			friendslist3=new ArrayList () ;
			nn.setName (friendName) ;
			friendslist3.add(nn) ;
			session.setAttribute("friendslist3",friendslist3) ;
			response.sendRedirect ("./friendManager/UpdateFriendMessage.jsp") ;
		}
			rs.close() ;
			stmt.close () ;
			con.close () ;

			}catch (Exception e) {
			e.printStackTrace ();
			}
		}
		}
			protected void doPost (HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
			doGet (request,response);
		}

}
