package loginRegister;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String userName = new String(request.getParameter("userName").getBytes("ISO-8859-1"), "UTF-8");
        String password1 = new String(request.getParameter("password1").getBytes("ISO-8859-1"), "UTF-8");
        String password2 = new String(request.getParameter("password2").getBytes("ISO-8859-1"), "UTF-8");
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        String sex = new String(request.getParameter("sex").getBytes("ISO-8859-1"), "UTF-8");
        String birth = request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("day");
        String nation = new String(request.getParameter("nation").getBytes("ISO-8859-1"), "UTF-8");
        String edu = new String(request.getParameter("edu").getBytes("ISO-8859-1"), "UTF-8");
        String work = new String(request.getParameter("work").getBytes("ISO-8859-1"), "UTF-8");
        String phone = new String(request.getParameter("phone").getBytes("ISO-8859-1"), "UTF-8");
        String place = new String(request.getParameter("place").getBytes("ISO-8859-1"), "UTF-8");
        String email = new String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");
        if (userName.length() == 0 || password1.length() == 0 || password2.length() == 0 || name.length() == 0 || sex.length() == 0 || birth.length() == 0 || nation.length() == 0 ||
                edu.length() == 0 || work.length() == 0 || phone.length() == 0 || place.length() == 0 || email.length() == 0) {
            out.print("<script language='javascript'> alert('不允许有空，注册失败!');window.history.back(-1);</script>");
        } else if (!(password1.equals(password2))) {
            out.print("<script language='javascript'> alert('两次密码不同，注册失败!');window.history.back(-1);</script>");
        } else {
            Connection con = null;
            Statement stmt = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/information?userUnicode=true&characterEncoding=utf-8";
                con = DriverManager.getConnection(url, "root", "123456");
                stmt = con.createStatement();

                String sql = "select * from myuser where userName='" + userName + "'";
                rs = stmt.executeQuery(sql);
                rs.last();
                int k;
                k = rs.getRow();
                if (k > 0) {
                    out.print("<script language='javascript'> alert('用户名已存在，注册失败!');window.history.back(-1);</script>");
                } else {
                    ps = con.prepareStatement("insert into myuser values(?,?,?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, userName);
                    ps.setString(2, password1);
                    ps.setString(3, name);
                    ps.setString(4, sex);
                    ps.setString(5, birth);
                    ps.setString(6, nation);
                    ps.setString(7, edu);
                    ps.setString(8, work);
                    ps.setString(9, phone);
                    ps.setString(10, place);
                    ps.setString(11, email);
                    k = ps.executeUpdate();
                    System.out.println(k + "值");
                    if (k > 0) {
                        out.print("<script language='javascript'> alert('注册成功!');self.location='login.jsp';</script>");
                    }
                }
                rs.close();
                stmt.close();
                ;
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
