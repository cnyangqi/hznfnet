var saveArticleUrl='/article/add';

var editor;


KindEditor.ready(function(K) {
	editor = K.create('textarea[name="content"]', {
		cssPath : ctx+'/js/kindeditor/plugins/code/prettify.css',
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

$(function() {

	// 文章异步提交
	$('#article').form({
		url : ctx + saveArticleUrl,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			if (data.rtn) {
				showMsg('发布文章成功。');

				$('#article').clearForm();
				editor.html('');

			} else {
				showMsg('发布文章失败。');
			}
		}
	});

});