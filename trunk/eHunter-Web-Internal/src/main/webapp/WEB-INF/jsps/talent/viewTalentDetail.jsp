<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">人才详细资料</td>
		</tr>
	</table>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><input class="standardButton" type="button" value="编辑" />&nbsp;
							<input class="standardButton" type="button" value="新增简历">&nbsp;
							<input class="standardButton" type="button" value="返回">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<common:standardTableRow />
			<tr>
				<td class="labelColumn">人才来源：</td>
				<td><c:out value="${talentDto.talentSrcDto.displayName }" escapeXml="true"></c:out></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</tbody>
	</table>
	<div class="emptyBlock"></div>
	<table width="100%">
		<tr>
			<td width="10%"><font face="Arial" size="2"><b>第一部分：</b></font></td>
			<td width="90%"><font face="Arial" size="2"><b>人才基本信息</b></font></td>
		</tr>
	</table>
	<div style="height: 5px"></div>
	<div class="contentTableBody">
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			<tbody>
				<common:standardTableRow />
				<tr>
					<td class="labelColumn">中文名：</td>
					<td><c:out value="${talentDto.cnName }" escapeXml="true"></c:out></td>
					<td class="labelColumn">英文名：</td>
					<td><c:out value="${talentDto.enName }" escapeXml="true"></c:out></td>
				</tr>
				<tr>
					<td class="labelColumn">性别：</td>
					<td>
					   <c:if test="${talentDto.gender == 'M' }"><c:out value="男" escapeXml="true"></c:out></c:if>
					   <c:if test="${talentDto.gender == 'F' }"><c:out value="女" escapeXml="true"></c:out></c:if>
					</td>
					<td class="labelColumn">婚姻状况：</td>
					<td>
					   <c:if test="${talentDto.maritalStatus == '001'}"><c:out value="未婚" escapeXml="true"></c:out></c:if>
					   <c:if test="${talentDto.maritalStatus == '002'}"><c:out value="已婚" escapeXml="true"></c:out></c:if>
					   <c:if test="${talentDto.maritalStatus == '003'}"><c:out value="保密" escapeXml="true"></c:out></c:if>
					</td>
				</tr>
				<tr>
					<td class="labelColumn">出生日期：
					</td>
					<td>
					   <c:out value="${talentDto.birthDateDto.year }" escapeXml="true"></c:out>/
					   <c:out value="${talentDto.birthDateDto.month }" escapeXml="true"></c:out>/
					   <c:out value="${talentDto.birthDateDto.day }" escapeXml="true"></c:out>
					</td>
					<td class="labelColumn">籍贯：</td>
					<td><c:out value="${talentDto.nativePlace }" escapeXml="true"></c:out></td>
				</tr>
				<tr>
					<td class="labelColumn">曾居地：</td>
					<td><c:out value="${talentDto.onceLivePlace }" escapeXml="true"></c:out></td>
					<td class="labelColumn">现居地：</td>
					<td><c:out value="${talentDto.nowLivePlace }" escapeXml="true"></c:out></td>
				</tr>
				<tr>
					<td class="labelColumn">最高学历：</td>
					<td><c:out value="${talentDto.degreeDto.displayName }" escapeXml="true"></c:out></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="emptyBlock"></div>
	<table width="100%">
		<tr>
			<td width="10%"><font face="Arial" size="2"><b>第二部分：</b></font></td>
			<td width="90%"><font face="Arial" size="2"><b>人才联系方式</b></font></td>
		</tr>
	</table>
	<div class="contentTableBody">
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			<tbody>
				<common:standardTableRow />
				<tr>
					<td class="labelColumn">家庭电话：</td>
					<td>
					  <c:if test="${talentDto.homeNumberDto != null && talentDto.homeNumberDto.phoneNumber != null && talentDto.homeNumberDto.phoneNumber != ''}">
					     <c:out value="${talentDto.homeNumberDto.regionCode }" escapeXml="true"></c:out>&nbsp;-&nbsp;
					     <c:out value="${talentDto.homeNumberDto.phoneNumber }" escapeXml="true"></c:out>
					  </c:if>
					</td>
					<td class="labelColumn">公司电话：</td>
					<td>
					  <c:if test="${talentDto.companyNumberDto != null && talentDto.companyNumberDto.phoneNumber != null && talentDto.companyNumberDto.phoneNumber != ''}">
					     <c:out value="${talentDto.companyNumberDto.regionCode }" escapeXml="true"></c:out>&nbsp;-&nbsp;
					     <c:out value="${talentDto.companyNumberDto.phoneNumber }" escapeXml="true"></c:out>
					  </c:if>
					</td>
				</tr>
				<tr>
					<td class="labelColumn">手机：</td>
					<td>
					   <c:out value="${talentDto.mobilePhoneDto1.phoneNumber }" escapeXml="true"></c:out><br/>
					   <c:out value="${talentDto.mobilePhoneDto2.phoneNumber }" escapeXml="true"></c:out>
					</td>
					<td class="labelColumn">邮箱：</td>
					<td><c:out value="${talentDto.email }" escapeXml="true"></c:out></td>
				</tr>
				<tr>
					<td class="labelColumn">家庭地址：</td>
					<td colspan="3"><c:out value="${talentDto.homeAddress }"></c:out></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="emptyBlock"></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><input class="standardButton" type="button" value="编辑" />&nbsp;
							<input class="standardButton" type="button" value="新增简历">&nbsp;
							<input class="standardButton" type="button" value="返回">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
</body>
</html>