<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-CUST-0001]</title>
</head>
<body>
	<form:form commandName="customerDto" action="${ctx}/customer/saveCustCoInfo.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">第一步：客户公司资料填写</td>
			</tr>
			<tr>
				<td><common:errorTable path="customerDto"></common:errorTable></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input id="create_top" type="button" value="Create New User" />&nbsp;
								<input id="search_top" type="submit" value="Search" />&nbsp; 
								<input id="reset_top" type="button" value="Reset" onclick="resetEnquireForm();" />&nbsp;
								<input id="close_top" type="button" value="Close" onclick="location.href='${ctx}/index.do'" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="t1" width="100%" class="standardTableForm" border="1"
			cellspacing="0" cellpadding="0">
			<tr></tr>
			<tr>
				<td class="labelColumn" width="20%">Login ID</td>
				<td width="30%"><input type="text" class="normalTextField"
					name="loginid"></td>
				<td class="labelColumn" width="20%">Full Name</td>
				<td width="30%"><input type="text" class="normalTextField"
					name="fullname"></td>
			</tr>
			<tr>
				<td class="labelColumn" width="20%">Token Serial No.</td>
				<td width="30%"><input type="text" class="normalTextField"
					name="token"></td>
				<td class="labelColumn" width="20%">Staff ID</td>
				<td width="30%"><input type="text" class="normalTextField"
					name="staffid"></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input id="create_top" type="button" value="Create New User" />&nbsp;
								<input id="search_top" type="submit" value="Search" />&nbsp; 
								<input id="reset_top" type="button" value="Reset" onclick="resetEnquireForm();" />&nbsp;
								<input id="close_top" type="button" value="Close" onclick="location.href='${ctx}/index.do'" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
	</form:form>
</body>
</html>