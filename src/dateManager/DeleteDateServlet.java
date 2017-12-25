package dateManager;

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

public class DeleteDateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String year = new String(request.getParameter("year").getBytes("ISO-8859-1"), "UTF-8");
        String month = new String(request.getParameter("month").getBytes("ISO-8859-1"), "UTF-8");
        String day = new String(request.getParameter("day").getBytes("ISO-8859-1"), "UTF-8");
        String date = "20" + year + "-" + month + "-" + day;
        if (year.length() == 0 || month.length() == 0 || day.length() == 0) {
            out.print("<script language='javascript'> alert('不允许有空，删除失败!');window.history.back(-1);</script>");
           /* wrong1();
            response.sendRedirect("http://localhost:8080/dateManager/deleteDate.jsp");*/
        } else if (year.length() != 2 || Integer.parseInt(year) < 1 || Integer.parseInt(month) > 12
                || Integer.parseInt(day) > 31) {
            out.print("<script language='javascript'> alert('请填写正确的日期格式，删除失败!');window.history.back(-1);</script>");
          /*  wrong2();
            response.sendRedirect("http: //localhost:8080/dateManager/deleteDate.jsp");*/
        } else {
            try {
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/information?userUnicode= true&characterEncoding=utf-8";
                con = DriverManager.getConnection(url, "root", "123456");
                stmt = con.createStatement();
                String userName = "";
                HttpSession session = request.getSession();
                ArrayList login = (ArrayList) session.getAttribute("login");
                if (login == null || login.size() == 0) {
                    response.sendRedirect("http://localhost:8080/login.jsp");
                } else {
                    for (int i = login.size() - 1; i >= 0; i--) {
                        LoginBean nn = (LoginBean) login.get(i);
                        userName = nn.getUserName();
                    }
                }
                String sql1 = "select *from date where date= '" + date + "'and userName='" + userName + "'";
                rs = stmt.executeQuery(sql1);
                rs.last();
                int k;
                k = rs.getRow();
                rs.beforeFirst();
                if (k < 1) {
                    out.print("<script language='javascript'> alert('无此日程，删除失败!');window.history.back(-1);</script>");

                } else {
                    String sql2 = "delete from date where date='" + date + "'and userName='" + userName + "'";
                    stmt.executeUpdate(sql2);
                    String sql3 = "select *from date where userName='" + userName + "'";
                    rs = stmt.executeQuery(sql3);
                    rs.last();
                    int list = rs.getRow();
                    rs.beforeFirst();
                    if (list < 1) {
                        ArrayList datelist = null;
                        session.setAttribute("datelist", datelist);
                    } else {
                        ArrayList datelist = null;
                        datelist = new ArrayList();
                        while (rs.next()) {
                            LookDateBean dd = new LookDateBean();
                            dd.setDate(rs.getString("date"));
                            dd.setThing(rs.getString("thing"));
                            datelist.add(dd);
                            session.setAttribute("datelist", datelist);
                        }
                    }
                    out.print("<script language='javascript'> alert('删除成功!');self.location='./dateManager/lookDate.jsp';</script>");
                   /* right();
                    response.sendRedirect("http: //localhost:8080/dateManager/lookDate.jsp");*/
                }
                rs.close();
                stmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
