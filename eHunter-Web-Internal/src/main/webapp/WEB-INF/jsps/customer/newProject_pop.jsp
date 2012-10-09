<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="fade" class="black_overlay"></div>
<div id="light" class="white_content_without_padding">
   <form:form id="newProjectForm" commandName="projectDto" action="${ctx}/project/saveProjectInfo.do" method="post">
      <table cellspacing="0" cellpadding="0" width="100%" >
		<tbody>
		    <tr height="0" style="display:none">
			   <td height="0" width="25%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="15%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="15%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="15%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="15%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="15%" style="padding: 0px; border-bottom: 0"></td>
			</tr>
			<tr class="popUpTitle">
			   <td colspan="4">
			      <span>&nbsp;&nbsp;请输入项目名称</span>
			   </td>
			   <td align="right"><span style="cursor: pointer;font:bold 14px Arial" onclick="document.getElementById('newProjectForm').submit();document.getElementById('light').style.display = 'none';document.getElementById('fade').style.display = 'none';showAllObject();">[确定]</span></td>
			   <td align="center"><span style="cursor: pointer;font: bold 14px Arial" onclick="document.getElementById('light').style.display = 'none';document.getElementById('fade').style.display = 'none';showAllObject();">[取消]</span></td>
			</tr>
			<tr height="5px"></tr>
			<tr><td class="seprator" colspan="6"></td></tr>
			<tr height="5px"></tr>
		</tbody>
	 </table>
	 <div class="emptyBlock"></div>
	 <table cellspacing="0" cellpadding="0" width="100%" >
		<tbody>
		    <tr height="0" style="display:none">
			   <td height="0" width="20%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="20%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="20%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="20%" style="padding: 0px; border-bottom: 0"></td>
			   <td height="0" width="20%" style="padding: 0px; border-bottom: 0"></td>
			</tr>
			<tr height="30px" style="background-color: rgb(245, 245, 245);font:12px Arial" >
				<td width="30%">
				  <span>&nbsp;&nbsp;项目名称：<span class="mandatoryField">*</span></span>
				</td>
				<td width="60%">
				   <form:input path="projectName" cssClass="standardInputText" />  
				</td>
				<td width="10%">&nbsp;</td>
			</tr>
			<tr height="5px"></tr>
		</tbody>
	  </table>
   </form:form>
</div>

