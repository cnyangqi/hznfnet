<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
String ctx=request.getContextPath();
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
	<link rel="stylesheet" href="${ctx}/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="${ctx}/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="${ctx}/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${ctx}/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${ctx}/kindeditor/plugins/code/prettify.js"></script>
	<script>
	
		KindEditor.ready(function(K) {
			var editor = K.create('textarea[name="content"]', {
				cssPath : '${ctx}/kindeditor/plugins/code/prettify.css',
				uploadJson : '${ctx}/kindeditor/upload',
				fileManagerJson : '${ctx}/kindeditor/list',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
	<%=htmlData%>
	<form name="example" method="post" action="/article/add">
		<textarea name="content" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
		<br />
		<input type="submit" name="button" value="提交内容" /> (提交快捷键: Ctrl + Enter)
	</form>
</body>
</html>
