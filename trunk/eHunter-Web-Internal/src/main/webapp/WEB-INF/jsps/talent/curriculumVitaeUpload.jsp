<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-PRJ-0001]</title>
</head>
<body>
 <hdiv-c:url value="/talent/viewTalentDetail.do?_id=${cvDto.talentID }" var="backUrl"></hdiv-c:url>
	<form:form commandName="cvDto" enctype="multipart/form-data" action="${ctx }/talent/submitCurriculumVitaeUpload.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">简历上传</td>
			</tr>
			<tr>
				<td><common:errorTable path="eduExpDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table border="0" width="100%">
			<tr>
				<td><common:errorTable path="cvDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
		   <tr>
		      <td width="20%" class="labelColumn">人才编号：</td>
		      <td>
		         <c:out value="${cvDto.talentID }" escapeXml="true"></c:out>
		      </td>
		   </tr>
		   <tr>
		       <td class="labelColumn">简历名称：<span class="mandatoryField">*</span></td>
		       <td>
		          <form:input path="cvName" cssClass="standardInputText"/>
		          <common:errorSign id="cvName" path="cvName"></common:errorSign>
		       </td>
		       <td class="labelColumn">语言版本：<span class="mandatoryField">*</span></td>
		       <td>
		         <form:radiobutton path="language" value="cn" /> 中文&nbsp;/
		         <form:radiobutton path="language" value="en" /> 英文&nbsp;
		         <common:errorSign id="language" path="language"></common:errorSign>
		       </td>
		   </tr>
		   <tr>
		      <td width="20%" class="labelColumn">上传文件：<span class="mandatoryField">*</span></td>
		      <td colspan="3">
		         <input name="uploadFile" type="file" value="" size="30" maxlength="10">
		         <common:errorSign id="type" path="type"></common:errorSign>
		         <common:errorSign id="size" path="size"></common:errorSign>
		      </td>
		   </tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />&nbsp;
							<input class="standardButton" type="submit" value="开始上传" />&nbsp;
							<input class="standardButton" type="button" value="取消上传" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>			
	</form:form>
</body>
</html>