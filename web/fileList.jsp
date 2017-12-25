<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>  
		<title>文件列表页面</title>
        <%@include file="include.jsp" %>
        <script type="text/javascript" src="<c:url value="/fileList.js"/>"></script>
	</head>
	<body>
        <table border=1 style="border-collapse: collapse;" id="tableFiles">
            <thead>
                <th>序号</th>
                <th>文件名</th>
                <th>文件预览</th>
                <th>文件下载</th>
                <th>文件删除</th>
            </thead>
            <tbody></tbody>
        </table>
	</body>
</html>