<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
</head>
<body>
<hdiv-c:url value="/usrMgmt/preResetPassword.do?_id=${internalUserDto.userRecId }" var="resetPasswordUrl"></hdiv-c:url>
<form:form id="accountMaintenanceForm" commandName="internalUserDto" action="${ctx}/usrMgmt/updateInternalUser.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">用户管理 - 账户修改</td>
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
							<input class="standardButton" type="button" value="重置密码" onclick="location.href='${resetPasswordUrl}'"/>&nbsp;
							<input class="standardButton" type="submit" value="更新" />&nbsp;
							<input class="standardButton" type="button" value="返回" onclick="location.href='${searchUrl}'"/>&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
		   <common:standardTableRow />
		   <tr>
		      <td class="labelColumn">用户编号：</td>
			  <td><c:out value="${internalUserDto.userRecId }" escapeXml="true"></c:out></td>
			  <td colspan="2"></td>
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
						   <form:input path="mobile" cssClass="standardInputText"  /> 
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
	    <div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<input class="standardButton" type="button" value="重置密码" onclick="location.href='${resetPasswordUrl}'"/>&nbsp;
							<input class="standardButton" type="submit" value="更新" />&nbsp;
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