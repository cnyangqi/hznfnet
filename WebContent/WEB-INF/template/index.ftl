<#import "/common.ftl" as common>

<#-- 统一过滤转义变量中的特殊HTML字符 -->
<#escape x as x?html>

<@common.page site="${site}" title="${title}">

<#-- body内容 -->
<div class="index">
<div class="container">

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
	<div class="container-upper-left">
		<div class="container-upper-left-nav">
			<ul>
				<li><a href="corporationDistribution.html"><img alt="" src="images/sleft-nav1.jpg" border="0" /></a></li>
				<li style="padding-left: 70px;"><a href="baseIntroduction.html"><img alt="" src="images/sleft-nav2.jpg" border="0" /></a></li>
                <li style="padding-left: 70px;"><a href="dataServices.html"><img alt="" src="images/sleft-nav3.jpg" border="0" /></a></li>
			</ul>
		</div>
		<div class="container-upper-left-img">
			<ul>
				<li><a href="corporationDistribution.html"><img alt="" src="images/tu1.jpg" border="0" /></a></li>
				<li><a href="baseIntroduction.html"><img alt="" src="images/tu2.jpg" border="0" /></a></li>
				<li><a href="dataServices.html"><img alt="" src="images/tu3.jpg" border="0" /></a></li>
			</ul>
		</div>
	</div>
	<div class="container-upper-right">
		<div class="introduction"><img alt="" src="images/jianjie.jpg" width="432" height="225" /></div>
		<div class="container-upper-right-button">
			<a href="chairmanspeech.html"><img alt="" src="images/danniu.jpg" width="117" height="37" border="0" /></a>
		</div>
	</div>
</div>

<div class="container-lower">
	<div class="container-lower-left">
		<div class="container-lower-new">
			<ul>
				<#list result as article>
					<li><span>
					<a href="${article.url}" target="_blank" title="${article.title}"</a>
					<#if article.title?length lt 18>
						${article.title}
						<#else>
						${article.title[0..18]}...
					</#if>
					</span></li>
				</#list>
			</ul>
		</div>
		
		<div class="container-lower-button">
			<a href="newsList1.html">
			<img alt="" src="images/xanniu.jpg" border="0" /></a></div>
		</div>
		
		<div class="container-lower-middle">
			<div class="container-lower-middle-picture"><img alt="" src="images/xtu1.jpg" /></div>
			<div class="container-lower-middle-text">&nbsp;&nbsp;丰富的商品，优惠的特价商品，满足您 采购的绝大部分需求询价、议价，短信免费 提示，采购到符合您要求的商品。</div>
			<div class="container-lower-middle-button"><a href="corporationDistribution.html"><img src="images/xanniu.jpg" alt="1" border="0" /></a></div>
		</div>
		
		<div class="container-lower-right">
			<div class="container-lower-middle-picture"><img alt="" src="images/xtu2.jpg" /></div>
			<div class="container-lower-right-text">&nbsp;&nbsp; 做优秀的农副产品及食品类垂直领域BSC 平台，这就是想购网的定位！想购网是依托杭 州农副产品物流园区而创立的专业电子商务网 …</div>
			<div class="container-lower-right-button"><a href="basicBusiness.html"><img src="images/xanniu.jpg" alt="1" border="0" /></a></div>
		</div>
</div>
            
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

</@common.page>

</#escape>