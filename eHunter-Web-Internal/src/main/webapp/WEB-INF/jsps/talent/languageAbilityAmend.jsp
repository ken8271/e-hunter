<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
    <hdiv-c:url value="/talent/backToFillLanguageAbility.do" var="backUrl"></hdiv-c:url>
	<form:form commandName="languageDto" action="${ctx}/talent/completeEditLanguageAbility.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">语言能力编辑</td>
			</tr>
			<tr>
				<td><common:errorTable path="languageDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<input class="standardButton" type="submit" value="确认"/>&nbsp;
							<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">外语语种：<span class="mandatoryField">*</span></td>
						<td>
						<form:select path="languageCategory" cssClass="standardSelect" >
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfLanguageCategory }" var="languageCategory">
						         <form:option value="${languageCategory.code }" label="${languageCategory.displayName }"></form:option>
						      </c:forEach>
						</form:select>
						<common:errorSign id="languageCategory" path="languageCategory"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				    <tr >
						<td class="labelColumn">读写能力：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="ablitityOfRW" cssClass="standardSelect" >
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfSkillLevel }" var="level">
						         <form:option value="${level.code }" label="${level.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
					       <common:errorSign id="ablitityOfRW" path="ablitityOfRW"></common:errorSign>
						</td>
						<td class="labelColumn">听说能力：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="ablitityOfLS" cssClass="standardSelect" >
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfSkillLevel }" var="level">
						         <form:option value="${level.code }" label="${level.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
					       <common:errorSign id="ablitityOfLS" path="ablitityOfLS"></common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<input class="standardButton" type="submit" value="确认"/>&nbsp;
							<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>