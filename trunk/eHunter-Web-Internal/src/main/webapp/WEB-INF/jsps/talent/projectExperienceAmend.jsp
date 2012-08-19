<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
    <hdiv-c:url value="/talent/backToFillProjectExperience.do" var="backUrl"></hdiv-c:url>
	<form:form commandName="prjExpDto" action="${ctx}/talent/completeEditProjectExperience.do" method="post">
		<table border="0" width="99%">
			<tr>
				<td class="pageTitle">人才项目经验编辑</td>
			</tr>
			<tr>
				<td><common:errorTable path="prjExpDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="99%">
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
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="99%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">时间：<span class="mandatoryField">*</span></td>
						<td>
						<common:inputDate dateYY="fromDateDto.year" dateMON="fromDateDto.month" dateDD="fromDateDto.day" ></common:inputDate>
						<common:errorSign id="fromDateDto.day" path="fromDateDto.day"></common:errorSign>
						</td>
						<td class="labelColumn">至：<span class="mandatoryField">*</span></td>
						<td>
						<common:inputDate dateYY="toDateDto.year" dateMON="toDateDto.month" dateDD="toDateDto.day" ></common:inputDate>
						<common:errorSign id="toDateDto.day" path="toDateDto.day"></common:errorSign>
					    </td>
					</tr>
				    <tr >
						<td class="labelColumn">项目名称：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="name" cssClass="standardInputText"  /> 
					       <common:errorSign id="name" path="name"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">职责描述：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						  <form:textarea path="duty" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>300){this.value = this.value.substring(0, 300)}" cssClass="standardInputText"/>
						  <common:errorSign id="duty" path="duty"></common:errorSign>
						</td>
					</tr>
					<tr >
						<td class="labelColumn">项目描述：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						   <form:textarea path="description" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>300){this.value = this.value.substring(0, 300)}" cssClass="standardInputText"/>
						   <common:errorSign id="description" path="description"></common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>			
		</div>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="99%">
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