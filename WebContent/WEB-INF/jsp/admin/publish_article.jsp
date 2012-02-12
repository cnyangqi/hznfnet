<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content") != null ? request.getParameter("content") : "";
String ctx=request.getContextPath();
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>发布文章</title>
	<link rel="stylesheet" href="${ctx}/script/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="${ctx}/script/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="${ctx}/script/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${ctx}/script/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${ctx}/script/kindeditor/plugins/code/prettify.js"></script>
	
	<script type="text/javascript" src="${ctx}/script/jquery/jquery-1.7.1.min.js"></script>
	<script>
	
		KindEditor.ready(function(K) {
			var editor = K.create('textarea[name="content"]', {
				cssPath : '${ctx}/script/kindeditor/plugins/code/prettify.css',
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
		});
	</script>
</head>
<body>

	<form name="article" method="post" action="/article/add">
		文章标题：<input style="width: 80%;" />
		<br/><br/>
		文章类型：
		<select style="margin-left: -9px;">
  			<option value ="news">网站新闻</option>
		</select>
		<br/><br/>
		<textarea name="content" cols="100" rows="8" style="width:700px;height:350px;visibility:hidden;"></textarea>
		<br />
		<font style="font-size: 12px;float: right;margin-top: 5px;" >(提交快捷键: Ctrl + Enter)</font>
		<input type="submit" name="button" value="发布文章" style="float: right;"/> 
	</form>
</body>
</html>
