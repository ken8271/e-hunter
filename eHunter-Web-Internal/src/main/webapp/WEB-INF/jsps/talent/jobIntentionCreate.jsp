<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
    <hdiv-c:url value="/talent/backToFillResume.do" var="backUrl"></hdiv-c:url>
	<form:form commandName="intentionDto" action="${ctx}/talent/completeAddJobIntention.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">求职意向填写/编辑</td>
			</tr>
			<tr>
				<td><common:errorTable path="intentionDto"></common:errorTable></td>
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
						<td class="labelColumn">期望工作性质：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						<form:radiobutton path="employmentCategory" value="M" /> &nbsp;全职&nbsp;&nbsp;&nbsp;
						<form:radiobutton path="employmentCategory" value="F" /> &nbsp;兼职&nbsp;&nbsp;&nbsp;
						<form:radiobutton path="employmentCategory" value="F" /> &nbsp;实习&nbsp;&nbsp;&nbsp;
						<common:errorSign id="employmentCategory" path="employmentCategory"></common:errorSign>
						</td>
					</tr>
				    <tr >
						<td class="labelColumn">期望工作地点：<span class="mandatoryField">*</span></td>
						<td colspan="2">
						   <form:input path="expectAddress" cssClass="standardInputText"  /> 
					       <common:errorSign id="expectAddress" path="expectAddress"></common:errorSign>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">期望从事职业：<span class="mandatoryField">*</span></td>
						<td>
						  <form:select path="expectPosition" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfPositionCategory }" var="positionCategory">
						         <form:option value="${positionCategory.typeCode }" label="${positionCategory.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						  <common:errorSign id="expectPosition" path="expectPosition"></common:errorSign>
						</td>
						<td class="labelColumn">期望从事行业：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="expectIndustry" cssClass="standardSelect" >
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfIndustryCategory }" var="industryCategory">
						         <form:option value="${industryCategory.categoryCode }" label="${industryCategory.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						   <common:errorSign id="expectIndustry" path="expectIndustry"></common:errorSign>
						</td>
					</tr>
					<tr >
					    <td class="labelColumn">期望月薪(税前)：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="expectSalary" cssClass="standardInputTextNoWidth"/>&nbsp;千元/月
						   <common:errorSign id="expectSalary" path="expectSalary"></common:errorSign>
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