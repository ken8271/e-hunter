<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
</head>
<body>
<form:form commandName="talentSrcDto" action="${ctx}/system/submitNewTalentSource.do" method="post">
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">人才来源创建</td>
		</tr>
		<tr>
			<td><common:errorTable path="talentSrcDto"></common:errorTable></td>
		</tr>
	</table>
	<table border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="submit" value="保存" />&nbsp;
						    <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'"/>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<common:standardTableRow />
			<tr>
				<td class="labelColumn">名称(显示名称)：<span class="mandatoryField">*</span></td>
				<td colspan="3">
				   <form:input path="displayName" cssClass="standardInputText" />
				   <common:errorSign id="displayName" path="displayName"></common:errorSign>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">官方网站：</td>
				<td colspan="3">
				   <form:input path="displayName" cssClass="standardInputText" />
				   <common:errorSign id="displayName" path="displayName"></common:errorSign>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="emptyBlock"></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="submit" value="保存" />&nbsp;
						    <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'"/>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form:form>
</body>
</html>