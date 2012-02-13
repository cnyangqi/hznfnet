<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content") != null ? request
			.getParameter("content") : "";
	String ctx = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>发布文章</title>
<link rel="stylesheet" href="${ctx}/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${ctx}/js/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="${ctx}/js/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${ctx}/js/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${ctx}/js/kindeditor/plugins/code/prettify.js"></script>

<script type="text/javascript" src="${ctx}/js/common/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/jquery.form.js"></script>
<script>
	var editor;

	KindEditor.ready(function(K) {
			editor = K.create('textarea[name="content"]', {
			cssPath : '${ctx}/js/kindeditor/plugins/code/prettify.css',
			uploadJson : '${ctx}/kindeditor/upload',
			fileManagerJson : '${ctx}/kindeditor/list',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['article'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['article'].submit();
				});
			}
		});
			
		prettyPrint();
		
		$('#article').ajaxForm({
			dataType : 'json',
			beforeSubmit: function(arr, $form, options){
				editor.sync();
			},
			success : function(data) {
				if (data.rtn) {
					alert('发布文章成功。');
					
					$('#article').clearForm();
					editor.html('');
					
				}else{
					alert('发布文章失败。');
				}
			}
		});
		
	});
	
</script>
</head>
<body>

	<form id="article" name="article" method="post" action="/article/add">
		文章标题：<input name="title" style="width: 80%;" /> <br /> <br />
		文章类型：
		<select name="type" style="margin-left: -9px;">
			<option value="1">网站新闻</option>
		</select>
		<br /> <br />
		作&nbsp;&nbsp;&nbsp;&nbsp;者：<input name="author" style="width: 20%;" /> <br /> <br />
		来&nbsp;&nbsp;&nbsp;&nbsp;源：<input name="source" style="width: 20%;" /> <br /> <br />
		<textarea name="content" cols="100" rows="8" style="width: 700px; height: 350px; visibility: hidden;"></textarea>
		<br />
		<font style="font-size: 12px; float: right; margin-top: 5px;">(提交快捷键: Ctrl + Enter)</font>
		<input type="submit" name="submitBtn" value="发布文章" style="float: right;" />
	</form>
</body>
</html>
