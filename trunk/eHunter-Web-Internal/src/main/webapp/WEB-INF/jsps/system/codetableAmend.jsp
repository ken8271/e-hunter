<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
</head>
<body>
<hdiv-c:url value="/system/viewCodetableDetail.do?_id=${codetableDto.id }" var="backUrl"></hdiv-c:url>
<form:form commandName="codetableDto" action="${ctx}/system/updateCodetable.do" method="post">
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">代码表维护</td>
		</tr>
		<tr>
			<td><common:errorTable path="codetableDto"></common:errorTable></td>
		</tr>
	</table>
	<table border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="submit" value="更新" />&nbsp;
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
				<td class="labelColumn">代码表编号：</td>
				<td colspan="3"><c:out value="${codetableDto.id }" escapeXml="true"></c:out></td>
			</tr>
			<tr>
				<td class="labelColumn">代码表名称：</td>
				<td colspan="3">
				   <form:input path="name" cssClass="standardInputText" />
				   <common:errorSign id="name" path="name"></common:errorSign>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">数据库表名称：</td>
				<td colspan="3">
				   <form:input path="reference" cssClass="standardInputText" />
				   <common:errorSign id="reference" path="reference"></common:errorSign>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">代码表描述：</td>
				<td colspan="3">
				   <form:input path="description" cssClass="standardInputText" />
				   <common:errorSign id="description" path="description"></common:errorSign>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">是否启用：</td>
				<td colspan="3">
				   <form:radiobutton path="activeIndicator" value="Y" /> 是&nbsp;&nbsp;
				   <form:radiobutton path="activeIndicator" value="N" /> 否&nbsp;&nbsp;
				   <common:errorSign id="activeIndicator" path="activeIndicator"></common:errorSign>
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
						    <input class="standardButton" type="submit" value="更新" />&nbsp;
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