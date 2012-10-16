<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="position_fade" class="black_overlay"></div>
<div id="position_light" class="white_content_without_padding">
	<table cellspacing="0" cellpadding="0" width="100%">
		<tr class="popUpTitle">
			<td width="70%"><span>&nbsp;&nbsp;请选择所任职位（您最多能选择3项）</span>
			</td>
			<td width="15%" align="right">
			   <span style="cursor: pointer; font: bold 14px Arial" onclick="getSelectedPositions();document.getElementById('position_light').style.display = 'none';document.getElementById('position_fade').style.display = 'none';showAllObject();">[确定]</span>
			</td>
			<td width="15%" align="center">
			   <span style="cursor: pointer; font: bold 14px Arial" onclick="document.getElementById('position_light').style.display = 'none';document.getElementById('position_fade').style.display = 'none';showAllObject();">[取消]</span>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table cellspacing="0" cellpadding="0" width="100%">
		<tr id="selectedPositions" height="25px">
			<td>
			   <select style="display: none" id="positionSelector">
			      <c:forEach items="${employmentHistoryDto.positionDtos }" var="positionDto">
			         <option value="${positionDto.typeCode }">${positionDto.displayName }</option>
			      </c:forEach>
			   </select>
			   <span style="font: 12px Arial">&nbsp;&nbsp;已选职位：</span>
			</td>
		</tr>
		<tr>
			<td class="seprator" colspan="6"></td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table cellspacing="0" cellpadding="0" width="100%">
		<tr height="30px" style="background-color: rgb(245, 245, 245); font: 12px Arial">
			<td width="20%"><span>&nbsp;&nbsp;职位类型选择：</span></td>
			<td width="30%">
			   <select id="positionCategorySelector" class="standardSelect" onchange="loadPositions();">
					<option value="">--- 请选择 ---</option>
					<c:forEach items="${listOfPositionCategory }" var="positionCategoryDto">
						<option value="${positionCategoryDto.typeCode }">${positionCategoryDto.displayName}</option>
					</c:forEach>
				</select>
			</td>
			<td width="50%">&nbsp;</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table id="positionDisplayPanel" cellspacing="0" cellpadding="0" width="100%">
	</table>
</div>