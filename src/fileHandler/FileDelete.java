package fileHandler;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件删除
 * 
 * @author Marydon
 * @createTime 2017�?10�?30日上�?11:13:39
 * @updateTime
 * @Email:Marydon20170307@163.com
 * @version:1.0.0
 */
@WebServlet(urlPatterns={"/deleteFile.do"})
public class FileDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 处理文件下载的post
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.设置参数编码
        request.setCharacterEncoding("UTF-8");
        // 设置响应数据字符�?
        response.setCharacterEncoding("UTF-8");
        // 2.获得请求文件�?
        String fileName = request.getParameter("fileName");
        fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
        // 3.获取该文件所在路�?
        String filePath = "WEB-INF/uploadFiles/" + fileName;
        filePath = this.getServletContext().getRealPath(filePath);
        // 4.在指定路径下创建指定名称的文�?
        File deleteFile = new File(filePath);
        boolean flag = false;
        String msg = "";
        // 5.判断该文件是否已存在
        if (deleteFile.exists()) {
            flag = deleteFile.delete();
            if (flag) {
                msg = "删除成功�?";
            } else {
                msg = "删除失败�?";
            }
        } else {
            msg = "该文件不存在�?";
        }
        
        // 6.返回客户端操作信�?
        response.getWriter().print(msg);
    }

}