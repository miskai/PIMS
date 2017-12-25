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

public class AddDateServlet extends HttpServlet {
    public void wrong1() {
        String msg = "请把日期填写完，添加失败";
        int type = JOptionPane.YES_NO_CANCEL_OPTION;
        String title = "信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    public void wrong2() {
        String msg = "请把日期填写完，添加失败";
        int type = JOptionPane.YES_NO_CANCEL_OPTION;
        String title = "信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    public void wrong3() {
        String msg = "请填写内容日程，添加失败";
        int type = JOptionPane.YES_NO_CANCEL_OPTION;
        String title = "信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    public void wrong4() {
        String msg = "该日程已有计划，添加失败";
        int type = JOptionPane.YES_NO_CANCEL_OPTION;
        String title = "信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    public void right() {
        String msg = "填写信息合格，添加成功";
        int type = JOptionPane.YES_NO_CANCEL_OPTION;
        String title = "信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String year = new String(request.getParameter("year").getBytes("ISO-8859-1"), "UTF-8");
        String month = new String(request.getParameter("month").getBytes("ISO-8859-1"), "UTF-8");
        String day = new String(request.getParameter("day").getBytes("ISO-8859-1"), "UTF-8");
        String thing = new String(request.getParameter("thing").getBytes("ISO-8859-1"), "UTF-8");
        String date = "20" + year + "-" + month + "-" + year;
        if (year.length() == 0 || month.length() == 0 || day.length() == 0) {
            out.print("<script language='javascript'> alert('请填写完整日期，添加失败!');window.history.back(-1);</script>");
        /*	System.out.println("进来了");
			wrong1();
			response.sendRedirect("http://localhost:8080/dateManager/addDate.jsp");*/
        } else if (year.length() != 2 || Integer.parseInt(year) < 1 || Integer.parseInt(month) < 1
                || Integer.parseInt(month) > 12 || Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
            out.print("<script language='javascript'> alert('请填写正确日期，添加失败!');window.history.back(-1);</script>");
        } else if (thing.length() == 0) {
            out.print("<script language='javascript'> alert('请填写日程内容，添加失败!');window.history.back(-1);</script>");
			/*wrong3();
			response.sendRedirect("http: //localhost :8080/dateManager/addDate.jsp");*/
        } else {
            try {
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                Class.forName("com.mysql.jdbc.Driver");
                String ur1 = "jdbc:mysql://localhost:3306/information?userUnicode=true&characterEncoding=utf-8";
                con = DriverManager.getConnection(ur1, "root", "123456");
                stmt = con.createStatement();
                String userName = "";
                HttpSession session = request.getSession();
                ArrayList login = (ArrayList) session.getAttribute("login");
                if (login == null || login.size() == 0) {
                    response.sendRedirect("http: //localhost :8080/login.jsp");
                } else {
                    for (int i = login.size() - 1; i >= 0; i--) {
                        LoginBean nn = (LoginBean) login.get(i);
                        userName = nn.getUserName();
                    }
                }
                String sqll = "select * from date where date= '" + date + "'and userName= '" + userName + "'";
                rs = stmt.executeQuery(sqll);
                rs.last();
                int k;
                k = rs.getRow();
                rs.beforeFirst();
                if (k > 0) {
                    out.print("<script language='javascript'> alert('该日期已有计划，添加失败!');window.history.back(-1);</script>");
				/*	wrong4();
					response.sendRedirect("http: //1oca1host :8080/dateManager/addDate.jsp");*/
                } else {
                    String sql2 = "insert into date" + " (userName,date,things)" + "values (" + "'" + userName + "'"
                            + "," + "'" + date + "'" + "," + "'" + thing + "'" + ")";
                    stmt.executeUpdate(sql2);

                    String sql3 = "select * from date where userName='" + userName + "'";
                    rs = stmt.executeQuery(sql3);
                    ArrayList datelist = null;
                    datelist = new ArrayList();
                    while (rs.next()) {
                        LookDateBean dd = new LookDateBean();
                        dd.setDate(rs.getString("date"));
                        dd.setThing(rs.getString("things"));
                        datelist.add(dd);
                        session.setAttribute("datelist", datelist);
                    }
                    out.print("<script language='javascript'> alert('添加成功!');self.location='./dateManager/lookDate.jsp';</script>");
                }
                rs.close();
                stmt.close();
                con.close();
               /* right();
                response.sendRedirect("http: //localhost:8080/dateManager/1ookDate.jsp");*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
