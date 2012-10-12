<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/style/verify.css" type="text/css" />
<title>e-Hunter System/[EH-PRJ-0001]</title>
</head>
<body>
    <hdiv-c:url value="/project/appendCandidateRepositoryActions.do?actionFlag=1" var="backUrl"></hdiv-c:url>
    <hdiv-c:url value="/customer/pop/viewCustomerDetail.do?_id=${projectDto.customerDto.systemCustRefNum }" var="viewCustomerUrl"></hdiv-c:url>
    <hdiv-c:url value="/project/pop/viewProjectDetail.do?_id=${projectDto.systemProjectRefNum }" var="viewProjectUrl"></hdiv-c:url>
	<form:form commandName="projectDto" action="${ctx}/project/assignCandidates2Project.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">项目人才库-新增候选人才</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input class="standardButton" type="submit" value="确认"/>&nbsp;
								<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
					<common:standardTableRow />
					<tr>
						<td class="labelColumn">项目编号：</td>
						<td colspan="3">
						   <c:out value="${projectDto.systemProjectRefNum }" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; 
						   <common:tips url="${viewProjectUrl }" title="查看项目资料"></common:tips>
						</td>
					</tr>
					<tr>
					    <td class="labelColumn">客户公司：</td>
						<td colspan="3">
						     <c:out value="${projectDto.customerDto.fullName }" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; 
						     <common:tips url="${viewCustomerUrl }" title="查看客户公司资料"></common:tips>
						</td>
					</tr>
					<tr>
						<td class="labelColumn">需求职位：</td>
						<td colspan="3">
						     <c:out value="${projectDto.postDescDto.positionName}" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; 
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table class="contentTableBody2" cellspacing="1" width="100%">
			<tr class="contentTableTitle">
				<td align="center" width="15%">人才编号</td>
				<td align="center" width="20%">中文名/英文名</td>
				<td align="center" width="10%">学历</td>
				<td align="center" width="40%">现居住地</td>
				<td align="center" width="10%">录入时间</td>
				<td align="center" width="5%">操作</td>
			</tr>
			<c:forEach items="${projectDto.cddtRepoDtos }" var="repoDto">
			   <tr class="contentTableRow">
				<td align="center"><span class="textCn8"><c:out value="${repoDto.talentDto.talentID }" escapeXml="true"></c:out></span></td>
				<td align="center"><span class="textCn8"><c:out value="${repoDto.talentDto.cnName }" escapeXml="true"></c:out>/<c:out value="${repoDto.talentDto.enName }" escapeXml="true"></c:out></span>
				<td align="center"><span class="textCn8"><c:out value="${repoDto.talentDto.degreeDto.displayName }"></c:out></span></td>
				<td align="center"><span class="textCn8"><c:out value="${repoDto.talentDto.nowLivePlace}" escapeXml="true"></c:out></span>
				<td align="center"><span class="textCn8"><fmt:formatDate type="both" value="${repoDto.talentDto.createDateTime}" pattern="yyyy-MM-dd"/></span>
				<td>
				    <hdiv-c:url value="/talent/pop/viewTalentDetail.do?_id=${repoDto.talentDto.talentID }" var="viewCandidateUrl"></hdiv-c:url>
                    <common:tips url="${viewCandidateUrl }" title="查看候选人资料"></common:tips>
				</td>
			   </tr>
			</c:forEach>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input class="standardButton" type="submit" value="确认"/>&nbsp;
								<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>