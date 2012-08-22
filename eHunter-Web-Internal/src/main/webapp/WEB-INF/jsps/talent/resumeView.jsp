<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<resume:baseinfo></resume:baseinfo>
		<div class="resumesLookAll">
		    <resume:jobIntention></resume:jobIntention>
			<resume:selfEvaluation></resume:selfEvaluation>
			<resume:jobExperience></resume:jobExperience>
			<resume:educationExperience></resume:educationExperience>
			<resume:trainingExperience></resume:trainingExperience>
			<resume:projectExperience></resume:projectExperience>
			<resume:languageAbility></resume:languageAbility>
			<resume:professionalSkill></resume:professionalSkill>
			<resume:cert></resume:cert>
		</div>
	</div>
</body>
</html>