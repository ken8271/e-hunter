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
    <hdiv-c:url value="/customer/pop/viewCustomerDetail.do?_id=${projectDto.customerDto.systemCustRefNum }" var="viewCustomerUrl"></hdiv-c:url>
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">项目详细资料</td>
		</tr>
	</table>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<input class="standardButton" type="button" value="项目人才库管理">&nbsp;
						    <input class="standardButton" type="button" value="编辑" />&nbsp;
							<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
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
				<td class="labelColumn">客户公司：</td>
				<td colspan="3">
				   <c:out value="${projectDto.customerDto.fullName }" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;
				   <img src="${imagePath }/icon/tips.gif" title="查看客户公司信息" style="vertical-align: middle;cursor: pointer;" onclick="var customerInfoWindow = window.open('${viewCustomerUrl}','customerInfoWindow', 'directories=no,height=550,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680,top=100,left=200');"/>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="emptyBlock"></div>
	<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<common:standardTableRow />
			<tr>
				<td class="labelColumn">项目编号：</td>
				<td colspan="3"><c:out value="${projectDto.systemProjectRefNum }" escapeXml="true"></c:out></td>
			</tr>
			<tr>
				<td class="labelColumn">项目名称：</td>
				<td colspan="3"><c:out value="${projectDto.projectName }" escapeXml="true"></c:out></td>
			</tr>
			<tr>
				<td class="labelColumn">项目状态：</td>
				<td><c:out value="" escapeXml="true"></c:out></td>
				<td class="labelColumn">创建时间：</td>
				<td><fmt:formatDate type="both" value="${projectDto.createDateTime}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td class="labelColumn">项目负责人：</td>
				<td colspan="3"><c:out value="${projectDto.adviserDto.cnName }" escapeXml="true"></c:out></td>
			</tr>
		</tbody>
	</table>
	<div class="emptyBlock"></div>
	<table width="100%">
		<tr>
			<td width="90%"><font face="Arial" size="2"><b>需求职位-职位描述</b></font></td>
		</tr>
	</table>
	<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
		    <common:standardTableRow />
			<tr>
				<td class="labelColumn">职位名称：</td>
				<td colspan="3"><c:out value="${projectDto.postDescDto.positionName}" escapeXml="true" /></td>
			</tr>
			<tr>
				<td class="labelColumn">需求人数：</td>
				<td><c:out value="${projectDto.postDescDto.expectNumberStr}" escapeXml="true" />人</td>
				<td class="labelColumn">截止日期：</td>
				<td>
				   
				       <c:out value="${projectDto.postDescDto.expiryDateDto.year }"escapeXml="true" />/
				       <c:out value="${projectDto.postDescDto.expiryDateDto.month }" escapeXml="true" />/
				       <c:out value="${projectDto.postDescDto.expiryDateDto.day }" escapeXml="true" />
				   
				</td>
			</tr>
			<tr>
				<td class="labelColumn">所属部门：</td>
				<td width="36%"><c:out value="${projectDto.postDescDto.department}" escapeXml="true" /></td>
				<td class="labelColumn">汇报对象：</td>
				<td width="30%"><c:out value="${projectDto.postDescDto.reportTarget}" escapeXml="true" /></td>
			</tr>
			<tr>
				<td class="labelColumn">工作地点：</td>
				<td colspan="3">
				      <c:forEach items="${projectDto.postDescDto.cityDtos }" var="cityDto">
				         <c:out value="${cityDto.displayName}" escapeXml="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				      </c:forEach>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">职位关键词：</td>
				<td colspan="3">
				      <c:forEach items="${projectDto.postDescDto.keyWords }" var="keyWord">
				        <c:out value="${keyWord }" escapeXml="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				      </c:forEach>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">职责描述：</td>
				<td colspan="3">
				       <c:out value="${projectDto.postDescDto.dutyDescription }"escapeXml="true" />
				</td>
			</tr>
	</table>
	<br/>
	<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
		    <common:standardTableRow />
			<tr>
				<td class="labelColumn">职位年薪：</td>
				<td colspan="3">
				      <c:out value="${projectDto.postDescDto.salaryFromStr}" escapeXml="true" />万元至
				      <c:out value="${projectDto.postDescDto.salaryToStr}" escapeXml="true" />万元
				</td>
			</tr>
			<tr>
				<td class="labelColumn">薪资构成：</td>
				<td width="36%">
				     <c:forEach items="${projectDto.postDescDto.salaryCategoryDtos }" var="scDto" varStatus="status">
				        <c:out value="${scDto.displayName }" escapeXml="true" /><c:if test="${not status.last }">&nbsp;/&nbsp;</c:if> 
				     </c:forEach>
				</td>
				<td class="labelColumn">社会福利：</td>
				<td width="30%">
				      <c:forEach items="${projectDto.postDescDto.scoitetyWelfareDtos }" var="swDto" varStatus="status">
				        <c:out value="${swDto.displayName }" escapeXml="true" /><c:if test="${not status.last }">&nbsp;/&nbsp;</c:if> 
				      </c:forEach>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">居住福利：</td>
				<td width="36%">
				      <c:forEach items="${projectDto.postDescDto.residentialWelfareDtos }" var="rwDto" varStatus="status">
				        <c:out value="${rwDto.displayName }" escapeXml="true" /><c:if test="${not status.last }">&nbsp;/&nbsp;</c:if> 
				      </c:forEach>
				</td>
				<td class="labelColumn">年假福利：</td>
				<td width="30%">
				      <c:forEach items="${projectDto.postDescDto.annualLeaveWelfareDtos }" var="awDto" varStatus="status">
				        <c:out value="${awDto.displayName }" escapeXml="true" /><c:if test="${not status.last }">&nbsp;/&nbsp;</c:if> 
				      </c:forEach>
				</td>
			</tr>
		</table>
	<div class="emptyBlock"></div>
	<table width="100%">
		<tr>
			<td width="90%"><font face="Arial" size="2"><b>需求职位-职位要求</b></font></td>
		</tr>
	</table>
	<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
		    <common:standardTableRow />
			<tr>
				<td class="labelColumn">基本素质要求</td>
				<td colspan="3">
				       1. 年龄要求：<c:out value="${not empty projectDto.postRequireDto.ageFromStr ? projectDto.postRequireDto.ageFromStr : '不限'}"escapeXml="true" />&nbsp;~&nbsp;<c:out value="${not empty projectDto.postRequireDto.ageToStr ? projectDto.postRequireDto.ageToStr : '不限'}"escapeXml="true" /><br/>
				       2. 性别要求：<c:out value="${not empty projectDto.postRequireDto.gender ? (projectDto.postRequireDto.gender== 'M' ? '男' : '女') : '不限'}" escapeXml="true" /><br/>
				       3. 专业要求：<c:out value="${not empty projectDto.postRequireDto.majorCategoryDto.displayName ? projectDto.postRequireDto.majorCategoryDto.displayName : '不限'}" escapeXml="true" /><br>
				       4. 总工作年限：<c:if test="${not empty projectDto.postRequireDto.workExperienceStr }"><c:out value="${projectDto.postRequireDto.workExperienceStr }"escapeXml="true" />年以上</c:if><br/>
				       5. 学历要求：<c:out value="${not empty projectDto.postRequireDto.degreeDto.displayName ? projectDto.postRequireDto.degreeDto.displayName : '不限'}" escapeXml="true" /><br/>
				       6. 是否统招全日制：<c:out value="${projectDto.postRequireDto.ftEduIndicator == 'Y' ? '是' : '不限' }" escapeXml="true" /><br>
				       7. 语言要求：
				       <c:forEach items="${projectDto.postRequireDto.language }" var="languageCode">
				          <c:if test="${languageCode == 'EN' }">英语&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				          <c:if test="${languageCode == 'JP' }">日语&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				          <c:if test="${languageCode == 'FR' }">法语&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				          <c:if test="${languageCode == 'CH' }">普通话&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				          <c:if test="${languageCode == 'CT' }">粤语&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				          <c:if test="${languageCode == 'OT' }">其他&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				       </c:forEach><br>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">期望人选来源行业</td>
				<td colspan="3">
				   <c:forEach items="${projectDto.postRequireDto.expectIndustryDtos }" var="industryDto">
				      <c:out value="${industryDto.displayName }" escapeXml="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				   </c:forEach>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">任职资格</td>
				<td colspan="3"><c:out value="${projectDto.postRequireDto.duty}" escapeXml="true" /></td>
			</tr>	
			<tr>
				<td class="labelColumn">补充说明</td>
				<td colspan="3">
				   <c:out value="${projectDto.postRequireDto.remark}" escapeXml="true" />
				</td>
			</tr>
	</table>
	<div class="emptyBlock"></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<input class="standardButton" type="button" value="项目人才库管理">&nbsp;
						    <input class="standardButton" type="button" value="编辑" />&nbsp;
							<input class="standardButton" type="button" value="返回"  onclick="location.href='${backUrl}'" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
</body>
</html>