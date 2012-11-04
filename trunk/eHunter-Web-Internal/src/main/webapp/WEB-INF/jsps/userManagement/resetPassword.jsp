<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><common:screenID id="SY80905" /></title>
<script type="text/javascript">
$(document).ready(function(){
	if('${resetSuccess}' == 'Y'){
		alert("The password of account ${internalUserDto.loginName} is reset successfully");
	}
});

function submitForm(buttonObject){
	if(confirm('Are you sure to reset password for user account ${internalUserDto.loginName} ?')){
		document.forms[0].submit();
		$('#save_top').attr('disabled' , true);
		$('#save_bottom').attr('disabled' , true);
		$('#close_top').attr('disabled' , true);
		$('#close_bottom').attr('disabled' , true);
		return true;
	} else {
		return false;
	}
}
</script>
</head>
<body>
<hdiv-c:url value="/usrMgmt/accountMaintenance.do?loginId=${internalUserDto.loginName}" var="accountMaintenanceUrl"></hdiv-c:url>
<form:form id="resetPasswordForm" commandName="internalUserDto" action="${ctx}/usrMgmt/resetPassword.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">Reset Password</td>
			</tr>
			<tr>
				<td><common:mvcErrorTable commandName="internalUserDto"></common:mvcErrorTable></td>
			</tr>
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input id="save_top" type="submit" value="Confirm" onclick="return submitForm(this);"/>&nbsp;
								<input id="close_top" type="button" value="Close" onclick="location.href='${accountMaintenanceUrl}'" /> 
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<table id="t1" width="100%" class="standardTableForm" border="1" cellspacing="0" cellpadding="0">
        	<tr></tr>
			<tr >
				<td class="labelColumn" width="20%">Password <span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:password path="password" size="30" maxlength="8"/>
					<common:errorSign path="password" id="password" />
				</td>
			</tr>
			<tr>
				<td class="labelColumn" width="20%">Confirm Password <span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:password path="confirmPassword" size="30" maxlength="8"/>
					<common:errorSign path="confirmPassword" id="confirmPassword" />
				</td>
			</tr>
		</table>
		<br>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		   <tr>
		      <td><b>Point(s) to note:</b><br> 
	              Length of password must be 8 characters consisting of both alphabetical and numeric characters.
	          </td>
	       </tr>
	    </table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input id="save_bottom" type="submit" value="Confirm" onclick="return submitForm(this);"/>&nbsp;
								<input id="close_bottom" type="button" value="Close" onclick="location.href='${accountMaintenanceUrl}'" /> 
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>		
	</form:form>
</body>
</html>