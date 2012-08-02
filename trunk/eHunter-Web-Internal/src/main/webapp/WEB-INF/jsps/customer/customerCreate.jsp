<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ taglib tagdir="/WEB-INF/tags/customer" prefix="customer"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-CUST-0001]</title>
<script type="text/javascript">
function changeCustType(){
	var custType = document.getElementById("custType");
	if(custType != null){
		var selectedIndex = custType.selectedIndex;
		if(selectedIndex == 1){
			$('#systemGroupRefNum').attr('disabled' , true);
			$('#groupFullName').attr('disabled' , false);
			$('#groupShortName').attr('disabled' , false);
		}
	}

}
</script>
</head>
<body>
	<form:form commandName="customerDto"
		action="${ctx}/customer/saveCustCoInfo.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">客户公司资料填写</td>
			</tr>
			<tr>
				<td><common:errorTable path="customerDto"></common:errorTable></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>							   
							   <input class="standardButton" type="submit" value="提交" />&nbsp;
							   <input class="standardButton" type="reset" value="重置">&nbsp;
							   <input class="standardButton" type="button" value="结束">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width=44><font face="Arial" size="2"><b>Part I</b></font></td>
				<td width="703"><font face="Arial" size="2"><b>集团资料</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0"
				cellpadding="0" width="100%">
				<tbody>
				    <customer:standardTableRow />
					<tr>
						<td class="labelColumn">客户类型：<span class="mandatoryField">*</span></td>
						<td>
						   <select id="custType" class="standardSelect" onchange="changeCustType();">
						      <option>--- 请选择  ---</option>
						      <option>集团客户</option>
						      <option>非集团客户（集团旗下子公司）</option>
						      <option>非集团客户（独立公司）</option>
						   </select>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
				</tbody>
			</table>
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <customer:standardTableRow />
					<tr >
						<td class="labelColumn">集团编号：</td>
						<td><form:input id="systemGroupRefNum" path="custGroup.systemGroupRefNum" cssClass="standardInputText" disabled="true"></form:input></td>
						<td colspan="2"></td>
					</tr>
					<tr >
						<td class="labelColumn">集团名称：</td>
						<td><form:input id="groupFullName" path="custGroup.fullName" cssClass="standardInputText" disabled="true"></form:input></td>
						<td class="labelColumn">集团简称：</td>
						<td>
						   <form:input id="groupShortName" path="custGroup.shortName" cssClass="standardInputText" disabled="true"/> 
					    </td>
					</tr>
				</tbody>
			</table>			
		</div>
		<table width="100%">
			<tr>
				<td width=44><font face="Arial" size="2"><b>Part II</b></font></td>
				<td width="703"><font face="Arial" size="2"><b>客户公司资料</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <customer:standardTableRow />
					<tr >
						<td class="labelColumn" >公司名称：<span class="mandatoryField">*</span></td>
						<td colspan="2"><form:input path="fullName" cssClass="standardInputText"></form:input></td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">公司简称：<span class="mandatoryField">*</span></td>
						<td colspan="2"><form:input path="shortName" cssClass="standardInputText"></form:input></td>
					    <td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">官方网址：</td>
						<td><form:input path="offcialSite" cssClass="standardInputText"></form:input></td>
						<td class="labelColumn">公司总机：</td>
						<td>
						   <form:input path="telExchange.regionCode" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/> - 
					       <form:input path="telExchange.phoneNumber" cssClass="standardInputTextNoWidth" maxlength="8" size="8"/>
					    </td>
					</tr>
					<tr >
						<td class="labelColumn">公司性质：<span class="mandatoryField">*</span></td>
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
					    <td class="labelColumn">公司规模：<span class="mandatoryField">*</span></td>
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
						<td class="labelColumn">客户等级：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="grade" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						      <form:option value="IR" label="有需求有意向"></form:option>
						      <form:option value="NI" label="有需求无意向"></form:option>
						      <form:option value="NR" label="无需求有意向"></form:option>
						      <form:option value="NN" label="无需求无意向"></form:option>
						   </form:select>
						</td>
						<td class="labelColumn">客户状态：<span class="mandatoryField">*</span></td>
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
		<table width="100%">
			<tr>
				<td width=44><font face="Arial" size="2"><b>Part III</b></font></td>
				<td width="703"><font face="Arial" size="2"><b>客户联系人资料</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <customer:standardTableRow />
				    <tr >
						<td class="labelColumn">姓名：<span class="mandatoryField">*</span></td>
						<td colspan="3"><form:input path="custRespPerson.name" cssClass="standardInputText"></form:input></td>
					</tr>
					<tr >
						<td class="labelColumn">职位类型：<span class="mandatoryField">*</span></td>
						<td>
						   <select  class="standardSelect"><option value="">--- 请选择 ---</option></select>
						</td>
						<td>
						   <form:select path="custRespPerson.positionType" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						   </form:select>
						</td>
						<td >&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">职位名称：<span class="mandatoryField">*</span></td>
						<td colspan="2"><form:input path="custRespPerson.positionName" cssClass="standardInputText"></form:input></td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">手机：<span class="mandatoryField">*</span></td>
						<td><form:input path="custRespPerson.telephone.phoneNumber" cssClass="standardInputText"></form:input></td>
						<td class="labelColumn">邮箱：<span class="mandatoryField">*</span></td>
						<td><form:input path="custRespPerson.email" cssClass="standardInputText"></form:input></td>
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
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							   <input class="standardButton" type="submit" value="提交" />&nbsp;
							   <input class="standardButton" type="reset" value="重置">&nbsp;
							   <input class="standardButton" type="button" value="结束">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
	</form:form>
</body>
</html>