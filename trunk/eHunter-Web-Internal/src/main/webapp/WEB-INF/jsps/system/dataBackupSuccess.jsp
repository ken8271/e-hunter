<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
</head>
<body>
    <hdiv-c:url value="/system/downloadBackupFile.do?_id=${backupHistoryDto.historyID }" var="downloadUrl"></hdiv-c:url>
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">成功备份系统数据</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="contentTableBody1" cellspacing="0">
		<tr>
			<td class="note"><common:message code="ehunter.data.backup.success" needSuccSign="true"></common:message></td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						<input class="standardButton" type="button" value="下载到本地" onclick="location.href='${downloadUrl}'" />&nbsp;
						<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>