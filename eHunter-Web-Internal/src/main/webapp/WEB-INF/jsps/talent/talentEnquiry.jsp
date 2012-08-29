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
	$("#gender").val('');
	$("#talentSrc").val('');
}
</script>
</head>
<body>
<form:form commandName="enquireDto" action="${ctx}/talent/talentsSearch.do">
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
				   <td colspan="2">&nbsp;</td>
			    </tr>
			</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">中文名/英文名：</td>
						<td>
						<form:input path="name" cssClass="standardInputText"></form:input>
						</td>
						<td class="labelColumn">性别：</td>
						<td>
						   <form:select path="gender" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <form:option value="M" label="男"></form:option>
						      <form:option value="F" label="女"></form:option>
						   </form:select>
					    </td>
					</tr>
					<tr >
						<td class="labelColumn">人才来源：</td>
						<td>
						   <form:select path="talentSrc"  cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfTalentSource }" var="src">
						         <form:option value="${src.sourceId }" label="${src.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						</td>
					    <td>&nbsp;</td>
						<td>&nbsp;</td>
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
