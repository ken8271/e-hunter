<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/meta.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ taglib tagdir="/WEB-INF/tags/talent/resume" prefix="resume"%>
<html>
<head>
<link rel="stylesheet" href="${ctx}/style/resume.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
	<div class="resumesLook">
		<resume:baseinfo path="${talentDto }"></resume:baseinfo>
		<div class="resumesLookAll">
		    <resume:jobIntention></resume:jobIntention>
			<resume:selfEvaluation></resume:selfEvaluation>
			<resume:jobExperience></resume:jobExperience>
			<resume:educationExperience></resume:educationExperience>
			<div class="resumesLookMain" style="display: none">
				<div class="resumesLookTitle">项目经验</div>
			</div>
			<div class="resumesLookMain" style="display: none">
				<div class="resumesLookTitle">培训经历</div>
			</div>
			<div class="resumesLookMain" style="display: none">
				<div class="resumesLookTitle">证书</div>
			</div>
			<div class="resumesLookMain">
				<div class="resumesLookTitle">语言能力</div>
			</div>
			<div class="resumesLookMain">
				<div class="lookLeftList notStrong">
					<ul>
						<li class="lookBordernone">英语：</li>
						<li>读写能力良好</li>
						<li class="lookBordernone">听说能力良好</li>
					</ul>
				</div>
			</div>
			<div class="resumesLookMain">
				<div class="resumesLookTitle">专业技能</div>
			</div>
			<div class="resumesLookMain">
				<div class="lookLeftList notStrong">
					<ul>
						<li class="lookBordernone">C++</li>
						<li>熟练</li>
						<li class="lookBordernone">60</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>