<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>  
		<title></title>
        <%@include file="include.jsp" %>
        <script type="text/javascript" src="<c:url value="/index2.js"/>"></script>
        <style type="text/css">
          
            .Button {
                width: 80px;
                margin: 3px 1px 0 5px;
                padding: 0 10px;
                background-color: #16a0d3;
                border: none;
                display: inline-block;
                font-family: "Microsoft Yahei";
                font-size: 14px;
                cursor: pointer;
                height: 30px;
                line-height: 30px;
                color: #FFF;
                border-radius: 5px;
                text-decoration:none;
                text-align:center;
            }
            
            .ButtonOver {
                width: 80px;
                margin: 3px 1px 0 5px;
                padding: 0 10px;
                background-color: #117ea6;
                border: none;
                display: inline-block;
                font-family: "Microsoft Yahei";
                font-size: 14px;
                cursor: pointer;
                height: 30px;
                line-height: 30px;
                color: #FFF;
                border-radius: 5px;
                text-decoration:none;
                text-align:center;
            }    
          
        </style>
	</head>
	<body>
      
		<div id="file_upload"></div>
        <div id="ctrlUpload" style="display:none;">
            <a href="javascript:;" onclick="$('#file_upload').uploadify('upload', '*');" class="Button" 
                onmouseover="javascript:this.className='ButtonOver'" onmouseout="javascript:this.className='Button'">
                        上传所有
            </a>    
            <a href="javascript:;" onclick="$('#file_upload').uploadify('cancel', '*');$('#ctrlUpload').hide();" class="Button"
                onmouseover="javascript:this.className='ButtonOver'" onmouseout="javascript:this.className='Button'">
                        取消上传
            </a>	
        </div>
	</body>
</html>