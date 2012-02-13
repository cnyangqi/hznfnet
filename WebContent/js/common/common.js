/** fix easyui 1.2.4 view bug */
$.parser.onComplete = function() {
	$('body').css('visibility', 'visible');
};

/** close browser window */
function closeWindow() {
	window.top.opener = null;// 无需用户确认直接关闭
	window.close();
}

/** show slide message */
function showMsg(msg) {
	$.messager.show({
		title : '提示',
		msg : msg,
		timeout : 5000,
		showType : 'slide'
	});
}