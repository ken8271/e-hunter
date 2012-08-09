<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ taglib tagdir="/WEB-INF/tags/customer" prefix="customer"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" href="${ctx}/style/verify.css" type="text/css" />
</head>
<body>
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
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="确认" />&nbsp;
								<input class="standardButton" type="button" value="返回" >
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table width="99%">
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
		<table class="verifyTable" style="border-collapse: collapse; margin: 5" border="2" width="99%" cellpadding="5px">
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
		<table class="verifyTable" style="border-collapse: collapse; margin: 5" border="2" width="99%" cellpadding="5px">
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
				<td><span class="showCh"><c:out value="${customerDto.telExchangeDto.regionCode}" escapeXml="true" />&nbsp;&nbsp;-&nbsp;<c:out value="${customerDto.telExchangeDto.phoneNumber }" escapeXml="true"></c:out> </span></td>
			</tr>
			<tr>
				<td><span class="textCh8">公司性质</span><br /></td>
				<td>  
				<span class="showCh">
				<c:if test="${customerDto.type == 'SE'}">国有企业</c:if>
				<c:if test="${customerDto.type == 'EF'}">外商独资/外企办事处</c:if>
				<c:if test="${customerDto.type == 'JV'}">中外合营(合资/合作)</c:if>
				<c:if test="${customerDto.type == 'IE'}">私营/名营企业</c:if>
				<c:if test="${customerDto.type == 'NA'}">政府机关/非盈利机构</c:if>
				<c:if test="${customerDto.type == 'JE'}">股份制企业</c:if>
				<c:if test="${customerDto.type == 'QC'}">上市公司</c:if>
				<c:if test="${customerDto.type == 'PI'}">事业单位</c:if>
				<c:if test="${customerDto.type == 'OT'}">其他</c:if>
				</span>
				</td>
				<td><span class="textCh8">公司规模</span></td>
				<td>
				<span class="showCh">
				<c:if test="${customerDto.size == '001' }">1-49人</c:if>
				<c:if test="${customerDto.size == '002' }">50-99人</c:if>
				<c:if test="${customerDto.size == '003' }">100-499人</c:if>
				<c:if test="${customerDto.size == '004' }">500-999人</c:if>
				<c:if test="${customerDto.size == '005' }">1000-2000人</c:if>
				<c:if test="${customerDto.size == '006' }">2000-5000人</c:if>
				<c:if test="${customerDto.size == '007' }">5000-10000人</c:if>
				<c:if test="${customerDto.size == '008' }">10000人以上</c:if>
				</span>
				</td>
			</tr>
			<tr>
				<td><span class="textCh8">客户等级</span></td>
				<td>
				<span class="showCh">
				<c:if test="${customerDto.grade == 'IR' }">有需求有意向</c:if>
				<c:if test="${customerDto.grade == 'NI' }">有需求无意向</c:if>
				<c:if test="${customerDto.grade == 'NR' }">无需求有意向</c:if>
				<c:if test="${customerDto.grade == 'NN' }">无需求无意向</c:if>
				</span>
				</td>
				<td><span class="textCh8">客户状态</span></td>
				<td>
				<span class="showCh">
				<c:if test="${customerDto.status == 'SGN' }">已签约客户</c:if>
				<c:if test="${customerDto.status == 'PTL' }">潜力客户</c:if>
				<c:if test="${customerDto.status == 'OTH' }">其他</c:if>
				</span>
				</td>
			</tr>
		</table>
		<br />
		<div>
			<span class="titleCh10">&nbsp;第三部分：</span><span class="titleCh10">客户联系人资料</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin: 5" border="2" cellSpacing="0" width="99%" cellpadding="5px">
			<tr>
				<td align="center" colspan="2" width="15%"><span class="textCh8">姓名</span><br /> <br /></td>
				<td align="center" width="30%"><span class="textCh8">职位类型/职位</span><br /> <br /></td>
				<td align="center" width="15%"><span class="textCh8">手机</span><br /><br /></td>
				<td align="center" width="25%"><span class="textCh8">邮箱</span><br /><br /></td>
				<td align="center" width="15%"><span class="textCh8">状态</span><br /><br /></td>
			</tr>
			<tr>
				<td align="center" width="2%"><span class="textEn8">1</span></td>
				<td><span class="textCn8"><c:out value="${customerDto.custRespPerson.name }"></c:out></span></td>
				<td><span class="textCn8"><c:out value="${customerDto.custRespPerson.positionName }"></c:out></span></td>
				<td><span class="textCn8"><c:out value="${customerDto.custRespPerson.telephoneDto.phoneNumber }"></c:out></span></td>
				<td><span class="textCn8"><c:out value="${customerDto.custRespPerson.email }"></c:out></span></td>
				<td>
					<table frame="void">
						<tr>
							<td><customer:checkSign comparisonValue="IS" actualValue="${customerDto.custRespPerson.status}" ></customer:checkSign> </td>
							<td><span class="showCh">在职</span></td>
						</tr>
						<tr>
							<td><customer:checkSign comparisonValue="OS" actualValue="${customerDto.custRespPerson.status}"></customer:checkSign> </td>
							<td><span class="showCh">已离职</span></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br />
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="确认" />&nbsp;
								<input class="standardButton" type="button" value="返回">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

	</form:form>
</body>
</html>
