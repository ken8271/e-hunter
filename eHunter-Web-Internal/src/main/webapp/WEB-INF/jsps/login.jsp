<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用电子猎头管理系统</title>
<script type="text/javascript">
function clear(){
	$('#j_username').val("");
	$('#j_password').val("");
}
</script>
</head>
<body>
<form action="${ctx}/j_spring_security_check" method="post">  
  	<div id="pageAll">
		<div id="pageTop">
		  <common:top></common:top>
		</div>
		<div id="pageMain">
		  <c:if test="${not empty param.login_error || not empty error_code_not_from_security}">
		    <div class="errorContainer" id='mvcErrorContainer'>
		       <h4><img src="${imagePath}/icon/icon_warning.gif" alt="Warning">Error:</h4>
	           <br clear="all"/>
	           <ul>
	             <c:if test="${not empty param.login_error}">
                 <li><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></li>
                 </c:if>
                 <c:if test="${not empty error_code_not_from_security}">
                 <li><spring:message code="${error_code_not_from_security}" text=""></spring:message></li>              
                 </c:if>
               </ul>
            </div>
          </c:if>
		  <table border="0" width="100%" cellspacing="6" cellpadding="3" align="left">		
			<tr>
				<td colspan="0" align="center"></td>
			</tr>
			<tr>
				<td class="note" colspan="2">输入你的帐号和密码。请不要将密码泄漏他人，请至少每六个月改变密码以增强帐号的安全性。</td>
			</tr>
			<tr>
				<td width="25%">
				登录名&nbsp;<span class="mandatoryField">*</span>
				</td>
				<td>
				<input type="text" size="20" maxlength="20" value="${param.j_username}" id="j_username" name="j_username" 
				 value='<c:if test="${not empty param.login_error}">${SPRING_SECURITY_LAST_USERNAME_KEY}</c:if>' />
				</td>
			</tr>	
			<tr>
				<td width="25%">
				密码&nbsp;<span class="mandatoryField">*</span>
				</td>
				<td >
				<input type="password" autocomplete="off" size="20" maxlength="8" name="j_password" id="j_password" value="${param.j_password}"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="buttonArea" align="left">
						<div class="buttonmenubox_R">						
							    <input type="submit" id="submitButton" name="submtButton" style="display:none" />
								<a class="buttondefault" id="loginButton" onclick="if (!this.disabled){this.disabled=true;document.getElementById('submitButton').click();}"  href="#">登录</a>
								<a class="button" id="clearButton" href="javascript:clear();">重置</a>
								<a class="button" id="closeButton" href="javascript:window.close();">取消</a>
						</div>
					</div>
				</td>
			</tr>
		</table>
		
		<div class="emptyBlock"></div>	
		</div>
	</div>
</form>
</body>
</html>