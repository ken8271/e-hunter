<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="fade" class="black_overlay"></div>
<div id="light" class="white_content">
	<table class="contentTableBody2" cellspacing="1" width="100%" border="0">
		<tbody>
			<tr height="0" style="display:none">
			   <td height="0" width="20%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="25%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="20%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="25%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="10%" style="padding: 0px; border-bottom: 0"></td>
			</tr>
			<tr class="contentTableRow">
				<td class="labelColumn">客户编号：</td>
				<td><input id="customerId" type="text" class="standardInputText"  /></td>
				<td class="labelColumn">客户名称/简称：</td>
				<td><input id="customerName" type="text" class="standardInputText"  /></td>
				<td>
				   <input class="standardButton" type="button" value="查询" onclick="clearResult();loadCustomerInfo();" />&nbsp;
				</td>
			</tr>
		</tbody>
	</table>
	<div class="emptyBlock"></div>
	<table id="resultTable" class="contentTableBody2" cellspacing="1" width="100%">
		   <tr class="contentTableTitle">
		      <td width="10%" align="center">选择</td>
		      <td width="20%">客户编号</td>
		      <td width="30%">公司名称</td>
		      <td width="20%" align="center">客户等级</td>
		      <td width="20%" align="center">客户状态</td>
		   </tr>
	</table>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						<input class="standardButton" type="button" value="确定" onclick="getSelectedCustomer();" />&nbsp;
						<input class="standardButton" type="button" value="取消" onclick="document.getElementById('light').style.display = 'none';document.getElementById('fade').style.display = 'none';showAllObject();">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>

