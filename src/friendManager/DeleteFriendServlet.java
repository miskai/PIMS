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
 * Servlet implementation class DeleteFriendServlet
 */
public class DeleteFriendServlet extends HttpServlet {
    public void wrong1() {
        String msg = "请输入要删除的人的姓名";
        int type = JOptionPane.YES_NO_CANCEL_OPTION;
        String title = "信息提示 ";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    public void wrong2() {
        String msg = "此联系人不存在";
        int type = JOptionPane.YES_NO_CANCEL_OPTION;
        String title = "信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    public void right() {
        String msg = "此联系人已成功删除";
        int type = JOptionPane.YES_NO_CANCEL_OPTION;
        String title = "信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String name = new
                String(request.getParameter("friendName").getBytes("ISO-8859-1"), "UTf-8");
        if (name.length() == 0) {
            out.print("<script language='javascript'> alert('不能为空，删除失败!');window.history.back(-1);</script>");
        } else {
            try {
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/information?userUnicode=true&characterEncoding=utf-8";
                con = DriverManager.getConnection(url, "root", "123456");
                stmt = con.createStatement();
                String userName = "";
                HttpSession session = request.getSession();
                ArrayList login = (ArrayList) session.getAttribute("login");
                if (login == null || login.size() == 0) {
                    response.sendRedirect("http: //localhost:8080/login.jsp");
                } else {
                    for (int i = login.size() - 1; i >= 0; i--) {
                        LoginBean nn = (LoginBean) login.get(i);
                        userName = nn.getUserName();
                    }
                }
                String sqll = "select * from friends where name= '" + name + "'and userName= '" + userName + "'";
                rs = stmt.executeQuery(sqll);
                rs.last();
                int k = rs.getRow();
                if (k < 1) {
                    out.print("<script language='javascript'> alert('没有此联系人，删除失败!');window.history.back(-1);</script>");
                } else {
                    String sq12 = "delete from friends where name='" + name + "'and userName= '" + userName + "'";
                    int n=stmt.executeUpdate(sq12);
                    if(n>0) {
                        out.print("<script language='javascript'> alert('删除成功!');self.location='./friendManager/lookFriend.jsp';</script>");
                    }
                }
                rs.close();
                stmt.close();
                con.close();

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
