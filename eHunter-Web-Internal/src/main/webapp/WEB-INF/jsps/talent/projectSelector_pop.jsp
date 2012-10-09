<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="fade" class="black_overlay"></div>
<div id="light" class="white_content_without_padding">
  <form:form id="projectSelectorForm" commandName="prjEnquireDto" action="${ctx }/project/loadUnassignedProjects.do" method="post">
    <form:input path="systemTalentRefNum" cssStyle="display:none"/>
	<table cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<tr height="0" style="display: none">
				<td height="0" width="200%" style="padding: 0px; border-bottom: 0"></td>
				<td height="0" width="20%" style="padding: 0px; border-bottom: 0"></td>
				<td height="0" width="10%" style="padding: 0px; border-bottom: 0"></td>
				<td height="0" width="20%" style="padding: 0px; border-bottom: 0"></td>
				<td height="0" width="20%" style="padding: 0px; border-bottom: 0"></td>
				<td height="0" width="10%" style="padding: 0px; border-bottom: 0"></td>
			</tr>
			<tr class="popUpTitle">
				<td colspan="4"><span>&nbsp;&nbsp;请选择猎头项目</span></td>
				<td align="right"><span style="cursor: pointer; font: bold 14px Arial" onclick="loadProjectsByAjax();">[查询]</span>
				</td>
				<td align="center"><span
					style="cursor: pointer; font: bold 14px Arial"
					onclick="document.getElementById('light').style.display = 'none';document.getElementById('fade').style.display = 'none';showAllObject();">[取消]</span>
				</td>
			</tr>
			<tr height="5px"></tr>
			<tr>
				<td>&nbsp;&nbsp;项目编号：</td>
				<td width="30%" colspan="2"><form:input path="systemProjectRefNum" cssClass="standardInputText"></form:input></td>
				<td>&nbsp;&nbsp;项目名称：</td>
				<td width="30%" colspan="2"><form:input path="projectName" cssClass="standardInputText"></form:input></td>
			</tr>
			<tr height="5px"></tr>
			<tr>
				<td>&nbsp;&nbsp;客户编号：</td>
				<td colspan="2"><form:input path="systemCustRefNum" cssClass="standardInputText"></form:input></td>
				<td>&nbsp;&nbsp;客户名称：</td>
				<td colspan="2"><form:input path="customerName" cssClass="standardInputText"></form:input></td>
			</tr>
			<tr height="5px"></tr>
			<tr height="5px"></tr>
			<tr>
				<td class="seprator" colspan="6"></td>
			</tr>
			<tr height="5px"></tr>
		</tbody>
	</table>
	<div class="emptyBlock"></div>
	<table id="resultTable" class="contentTableBody2" cellspacing="1" width="100%" style="display: none">
		   <tr class="contentTableTitle">
		      <td width="10%" align="center">选择</td>
		      <td width="20%">项目编号</td>
		      <td width="30%">项目名称</td>
		      <td width="25%">客户公司</td>
		      <td width="15%" align="center">项目状态</td>
		   </tr>
	</table>
  </form:form>
</div>