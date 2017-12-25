package friendManager;

import loginRegister.LoginBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class UpdateFriendsMessageServlet
 */

public class UpdateFriendsMessageServlet extends HttpServlet {
    public void wrong1() {
        String msg = "不允许有空，修改失败";
        int type = JOptionPane.YES_NO_CANCEL_OPTION;
        String title = "信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    public void right() {
        String msg = "填写信息合格，修改成功";
        int type = JOptionPane.YES_NO_CANCEL_OPTION;
        String title = "信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String phone = new
                String(request.getParameter("phone").getBytes("ISO-8859-1"), "UTF-8");
        String email = new
                String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");
        String workPlace = new
                String(request.getParameter("workPlace").getBytes("ISO-8859-1"), "UTF-8");
        String place = new
                String(request.getParameter("place").getBytes("ISO-8859-1"), "UTF-8");
        String QQ = new
                String(request.getParameter("QQ").getBytes("ISO-8859-1"), "UTF-8");
        if (phone.length() == 0 || email.length() == 0 || workPlace.length() == 0 ||
                place.length() == 0 || QQ.length() == 0) {
            out.print("<script language='javascript'> alert('不允许有空，修改失败!');window.history.back(-1);</script>");
        } else {
            try {
                Connection con = null;
                Statement stmt = null;
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/information?useUnicode=true&characterEncoding=utf-8";
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
                String name = null;
                ArrayList friendslist3 = (ArrayList) session.getAttribute("friendslist3");
                if (friendslist3 == null || friendslist3.size() == 0) {
                    response.sendRedirect("./main/bottom.jsp");
                } else {
                    for (int i = friendslist3.size() - 1; i >= 0; i--) {
                        UpdateFriendBean ff = (UpdateFriendBean)
                                friendslist3.get(i);
                        name = ff.getName();
                    }
                }
                String sqll = "update friends set phone='" + phone + "',email='" + email + "',workplace='" + workPlace + "', place='" + place + "',QQ='" + QQ + "'where name='" + name + "'and userName='" + userName + "'";
                stmt.executeUpdate(sqll);
                out.print("<script language='javascript'> alert('修改成功!');self.location='./friendManager/lookFriend.jsp';</script>");
                stmt.close();
                con.close();

			/*right();
			response.sendRedirect("http: //localhost:8080/LookFriendServlet");*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
