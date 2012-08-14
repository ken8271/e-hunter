<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" href="${ctx}/style/verify.css" type="text/css" />
</head>
<body>
        <hdiv-c:url value="/customer/viewCustomerDetail.do?_id=${customerDto.systemCustRefNum }" var="viewCustomerDetailUrl"></hdiv-c:url>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">客户公司资料预览</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="创建新项目" onclick="location.href='${ctx}/project/initAddProject.do'"/>&nbsp;
								<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
							</td>
						</tr>
					</table>
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
		<table class="verifyTable" style="border-collapse: collapse; margin: 5" border="2" cellSpacing="0" width="99%" cellpadding="5px">
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
				<c:if test="${customerDto.grade == 'IR' }">有需求有意向</c:if>
				<c:if test="${customerDto.grade == 'NI' }">有需求无意向</c:if>
				<c:if test="${customerDto.grade == 'NR' }">无需求有意向</c:if>
				<c:if test="${customerDto.grade == 'NN' }">无需求无意向</c:if>
				</span>
				</td>
				<td align="center">
				<span class="showCh">
				<c:if test="${customerDto.status == 'SGN' }">已签约客户</c:if>
				<c:if test="${customerDto.status == 'PTL' }">潜力客户</c:if>
				<c:if test="${customerDto.status == 'OTH' }">其他</c:if>
				</span>
				</td>
				<td>
                    <input class="standardButton" type="button" value="查看" onclick="location.href='${viewCustomerDetailUrl}'">
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="创建新项目" onclick="location.href='${ctx}/project/initAddProject.do'"/>&nbsp;
								<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>