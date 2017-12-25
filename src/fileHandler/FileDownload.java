package fileHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

/**
 * 文件下载
 * 
 * @author Marydon
 * @createTime 2017�?10�?30日上�?11:13:27
 * @updateTime
 * @Email:Marydon20170307@163.com
 * @version:1.0.0
 */
@WebServlet(urlPatterns={"/downloadFile.do"})
public class FileDownload extends HttpServlet {
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
        // 1.获得请求文件�?
        String fileName = request.getParameter("fileName");
        // 以UTF-8格式解码
        fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
        // 2.设置文件MIME类型（指定要返回内容的类型）
        response.setContentType(getServletContext().getMimeType(fileName));
        // 为什么不适用这种方式？因为不兼容ie浏览�?
        // String formatFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1"); 
        // 对中文以UTF-8格式编码
        String formatFileName = URLEncoder.encode(fileName, "UTF-8");
        
        // 3.设置Content-Disposition（指定下载该文件时的文件名）
        response.setHeader("content-disposition", "attachment;filename=" + formatFileName);
        // 4.读取目标文件，�?�过response将目标文件写到客户端
        // 4.1 获取目标文件的绝对路�?
        String filePath = "WEB-INF/uploadFiles/" + fileName;
        filePath = this.getServletContext().getRealPath(filePath);
        // 4.2 读取文件
        InputStream in = new FileInputStream(filePath);
        // 4.3 输出文件
        OutputStream out = response.getOutputStream();
        // 写文�?
        int n;
        while ((n = in.read()) != -1) {
            out.write(n);
        }

        in.close();
        out.close();
    }
    
    public static String encodingFileName(String fileName) {
        String returnFileName = "";
        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if (returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
                returnFileName = StringUtils.replace(returnFileName, " ", "%20");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnFileName;
    }
}