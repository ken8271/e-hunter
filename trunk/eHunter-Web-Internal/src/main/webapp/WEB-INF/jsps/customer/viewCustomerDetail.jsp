<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/meta.jsp"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-CUST-0102]</title>
</head>
<body>
        <hdiv-c:url value="/customer/editCustomer.do" var="editCustomerUrl"></hdiv-c:url>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">客户公司详细资料</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input class="standardButton" type="button" value="创建新项目" onclick="location.href='${ctx}/project/initAddProject.do'"/>&nbsp;
								<input class="standardButton" type="button" value="编辑" onclick="location.href='${editCustomerUrl}'"/>&nbsp;
								<input class="standardButton" type="button" value="返回" onclick="location.href='${ctx}/customer/initCustomersSearch.do'">
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
				<tr >
				   <td class="labelColumn">客户编号：</td>
				   <td><c:out value="${customerDto.systemCustRefNum }" escapeXml="true"></c:out></td>
				   <td colspan="2">&nbsp;</td>
			    </tr>
			</tbody>
		</table>
		<div class="emptyBlock"></div>
		<c:if test="${customerDto.groupIndicator == 'GRP' || customerDto.groupIndicator == 'SUB'}">
		<table width="100%">
			<tr>
				<td width="10%"><font face="Arial" size="2"><b>第一部分：</b></font></td>
				<td width="90%"><font face="Arial" size="2"><b>集团资料</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">集团名称：</td>
						<td><c:out value="${customerDto.custGroup.fullName }" escapeXml="true"></c:out></td>
						<td class="labelColumn">集团简称：</td>
						<td><c:out value="${customerDto.custGroup.shortName }" escapeXml="true"></c:out></td>
					</tr>
				</tbody>
		</table>
		</div>
		</c:if>
		<div class="emptyBlock"></div>	
		<table width="100%">
			<tr>
			<c:choose>
			<c:when test="${customerDto.groupIndicator == 'GRP' || customerDto.groupIndicator == 'SUB'}">
				<td width="10%"><font face="Arial" size="2"><b>第二部分：</b></font></td>
			</c:when>
			<c:otherwise>
			    <td width="10%"><font face="Arial" size="2"><b>第一部分：</b></font></td>
			</c:otherwise>
			</c:choose>
				<td width="90%"><font face="Arial" size="2"><b>客户公司资料</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn" >公司名称：</td>
						<td colspan="2"><c:out value="${customerDto.fullName}" escapeXml="true"></c:out> </td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">公司简称：</td>
						<td colspan="2"><c:out value="${customerDto.shortName}" escapeXml="true"></c:out></td>
					    <td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">官方网址：</td>
						<td><a href="http://${customerDto.offcialSite}" target="_blank"><c:out value="${customerDto.offcialSite }" escapeXml="true"></c:out></a></td>
						<td class="labelColumn">公司总机：</td>
						<td>
						   <c:out value="${customerDto.telExchangeDto.regionCode }" escapeXml="true"></c:out>&nbsp;&nbsp;-&nbsp;
						   <c:out value="${customerDto.telExchangeDto.phoneNumber }" escapeXml="true"></c:out>
					    </td>
					</tr>
					<tr >
						<td class="labelColumn">公司性质：</td>
						<td><c:out value="${customerDto.type }" escapeXml="true"></c:out></td>
					    <td class="labelColumn">公司规模：</td>
						<td><c:out value="${customerDto.size }" escapeXml="true"></c:out></td>
					</tr>
					<tr >
						<td class="labelColumn">客户等级：</td>
						<td><c:out value="${customerDto.grade }" escapeXml="true"></c:out></td>
						<td class="labelColumn">客户状态：</td>
						<td><c:out value="${customerDto.status }" escapeXml="true"></c:out></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>	
		<table width="100%">
			<tr>
		    <c:choose>
			<c:when test="${customerDto.groupIndicator == 'GRP' || customerDto.groupIndicator == 'SUB'}">
				<td width="10%"><font face="Arial" size="2"><b>第三部分：</b></font></td>
			</c:when>
			<c:otherwise>
			    <td width="10%"><font face="Arial" size="2"><b>第二部分：</b></font></td>
			</c:otherwise>
			</c:choose>
				<td width="90%"><font face="Arial" size="2"><b>客户联系人资料</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
				    <tr >
						<td class="labelColumn">姓名：</td>
						<td colspan="3"><c:out value="${customerDto.custRespPerson.name }" escapeXml="true"></c:out></td>
					</tr>
					<tr >
						<td class="labelColumn">职位类型：</td>
						<td colspan="2"><c:out value="${customerDto.custRespPerson.positionType }" escapeXml="true"></c:out></td>
						<td >&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">职位名称：</td>
						<td colspan="2"><c:out value="${customerDto.custRespPerson.positionName }"></c:out></td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">手机：</td>
						<td><c:out value="${customerDto.custRespPerson.telephoneDto.phoneNumber }" escapeXml="true"></c:out></td>
						<td class="labelColumn">邮箱：</td>
						<td><c:out value="${customerDto.custRespPerson.email }" escapeXml="true"></c:out></td>
					</tr>	
					<tr >
						<td class="labelColumn">状态：</td>
						<td><c:out value="${customerDto.custRespPerson.status }" escapeXml="true"></c:out> </td>
						<td colspan="2">
					</tr>				
				</tbody>
			</table>
		</div>	
		<div class="emptyBlock"></div>
		<div>${listOfCustomerProjects}</div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input class="standardButton" type="button" value="创建新项目" onclick="location.href='${ctx}/project/initAddProject.do'"/>&nbsp;
								<input class="standardButton" type="button" value="编辑" onclick="location.href='${editCustomerUrl}'"/>&nbsp;
								<input class="standardButton" type="button" value="返回" onclick="location.href='${ctx}/customer/initCustomersSearch.do'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>
