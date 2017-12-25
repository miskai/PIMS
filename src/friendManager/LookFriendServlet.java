package friendManager;
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
import javax.swing.JOptionPane;


public class LookFriendServlet extends HttpServlet{
	public void wrong1(){
		String msg="不允许有空，注册失败";
		int type = JOptionPane.YES_NO_CANCEL_OPTION;
		String title = "信息提示";
		JOptionPane.showMessageDialog(null, msg, title, type);
	}
	protected void doGet (HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		try{
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			Class.forName("com.mysql.jdbc,Driver");
			String url="jdbc:mysql://localhost:3306/information?useUnicode=true&characterEncoding=utf-8";
			con=DriverManager.getConnection(url,"root","123456");
			stmt=con.createStatement();
			String userName="";
			HttpSession session=request.getSession();
			ArrayList login=(ArrayList)session.getAttribute("login");
			if(login==null || login.size()==0){
				response.sendRedirect("");
			}else{
				for(int i=login.size()-1;i>=0;i--){
					LoginBean nn=(LoginBean)login.get(i);
					userName=nn.getUserName();
				}
			}
			String sqll="select*from friends where userName='"+userName+"'";
			rs=stmt.executeQuery(sqll);
			ArrayList friendslist=null;
			if((ArrayList)session.getAttribute("friendslist")==null){
				friendslist=new ArrayList();
				while(rs.next()){
					LookFriendBean ff=new LookFriendBean();
					ff.setName(rs.getString("name"));
					ff.setPhone(rs.getString("photo"));
					ff.setEmail(rs.getString("email"));
					ff.setWorkPlace(rs.getString("workPlace"));
					ff.setPlace(rs.getString("place"));
					ff.setQQ(rs.getString("QQ"));
					friendslist.add(ff);
					session.setAttribute("friendslist", friendslist);
				}
			}
			rs.close();
			stmt.close();
			con.close();
			response.sendRedirect("http: //localhost:8080/friendManager/lookFriend.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		doGet(request,response);
	}

}
