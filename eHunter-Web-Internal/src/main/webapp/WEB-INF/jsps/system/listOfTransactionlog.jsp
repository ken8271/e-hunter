<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0101]</title>
<script type="text/javascript">
function clearInput(){
	$(':text').val('');
}
</script>
<common:jmesaScript actionFlagStr="90"></common:jmesaScript>
</head>
<body>
<form:form commandName="txlogEnquireDto" action="${ctx}/system/searchTransactionlog.do">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">用户操作日志查询</td>
			</tr>
			<tr>
				<td><common:errorTable path="txlogEnquireDto"></common:errorTable></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="查询" />&nbsp;
								<input class="standardButton" type="button" value="重置" onclick="clearInput();"/>&nbsp;
								<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			<tbody>
				<tr>
				   <td width="16%" class="labelColumn">操作时间从：<br/>(YYYY-MM-DD HH:MM)</td>
				   <td width="34%" >
				      <common:inputDateTime dateYY="fromDto.year" dateMON="fromDto.month" dateDD="fromDto.day" dateHH="fromDto.hour" dateMM="fromDto.minute"></common:inputDateTime>
				      <common:errorSign id="fromDto.day" path="fromDto.day"></common:errorSign>
				   </td>
				   <td width="16%" class="labelColumn">至：<br/>(YYYY-MM-DD HH:MM)</td>
				   <td width="34%">
				      <common:inputDateTime dateYY="toDto.year" dateMON="toDto.month" dateDD="toDto.day" dateHH="toDto.hour" dateMM="toDto.minute"></common:inputDateTime>
				      <common:errorSign id="toDto.day" path="toDto.day"></common:errorSign>
				   </td>
			    </tr>
			    <tr>
				   <td width="16%" class="labelColumn">操作用户：</td>
				   <td width="34%">
				      <form:input path="user" cssClass="standardInputText"  /> 
				   </td>
				   <td width="16%" class="labelColumn">操作模块：</td>
				   <td width="34%">
				       <form:select path="module" cssClass="standardSelectNoWidth" cssStyle="width:50%">
						      <form:option value="" label="--- 请选择  ---" cssStyle="align:center"></form:option>
						      <form:option value="C" label="--- 客户管理  ---"></form:option>
						      <form:option value="T" label="--- 人才管理  ---"></form:option>
						      <form:option value="P" label="--- 项目管理  ---"></form:option>
						      <form:option value="R" label="--- 报表管理  ---"></form:option>
						      <form:option value="S" label="--- 系统管理  ---"></form:option>
					   </form:select>
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
							<td><input class="standardButton" type="submit" value="查询" />&nbsp;
							    <input class="standardButton" type="button" value="重置" onclick="clearInput();"/>&nbsp;
								<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div>${listOfTransactionlog }</div>
</form:form>
</body>
</html>
