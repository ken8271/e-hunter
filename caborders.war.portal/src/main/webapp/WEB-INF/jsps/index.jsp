<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="baidu_union_verify" content="160d95d45a9005d7d764e2b3a5a5143e">
<link rel="stylesheet" rev="stylesheet" href="${ctx }/style/banner.css" type="text/css" media="all" />
<link rel="stylesheet" rev="stylesheet" href="${ctx }/style/default.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/script/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery.easing.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery.slider.js"></script>
<title>深圳市铠博德企业管理咨询有限公司</title>
<script type="text/javascript"> 
			$(function(){
				$('.j_Slide ul').slide({
					slidetag : $('.j_Slide ul > li'),
					easing : 'easeOutExpo',
					direct : 'up',
					speed : 450
				});
				
				$('.navigation li').each(function(){
					$(this).removeClass('this');
				});
				
				$('.navigation li:first').addClass('this');
			});
		</script> 
</head>
<body>
<!--header-->
<div class="header">
   <ul class="navigation">
      <li rel="1"><a href="${ctx }/landing_page.html">首页</a></li>
	  <li rel="2"><a href="${ctx }/solutions/index.html">人力资源解决方案</a></li>							
	  <li rel="7"><a href="${ctx }/career/index.html">职场管家</a></li>							
	  <li rel="18"><a href="${ctx }/infocenter/index.html">资讯中心</a></li>							
	  <li rel="41"><a href="${ctx}/partners/index.html">合作伙伴</a></li>
	  <li rel="24"><a href="${ctx }/about/index.html">关于我们</a></li>						
   </ul>
</div>

<!--content-->
<div id="IBanner" class="j_Slide loading"> 
   <ul class="tb-slide-list"> 
      <li><a href=""><img src="${imagePath}/bg01.jpg" target="_blank" /></a></li>
      <li><a href=""><img src="${imagePath}/bg02.jpg" target="_blank" /></a></li>
      <li><a href=""><img src="${imagePath}/bg03.jpg" target="_blank" /></a></li>			
   </ul> 
</div> 

<div id="content">
   <div class="first_l">
      <!--最新职位板块开始-->
	  <div class="new_office">
	     <div style="height:40px; width:100%; ">
		    <div style="float:left">
			   <h4><a href="${ctx }/career/positions.html" style="color:#000;">最新职位</a></h4>
			</div>
			<div style="float:right; height:40px; line-height:40px;">
			   <a href="${ctx }/career/index.html">more&gt;&gt;</a>
			</div>
	     </div>
		 <ul>
		    <li>
			   <a href="#">某知名文化集团/顶级会所总经理/50-100万</a>
			   <p>工作地点：&nbsp; | &nbsp; 2012-09-14</p>
			</li>
	     </ul>
	  </div>
	  <!--最新职位板块结束-->
				  
	  <div class="news">
	     <!--资讯中心板块开始-->
	     <div style="height:40px; width:100%; ">
		    <div style="float:left"><h4><a href="${ctx }/infocenter/index.html" style="color:#000;">资讯中心</a></h4></div>
			<div style="float:right; height:40px; line-height:40px;"><a href="${ctx }/infocenter/index.html">more&gt;&gt;</a></div></div>
			<ul>
			   <li><cite>2012-09-20</cite><a href="#">文章标题</a></li>
			</ul>
		 </div>
		 <!--资讯中心板块结束-->
				
	     <!--关于我们板块开始-->
	     <div class="about">
	        <h4><a href="${ctx }/about/index.html" style="color:#000;">关于我们</a></h4>
		    <img src="${imagePath }/about.jpg" height="90" width="285" border="0"/>
		    <p style="color:#666;">公司简介...</p>
         </div>
	     <!--关于我们板块结束-->
				
	     <div class="clear"></div>
	  </div>

      <!--footer-->
	  <div id="footer" align="center">@2003 - 2012   深圳市铠博德企业管理咨询有限公司   备案号：0000000000</div>
   </div>
</div>
</body>
</html>
