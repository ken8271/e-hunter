<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
</head>
<body>
	<table class="contentTableBody1" cellspacing="0">
		<tr>
			<td class="note">
			   <common:message code="ehunter.changepassword.confirm" needSuccSign="true"></common:message>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table align="right" border="0" cellspacing="0" cellpadding="0">
	   <tr>
	      <td><input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'" />&nbsp;</td>
	   </tr>
	</table>
</body>
</html>