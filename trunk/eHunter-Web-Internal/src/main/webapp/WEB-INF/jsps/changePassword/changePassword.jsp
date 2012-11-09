<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
<script type="text/javascript">
function clearInput(){
	$('#password').val('');
	$('#confirmPassword').val('');
}
</script>
</head>
<body>
	<form:form id="changePasswordForm" commandName="changePasswordDto" action="${ctx}/changePassword.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">修改密码</td>
			</tr>
			<tr>
				<td><common:errorTable path="changePasswordDto"></common:errorTable></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							   <input class="standardButton" type="submit" value="保存" />&nbsp;
							   <input class="standardButton" type="button" value="重置" onclick="clearInput();" />&nbsp; 
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${editUrl}'" />&nbsp;
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
					<td class="labelColumn">原密码：<span class="mandatoryField">*</span></td>
					<td>
					   <form:password path="oldPassword" size="30" maxlength="8" cssClass="standardInputText" /> 
					   <common:errorSign id="oldPassword" path="oldPassword"></common:errorSign>
					</td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td class="labelColumn">新密码：<span class="mandatoryField">*</span></td>
					<td>
					   <form:password path="newPassword" size="30" maxlength="8" cssClass="standardInputText" /> 
					   <common:errorSign id="newPassword" path="newPassword"></common:errorSign>
					</td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td class="labelColumn">确认密码：<span class="mandatoryField">*</span></td>
					<td>
					   <form:password path="confirmPassword" size="30" maxlength="8" cssClass="standardInputText" /> 
					   <common:errorSign id="confirmPassword" path="confirmPassword"></common:errorSign>
					</td>
					<td colspan="2"></td>
				</tr>
			</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				   <b>Point(s) to note:</b><br>  密码长度必须是8个字符，包括字母和数字字符。<br />Length of password must be 8 characters consisting of both alphabetical and numeric characters.
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							   <input class="standardButton" type="submit" value="保存" />&nbsp;
							   <input class="standardButton" type="button" value="重置" onclick="clearInput();" />&nbsp; 
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${editUrl}'" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>