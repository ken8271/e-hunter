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
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">候选人联系记录</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
					<common:standardTableRow />
					<tr>
					    <td class="labelColumn">人才编号：</td>
						<td colspan="3">
						     <c:out value="" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; 
						     <img src="${imagePath }/icon/tips.gif" title="查看人才资料" style="vertical-align: middle; cursor: pointer;" onclick="var customerInfoWindow = window.open('${viewProjectUrl}','customerInfoWindow', 'directories=no,height=550,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680,top=100,left=200');" />
						</td>
					</tr>
					<tr>
					    <td class="labelColumn">中文名/英文名：</td>
						<td colspan="3">
						     <c:out value="" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; 
						</td>
					</tr>
				</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
					<common:standardTableRow />
					<tr>
						<td class="labelColumn">项目编号：</td>
						<td colspan="3">
						   <c:out value="" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; 
						   <img src="${imagePath }/icon/tips.gif" title="查看项目资料" style="vertical-align: middle; cursor: pointer;" onclick="var customerInfoWindow = window.open('${viewProjectUrl}','customerInfoWindow', 'directories=no,height=550,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680,top=100,left=200');" />
						</td>
					</tr>
					<tr>
						<td class="labelColumn">项目名称：</td>
						<td colspan="3">
						   <c:out value="" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; 
						</td>
					</tr>
				</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table width="100%"  border="1" cellspacing="0" cellpadding="1" class="standardTableForm">
		   <tr>
		      <td class="labelColumn">联系时间</td>
		      <td class="labelColumn">联系类型</td>
		      <td class="labelColumn">联系记录</td>
		      <td class="labelColumn">联系人</td>
		   </tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input class="standardButton" type="button" value="新增" onclick="location.href='${addCandiateUrl}'"/>&nbsp;
								<input class="standardButton" type="button" value="移除">
								<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>