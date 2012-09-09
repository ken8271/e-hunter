<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="fade" class="black_overlay"></div>
<div id="light" class="white_content_without_padding">
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
			      <span>&nbsp;&nbsp;请选择工作地点（您最多能选择5项）</span>
			   </td>
			   <td align="right"><span style="cursor: pointer;font:bold 14px Arial" onclick="getSelectedCities();document.getElementById('light').style.display = 'none';document.getElementById('fade').style.display = 'none';showAllObject();">[确定]</span></td>
			   <td align="center"><span style="cursor: pointer;font: bold 14px Arial" onclick="document.getElementById('light').style.display = 'none';document.getElementById('fade').style.display = 'none';showAllObject();">[取消]</span></td>
			</tr>
			<tr height="5px">
			   <td colspan="10">
			      <select style="display: none" id="citySelector">
			         <c:forEach items="${postDescDto.cityDtos }" var="cityDto">
			            <option value="${cityDto.cityCode }">${cityDto.displayName }</option>
			         </c:forEach>
			      </select>
			   </td>
			</tr>
			<tr id="selectedCities" height="25px">
			    <td><span style="font:12px Arial">&nbsp;&nbsp;已选城市：</span></td>
			</tr>
			<tr><td class="seprator" colspan="6"></td></tr>
			<tr height="5px"></tr>
		</tbody>
	</table>
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
				<td width="20%">
				  <span>&nbsp;&nbsp;城市选择：</span>
				</td>
				<td width="30%">
				   <select id="provinceSelector" class="standardSelect" onchange="loadCities();">
				      <option value="">--- 请选择  ---</option>
				      <c:forEach items="${listOfProvince }" var="provinceDto">
				         <option value="${provinceDto.provinceCode }">${provinceDto.displayName }</option>
				      </c:forEach>
				   </select>
				</td>
				<td width="50%">&nbsp;</td>
			</tr>
			<tr height="5px"></tr>
		</tbody>
	</table>
	<table id="cityDisplayPanel" cellspacing="0" cellpadding="0" width="100%" >
	</table>
</div>