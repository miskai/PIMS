var uploadFile = new UploadFile();
window.onload = function() {
	uploadFile.init();
}

function UploadFile() {
	var object = this;
	this.init = function() {
		// 队列中的文件数
		var selectedCount = 0;
		$('#file_upload').uploadify({
			'method' : 'get',// 默认值post，设置成get是为了向后台传递自定义的参数
			'auto' : false,// 设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传。默认值为TRUE
			'buttonText' : '添加文件',// 按钮文本
        	'fileTypeExts' : '*.gif; *.jpg; *.png;*.pdf;*.zip;',// 限制上传文件类型，默认值没有限制（*.*）
        	'fileTypeDesc' : '请选择gif jpg png pdf zip类型的文件',// 这个属性值必须设置fileTypeExts属性后才有效，用来设置选择文件对话框中的提示文本，默认值：All Files
            'swf' : baseUrl + '/uploadify/flash/uploadify.swf', // flash文件路径（帮助我们与后端交互数据）
            'uploader' : baseUrl + '/uploadFile.do' , // 处理文件上传请求地址 
            'formData' : {'param1':'测试文件上传'},// 请求参数：上传每个文件的同时提交到服务器的额外数据
			'onDialogClose' : function(queueData) {
				// 获取该队列中有多少个要上传的文件 
				var queueSize = $('#file_upload-queue').children('div').length;
				if (queueSize > 0) {
					$('#ctrlUpload').show();
				}
			},
            'onUploadSuccess' : function(file, data, response) {// 上传成功
            	window.open(baseUrl + '/fileList.jsp');
	        },
	        'onUploadError' : function(file, errorCode, errorMsg, errorString) {// 上传失败
	        	
	        }
        });
		
	}
	
	/**
	 * 删除文件
	 * @param 文件名
	 */
	this.deleteFile = function(fileName,rowIndex) {
		fileName = encodeURI(encodeURI(fileName));
		// 设置删除文件所需参数
    	var param = "fileName=" + fileName;
		// 删除文件请求地址
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