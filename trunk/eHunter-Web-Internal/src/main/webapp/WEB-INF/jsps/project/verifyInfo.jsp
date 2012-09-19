<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ taglib tagdir="/WEB-INF/tags/customer" prefix="customer"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" href="${ctx}/style/verify.css" type="text/css" />
</head>
<body>
    <hdiv-c:url value="/project/backToPositionRequirement.do" var="backUrl"></hdiv-c:url>
	<form:form method="post" name="projectDto" action="${ctx}/project/submitProject.do">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">猎头项目资料确认</td>
			</tr>
			<tr>
				<td><common:errorTable path="projectDto"></common:errorTable>
				</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="确认" />&nbsp;
								<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table width="100%">
		    <tr>
		    <td align="right"><span class="textCh8" ><u>仅限公司内部查看/使用</u></span><br /></td>
		    </tr>
			<tr>
				<td align="center">
				<span class="titleEn12">e-Hunter System</span><br /> 
				<span class="titleCh11">电子猎头系统</span><br /> <br />
				<span class="titleEn12">Registration Form for Project</span><br />
				<span class="titleCh11">猎头项目登记表格</span><br /> <br />
				</td>
			</tr>
		</table>
		<br />
		<br />
		<div>
			<span class="titleCh10">&nbsp;第一部分：</span> <span class="titleCh10">项目基本信息</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" width="100%" cellpadding="5px">
			<tr>
				<td width="17%"><span class="textCh8">项目名称</span></td>
				<td width="36%"><span class="showCh"><c:out value="${projectDto.projectName}" escapeXml="true" /> </span></td>
				<td width="17%"><span class="textCh8">项目负责人</span><br /></td>
				<td width="30%"><span class="showCh"><c:out value="${projectDto.adviserDto.cnName}" escapeXml="true" /></span></td>
			</tr>
			<tr>
				<td width="17%"><span class="textCh8">客户编号</span></td>
				<td width="36%"><span class="showCh"><c:out value="${projectDto.customerDto.systemCustRefNum}" escapeXml="true" /> </span></td>
				<td width="17%"><span class="textCh8">客户名称</span><br /></td>
				<td width="30%"><span class="showCh"><c:out value="${projectDto.customerDto.fullName}" escapeXml="true" /></span></td>
			</tr>
		</table>
		<br />
		<div>
			<span class="titleCh10">&nbsp;第二部分：</span> <span class="titleCh10">需求职位描述</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" width="100%" cellpadding="5px">
		    <tr style="display: none">
		        <td width="17%"></td>
				<td width="36%"></td>
				<td width="17%"></td>
				<td width="30%"></td>
		    </tr>
			<tr>
				<td><span class="textCh8">职位名称</span></td>
				<td colspan="3"><span class="showCh"><c:out value="${projectDto.postDescDto.positionName}" escapeXml="true" /></span></td>
			</tr>
			<tr>
				<td><span class="textCh8">需求人数</span></td>
				<td><span class="showCh"><c:out value="${projectDto.postDescDto.expectNumberStr}" escapeXml="true" />人</span></td>
				<td><span class="textCh8">截止日期</span></td>
				<td>
				   <span class="showCh">
				       <c:out value="${projectDto.postDescDto.expiryDateDto.year }"escapeXml="true" />/
				       <c:out value="${projectDto.postDescDto.expiryDateDto.month }" escapeXml="true" />/
				       <c:out value="${projectDto.postDescDto.expiryDateDto.day }" escapeXml="true" />
				   </span>
				</td>
			</tr>
			<tr>
				<td width="17%"><span class="textCh8">所属部门</span></td>
				<td width="36%"><span class="showCh"><c:out value="${projectDto.postDescDto.department}" escapeXml="true" /></span></td>
				<td width="17%"><span class="textCh8">汇报对象</span></td>
				<td width="30%"><span class="showCh"><c:out value="${projectDto.postDescDto.reportTarget}" escapeXml="true" /></span></td>
			</tr>
			<tr>
				<td><span class="textCh8">工作地点</span></td>
				<td colspan="3">
				   <span class="showCh">
				      <c:forEach items="${projectDto.postDescDto.cityDtos }" var="cityDto">
				         <c:out value="${cityDto.displayName}" escapeXml="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				      </c:forEach>
				   </span>
				</td>
			</tr>
			<tr>
				<td width="17%"><span class="textCh8">职位关键词</span></td>
				<td colspan="3">
				   <span class="showCh">
				      <c:forEach items="${projectDto.postDescDto.keyWords }" var="keyWord">
				        <c:out value="${keyWord }" escapeXml="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				      </c:forEach>
				   </span>
				</td>
			</tr>
			<tr>
				<td><span class="textCh8">职责描述</span></td>
				<td colspan="3">
				   <span class="showCh">
				       <c:out value="${projectDto.postDescDto.dutyDescription }"escapeXml="true" />
				   </span>
				</td>
			</tr>
		</table>
		<br />
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" width="100%" cellpadding="5px">
		    <tr style="display: none">
		        <td width="17%"></td>
				<td width="36%"></td>
				<td width="17%"></td>
				<td width="30%"></td>
		    </tr>
			<tr>
				<td width="17%"><span class="textCh8">职位年薪</span></td>
				<td colspan="3">
				   <span class="showCh">
				      <c:out value="${projectDto.postDescDto.salaryFromStr}" escapeXml="true" />万元至
				      <c:out value="${projectDto.postDescDto.salaryToStr}" escapeXml="true" />万元
				   </span>
				</td>
			</tr>
			<tr>
				<td width="17%"><span class="textCh8">薪资构成</span></td>
				<td width="36%">
				   <span class="showCh">
				     <c:forEach items="${projectDto.postDescDto.salaryCategoryDtos }" var="scDto" varStatus="status">
				        <c:out value="${scDto.displayName }" escapeXml="true" /><c:if test="${not status.last }">&nbsp;/&nbsp;</c:if> 
				     </c:forEach>
				   </span>
				</td>
				<td width="17%"><span class="textCh8">社会福利</span></td>
				<td width="30%">
				   <span class="showCh">
				      <c:forEach items="${projectDto.postDescDto.scoitetyWelfareDtos }" var="swDto" varStatus="status">
				        <c:out value="${swDto.displayName }" escapeXml="true" /><c:if test="${not status.last }">&nbsp;/&nbsp;</c:if> 
				      </c:forEach>
				   </span>
				</td>
			</tr>
			<tr>
				<td width="17%"><span class="textCh8">居住福利</span></td>
				<td width="36%">
				   <span class="showCh">
				      <c:forEach items="${projectDto.postDescDto.residentialWelfareDtos }" var="rwDto" varStatus="status">
				        <c:out value="${rwDto.displayName }" escapeXml="true" /><c:if test="${not status.last }">&nbsp;/&nbsp;</c:if> 
				      </c:forEach>
				   </span>
				</td>
				<td width="17%"><span class="textCh8">年假福利</span></td>
				<td width="30%">
				   <span class="showCh">
				      <c:forEach items="${projectDto.postDescDto.annualLeaveWelfareDtos }" var="awDto" varStatus="status">
				        <c:out value="${awDto.displayName }" escapeXml="true" /><c:if test="${not status.last }">&nbsp;/&nbsp;</c:if> 
				      </c:forEach>
				   </span>
				</td>
			</tr>
		</table>
		<br />
		<div>
			<span class="titleCh10">&nbsp;第三部分：</span> <span class="titleCh10">需求职位要求</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" width="100%" cellpadding="5px">
		    <tr style="display: none">
		        <td width="17%"></td>
				<td width="36%"></td>
				<td width="17%"></td>
				<td width="30%"></td>
		    </tr>
			<tr>
				<td width="17%"><span class="textCh8">基本素质要求</span></td>
				<td colspan="3">
				   <span class="showCh">
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
				   </span>
				</td>
			</tr>
			<tr>
				<td width="17%"><span class="textCh8">期望人选来源行业</span></td>
				<td colspan="3">
				   <c:forEach items="${projectDto.postRequireDto.expectIndustryDtos }" var="industryDto">
				      <c:out value="${industryDto.displayName }" escapeXml="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				   </c:forEach>
				</td>
			</tr>
			<tr>
				<td><span class="textCh8">任职资格</span></td>
				<td colspan="3"><span class="showCh"><c:out value="${projectDto.postRequireDto.duty}" escapeXml="true" /></span></td>
			</tr>	
			<tr>
				<td width="17%"><span class="textCh8">补充说明</span></td>
				<td colspan="3">
				   <c:out value="${projectDto.postRequireDto.remark}" escapeXml="true" />
				</td>
			</tr>
		</table>
		<br/>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="确认" />&nbsp;
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
