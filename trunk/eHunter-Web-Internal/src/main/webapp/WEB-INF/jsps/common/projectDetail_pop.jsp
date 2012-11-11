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
			<td class="pageTitle">猎头项目详细资料</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="contentTableBody1" cellspacing="0" cellpadding="0" width="100%">
		<common:standardTableRow />
		<tr class="contentTableRow1">
			<td>项目编号：</td>
			<td colspan="3"><c:out value="${projectDto.systemProjectRefNum }" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
			<td>项目名称：</td>
			<td colspan="3"><c:out value="${projectDto.projectName }" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
			<td>客户公司：</td>
			<td colspan="3"><c:out value="${projectDto.customerDto.fullName }" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
			<td>项目状态：</td>
			<td><c:out value="${projectDto.statusDto.displayName }" escapeXml="true"></c:out></td>
			<td>创建时间：</td>
			<td><fmt:formatDate type="both" value="${projectDto.createDateTime}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr class="contentTableRow1">
			<td>项目负责人：</td>
			<td colspan="3"><c:out value="${projectDto.adviserDto.cnName }" escapeXml="true"></c:out></td>
		</tr>
		<tr>
			<td colspan="4"><strong><br>Part I&nbsp;-&nbsp;职位描述</strong></td>
		</tr>
		<tr class="contentTableRow1">
			<td>职位名称：</td>
			<td colspan="3"><c:out value="${projectDto.postDescDto.positionName}" escapeXml="true" /></td>
		</tr>
		<tr class="contentTableRow1">
			<td>需求人数：</td>
			<td><c:out value="${projectDto.postDescDto.expectNumberStr}" escapeXml="true" />人</td>
			<td>截止日期：</td>
			<td>
		       <c:out value="${projectDto.postDescDto.expiryDateDto.year }"escapeXml="true" />/
			   <c:out value="${projectDto.postDescDto.expiryDateDto.month }" escapeXml="true" />/
			   <c:out value="${projectDto.postDescDto.expiryDateDto.day }" escapeXml="true" />
			</td>
		</tr>
		<tr class="contentTableRow1">
			<td>所属部门：</td>
			<td width="36%"><c:out value="${projectDto.postDescDto.department}" escapeXml="true" /></td>
			<td>汇报对象：</td>
			<td width="30%"><c:out value="${projectDto.postDescDto.reportTarget}" escapeXml="true" /></td>
		</tr>
		<tr class="contentTableRow1">
			<td>工作地点：</td>
			<td colspan="3">
			   <c:forEach items="${projectDto.postDescDto.cityDtos }" var="cityDto">
			      <c:out value="${cityDto.displayName}" escapeXml="true" />&nbsp;&nbsp;&nbsp;&nbsp;
			   </c:forEach>
			</td>
		</tr>
		<tr class="contentTableRow1">
			<td>职责描述：</td>
			<td colspan="3">
			   <c:out value="${projectDto.postDescDto.dutyDescription }"escapeXml="true" />
			</td>
		</tr>
		<tr class="contentTableRow1">
			<td>职位年薪：</td>
			<td colspan="3">
			   <c:out value="${projectDto.postDescDto.salaryFromStr}" escapeXml="true" />万元至
			   <c:out value="${projectDto.postDescDto.salaryToStr}" escapeXml="true" />万元
			</td>
		</tr>
		<tr class="contentTableRow1">
		    <td>薪资构成：</td>
			<td width="36%">
			   <c:forEach items="${projectDto.postDescDto.salaryCategoryDtos }" var="scDto" varStatus="status">
			      <c:out value="${scDto.displayName }" escapeXml="true" /><c:if test="${not status.last }">&nbsp;/&nbsp;</c:if> 
			   </c:forEach>
			</td>
			<td>社会福利：</td>
			<td width="30%">
			   <c:forEach items="${projectDto.postDescDto.scoitetyWelfareDtos }" var="swDto" varStatus="status">
			      <c:out value="${swDto.displayName }" escapeXml="true" /><c:if test="${not status.last }">&nbsp;/&nbsp;</c:if> 
			   </c:forEach>
			</td>
		</tr>
		<tr class="contentTableRow1">
			<td>居住福利：</td>
			<td width="36%">
			   <c:forEach items="${projectDto.postDescDto.residentialWelfareDtos }" var="rwDto" varStatus="status">
			      <c:out value="${rwDto.displayName }" escapeXml="true" /><c:if test="${not status.last }">&nbsp;/&nbsp;</c:if> 
			   </c:forEach>
			</td>
			<td>年假福利：</td>
			<td width="30%">
			   <c:forEach items="${projectDto.postDescDto.annualLeaveWelfareDtos }" var="awDto" varStatus="status">
			      <c:out value="${awDto.displayName }" escapeXml="true" /><c:if test="${not status.last }">&nbsp;/&nbsp;</c:if> 
			   </c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan="4"><strong><br>Part II&nbsp;-&nbsp;职位要求</strong></td>
		</tr>
		<tr class="contentTableRow1">
			<td>基本素质要求</td>
			<td colspan="3">
				       1. 年龄要求：<c:out value="${not empty projectDto.postRequireDto.ageFromStr ? projectDto.postRequireDto.ageFromStr : '不限'}"escapeXml="true" />&nbsp;~&nbsp;<c:out value="${not empty projectDto.postRequireDto.ageToStr ? projectDto.postRequireDto.ageToStr : '不限'}"escapeXml="true" /><br/>
				       2. 性别要求：<c:out value="${not empty projectDto.postRequireDto.gender ? (projectDto.postRequireDto.gender== 'M' ? '男' : '女') : '不限'}" escapeXml="true" /><br/>
				       3. 专业要求：<c:out value="${not empty projectDto.postRequireDto.majorCategoryDto.displayName ? projectDto.postRequireDto.majorCategoryDto.displayName : '不限'}" escapeXml="true" /><br>
				       4. 总工作年限：<c:if test="${not empty projectDto.postRequireDto.workExperienceStr }"><c:out value="${projectDto.postRequireDto.workExperienceStr }"escapeXml="true" />年以上</c:if><br/>
				       5. 学历要求：<c:out value="${not empty projectDto.postRequireDto.degreeDto.displayName ? projectDto.postRequireDto.degreeDto.displayName : '不限'}" escapeXml="true" /><br/>
				       6. 语言要求：
				       <c:forEach items="${projectDto.postRequireDto.languageStr }" var="languageCode">
				          <c:if test="${languageCode == 'EN' }">英语&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				          <c:if test="${languageCode == 'JP' }">日语&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				          <c:if test="${languageCode == 'FR' }">法语&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				          <c:if test="${languageCode == 'CH' }">普通话&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				          <c:if test="${languageCode == 'CT' }">粤语&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				          <c:if test="${languageCode == 'OT' }">其他&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				       </c:forEach><br>
			</td>
		</tr>
		<tr class="contentTableRow1">
			<td>期望人选来源行业</td>
			<td colspan="3">
				   <c:forEach items="${projectDto.postRequireDto.expectIndustryDtos }" var="industryDto">
				      <c:out value="${industryDto.displayName }" escapeXml="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				   </c:forEach>
			</td>
		</tr>
		<tr class="contentTableRow1">
			<td>任职资格</td>
			<td colspan="3"><c:out value="${projectDto.postRequireDto.duty}" escapeXml="true" /></td>
		</tr>	
		<tr class="contentTableRow1">
			<td>补充说明</td>
			<td colspan="3">
				 <c:out value="${projectDto.postRequireDto.remark}" escapeXml="true" />
			</td>
		</tr>
	</table>
</body>
</html>
