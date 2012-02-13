<#-- 统一过滤转义变量中的特殊HTML字符 -->
<#escape x as x?html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${site} | ${title}</title>
    <link rel="stylesheet" type="text/css" href="styles/main.css" />
    <link href="styles/subpage.css" rel="stylesheet" type="text/css" />
    <link href="styles/leftsidebar.css" rel="stylesheet" type="text/css" />
</head>
<body>

<#-- body内容 -->
<div class="aboutus">
<div class="container" id="newsDetail">

<div class="header">
    <div class="logo">
        <img alt="" src="images/logo.jpg" />
    </div>
    <div class="nav">
        <ul>
            <li><a href="index.html" class="nav1-a"></a></li>
            <li><a href="chairmanspeech.html" class="nav2-a"></a></li>
            <li><a href="basicBusiness.html" class="nav3-a"></a></li>
            <li><a href="dataServices.html" class="nav4-a"></a></li>
            <li><a href="baseIntroduction.html" class="nav5-a"></a></li>
            <li><a href="importantCustomer.html" class="nav6-a"></a></li>
            <li><a href="contactUs.html" class="nav7-a"></a></li>
        </ul>
    </div>
</div>

<div class="container-upper">
                
<div class="left">
    <div class="leftnav">
        <ul>
            <li id="#leftnav1-a"><a href="chairmanspeech.html" class="leftnav1-a"></a></li>
            <li><a href="areaIntroduction.html" class="leftnav2-a"></a></li>
            <li><a href="companyIntroduction.html" class="leftnav3-a"></a></li>
            <li><a href="qualification.html" class="leftnav4-a"></a></li>
            <li><a href="companyTarget.html" class="leftnav15-a"></a></li>
            <li><a href="newsList.html" class="leftnav5-a"></a></li>
            <li><img alt="" src="images/leftline.jpg" /></li>
			<li></li>
			<li></li>
			<li></li>
			<li><img alt="" src="images/left-2-1.jpg" /></li>
			<li><img alt="" src="images/left-2-2.jpg" /></li>
			<li><img alt="" src="images/left-2-3.jpg" /></li>
			<li><img alt="" src="images/left-2-4.jpg" /></li>
			<li><img alt="" src="images/left-2-5.jpg" /></li>
			<li><img alt="" src="images/leftline.jpg" /></li>
        </ul>
    </div>
</div>

<div class="subpage">
	<div class="subpage-news">
		<div class="subpage-news-title">${article.title}</div>
		<div class="subpage-news-time">${article.createDate?date}</div>
		<div class="subpage-news-content">
			<#noescape>${article.content}</#noescape>
		</div>
	</div>
</div>

</div>
</div>
        
<div id="end" class="end">
<div id="footer" class="footer">
	<div id="ftxet" class="ftext">
		<p align="left">
		地址：杭州市余杭区良渚镇博园路28号粮油市场办公大楼四楼<br />
		Copyright©2010-2012 版权所有 All Rights Reserved.<br />
		中国·杭州 浙ICP备09076267号
		</p>
	</div>
</div>
</div>

</div>
</body>
</html>
</#escape>
