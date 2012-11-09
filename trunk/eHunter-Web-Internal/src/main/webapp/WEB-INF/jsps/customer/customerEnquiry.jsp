<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-CUST-0101]</title>
<script type="text/javascript">
function resetForm(){
	$("#systemCustRefNum").val('');
	$("#groupName").val('');
	$("#name").val('');
	$("#type").val('');
	$("#size").val('');
	$("#grade").val('');
	$("#status").val('');
}
</script>
<common:jmesaScript actionFlagStr="90"></common:jmesaScript>
</head>
<body>
<form:form commandName="customerEnquireDto" action="${ctx}/customer/customersSearch.do">
        <div style="display: none">
			<input type="hidden" id="actionFlag" name="actionFlag" />
			<input type="hidden" name="module" value="${module }"/>
		</div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">客户公司查询</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="查询" />&nbsp;
								<input class="standardButton" type="button" value="重置" onclick="resetForm();"/>&nbsp;
								<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
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
				<tr >
				   <td class="labelColumn">客户编号：</td>
				   <td><form:input path="systemCustRefNum" cssClass="standardInputText"></form:input></td>
				   <td colspan="2">&nbsp;</td>
			    </tr>
			</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">集团名称/简称：</td>
						<td>
						<form:input path="groupName" cssClass="standardInputText"></form:input>
						</td>
						<td class="labelColumn">客户名称/简称：</td>
						<td>
						   <form:input path="name" cssClass="standardInputText"/> 
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
		<div class="emptyBlock"></div>		
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="查询" />&nbsp;
							    <input class="standardButton" type="button" value="重置" onclick="resetForm();"/>&nbsp;
								<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div>${listOfCustomers }</div>
</form:form>
</body>
</html>
