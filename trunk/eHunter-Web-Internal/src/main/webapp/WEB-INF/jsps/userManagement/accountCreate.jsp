<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
<script type="text/javascript">
function clearInput(){
	$('#loginId').val('');
	$('#staffId').val('');
	$('#cnName').val('');
	$('#enName').val('');
	$('#email').val('');
	$('#mobile').val('');
	$('#roleStr').val('');
	$('#password').val('');
	$('#confirmPassword').val('');
}
</script>
</head>
<body>
<hdiv-c:url value="/usrMgmt/searchInternalUsers.do?fromCreate=true" var="searchUrl"></hdiv-c:url>
<form:form id="createUserAcountForm" commandName="internalUserDto" action="${ctx}/usrMgmt/saveUserAccount.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">用户管理 - 账户创建</td>
			</tr>
			<tr>
				<td><common:errorTable path="internalUserDto"></common:errorTable></td>
			</tr>
		</table>
		<table border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<input class="standardButton" type="submit" value="保存" />&nbsp;
							<input class="standardButton" type="button" value="重置" onclick="clearInput();"/>&nbsp;
							<input class="standardButton" type="button" value="返回" onclick="location.href='${searchUrl}'"/>&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">登录名：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="loginId" cssClass="standardInputText"  /> 
						   <common:errorSign id="loginId" path="loginId"></common:errorSign>
						</td>
						<td class="labelColumn">员工号：</td>
						<td>
						   <form:input path="staffId" cssClass="standardInputText"  /> 
						   <common:errorSign id="staffId" path="staffId"></common:errorSign>
					    </td>
					</tr>
				    <tr >
						<td class="labelColumn">中文名：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="cnName" cssClass="standardInputText"  /> 
						   <common:errorSign id="cnName" path="cnName"></common:errorSign>
						</td>
						<td class="labelColumn">英文名：</td>
						<td>
						   <form:input path="enName" cssClass="standardInputText"  /> 
						   <common:errorSign id="enName" path="enName"></common:errorSign>
						</td>
					</tr>
					<tr>
					    <td class="labelColumn">邮箱：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="email" cssClass="standardInputText"  /> 
						   <common:errorSign id="email" path="email"></common:errorSign>
						</td>
					    <td class="labelColumn">手机：</td>
						<td>
						   <form:input path="mobile" cssClass="standardInputText" maxlength="12"/> 
						   <common:errorSign id="mobile" path="mobile"></common:errorSign>
						</td>
					</tr>
					<tr>
						<td class="labelColumn">用户角色：<span class="mandatoryField">*</span></td>
						<td>
					       <form:select path="roleStr" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfRole }" var="role">
						         <form:option value="${role.sysRefRole }" label="${role.roleName }"></form:option>
						      </c:forEach>
						   </form:select>
						   <common:errorSign id="roleStr" path="roleStr"></common:errorSign>
						</td>
						<td colspan="2"></td>
					</tr>
				</tbody>
		</table>		
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">密码：<span class="mandatoryField">*</span></td>
						<td>
						   <form:password path="password" size="30" maxlength="8" cssClass="standardInputText"/>
						   <common:errorSign id="password" path="password"></common:errorSign>
						</td>
						<td colspan="2"></td>
					</tr>
				    <tr >
						<td class="labelColumn">确认密码：<span class="mandatoryField">*</span></td>
						<td>
						   <form:password path="confirmPassword" size="30" maxlength="8" cssClass="standardInputText"/>
						   <common:errorSign id="confirmPassword" path="confirmPassword"></common:errorSign>
						</td>
						<td colspan="2"></td>
					</tr>
				</tbody>
		</table>			
		<div class="emptyBlock"></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		   <tr>
		      <td><b>Point(s) to note:</b><br> 
	                                     密码长度必须是8个字符，包括字母和数字字符。<br/>
	              Length of password must be 8 characters consisting of both alphabetical and numeric characters.
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
							<input class="standardButton" type="button" value="重置" onclick="clearInput();"/>&nbsp;
							<input class="standardButton" type="button" value="返回" onclick="location.href='${searchUrl}'"/>&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>