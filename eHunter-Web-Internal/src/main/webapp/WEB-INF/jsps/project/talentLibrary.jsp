<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
	<form:form  action="${ctx}/project/savePositionRequirement.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">项目人才库 - 添加项目人才</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>							   
							   <input class="standardButton" type="submit" value="提交" />&nbsp;
							   <input class="standardButton" type="reset" value="重置" />&nbsp;
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
							</td>
						</tr>
					</table>
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
							   <input class="standardButton" type="submit" value="提交" />&nbsp;
							   <input class="standardButton" type="reset" value="重置" />&nbsp;
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
	</form:form>
</body>
</html>