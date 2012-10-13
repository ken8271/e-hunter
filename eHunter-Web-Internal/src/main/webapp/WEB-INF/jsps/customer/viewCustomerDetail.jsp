<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/meta.jsp"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-CUST-0102]</title>
<script type="text/javascript">
function popUpInput(){
	$('#projectName').val('');
	setPopUpFramePosition('light',300,100);
	setOverlayDimension('fade');	
	popUpFrame('light','fade');
}
</script>
<common:jmesaScript actionFlagStr="90"></common:jmesaScript>
</head>
<body>
        <hdiv-c:url value="/customer/preEditCustomerInfo.do" var="editCustomerUrl"></hdiv-c:url>
        <div style="display: none">
			<input type="hidden" id="actionFlag" name="actionFlag" />
		</div>
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
							    <input class="standardButton" type="button" value="创建新项目" onclick="popUpInput();"/>&nbsp;
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
						   <c:if test="${not empty customerDto.telExchangeDto.regionCode && not empty customerDto.telExchangeDto.phoneNumber}">
				              <c:out value="${customerDto.telExchangeDto.regionCode}" escapeXml="true" />&nbsp;&nbsp;-&nbsp;
				              <c:out value="${customerDto.telExchangeDto.phoneNumber }" escapeXml="true"></c:out> 
				           </c:if>
					    </td>
					</tr>
					<tr >
						<td class="labelColumn">公司性质：</td>
						<td><c:out value="${customerDto.typeDto.displayName }" escapeXml="true"></c:out></td>
					    <td class="labelColumn">公司规模：</td>
						<td><c:out value="${customerDto.sizeDto.displayName }" escapeXml="true"></c:out></td>
					</tr>
					<tr >
						<td class="labelColumn">客户等级：</td>
						<td><c:out value="${customerDto.gradeDto.displayName }" escapeXml="true"></c:out></td>
						<td class="labelColumn">客户状态：</td>
						<td><c:out value="${customerDto.customerStatusDto.displayName }" escapeXml="true"></c:out></td>
					</tr>
					<tr >
						<td class="labelColumn">客户介绍：</td>
						<td><c:out value="${customerDto.customerDescription }" escapeXml="true"></c:out></td>
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
			   <tr class="contentTableTitle">
			      <td align="center" width="5%">序号</td>
				  <td width="15%">姓名</td>
				  <td width="25%">职位类型/职位</td>
				  <td width="15%">手机</td>
				  <td width="30%">邮箱</td>
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
		</div>	
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
		    <c:choose>
			<c:when test="${customerDto.groupIndicator == 'GRP' || customerDto.groupIndicator == 'SUB'}">
				<td width="10%"><font face="Arial" size="2"><b>第四部分：</b></font></td>
			</c:when>
			<c:otherwise>
			    <td width="10%"><font face="Arial" size="2"><b>第三部分：</b></font></td>
			</c:otherwise>
			</c:choose>
				<td width="90%"><font face="Arial" size="2"><b>客户公司猎头项目</b></font></td>
			</tr>
		</table>
		<div><jsp:include page="newProject_pop.jsp"></jsp:include></div>
		<form:form commandName="prjEnquireDto" action="${ctx }/customer/projectsSearch.do" method="post">
		<div>${listOfProject}</div>
		</form:form>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input class="standardButton" type="button" value="创建新项目" onclick="popUpInput();"/>&nbsp;
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
