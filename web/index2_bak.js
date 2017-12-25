var operateFile = new OperateFile();
window.onload = function() {
	operateFile.init();
	operateFile.viewFileList();
}

/**
 * 对文件进行操作
 * @returns
 */
function OperateFile() {
	var object = this;
	
	this.viewFileList = function() {
		
		$.get(baseUrl + '/viewFileList.do',function(fileNameList){
			// 移除tr
			$('#tableFiles tbody tr').remove();
			var trTemplate = "";
			var viewUrl = "";
			var param = "";
			var downloadUrl = "";
			fileNameList = eval('(' + fileNameList + ')');
			
			$(fileNameList).each(function(index, fileName){
				param = "fileName=" + fileName;
				viewUrl = baseUrl + '/viewFile.do?' + param;
				downloadUrl = baseUrl + '/downloadFile.do?' + param;
				trTemplate = '<tr>'
							+	'<td>' + (index+1) + '</td>'
							+	'<td>' + fileName + '</td>'
							+   '<td>'
            				+		'<a href="' + viewUrl + '" target="_blank">点击预览</a>'
            				+	'</td>'
            				+   '<td>'
        					+		'<a href="' + downloadUrl + '">点击下载</a>'
        					+	'</td>'	
        					+   '<td>'
        					+		'<a href="javascript:;" onclick="operateFile.deleteFile(\'' + fileName + '\',' + (index+1) +');">点击删除</a>'
        					+	'</td>'
						    +'</tr>';
				
				$('#tableFiles').append(trTemplate);
			});
		});
	}
	
	/**
	 * 初始化操作：
	 */
	this.init = function() {
		// 队列中的文件数
		var selectedCount = 0;
		$('#file_upload').uploadify({
			'method' : 'get',
			'auto' : false,// 设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传。默认值为TRUE
			'buttonText' : '添加文件',// 按钮文本
        	'fileTypeExts' : '*.gif; *.jpg; *.png;*.pdf;*.zip;',// 限制上传文件类型，默认值没有限制（*.*）
        	'fileTypeDesc' : '请选择gif jpg png pdf zip类型的文件',
            'swf' : baseUrl + '/uploadify/flash/uploadify.swf', 
            'uploader' : baseUrl + '/uploadFile.do' , 
            'formData' : {'param1':'测试文件上传'},
			'onDialogClose' : function(queueData) {
				
				var queueSize = $('#file_upload-queue').children('div').length;
				if (queueSize > 0) {
					$('#ctrlUpload').show();
				}
			},
            'onUploadSuccess' : function(file, data, response) {// 上传成功
            	
            	// 将josn字符串转换成JSON对象
            	data = eval('(' + data + ')');
            	// 获取页面上文件展示table 有多少行
            	var rowsLength = $('#tableFiles')[0].rows.length;
            	var fileName = encodeURI(encodeURI(data.fileName));
            	// 设置查看文件所需参数
            	var param = "fileName=" + fileName;
            	// 查看文件请求地址
            	var viewUrl = baseUrl + '/viewFile.do?' + param;
            	// 下载文件请求地址
            	var downloadUrl = baseUrl + '/downloadFile.do?' + param;
            	
            	// 拼接一行tr
            	var trTemplate = '<tr>'
            					+	'<td>'
            					+		rowsLength	 
            					+	'</td>'
            					+   '<td>'
            					+		file.name // 仍展示原文件名
            					+	'</td>'
            					+   '<td>'
            					+		'<a href="' + viewUrl + '" target="_blank">点击预览</a>'
            					+	'</td>'
            					+   '<td>'
            					+		'<a href="' + downloadUrl + '">点击下载</a>'
            					+	'</td>'
            					+   '<td>'
            					+		'<a href="javascript:;" onclick="operateFile.deleteFile(\'' + data.fileName + '\',' + rowsLength +');">点击删除</a>'
            					+	'</td>'
            					+'</tr>';
            	$('#tableFiles').append(trTemplate);
	        },
	        'onUploadError' : function(file, errorCode, errorMsg, errorString) {// 上传失败
	        	
	        }
        });
		
	}
	
	this.deleteFile = function(fileName,rowIndex) {
		fileName = encodeURI(encodeURI(fileName));
		// 设置删除文件所需参数
    	var param = "fileName=" + fileName;
    	var deleteUrl = baseUrl + '/deleteFile.do?' + param;
		$.get(
			deleteUrl, 
			function(msg) {
				alert(msg);
				if ("删除失败！" != msg) {
					// 删除该行记录
					$('#tableFiles')[0].deleteRow(rowIndex);
				}
			}
		);
		
	}
	
}