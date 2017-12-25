var operateFile = new OperateFile();
window.onload = function() {
	operateFile.init();
	
}

function OperateFile() {
	var object = this;
	
	/**
	 * 初始化操作：
	 */
	this.init = function() {
		$('#file_upload').uploadify({
			'method' : 'get',
			'buttonText' : '添加文件',
        	'fileTypeExts' : '*.gif; *.jpg; *.png;*.pdf;*.zip;',// 限制上传文件类型
        	'sizeLimit': 10240000000, 
        	'fileSizeLimit':'2MB',//上传文件的大小限制，单位为kb
        	'simUploadLimit' :500, //并发上传数据
        	'queueSizeLimit' :500, //可上传的文件个数，默认值999
            'swf' : baseUrl + '/uploadify/flash/uploadify.swf', // flash文件路径（帮助我们与后端交互数据）
            'uploader' : baseUrl + '/uploadFile.do' , // 处理文件上传请求地址 
            'formData' : {'param1':'测试文件上传'},// 请求参数，
//            'checkExisting' :true,
            'onUploadSuccess' : function(file, data, response) {
            	alert(data);
	        }
        });
	}
	
}