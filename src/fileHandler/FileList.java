package fileHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

/**
 * 文件列表展示
 * @author Marydon
 * @createTime 2017�?10�?20日下�?2:21:48
 * @updateTime
 * @Email:Marydon20170307@163.com
 * @version:1.0.0
 */
@WebServlet(urlPatterns={"/viewFileList.do"})
public class FileList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应数据字符�?
        response.setCharacterEncoding("UTF-8");
        String filePath = "WEB-INF/uploadFiles";
        // 获取当前web应用程序
        ServletContext webApp = this.getServletContext();
        // 6.获取指定文件上传的真实路�?
        filePath = webApp.getRealPath(filePath);
        File uploadFiles = new File(filePath);
        // 获取uploadFiles文件夹下的所有文�?
        File[] fileArray = uploadFiles.listFiles(); 
        // 用于存放文件�?
        List fileNameList = new ArrayList();
        for (File file : fileArray) {
            // 获取文件名称
            String dcmFileName = file.getName();
            // 将文件名放入到list集合�?
            fileNameList.add(dcmFileName);
        }
        JSONArray ja = JSONArray.fromObject(fileNameList);
        response.getWriter().print(ja.toString());
    }

}
