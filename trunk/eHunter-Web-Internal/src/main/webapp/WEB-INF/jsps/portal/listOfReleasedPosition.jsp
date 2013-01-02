<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
</head>
<body>
<hdiv-c:url value="/portal/preCreatePosition.do" var="releasePositionUrl"></hdiv-c:url>
<form:form action="${ctx}/portal/listOfReleasedPosition.do" method="post">
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">已发布职位</td>
		</tr>
	</table>
	<table border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="button" value="发布新职位" onclick="location.href='${releasePositionUrl}'" />&nbsp;
						    <input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'"/>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<div>${listOfReleasedPosition}</div>
	<div class="emptyBlock"></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="button" value="发布新职位" onclick="location.href='${releasePositionUrl}'" />&nbsp;
						    <input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'"/>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form:form>
</body>
</html>