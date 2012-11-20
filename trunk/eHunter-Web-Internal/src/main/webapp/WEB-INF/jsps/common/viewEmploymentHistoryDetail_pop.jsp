<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/meta.jsp"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>

<title>职业概况详细资料</title>
</head>
<body>
<table border="0" width="100%">
		<tr>
			<td class="pageTitle">人才历史职业详细资料</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="contentTableBody1" cellspacing="0" cellpadding="0" width="100%">
		<common:standardTableRow />
		<tr class="contentTableRow1">
			
				<td>人才编号：<c:out value="${talentDto.talentID }" escapeXml="true"></c:out></td>
          
		</tr>
	</table>
<div class="emptyBlock"></div>
	<table class="contentTableBody1" cellspacing="0" cellpadding="0" width="100%">
		<common:standardTableRow />
		<tr class="contentTableRow1">
			<td>开始时间：</td>
			<td colspan="3"><c:out value="${employmentHistoryDto.beginTimeDto.year }" escapeXml="true"></c:out>-<c:out value="${employmentHistoryDto.beginTimeDto.month }" escapeXml="true"></c:out>-<c:out value="${employmentHistoryDto.beginTimeDto.day }" escapeXml="true"></c:out></td>
		</tr>
		
		<tr class="contentTableRow1">
			<td>结束时间：</td>
			<td colspan="2"><c:out value="${employmentHistoryDto.endTimeDto.year}" escapeXml="true"></c:out>-<c:out value="${employmentHistoryDto.endTimeDto.month }" escapeXml="true"></c:out>-<c:out value="${employmentHistoryDto.endTimeDto.day }" escapeXml="true"></c:out></td>
			<td>&nbsp;</td>
		</tr>
		<tr class="contentTableRow1">
			<td>公司名称：</td>
			<td colspan="2"><c:out value="${employmentHistoryDto.companyName}" escapeXml="true"></c:out></td>
			<td>&nbsp;</td>
		</tr>
		<tr class="contentTableRow1">
			<td>曾任职位：</td>
			<td>
		               <c:forEach items="${employmentHistoryDto.positionDtos }" var="positionDto">
		                 <c:out value="${positionDto.displayName }" escapeXml="true" /><br/> 
		               </c:forEach>
		     </td>
			<td>行业类别：</td>
			<td><c:out value="${employmentHistoryDto.industryDto.displayName }" escapeXml="true"></c:out></td>
		</tr>
		
</body>
</html>