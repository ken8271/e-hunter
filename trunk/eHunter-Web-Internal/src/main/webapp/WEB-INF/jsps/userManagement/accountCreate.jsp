<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
<script type="text/javascript" src="${scriptPath }/multiSelector.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	getSelectedRoles();
	displaySelectedRoles(document.getElementById('roleSelector'));
});

function popUpRoleSelector(){
	$('#roleSelector').hide();
	setPopUpFramePosition('role_light',400,200);
	setOverlayDimension('role_fade');	
	popUpFrame('role_light','role_fade');
}

function handleUnselect(c){
	var roleSelector = document.getElementById('roleSelector');
	if(!c.checked){
		removeByCode(roleSelector , c.value);
		
		var role = $('#roleDisplayPanel [value='+ c.value + ']');
		if(role != null && role[0] != null){
			role[0].checked = false;
		}
		
		displaySelectedRoles(roleSelector);
	}
}

function handleSelect(role){
	var roleSelector = document.getElementById('roleSelector');
	var c = $(role).children();
	var label = $(role).text();
	c[0].checked = !c[0].checked;
	
	if(c[0].checked){
		if(roleSelector.length >= 5){
			alert('最多可添加5个角色');
			c[0].checked = false;
			return ;
		}
		
		if(!hasBeenSelect(roleSelector , c[0].value)){			
			roleSelector.options[roleSelector.length] = new Option(label, c[0].value);
		}else {
		   c[0].checked = false;
		   removeByCode(roleSelector,c[0].value);
		}
	}else {		
		removeByCode(roleSelector,c[0].value);
	}
	
	displaySelectedRoles(roleSelector);
}

function displaySelectedRoles(roleSelector){
	var str = '';
	for(var i=0 ; i<5 ; i++){
		if(i < roleSelector.length){
			str = str + "<td><input type='checkbox' value='" + roleSelector.options[i].value + "' checked onclick='handleUnselect(this)'/>" + roleSelector.options[i].text +"</td>";
		}else {
			str = str + "<td>&nbsp;</td>";
		}
	}
	
	clearSelectedCities();
	$(str).appendTo('#selectedRoles');
}

function clearSelectedCities(){
	$('#selectedRoles td:gt(0)').remove();
}

function getSelectedRoles(){
	var roleSelector = document.getElementById('roleSelector');
	
	var displayStr = "<span>";
	var displayValue = '';
	for(var i=0 ;  i<roleSelector.length ; i++){
		displayStr = displayStr + roleSelector.options[i].text + "&nbsp;&nbsp;&nbsp;&nbsp;";
		
		if(i == roleSelector.length-1){
			displayValue = displayValue + roleSelector.options[i].value;
		}else {
			displayValue = displayValue + roleSelector.options[i].value + ',';
		}
	}
	displayStr = displayStr + "</span>";
	
	$('#asgnedRolesDisplay span').remove();
	$(displayStr).appendTo('#asgnedRolesDisplay');
	
	$('#roleStr').val(displayValue);
}
</script>
</head>
<body>
<hdiv-c:url value="/usrMgmt/searchInternalUsers.do?fromCreate=true" var="searchUrl"></hdiv-c:url>
<form:form id="createUserAcountForm" commandName="internalUserDto" action="${ctx}/usrMgmt/saveUserAccount.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">用户管理 - 创建新用户</td>
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
						</td>
						<td class="labelColumn">员工号：</td>
						<td>
						   <form:input path="staffId" cssClass="standardInputText"  /> 
					    </td>
					</tr>
				    <tr >
						<td class="labelColumn">中文名：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="cnName" cssClass="standardInputText"  /> 
						</td>
						<td class="labelColumn">英文名：</td>
						<td>
						   <form:input path="enName" cssClass="standardInputText"  /> 
						</td>
					</tr>
					<tr >
						<td class="labelColumn">邮箱：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="email" cssClass="standardInputText"  /> 
						</td>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td class="labelColumn">用户角色：<span class="mandatoryField">*</span></td>
						<td>
					       <input type="button" class="selectButton" value="选择/修改" onFocus="this.blur()" onclick="popUpRoleSelector();">
					       <form:hidden path="roleStr"/>
						</td>
						<td id="asgnedRolesDisplay" colspan="2"></td>
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
						</td>
						<td colspan="2"></td>
					</tr>
				    <tr >
						<td class="labelColumn">确认密码：<span class="mandatoryField">*</span></td>
						<td>
						   <form:password path="confirmPassword" size="30" maxlength="8" cssClass="standardInputText"/>
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
		<jsp:include page="roleSelector_pop.jsp"></jsp:include>
	</form:form>
</body>
</html>