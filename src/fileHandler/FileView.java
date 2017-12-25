package fileHandler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件查看
 * 
 * @author Marydon
 * @createTime 2017�?10�?30日上�?11:12:57
 * @updateTime
 * @Email:Marydon20170307@163.com
 * @version:1.0.0
 */
@WebServlet(urlPatterns={"/viewFile.do"})
public class FileView extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // 设定输出的类�?
    private static final String GIF = "image/gif;charset=UTF-8";
    private static final String JPG = "image/jpeg;charset=UTF-8";
    private static final String PNG = "image/png;charset=UTF-8";
    private static final String PDF = "application/pdf;charset=UTF-8";
    private static final String ZIP = "application/zip;charset=UTF-8";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
    /**
     * 处理文件查看的post
     * @throws IOException 
     * @precaution 下方的类名出自包import org.apache.commons.fileupload.*
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 文件�?
        InputStream is = null;
        // 输入缓冲�?
        BufferedInputStream bis = null;
        // 得到输出�?
        OutputStream output = null;
        // 输出缓冲�?
        BufferedOutputStream bos = null;
        // 1.设置参数编码
        request.setCharacterEncoding("UTF-8");
        // 2.设置响应数据字符�?
        response.setCharacterEncoding("UTF-8");
        // 3.获取客户端请求参数：文件�?
        String fileName = request.getParameter("fileName");
        fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
        // 4.重置response
        response.reset();
        // 5.设置响应数据格式
        if (fileName.endsWith(".gif")) {
            response.setContentType(GIF);
        } else if (fileName.endsWith(".jpg")) {
            response.setContentType(JPG);
        } else if (fileName.endsWith(".png")) {
            response.setContentType(PNG);
        } else if (fileName.endsWith(".pdf")) {
            response.setContentType(PDF);
        } else if (fileName.endsWith(".gif")) {
            response.setContentType(GIF);
        } else if (fileName.endsWith(".zip")) {
            response.setContentType(ZIP);
        }

        String filePath = "WEB-INF/uploadFiles/" + fileName;
        // 获取当前web应用程序
        ServletContext webApp = this.getServletContext();
        // 6.获取指定文件上传的真实路�?
        filePath = webApp.getRealPath(filePath);
        // 7.读取目标文件，�?�过response将目标文件写到客户端
        is = new FileInputStream(filePath);
        bis = new BufferedInputStream(is);
        output = response.getOutputStream();
        bos = new BufferedOutputStream(output);
        byte data[] = new byte[1024];// 缓冲字节�?
        int size = bis.read(data);
        while (size != -1) {
            bos.write(data, 0, size);
            size = bis.read(data);
        }

        // 关闭�?
        bis.close();
        bos.flush();// 清空输出缓冲�?
        bos.close();
        output.close();
    }

}