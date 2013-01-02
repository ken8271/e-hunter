<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ taglib tagdir="/WEB-INF/tags/customer" prefix="customer"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" href="${ctx}/style/verify.css" type="text/css" />
</head>
<body>
    <hdiv-c:url value="/customer/fillMultiResponsePerson.do" var="backUrl"></hdiv-c:url>
	<form:form method="post" name="customerDto" action="${ctx}/customer/submitCustomer.do">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">客户公司资料确认</td>
			</tr>
			<tr>
				<td><common:errorTable path="customerDto"></common:errorTable>
				</td>
			</tr>
		</table>
		<table align="right" border="0" cellspacing="0" cellpadding="0">
		   <tr>
		      <td>
		         <input class="standardButton" type="submit" value="确认" />&nbsp;
		         <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
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
				<span class="titleEn12">Registration Form for Customer</span><br />
				<span class="titleCh11">客户公司登记表格</span><br /> <br />
				</td>
			</tr>
		</table>
		<br />
		<br />
		<div>
			<span class="titleCh10">&nbsp;第一部分：</span> <span class="titleCh10">客户集团资料</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" width="100%" cellpadding="5px">
			<tr>
				<td width="17%"><span class="textCh8">集团名称</span></td>
				<td width="36%"><span class="showCh"><c:out value="${customerDto.custGroup.fullName}" escapeXml="true" /> </span></td>
				<td width="17%"><span class="textCh8">集团简称</span><br /></td>
				<td width="30%"><span class="showCh"><c:out value="${customerDto.custGroup.shortName}" escapeXml="true" /></span></td>
			</tr>
		</table>
		<br />
		<div>
			<span class="titleCh10">&nbsp;第二部分：</span> <span class="titleCh10">客户公司资料</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" width="100%" cellpadding="5px">
			<tr>
				<td><span class="textCh8">公司名称</span></td>
				<td><span class="showCh"><c:out value="${customerDto.fullName}" escapeXml="true" /></span></td>
				<td><span class="textCh8">公司简称</span></td>
				<td><span class="showCh"><c:out value="${customerDto.shortName}" escapeXml="true" /></span></td>
			</tr>
			<tr>
				<td><span class="textCh8">官网地址</span></td>
				<td><span class="showCh"><c:out value="${customerDto.offcialSite}" escapeXml="true" /> </span></td>
				<td><span class="textCh8">公司总机</span><br /></td>
				<td>
				   <span class="showCh">
				      <c:if test="${not empty customerDto.telExchangeDto.regionCode && not empty customerDto.telExchangeDto.phoneNumber}">
				      <c:out value="${customerDto.telExchangeDto.regionCode}" escapeXml="true" />&nbsp;&nbsp;-&nbsp;
				      <c:out value="${customerDto.telExchangeDto.phoneNumber }" escapeXml="true"></c:out> 
				      </c:if>
				   </span>
				</td>
			</tr>
			<tr>
				<td><span class="textCh8">公司性质</span><br /></td>
				<td>  
				<span class="showCh">
				   <c:out value="${customerDto.typeDto.displayName }" escapeXml="true"></c:out>
				</span>
				</td>
				<td><span class="textCh8">公司规模</span></td>
				<td>
				<span class="showCh">
				   <c:out value="${customerDto.sizeDto.displayName }" escapeXml="true"></c:out>
				</span>
				</td>
			</tr>
			<tr>
				<td><span class="textCh8">客户等级</span></td>
				<td>
				<span class="showCh">
				   <c:out value="${customerDto.gradeDto.displayName }" escapeXml="true"></c:out>
				</span>
				</td>
				<td><span class="textCh8">客户状态</span></td>
				<td>
				<span class="showCh">
				   <c:out value="${customerDto.customerStatusDto.displayName }" escapeXml="true"></c:out>
				</span>
				</td>
			</tr>
			<tr>
				<td><span class="textCh8">客户介绍</span></td>
				<td colspan="3">
				<span class="showCh">
				   <c:out value="${customerDto.customerDescription }" escapeXml="true"></c:out>
				</span>
				</td>
			</tr>
		</table>
		<br />
		<div>
			<span class="titleCh10">&nbsp;第三部分：</span><span class="titleCh10">客户联系人资料</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" cellSpacing="0" width="100%" cellpadding="5px">
			<tr>
				<td align="center" colspan="2" width="15%"><span class="textCh8">姓名</span><br /> <br /></td>
				<td align="center" width="30%"><span class="textCh8">职位类型/职位</span><br /> <br /></td>
				<td align="center" width="15%"><span class="textCh8">手机</span><br /><br /></td>
				<td align="center" width="25%"><span class="textCh8">邮箱</span><br /><br /></td>
				<td align="center" width="15%"><span class="textCh8">状态</span><br /><br /></td>
			</tr>
			<c:forEach items="${customerDto.multiResponsePerson }" var="rp" varStatus="status">
			<tr>
				<td align="center" width="2%"><span class="textEn8">${status.index+1 }</span></td>
				<td><span class="textCn8"><c:out value="${rp.name }"></c:out></span></td>
				<td><span class="textCn8"><c:out value="${rp.positionName }"></c:out></span></td>
				<td><span class="textCn8"><c:out value="${rp.telephoneDto.phoneNumber }"></c:out></span></td>
				<td><span class="textCn8"><c:out value="${rp.email }"></c:out></span></td>
				<td>
					<table frame="void">
						<tr>
							<td><customer:checkSign comparisonValue="IS" actualValue="${rp.status}" ></customer:checkSign> </td>
							<td><span class="showCh">在职</span></td>
						</tr>
						<tr>
							<td><customer:checkSign comparisonValue="OS" actualValue="${rp.status}"></customer:checkSign> </td>
							<td><span class="showCh">已离职</span></td>
						</tr>
					</table>
				</td>
			</tr>
			</c:forEach>
		</table>
		<br />
		<table align="right" border="0" cellspacing="0" cellpadding="0">
		   <tr>
		      <td>
		         <input class="standardButton" type="submit" value="确认" />&nbsp;
		         <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
		      </td>
		   </tr>
		</table>

	</form:form>
</body>
</html>
