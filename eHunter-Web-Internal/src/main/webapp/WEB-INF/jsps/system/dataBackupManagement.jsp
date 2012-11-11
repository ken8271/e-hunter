<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
<hdiv-c:url value="/system/backupDataImmediately.do" var="backupUrl"></hdiv-c:url>
<common:jmesaScript actionFlagStr=""></common:jmesaScript>
<script type="text/javascript">
function handleBackup(){
	$().progressDialog.showDialog("");
	window.location.href='${backupUrl}';
}
</script>
</head>
<body>
<form:form action="${ctx}/system/listOfDataBackupHistory.do" method="post">
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">系统数据备份历史</td>
		</tr>
	</table>
	<table border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="button" value="立即备份" onclick="handleBackup();"/>&nbsp;
						    <input class="standardButton" type="submit" value="刷新" />&nbsp;
						    <input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'"/>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<div>${listOfBackupHistory }</div>
	<div class="emptyBlock"></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="button" value="立即备份" onclick="handleBackup();"/>&nbsp;
						    <input class="standardButton" type="submit" value="刷新" />&nbsp;
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