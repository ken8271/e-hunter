<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><common:screenID id="SY80903" /></title>
<hdiv-c:url value="/usrMgmt/searchInternalUsers.do" var="searchUrl"></hdiv-c:url>
<script type="text/javascript">
$().ready(function(){
	endList();
	if('${internalUserDto.lockedIndicator}' == 'Y'){
		$('#accountStatus').attr('disabled',true);
	}
	if('${isSuccess}' == 'Y'){
		alert('The account ${internalUserDto.loginName} is deleted successfully');
		//delete button
		$('#delete_top').attr('disabled' , true);
		$('#delete_bottom').attr('disabled' , true);
		//resetpassword button
		$('#resetpassword_top').attr('disabled' , true);
		$('#resetpassword_bottom').attr('disabled' , true);
		//roleassign button
		$('#roleassign_top').attr('disabled' , true);
		$('#roleassign_bottom').attr('disabled' , true);
		//save button
		$('#save_top').attr('disabled' , true);
		$('#save_bottom').attr('disabled' , true);
		//reset button
		$('#reset_top').attr('disabled' , true);
		$('#reset_bottom').attr('disabled' , true);
		//close button
	    $('#close_top').attr('disabled' , true);
		$('#close_bottom').attr('disabled' , true);
		var url = '${searchUrl}';
		window.location.href=url;
	}else if('${isSuccess}' == 'N'){
		alert('The account ${internalUserDto.loginName} is deleted failed , may be it has been deleted or cannot be found, please contact administrator.');
	}

	if('${updateSuccess}' == 'Y'){
		alert('The account ${internalUserDto.loginName} is updated successfully');
	}
});

function deleteAccount(path){
	if(confirm('Are you sure to delete the account ${internalUserDto.loginName} ?')){
		window.location.href = path;
		//delete button
		$('#delete_top').attr('disabled' , true);
		$('#delete_bottom').attr('disabled' , true);
		//resetpassword button
		$('#resetpassword_top').attr('disabled' , true);
		$('#resetpassword_bottom').attr('disabled' , true);
		//roleassign button
		$('#roleassign_top').attr('disabled' , true);
		$('#roleassign_bottom').attr('disabled' , true);
		//save button
		$('#save_top').attr('disabled' , true);
		$('#save_bottom').attr('disabled' , true);
		//reset button
		$('#reset_top').attr('disabled' , true);
		$('#reset_bottom').attr('disabled' , true);
		//close button
	    $('#close_top').attr('disabled' , true);
		$('#close_bottom').attr('disabled' , true);
		return true;
	} else {
		return false;
	}
}

function resetForm() {
	$("#displayName").val('${internalUserDto.displayName}');
	$("#team").val('${internalUserDto.team}');
	$("#securityToken").val('${internalUserDto.securityToken}');
	$("#staffId").val('${internalUserDto.staffId}');
	$("#accountStatus").val('${internalUserDto.accountStatus}');
}

function submitForm(){
	if(confirm('Are you sure to update the account ${internalUserDto.loginName} ?')){
		document.forms[0].submit();
		//delete button
		$('#delete_top').attr('disabled' , true);
		$('#delete_bottom').attr('disabled' , true);
		//resetpassword button
		$('#resetpassword_top').attr('disabled' , true);
		$('#resetpassword_bottom').attr('disabled' , true);
		//roleassign button
		$('#roleassign_top').attr('disabled' , true);
		$('#roleassign_bottom').attr('disabled' , true);
		//save button
		$('#save_top').attr('disabled' , true);
		$('#save_bottom').attr('disabled' , true);
		//reset button
		$('#reset_top').attr('disabled' , true);
		$('#reset_bottom').attr('disabled' , true);
		//close button
	    $('#close_top').attr('disabled' , true);
		$('#close_bottom').attr('disabled' , true);
		return true;
	} else {
		return false;
	}
}

function toUpperTeamValue(){
	var teamValue = document.getElementById('team');
	teamValue.value = teamValue.value.toUpperCase();
}
</script>
</head>
<body>
<hdiv-c:url value="/usrMgmt/preAssignRoleToUserAccount.do?loginId=${internalUserDto.loginName}&type=U" var="preAssignRoleURL"></hdiv-c:url>
<hdiv-c:url value="/usrMgmt/deleteUserAccount.do?loginId=${internalUserDto.loginName}" var="deleteUrl"></hdiv-c:url>
<hdiv-c:url value="/usrMgmt/preResetPassword.do?loginId=${internalUserDto.loginName}" var="resetPasswordUrl"></hdiv-c:url>
<hdiv-c:url value="/usrMgmt/searchInternalUsers.do" var="searchUrl"></hdiv-c:url>
<form:form id="accountMaintenanceForm" commandName="internalUserDto" action="${ctx}/usrMgmt/updateUserAccount.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">Account Maintenance</td>
			</tr>
			<tr>
				<td><common:mvcErrorTable commandName="internalUserDto"></common:mvcErrorTable></td>
			</tr>
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input id="delete_top" type="button" value="Delete" onclick="return deleteAccount('${deleteUrl}')" <c:if test="${disableRoleAsgnBtn}">disabled</c:if>/>&nbsp;
							    <input id="resetpassword_top" type="button" value="Reset Password" onclick="location.href='${resetPasswordUrl}'" <c:if test="${disableRoleAsgnBtn}">disabled</c:if>/>&nbsp;
							    <input id="roleassign_top" type="button" value="Assign/Amend Role" onclick="location.href='${preAssignRoleURL}'" <c:if test="${disableRoleAsgnBtn}">disabled</c:if>/>&nbsp;
							    <input id="save_top" type="button" value="Save" onclick="return submitForm();"/>&nbsp;
								<input id="reset_top" type="button" value="Reset"  onclick="resetForm();"/>&nbsp;
								<input id="close_top" type="button" value="Close" onclick="location.href='${searchUrl}'" /> 
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyDiv"></div>	
		<table id="t1" width="100%" class="standardTableForm" border="1" cellspacing="0" cellpadding="0">
        	<tr></tr>
			<tr >
				<td class="labelColumn" width="20%">Login ID <span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:input id="loginName" cssClass="normalTextField" path="loginName" disabled="true" />
					<common:errorSign path="loginName" id="loginName" />
				</td>
				<td class="labelColumn" width="20%">Full Name <span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="displayName" />
					<common:errorSign path="displayName" id="displayName" />
				</td>				
			</tr>
			<tr>
			    <td class="labelColumn" width="20%">Site <span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:select path="actvDirSite" disabled="true">
					        <form:option value="" label="-Please Select-"></form:option>
							<form:options items="${siteList}" itemValue="value" itemLabel="label"/>
						</form:select>
					<common:errorSign path="actvDirSite" id="actvDirSite" />
				</td>
				<td class="labelColumn" width="20%">Team<span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="team" maxlength="1" onblur="toUpperTeamValue();"/>
					<common:errorSign path="team" id="team" />
				</td>				
			</tr>
            <tr>
				<td class="labelColumn" width="20%">Token Serial No. </td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="securityToken" />
					<common:errorSign path="securityToken" id="securityToken" />
				</td>
				<td class="labelColumn" width="20%">Staff ID</td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="staffId" />
					<common:errorSign path="staffId" id="staffId" />
				</td>
			</tr>
			<tr>
				<td class="labelColumn" width="20%">Account Status <span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:select cssClass="normalTextField" path="accountStatus" cssStyle="width:42%;height:20px" >
					    <form:option value="" label="-Please Select-"></form:option>
						<form:option value="AC" label="Active"></form:option>
						<form:option value="IA" label="Inactive"></form:option>
					</form:select>
					<common:errorSign path="accountStatus" id="accountStatus" />
				</td>
				<td class="labelColumn" width="20%">User Record ID</td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="internalUserId" disabled="true"/>
					<common:errorSign path="internalUserId" id="internalUserId" />
				</td>
			</tr>
			<tr>
				<td class="labelColumn" width="20%">Account Inactive Detail</td>
				<td width="30%">
					<c:if test="${internalUserDto.accountStatus == 'IA'}"> 
					   <c:if test="${internalUserDto.accountDisabled == 'Y' && internalUserDto.lockedIndicator == 'N'}"><c:out value="Account Disabled" escapeXml="true"></c:out></c:if>
					   <c:if test="${internalUserDto.accountDisabled == 'N' && internalUserDto.lockedIndicator == 'Y'}"><c:out value="Account Locked" escapeXml="true"></c:out></c:if>
					   <c:if test="${internalUserDto.accountDisabled == 'Y' && internalUserDto.lockedIndicator == 'Y'}"><c:out value="Account Disabled and Locked" escapeXml="true"></c:out></c:if>
					</c:if>
				</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input id="delete_bottom" type="button" value="Delete" onclick="return deleteAccount('${deleteUrl}')" <c:if test="${disableRoleAsgnBtn}">disabled</c:if>/>&nbsp;
							    <input id="resetpassword_bottom" type="button" value="Reset Password" onclick="location.href='${resetPasswordUrl}'" <c:if test="${disableRoleAsgnBtn}">disabled</c:if>/>&nbsp;
							    <input id="roleassign_bottom" type="button" value="Assign/Amend Role" onclick="location.href='${preAssignRoleURL}'" <c:if test="${disableRoleAsgnBtn}">disabled</c:if>/>&nbsp;
							    <input id="save_bottom" type="button" value="Save" onclick="return submitForm();"/>&nbsp;
								<input id="reset_bottom" type="button" value="Reset"  onclick="resetForm();"/>&nbsp;
								<input id="close_bottom" type="button" value="Close" onclick="location.href='${searchUrl}'" /> 
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>		
	</form:form>
</body>
</html>