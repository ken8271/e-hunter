<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
    <hdiv-c:url value="/talent/backToFillProfessionalSkill.do" var="backUrl"></hdiv-c:url>
	<form:form commandName="skillDto" action="${ctx}/talent/completeEditProfessionalSkill.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">专业技能编辑</td>
			</tr>
			<tr>
				<td><common:errorTable path="skillDto"></common:errorTable></td>
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
						<td class="labelColumn">技能类型/名称：<span class="mandatoryField">*</span></td>
						<td>
						<form:select path="categoryCode" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfSkillCategory }" var="skillCategory">
						         <form:option value="${skillCategory.code }" label="${skillCategory.displayName }"></form:option>
						      </c:forEach>
						</form:select>
						<common:errorSign id="categoryCode" path="categoryCode"></common:errorSign>
						</td>
						<td>
						   <form:input path="skillName" cssClass="standardInputText"  cssStyle="display:none"/> 
						   <common:errorSign id="skillName" path="skillName"></common:errorSign>
					    </td>
					    <td>&nbsp;</td>
					</tr>
				    <tr >
						<td class="labelColumn">使用时间：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="duration" cssClass="standardInputText"  />&nbsp;月
					       <common:errorSign id="duration" path="duration"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">掌握程度：<span class="mandatoryField">*</span></td>
						<td>
						  <form:select path="levelCode" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfSkillLevel }" var="skillLevel">
						         <form:option value="${skillLevel.code }" label="${skillLevel.displayName }"></form:option>
						      </c:forEach>
						  </form:select> 
						  <common:errorSign id="levelCode" path="levelCode"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
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