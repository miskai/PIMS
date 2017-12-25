var operateFile = new OperateFile();
window.onload = function() {
	operateFile.viewFileList();
}

function OperateFile() {
	var object = this;
	
	this.viewFileList = function() {
		
		$.get(baseUrl + '/viewFileList.do',function(fileNameList){
			
			$('#tableFiles tbody tr').remove();
			var trTemplate = "";
			var viewUrl = "";
			var param = "";
			var downloadUrl = "";
			fileNameList = eval('(' + fileNameList + ')');
			
			$(fileNameList).each(function(index, fileName){
				// fileName仅用于页面展示
				// 用于文件预览和下载（前台进行2次转码，目的：中文会出现乱码现象）
				var fileName2 = encodeURI(encodeURI(fileName));
				param = "fileName=" + fileName2;
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

	this.deleteFile = function(fileName,rowIndex) {
		fileName = encodeURI(encodeURI(fileName));
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