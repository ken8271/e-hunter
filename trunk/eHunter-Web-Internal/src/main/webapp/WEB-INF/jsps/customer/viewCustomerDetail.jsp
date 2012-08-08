<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-CUST-0102]</title>
</head>
<body>
<hdiv-c:url value="/customer/editCustomer.do" var="editCustomerUrl"></hdiv-c:url>
<form:form commandName="customerDto">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">客户公司详细资料</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input class="standardButton" type="button" value="创建新项目" onclick="location.href='${ctx}/project/initAddProject.do'"/>&nbsp;
								<input class="standardButton" type="button" value="编辑" onclick="location.href='${editCustomerUrl}'"/>&nbsp;
								<input class="standardButton" type="button" value="返回" onclick="location.href='${ctx}/customer/initCustomersSearch.do'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			<tbody>
				<customer:standardTableRow />
				<tr >
				   <td class="labelColumn">客户编号：</td>
				   <td><form:input path="systemCustRefNum" cssClass="standardInputText" disabled="true"></form:input></td>
				   <td colspan="2">&nbsp;</td>
			    </tr>
			</tbody>
		</table>
		<div class="emptyBlock"></div>
		<c:if test="${customerDto.groupIndicator == 'group' || customer.groupIndicator == 'subsidiary'}">
		<table width="100%">
			<tr>
				<td width="10%"><font face="Arial" size="2"><b>第一部分：</b></font></td>
				<td width="90%"><font face="Arial" size="2"><b>集团资料</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <customer:standardTableRow />
					<tr >
						<td class="labelColumn">集团名称：</td>
						<td>
						<form:input path="custGroup.fullName" cssClass="standardInputText"></form:input>
						</td>
						<td class="labelColumn">集团简称：</td>
						<td>
						   <form:input path="custGroup.shortName" cssClass="standardInputText"/> 
					    </td>
					</tr>
				</tbody>
		</table>
		</div>
		</c:if>
		<table width="100%">
			<tr>
				<td width="10%"><font face="Arial" size="2"><b>第二部分：</b></font></td>
				<td width="90%"><font face="Arial" size="2"><b>客户公司资料</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <customer:standardTableRow />
					<tr >
						<td class="labelColumn" >公司名称：</td>
						<td colspan="2">
						<form:input path="fullName" cssClass="standardInputText"></form:input>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">公司简称：</td>
						<td colspan="2">
						<form:input path="shortName" cssClass="standardInputText"></form:input>
						</td>
					    <td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">官方网址：</td>
						<td>
						<form:input path="offcialSite" cssClass="standardInputText"></form:input>
						</td>
						<td class="labelColumn">公司总机：</td>
						<td>
						   <form:input path="telExchangeDto.regionCode" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/> - 
					    </td>
					</tr>
					<tr >
						<td class="labelColumn">公司性质：</td>
						<td>
						   <form:select path="type" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						      <form:option value="SE" label="国有企业"></form:option>
						      <form:option value="EF" label="外商独资/外企办事处"></form:option>
						      <form:option value="JV" label="中外合营(合资/合作)"></form:option>
						      <form:option value="IE" label="私营/民营企业"></form:option>
						      <form:option value="NA" label="政府机关/非盈利机构"></form:option>
						      <form:option value="JE" label="股份制企业"></form:option>
						      <form:option value="QC" label="上市公司"></form:option>
						      <form:option value="PI" label="事业单位"></form:option>
						      <form:option value="OT" label="其他"></form:option>
						   </form:select>
						</td>
					    <td class="labelColumn">公司规模：</td>
						<td>
						   <form:select path="size" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						      <form:option value="001" label="1-49人"></form:option>
						      <form:option value="002" label="50-99人"></form:option>
						      <form:option value="003" label="100-499人"></form:option>
						      <form:option value="004" label="500-999人"></form:option>
						      <form:option value="005" label="1000-2000人"></form:option>
						      <form:option value="006" label="2000-5000人"></form:option>
						      <form:option value="007" label="5000-10000人"></form:option>
						      <form:option value="008" label="10000人以上"></form:option>
						   </form:select>
						</td>
					</tr>
					<tr >
						<td class="labelColumn">客户等级：</td>
						<td>
						   <form:select path="grade" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						      <form:option value="IR" label="有需求有意向"></form:option>
						      <form:option value="NI" label="有需求无意向"></form:option>
						      <form:option value="NR" label="无需求有意向"></form:option>
						      <form:option value="NN" label="无需求无意向"></form:option>
						   </form:select>
						</td>
						<td class="labelColumn">客户状态：</td>
						<td>
						   <form:select path="status" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						      <form:option value="SGN" label="已签约客户"></form:option>
						      <form:option value="PTL" label="潜力客户"></form:option>
						      <form:option value="OTH" label="其它"></form:option>
						   </form:select>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>	
		<table width="100%">
			<tr>
				<td width="10%"><font face="Arial" size="2"><b>第三部分：</b></font></td>
				<td width="90%"><font face="Arial" size="2"><b>客户联系人资料</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <customer:standardTableRow />
				    <tr >
						<td class="labelColumn">姓名：</td>
						<td colspan="3">
						<form:input path="custRespPerson.name" cssClass="standardInputText"></form:input>
						</td>
					</tr>
					<tr >
						<td class="labelColumn">职位类型：</td>
						<td>
						   <select  class="standardSelect" id="postSelector" onchange="loadPositions();">
						   <option value="">--- 请选择 ---</option>
						   <c:forEach items="${listOfPositionType}" var="postType">
						      <option value="${postType.value }">${postType.label }</option>
						   </c:forEach>
						   </select>
						</td>
						<td>
						   <form:select id="subPostSelector" path="custRespPerson.positionType" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						   </form:select>
						</td>
						<td >&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">职位名称：<span class="mandatoryField">*</span></td>
						<td colspan="2">
						<form:input path="custRespPerson.positionName" cssClass="standardInputText"></form:input>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">手机：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="custRespPerson.telephoneDto.phoneNumber" cssClass="standardInputText"></form:input>
						</td>
						<td class="labelColumn">邮箱：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="custRespPerson.email" cssClass="standardInputText"></form:input>
						</td>
					</tr>	
					<tr >
						<td class="labelColumn">状态：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="custRespPerson.status" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						      <form:option value="IS" label="在职"></form:option>
						      <form:option value="OS" label="已离职"></form:option>
						   </form:select>
						</td>
						<td colspan="2">
					</tr>				
				</tbody>
			</table>
		</div>	
		<div class="emptyBlock"></div>
		<div>${listOfCustomerProjects}</div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input class="standardButton" type="button" value="创建新项目" onclick="location.href='${ctx}/project/initAddProject.do'"/>&nbsp;
								<input class="standardButton" type="button" value="编辑" onclick="location.href='${editCustomerUrl}'"/>&nbsp;
								<input class="standardButton" type="button" value="返回" onclick="location.href='${ctx}/customer/initCustomersSearch.do'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</form:form>
</body>
</html>
