package friendManager;


import loginRegister.LoginBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AddFriendServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String name = new
                String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(name);
        String photo = new
                String(request.getParameter("phone").getBytes("ISO-8859-1"), "UTF-8");
        String email = new
                String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");
        String workPlace = new
                String(request.getParameter("workplace").getBytes("ISO-8859-1"), "UTF-8");
        String place = new
                String(request.getParameter("place").getBytes("ISO-8859-1"), "UTF-8");
        String QQ = new
                String(request.getParameter("QQ").getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(name + photo + email + workPlace + place + QQ);
        if (name.length() == 0 || photo.length() == 0 || email.length() == 0 ||
                workPlace.length() == 0 || place.length() == 0 || QQ.length() == 0) {
            out.print("<script language='javascript'> alert('不允许有空，添加失败!');window.history.back(-1);</script>");
        } else {
            try {
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/information?useUnicode=true&characterEncoding=UTF8";
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
                String sqll = "select * from friends where name='" + name + "'and userName='" + userName + "'";
                rs = stmt.executeQuery(sqll);
                rs.last();
                int k;
                k = rs.getRow();
                if (k > 0) {
                    out.print("<script language='javascript'> alert('用户已存在，添加失败!');window.history.back(-1);</script>");
                } else {
                    String sq12 = "insert into friends" + "(userName,name,phone,email,workPlace,place,QQ)"
                            + "values(" + "'" + userName + "'" + "," + "'" + name + "'" + "," + "'" + photo + "'" + ","
                            + "" + "'" + email + "'" + "," + "'" + workPlace + "'" + "," + "'" + place + "'" + "," + "'" + QQ + "'" + ")";
                    int n = stmt.executeUpdate(sq12);
                    if (n > 0) {
                        out.print("<script language='javascript'> alert('添加成功!');self.location='./friendManager/lookFriend.jsp';</script>");
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