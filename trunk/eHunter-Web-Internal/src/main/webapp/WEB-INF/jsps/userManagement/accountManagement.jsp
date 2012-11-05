<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT</title>
<hdiv-c:url value="/usrMgmt/viewInternalUserDetail.do" var="viewDetailUrl"></hdiv-c:url>
<script type="text/javascript">
function clearInput() {
	$('#loginId').val('');
	$('#staffId').val('');
	$('#name').val('');
	$('#role').val('');
	$('#accountStatus').val('');
}

function popUpViewDialog(userRecordID){
	if(userRecordID == null || userRecordID == '') return ;
	
	$.ajax({
		type:'post',
		url:'${viewDetailUrl}',
		dataType:'xml',
		data:{'_id':userRecordID},
		success:function(xml){	
			$(xml).find('account').each(function(i , element){
				document.getElementById('view_userRecId').innerHTML = $(this).find("userRecId").text();
				document.getElementById('view_loginId').innerHTML = $(this).find("loginId").text();
				document.getElementById('view_staffId').innerHTML = $(this).find("staffId").text();
				document.getElementById('view_name').innerHTML = $(this).find("name").text();
				document.getElementById('view_email').innerHTML = $(this).find("email").text();
				document.getElementById('view_mobile').innerHTML = $(this).find("mobile").text();
				document.getElementById('view_accountStatus').innerHTML = $(this).find("accountStatus").text();
				document.getElementById('view_role').innerHTML = $(this).find("role").text();
			});	
		},
		error:function(){
			alert('系统错误');
		}
	});
	
	setPopUpFramePosition('view_light',500,230);
	setOverlayDimension('view_fade');	
	popUpFrame('view_light','view_fade');
}
</script>
</head>
<body>
   <hdiv-c:url value="/usrMgmt/preCreateInternalUser.do" var="createUrl"></hdiv-c:url>
   <form:form id="enquireForm" commandName="userEnquireDto" action="${ctx}/usrMgmt/searchInternalUsers.do" method="post">
		<jsp:include page="accountDetailView_pop.jsp"></jsp:include>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">用户管理 - 用户查询</td>
			</tr>
			<tr>
				<td><common:errorTable path="userEnquireDto"></common:errorTable></td>
			</tr>
		</table>
		<table border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<input class="standardButton" type="button" value="创建新用户" onclick="location.href='${createUrl}'"/>&nbsp;
							<input class="standardButton" type="submit" value="查询" />&nbsp;
							<input class="standardButton" type="button" value="重置" onclick="clearInput();"/>&nbsp;
							<input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'"/>&nbsp;
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
						<td class="labelColumn">登录名：</td>
						<td>
						   <form:input path="loginId" cssClass="standardInputText"  /> 
						</td>
						<td class="labelColumn">员工号：</td>
						<td>
						   <form:input path="staffId" cssClass="standardInputText"  /> 
					    </td>
					</tr>
				    <tr >
						<td class="labelColumn">中文名/英文名：</td>
						<td>
						   <form:input path="name" cssClass="standardInputText"  /> 
						</td>
						<td class="labelColumn">用户角色：</td>
						<td>
						   <form:select path="role" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfRole }" var="role">
						         <form:option value="${role.sysRefRole }" label="${role.roleName }"></form:option>
						      </c:forEach>
						   </form:select>
						</td>
					</tr>
					<tr>
						<td class="labelColumn">帐号状态：</td>
						<td>
						   <form:select path="accountStatus" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <form:option value="AC" label="正常/活跃"></form:option>
						      <form:option value="IN" label="禁用/锁定"></form:option>
						   </form:select>
						</td>
						<td colspan="2">&nbsp;</td>
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
							<input class="standardButton" type="button" value="创建新用户" onclick="location.href='${createUrl}'"/>&nbsp;
							<input class="standardButton" type="submit" value="查询" />&nbsp;
							<input class="standardButton" type="button" value="重置" onclick="clearInput();"/>&nbsp;
							<input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'"/>&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
	    <div align="center">
	       <common:jmesaScript action=""></common:jmesaScript>
	    </div>
		<div>
		    ${listOfUser}
		</div>
	</form:form>
</body>
</html>