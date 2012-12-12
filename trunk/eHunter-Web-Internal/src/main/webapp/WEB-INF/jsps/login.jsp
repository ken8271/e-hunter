<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter [欢迎使用电子猎头管理系统]</title>
<style type="text/css">
.loginform {
	background: #f7f7f7;
	background: -moz-linear-gradient(90deg, #ccc, #fff);
	background: -webkit-gradient(linear, left top, left bottom, from(#fff),to(#ccc) );
	border: 1px solid #aaa;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	-moz-box-shadow: 0 0 15px #aaa;
	-webkit-box-shadow: 0 0 15px #aaa;
	margin: 0px auto 0;
	padding: 20px;
	width: 380px;
}

.welcome{
	border-bottom:1px solid #ccc;
	font-size:11px;
	font-weight:bold;
	letter-spacing:2px;
	margin-bottom:20px;
	text-transform:uppercase;
}

.loginerror {
	font-family: "Arial", "Verdana", "Helvetica", "sans-serif";
	font-weight: normal;
	color: #CC0000;
	border: 1px solid #CC0000;
	width: 99%;
	padding: 2px;
}

.loginerror LI {
	margin-left: 25px;
	LIST-STYLE-TYPE: disc;
	LINE-HEIGHT: 1.0em;
	font-size: 110%;
	font-color: #CC0000;
}
</style>
<script type="text/javascript">
	function clear() {
		$('#j_username').val("");
		$('#j_password').val("");
	}

	$().ready(function() {
		$('#loginForm').submit(function() {
			if ($('#j_username').val() == ''|| $('#j_password').val() == '') {
				displayError('<spring:message code="EHT-E-0501" text="User ID or Password is required. [EHT-E-0501]"/>');
				return false;
			}
		});
		
		$("input[type=submit] , input[type=reset]").button();
	});

	function displayError(msg) {
		$("#mvcErrorContainer_client").hide();
		$("#errorMsg").html(msg);
		$("#mvcErrorContainer_client").show();
	}
</script>
</head>
<body>
	<form id="loginForm" action="${ctx}/j_spring_security_check" method="post">
		<div id="pageTop" height="250px">
			<div class="boxHolder2">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" height="190px">
					<tr>
						<td width="100%" background="${ctx}/images/default/page_bg.gif"><img src="${ctx}/images/default/logo.gif" border="0" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div style="padding: 150px; display: block"></div>
		<div class="loginform">
			<table border="0" width="380" cellspacing="10" cellpadding="2" align="center">
			    <tr>
			       <td colspan="3"><h1 class="welcome">Welcome to e-Hunter System</h1></td>
			    </tr>
				<tr>
				   <td colspan="3">
					   <c:if test="${not empty param.login_error || not empty error_code_not_from_security}">
							<div class="loginerror" id='mvcErrorContainer'>
								<h4><img src="${imagePath}/icon/icon_warning.gif" alt="Warning">Error:</h4>
								<br>
								<ol>
									<c:if test="${not empty param.login_error}">
										<li><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></li>
									</c:if>
									<c:if test="${not empty error_code_not_from_security}">
										<li><spring:message code="${error_code_not_from_security}" text=""></spring:message></li>
									</c:if>
								</ol>
							</div>
							<div class="emptyBlock"></div>
					   </c:if> 
					   <c:if test="${empty param.login_error && empty error_code_not_from_security}">
							<div class="loginerror" id='mvcErrorContainer_client' style="display: none">
								<h4><img src="${imagePath}/icon/icon_warning.gif" alt="Warning">Error:</h4>
								<br>
								<ol><li id="errorMsg"></li></ol>
							</div>
							<div class="emptyBlock"></div>
					   </c:if>
					</td>
				</tr>
				<tr>
					<td width="35%" class="rightText">登录名(User ID) ：</td>
					<td width="55%" align="left">
					   <input class="form-text" type="text" size="20" maxlength="20" value="${param.j_username}" id="j_username" name="j_username" value='<c:if test="${not empty param.login_error}">${SPRING_SECURITY_LAST_USERNAME_KEY}</c:if>' />
					</td>
					<td width="10%"><span class="mandatoryField">*</span></td>
				</tr>
				<tr>
					<td width="35%" class="rightText">密码(Password) ：</td>
					<td width="55%" align="left">
					   <input class="form-text" type="password" autocomplete="off" size="20" maxlength="8" name="j_password" id="j_password" value="${param.j_password}" />
					</td>
					<td width="10%" align="left"><span class="mandatoryField">*</span></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<div align="left">
							<input id="submitButton" type="submit" value=" 登录 " style="color: #071a54" />&nbsp; 
							<input type="reset" value=" 重置  " style="color: #071a54" onclick="clear();">
						</div>
				    </td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>