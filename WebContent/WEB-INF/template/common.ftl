<#-- 定义page宏，title为参数 -->
<#macro page site title>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#-- ?html用于将字符串中可能包含的HTML特殊字符进行过滤转义 -->
	<title>${site?html} - ${title?html}</title>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="styles/main.css" />
    <link rel="stylesheet" type="text/css" href="styles/css.css" />
</head>
<body>
    <#nested>
</body>
</html>
</#macro>