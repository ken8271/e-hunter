<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0101]</title>
<script type="text/javascript">
function resetForm(){
	$("#talentID").val('');
	$("#name").val('');
}
</script>
<common:jmesaScript actionFlagStr="90"></common:jmesaScript>
</head>
<body>
<form:form commandName="talentEnquireDto" action="${ctx}/talent/candidatesSearch.do">
        <div style="display: none">
			<input type="hidden" id="actionFlag" name="actionFlag" />
		</div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">人才资料查询</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="查询" />&nbsp;
								<input class="standardButton" type="button" value="重置" onclick="resetForm();"/>&nbsp;
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
				<common:standardTableRow />
				<tr >
				   <td class="labelColumn">人才编号：</td>
				   <td><form:input path="talentID" cssClass="standardInputText"></form:input></td>
				   <td class="labelColumn">中文名/英文名：</td>
				   <td>
				      <form:input path="name" cssClass="standardInputText"></form:input>
				   </td>
			    </tr>
			</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">项目编号：</td>
						<td>
						<form:input path="systemProjectRefNum" cssClass="standardInputText"></form:input>
						</td>
						<td colspan="2"></td>
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
							    <input class="standardButton" type="button" value="重置" onclick="resetForm();"/>&nbsp;
								<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div>${listOfTalent }</div>
</form:form>
</body>
</html>
