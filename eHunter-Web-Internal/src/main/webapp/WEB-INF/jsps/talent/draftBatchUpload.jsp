<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-PRJ-0001]</title>
</head>
<body>
    <hdiv-c:url value="/talent/downloadBatchUploadTemplate.do" var="downloadUrl"></hdiv-c:url>
	<form:form commandName="batchUploadDto" enctype="multipart/form-data" action="${ctx }/talent/submitBatchUploadDraft.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">批量导入候选人才资料</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
		   <tr>
		      <td width="20%" class="labelColumn">上传文件：<span class="mandatoryField">*</span></td>
		      <td>
		         <input name="uploadFile" type="file" value="" size="30" maxlength="10">
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
							<input class="standardButton" type="button" value="获取模版" onclick="location.href='${downloadUrl}'" />&nbsp;
							<input class="standardButton" type="submit" value="开始上传" />&nbsp;
							<input class="standardButton" type="button" value="取消上传" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>			
	</form:form>
</body>
</html>