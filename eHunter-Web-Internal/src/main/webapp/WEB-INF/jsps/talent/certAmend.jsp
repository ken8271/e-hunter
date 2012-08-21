<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
    <hdiv-c:url value="/talent/backToFillCert.do" var="backUrl"></hdiv-c:url>
	<form:form commandName="certDto" action="${ctx}/talent/completeEditCert.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">证书编辑</td>
			</tr>
			<tr>
				<td><common:errorTable path="certDto"></common:errorTable></td>
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
				    <tr >
						<td class="labelColumn">证书名称：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="certName" cssClass="standardInputText"  /> 
					       <common:errorSign id="certName" path="certName"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">获得时间：<span class="mandatoryField">*</span></td>
						<td>
						<common:inputDate dateYY="gainedDateDto.year" dateMON="gainedDateDto.month" dateDD="gainedDateDto.day" ></common:inputDate>
						<common:errorSign id="gainedDateDto.day" path="gainedDateDto.day"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>   
					<tr>
						<td class="labelColumn">说明：</td>
						<td colspan="3">
						  <form:textarea path="description" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>300){this.value = this.value.substring(0, 300)}" cssClass="standardInputText"/>
						  <common:errorSign id="description" path="description"></common:errorSign>
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