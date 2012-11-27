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
			<td class="pageTitle">候选人才详细资料</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="contentTableBody1" cellspacing="0" cellpadding="0" width="100%">
		<common:standardTableRow />
		<tr class="contentTableRow1">
			<tr>
				<td>人才编号：</td>
				<td colspan="3"><c:out value="${talentDto.talentID }" escapeXml="true"></c:out></td>
			</tr>
		</tr>
		<tr>
			<td colspan="4"><strong><br>Part I&nbsp;-&nbsp;基本信息</strong></td>
		</tr>
		<tr class="contentTableRow1">
		   <td>中文名：</td>
		   <td><c:out value="${talentDto.cnName }" escapeXml="true"></c:out></td>
		   <td>英文名：</td>
		   <td><c:out value="${talentDto.enName }" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
		   <td>性别：</td>
		   <td>
		      <c:if test="${talentDto.gender == 'M' }"><c:out value="男" escapeXml="true"></c:out></c:if>
			  <c:if test="${talentDto.gender == 'F' }"><c:out value="女" escapeXml="true"></c:out></c:if>
		   </td>
		   <td>婚姻状况：</td>
		   <td>
		      <c:if test="${talentDto.maritalStatus == '01'}"><c:out value="未婚" escapeXml="true"></c:out></c:if>
		      <c:if test="${talentDto.maritalStatus == '02'}"><c:out value="已婚" escapeXml="true"></c:out></c:if>
		      <c:if test="${talentDto.maritalStatus == '03'}"><c:out value="保密" escapeXml="true"></c:out></c:if>
		   </td>
		</tr>
		<tr class="contentTableRow1">
		   <td>出生日期：</td>
		   <td>
		     <c:if test="${talentDto.birthDateDto != null}">
			    <c:out value="${talentDto.birthDateDto.year }" escapeXml="true"></c:out>/
			    <c:out value="${talentDto.birthDateDto.month }" escapeXml="true"></c:out>/
				<c:out value="${talentDto.birthDateDto.day }" escapeXml="true"></c:out>
			 </c:if>
		   </td>
		   <td>籍贯：</td>
		   <td><c:out value="${talentDto.nativePlace }" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
		   <td>曾居地：</td>
		   <td><c:out value="${talentDto.onceLivePlace }" escapeXml="true"></c:out></td>
		   <td >现居地：</td>
		   <td><c:out value="${talentDto.nowLivePlace }" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
		   <td >最高学历：</td>
		   <td><c:out value="${talentDto.degreeDto.displayName }" escapeXml="true"></c:out></td>
		   <td >人才来源：</td>
		   <td><c:out value="${talentDto.talentSrcDto.displayName }" escapeXml="true"></c:out></td>
		</tr>
		<tr>
			<td colspan="4"><strong><br>Part III&nbsp;-&nbsp;联系方式</strong></td>
		</tr>
		<tr class="contentTableRow1">
		   <td>家庭电话：</td>
		   <td>
		      <c:if test="${talentDto.homeNumberDto != null && talentDto.homeNumberDto.phoneNumber != null && talentDto.homeNumberDto.phoneNumber != ''}">
		         <c:out value="${talentDto.homeNumberDto.regionCode }" escapeXml="true"></c:out>&nbsp;-&nbsp;
		         <c:out value="${talentDto.homeNumberDto.phoneNumber }" escapeXml="true"></c:out>
		      </c:if>
		   </td>
		   <td>公司电话：</td>
		   <td>
		      <c:if test="${talentDto.companyNumberDto != null && talentDto.companyNumberDto.phoneNumber != null && talentDto.companyNumberDto.phoneNumber != ''}">
		        <c:out value="${talentDto.companyNumberDto.regionCode }" escapeXml="true"></c:out>&nbsp;-&nbsp;
		        <c:out value="${talentDto.companyNumberDto.phoneNumber }" escapeXml="true"></c:out>
		     </c:if>
		  </td>
		</tr>
		<tr class="contentTableRow1">
		   <td>手机：</td>
		   <td>
		      <c:out value="${talentDto.mobilePhoneDto1.phoneNumber }" escapeXml="true"></c:out><br/>
		      <c:out value="${talentDto.mobilePhoneDto2.phoneNumber }" escapeXml="true"></c:out>
		   </td>
		   <td>邮箱：</td>
		   <td><c:out value="${talentDto.email }" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
		   <td>QQ：</td>
		   <td>
		      <c:out value="${talentDto.QQ }" escapeXml="true"></c:out><br/>
		   </td>
		   <td>MSN：</td>
		   <td><c:out value="${talentDto.msn }" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
		   <td>家庭地址：</td>
		   <td colspan="3"><c:out value="${talentDto.homeAddress }"></c:out></td>
		</tr>
	</table>
</body>
</html>
