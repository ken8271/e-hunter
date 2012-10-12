<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/meta.jsp"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-CUST-0102]</title>
</head>
<body>
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">客户公司详细资料</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="contentTableBody1" cellspacing="0" cellpadding="0" width="100%">
		<common:standardTableRow />
		<tr class="contentTableRow1">
			<td>客户编号：</td>
			<td colspan="3"><c:out value="${customerDto.systemCustRefNum }" escapeXml="true"></c:out></td>
		</tr>
		<tr>
			<td colspan="4"><strong><br>Part I&nbsp;-&nbsp;集团信息</strong></td>
		</tr>
		<tr class="contentTableRow1">
			<td>集团名称：</td>
			<td><c:out value="${customerDto.custGroup.fullName }" escapeXml="true"></c:out></td>
			<td>集团简称：</td>
			<td><c:out value="${customerDto.custGroup.shortName }" escapeXml="true"></c:out></td>
		</tr>
		<tr>
			<td colspan="4"><strong><br>Part II&nbsp;-&nbsp;客户公司资料</strong></td>
		</tr>
		<tr class="contentTableRow1">
			<td>公司名称：</td>
			<td colspan="2"><c:out value="${customerDto.fullName}" escapeXml="true"></c:out></td>
			<td>&nbsp;</td>
		</tr>
		<tr class="contentTableRow1">
			<td>公司简称：</td>
			<td colspan="2"><c:out value="${customerDto.shortName}" escapeXml="true"></c:out></td>
			<td>&nbsp;</td>
		</tr>
		<tr class="contentTableRow1">
			<td>官方网址：</td>
			<td><c:out value="${customerDto.offcialSite }" escapeXml="true"></c:out></td>
			<td>公司总机：</td>
			<td>
			   <c:if test="${not empty customerDto.telExchangeDto.regionCode && not empty customerDto.telExchangeDto.phoneNumber}">
			      <c:out value="${customerDto.telExchangeDto.regionCode}" escapeXml="true" />&nbsp;&nbsp;-&nbsp;
				  <c:out value="${customerDto.telExchangeDto.phoneNumber }" escapeXml="true"></c:out>
			   </c:if>
			</td>
		</tr>
		<tr class="contentTableRow1">
			<td>公司性质：</td>
			<td><c:out value="${customerDto.typeDto.displayName }" escapeXml="true"></c:out></td>
			<td>公司规模：</td>
			<td><c:out value="${customerDto.sizeDto.displayName }" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
			<td>客户等级：</td>
			<td><c:out value="${customerDto.gradeDto.displayName }" escapeXml="true"></c:out></td>
			<td>客户状态：</td>
			<td><c:out value="${customerDto.customerStatusDto.displayName }" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
			<td>客户介绍：</td>
			<td colspan="3"><c:out value="${customerDto.customerDescription }" escapeXml="true"></c:out></td>
		</tr>
		<tr>
			<td colspan="4"><strong><br>Part III&nbsp;-&nbsp;联系人资料</strong></td>
		</tr>
		<tr class="contentTableRow1">
			<td colspan="4">
			   <table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			      <tr class="contentTableTitle">
				     <td align="center" width="7%">序号</td>
				     <td width="15%">姓名</td>
				     <td width="25%">职位类型/职位</td>
				     <td width="15%">手机</td>
				     <td width="28%">邮箱</td>
				     <td align="center" width="10%">状态</td>
				  </tr>
			      <c:if test="${not empty customerDto.multiResponsePerson }">
				     <c:forEach items="${customerDto.multiResponsePerson }" var="rp" varStatus="status">
					    <tr class="contentTableRow">
						   <td align="center" width="2%">${status.index+1 }</td>
						   <td><c:out value="${rp.name }"></c:out></td>
						   <td><c:out value="${rp.positionName }"></c:out></td>
						   <td><c:out value="${rp.telephoneDto.phoneNumber }"></c:out></td>
						   <td><c:out value="${rp.email }"></c:out></td>
						   <td align="center">
						      <c:if test="${rp.status == 'IS' }"><c:out value="在职" escapeXml="true"></c:out></c:if> 
						      <c:if test="${rp.status == 'OS' }"><c:out value="离职" escapeXml="true"></c:out></c:if>
						   </td>
					    </tr>
					 </c:forEach>
				 </c:if>
		       </table>
			</td>
		</tr>
	</table>
</body>
</html>
