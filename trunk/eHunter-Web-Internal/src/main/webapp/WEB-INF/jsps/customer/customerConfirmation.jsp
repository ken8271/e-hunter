<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" href="${ctx}/style/verify.css" type="text/css" />
<script type="text/javascript">
function popUpInput(){
	$('#projectName').val('');
	setPopUpFramePosition('light',300,100);
	setOverlayDimension('fade');	
	popUpFrame('light','fade');
}
</script>
</head>
<body>
        <hdiv-c:url value="/customer/viewCustomerDetail.do?_id=${customerDto.systemCustRefNum }" var="viewCustomerDetailUrl"></hdiv-c:url>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">客户公司资料预览</td>
			</tr>
		</table>
		<table align="right" border="0" cellspacing="0" cellpadding="0">
		   <tr>
		      <td>
		         <input class="standardButton" type="submit" value="创建新项目" onclick="popUpInput();"/>&nbsp;
		         <input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
		      </td>
		   </tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="contentTableBody1" cellspacing="0">
				<tr>
					<td class="note">
						<common:message code="ehunter.customer.confirm" needSuccSign="true"></common:message>
					</td>
				</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" cellSpacing="0" width="100%" cellpadding="5px">
			<tr>
				<td align="center" width="10%"><span class="textCh8">客户编号</span><br /> <br /></td>
				<td align="center" width="30%"><span class="textCh8">客户名称/简称</span><br /> <br /></td>
				<td align="center" width="25%"><span class="textCh8">客户类型</span><br /><br /></td>
				<td align="center" width="20%"><span class="textCh8">客户等级</span><br /><br /></td>
				<td align="center" width="10%"><span class="textCh8">客户状态</span><br /><br /></td>
				<td align="center" width="5%"><span class="textCh8">操作</span><br /><br /></td>
			</tr>
			<tr>
				<td align="center"><span class="textCn8"><c:out value="${customerDto.systemCustRefNum }" escapeXml="true"></c:out></span></td>
				<td align="center"><span class="textCn8"><c:out value="${customerDto.fullName }"></c:out>/<c:out value="${customerDto.shortName }" escapeXml="true"></c:out> </span></td>
				<td align="center">
				<span class="textCn8">
				<c:if test="${customerDto.groupIndicator == 'GRP'}"><c:out value="集团客户" escapeXml="true"></c:out></c:if>
				<c:if test="${customerDto.groupIndicator == 'SUB'}"><c:out value="非集团客户（集团旗下子公司）" escapeXml="true"></c:out></c:if>
				<c:if test="${customerDto.groupIndicator == 'IND'}"><c:out value="非集团客户（独立公司）" escapeXml="true"></c:out></c:if>
				</span>
				</td>
				<td align="center">
				<span class="showCh">
				   <c:out value="${customerDto.gradeDto.displayName }" escapeXml="true"></c:out>
				</span>
				</td>
				<td align="center">
				<span class="showCh">
				   <c:out value="${customerDto.customerStatusDto.displayName }" escapeXml="true"></c:out>
				</span>
				</td>
				<td>
                    <input class="standardButton" type="button" value="查看" onclick="location.href='${viewCustomerDetailUrl}'">
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div><jsp:include page="newProject_pop.jsp"></jsp:include></div>
		<table align="right" border="0" cellspacing="0" cellpadding="0">
		   <tr>
		      <td>
		         <input class="standardButton" type="submit" value="创建新项目" onclick="popUpInput();"/>&nbsp;
		         <input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
		      </td>
		   </tr>
		</table>
</body>
</html>
