<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
</head>
<body>
<hdiv-c:url value="/system/listSystemParameters.do" var="backUrl"></hdiv-c:url>
<form:form commandName="systemParameterDto" action="${ctx}/system/submitSystemParameter.do" method="post">
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">系统参数表管理&nbsp;-&nbsp;参数创建</td>
		</tr>
		<tr>
			<td><common:errorTable path="systemParameterDto"></common:errorTable></td>
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
				<td class="labelColumn">参数代号：</td>
				<td colspan="3">
				   <form:input path="parameterKey" cssClass="standardInputText" />
				   <common:errorSign id="parameterKey" path="parameterKey"></common:errorSign>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">参数类型：</td>
				<td colspan="3">
				   <form:radiobutton path="valueType" value="N" /> 数字(Number)&nbsp;&nbsp;
				   <form:radiobutton path="valueType" value="S" /> 字符(串)(String/Character)&nbsp;&nbsp;
				   <form:radiobutton path="valueType" value="D" /> 日期(Date)&nbsp;&nbsp;
				   <common:errorSign id="valueType" path="valueType"></common:errorSign>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">参数值：</td>
				<td colspan="3">
				   <form:input path="value" cssClass="standardInputText" />
				   <common:errorSign id="value" path="value"></common:errorSign>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">参数描述：</td>
				<td colspan="3">
				   <form:input path="description" cssClass="standardInputText" />
				   <common:errorSign id="description" path="description"></common:errorSign>
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