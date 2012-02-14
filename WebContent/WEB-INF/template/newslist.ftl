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
<div class="aboutus">
<div class="container" id="newsList">

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
            <li><a href="newsList1.html" class="leftnav5-a"></a></li>
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

<div class="subpage" style="height: 710px;">
<div class="subpage-upper"><img alt="" src="images/yaowentu.jpg" width="635" height="151" /></div>

<div class="subpage-lower">
	<ul>
		<#list result as article>
			<li><label class="title"><a href="${article.url}">${article.title}</a></label>${article.createDate?date}</li>
		</#list>
	</ul>
</div>
                    
<div id="pager" class="asppager">

	<#-- 当分页只有1页或者分页是首页的时候不显示首页和上一页超链接 -->
	<#if (pager.pageCount>1) && (index>1)>
		<a class="firstlastbutton" href="newsList1.html" disabled="disabled" style="margin-right:5px;">首页</a>
		<a class="firstlastbutton" href="newsList${index-1}.html" disabled="disabled" style="margin-right:5px;">上一页</a>
	</#if>
	
	<#list 1..pager.pageCount as i>
		<#if i==index>
				<a href="newsList.html" class="active_tnt_link" style="margin-right:5px;">${index}</a>
			<#else>
				<a href="newsList${i}.html" class="pagebutton" style="margin-right:5px;">${i}</a>
		</#if>
	</#list>
	
	<#-- 当分页只有1页或者分页是最后1页的时候不显示下一页与尾页超链接 -->
	<#if (pager.pageCount>1) && (pager.pageCount!=index)>
		<a class="firstlastbutton" href="newsList${index+1}.html" style="margin-right:5px;">下一页</a>
		<a class="firstlastbutton" href="newsList${pager.pageCount}.html" style="margin-right:5px;">尾页</a>
	</#if>
	
</div>

</div>
            
</div>
</div>

<div class="end">
            
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
