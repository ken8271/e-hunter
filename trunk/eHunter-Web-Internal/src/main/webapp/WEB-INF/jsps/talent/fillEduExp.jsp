<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
	<form:form commandName="eduExpDto" action="${ctx}/talent/saveTalentBaseInfo.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">人才教育经历填写</td>
			</tr>
			<tr>
				<td><common:errorTable path="eduExpDto"></common:errorTable></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>							   
							   <input class="standardButton" type="submit" value="保存" />&nbsp;
							   <input class="standardButton" type="reset" value="重置">&nbsp;
							   <input class="standardButton" type="button" value="结束">
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
						<td class="labelColumn">时间：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="fromDateDto.year" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/>&nbsp;-&nbsp; 
						<form:input path="fromDateDto.month" cssClass="standardInputTextNoWidth" maxlength="2" size="2"/>&nbsp;-&nbsp; 
						<form:input path="fromDateDto.day" cssClass="standardInputTextNoWidth" maxlength="2" size="2"/>
						<common:errorSign id="fromDateDto.day" path="fromDateDto.day"></common:errorSign>
						</td>
						<td class="labelColumn">至：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="toDateDto.year" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/>&nbsp;-&nbsp; 
						<form:input path="toDateDto.month" cssClass="standardInputTextNoWidth" maxlength="2" size="2"/>&nbsp;-&nbsp; 
						<form:input path="toDateDto.day" cssClass="standardInputTextNoWidth" maxlength="2" size="2"/>
						<common:errorSign id="toDateDto.day" path="toDateDto.day"></common:errorSign>
					    </td>
					</tr>
				    <tr >
						<td class="labelColumn">学校名称：</td>
						<td>
						   <form:input path="school" cssClass="standardInputText"  /> 
					       <common:errorSign id="school" path="school"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">专业名称：<span class="mandatoryField">*</span></td>
						<td>
						   <select  class="standardSelect" id="majorSelector" onchange="loadPositions();">
						   <option value="">--- 请选择 ---</option>
						   <c:forEach items="${listOfPositionType}" var="postType">
						      <option value="${postType.value }">${postType.label }</option>
						   </c:forEach>
						   </select>
						</td>
						<td>
						   <form:select id="subMajorSelector" path="major" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						   </form:select>
						   <common:errorSign id="major" path="major"></common:errorSign>
						</td>
						<td >&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">学历/学位：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="degree" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						   </form:select>
						   <common:errorSign id="degree" path="degree"></common:errorSign>
						</td>
						<td >&nbsp;</td>
						<td >&nbsp;</td>
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
							   <input class="standardButton" type="submit" value="保存" />&nbsp;
							   <input class="standardButton" type="reset" value="重置">&nbsp;
							   <input class="standardButton" type="button" value="结束">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
	</form:form>
</body>
</html>