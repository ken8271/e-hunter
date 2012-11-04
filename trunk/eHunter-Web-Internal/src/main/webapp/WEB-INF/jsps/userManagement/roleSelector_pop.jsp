<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="role_fade" class="black_overlay"></div>
<div id="role_light" class="white_content_without_padding">
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
			      <span>&nbsp;&nbsp;请选择系统角色（您最多能选择5项）</span>
			   </td>
			   <td align="right"><span style="cursor: pointer;font:bold 14px Arial" onclick="getSelectedRoles();document.getElementById('role_light').style.display = 'none';document.getElementById('role_fade').style.display = 'none';showAllObject();">[确定]</span></td>
			   <td align="center"><span style="cursor: pointer;font: bold 14px Arial" onclick="document.getElementById('role_light').style.display = 'none';document.getElementById('role_fade').style.display = 'none';showAllObject();">[取消]</span></td>
			</tr>
			<tr height="5px">
			   <td colspan="10">
			      <select style="display: none" id="roleSelector">
			         <c:forEach items="${internalUserDto.activeRoles }" var="roleDto">
			            <option value="${roleDto.sysRefRole }">${roleDto.roleName }</option>
			         </c:forEach>
			      </select>
			   </td>
			</tr>
			<tr id="selectedRoles" height="25px">
			    <td><span style="font:12px Arial">&nbsp;&nbsp;已选角色：</span></td>
			</tr>
			<tr><td class="seprator" colspan="6"></td></tr>
			<tr height="5px"></tr>
		</tbody>
	</table>
	<table id="roleDisplayPanel" cellspacing="0" cellpadding="0" width="100%">
	   <c:forEach items="${listOfRole}" var="roleDto" varStatus="status">
	      <c:if test="${status.index%2 == 0 }">
	         <tr height='30px'>
	      </c:if>
	         <td align="center" style='cursor:pointer; width: 20%; padding-left: 1px;' onclick='handleSelect(this);'>
	            <input style='display:none' type='checkbox' value='${roleDto.sysRefRole }'/>&nbsp;${roleDto.roleName } 
	         </td>
	      <c:if test="${status.index%2 == 1 }">
	         </tr>
	      </c:if>
	   </c:forEach>
	</table>
</div>