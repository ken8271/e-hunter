<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<hdiv-c:url value="/project/loadCities.do" var="loadCities"></hdiv-c:url>
<script type="text/javascript" src="${scriptPath }/multiSelector.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	getSelectedCities();
	displaySelectedCities(document.getElementById('citySelector'));
	
	getSelectedIndustries();
	displaySelectedIndustries(document.getElementById('industrySelector'));
});

function popUpCitySelector(){
	$('#citySelector').hide();
	setPopUpFramePosition('city_light',600,300);
	setOverlayDimension('city_fade');	
	popUpFrame('city_light','city_fade');
}

function getSelectedCities(){
	var citySelector = document.getElementById('citySelector');
	
	var displayStr = "<span>";
	var displayValue = '';
	for(var i=0 ;  i<citySelector.length ; i++){
		displayStr = displayStr + citySelector.options[i].text + "&nbsp;&nbsp;&nbsp;&nbsp;";
		
		if(i == citySelector.length-1){
			displayValue = displayValue + citySelector.options[i].value;
		}else {
			displayValue = displayValue + citySelector.options[i].value + ',';
		}
	}
	displayStr = displayStr + "</span>";
	
	$('#expectCitiesDisplay span').remove();
	$(displayStr).appendTo('#expectCitiesDisplay');
	
	$('#expectAddress').val(displayValue);
}

function displaySelectedCities(citySelector){
	var str = '';
	for(var i=0 ; i<5 ; i++){
		if(i < citySelector.length){
			str = str + "<td><input type='checkbox' value='" + citySelector.options[i].value + "' checked onclick='handleUnselect(this)'/>" + citySelector.options[i].text +"</td>";
		}else {
			str = str + "<td>&nbsp;</td>";
		}
	}
	
	clearSelectedCities();
	$(str).appendTo('#selectedCities');
}

function clearCities(){
	$('#cityDisplayPanel tr').remove();
}

function clearSelectedCities(){
	$('#selectedCities td:gt(0)').remove();
}

function handleUnselect(c){
	var citySelector = document.getElementById('citySelector');
	if(!c.checked){
		removeByCode(citySelector , c.value);
		
		var city = $('#cityDisplayPanel [value='+ c.value + ']');
		if(city != null && city[0] != null){
			city[0].checked = false;
		}
		
		displaySelectedCities(citySelector);
	}
}

function handleSelect(city){
	var citySelector = document.getElementById('citySelector');
	var c = $(city).children();
	var label = $(city).text();
	c[0].checked = !c[0].checked;
	
	if(c[0].checked){
		if(citySelector.length >= 5){
			alert('最多可添加5个地区');
			c[0].checked = false;
			return ;
		}
		
		if(!hasBeenSelect(citySelector , c[0].value)){			
		   citySelector.options[citySelector.length] = new Option(label, c[0].value);
		}else {
		   c[0].checked = false;
		   removeByCode(citySelector,c[0].value);
		}
	}else {		
		removeByCode(citySelector,c[0].value);
	}
	
	displaySelectedCities(citySelector);
}

function loadCities(){
	clearCities();
	var provinceSelector = document.getElementById("provinceSelector");
	if(provinceSelector != null && provinceSelector.selectedIndex != 0){
		$().progressDialog.showDialog("");
		var code = provinceSelector.options[provinceSelector.selectedIndex].value;
		$.ajax({
			type:'post',
			url:'${loadCities}',
			dataType:'xml',
			data:{'_id':code},
			success:function(xml){
				var str = '';
				var count = 0;
				$(xml).find('city').each(function(i , element){
					count++;
					var cityCode = $(this).find("value").text();
					var cityName = $(this).find("label").text();
					if(count%5 == 1){
						str = str + "<tr height='30px'>";
					}
					
					str = str + "<td style='cursor:pointer; width: 20%; padding-left: 1px;' onclick='handleSelect(this);'><input style='display:none' type='checkbox' value='" + cityCode + "'/>&nbsp;" + cityName +"</td>";

					if(count%5 == 0){
						str = str + "</tr>";
					}
				});
				
				$(str).appendTo('#cityDisplayPanel');
				$().progressDialog.hideDialog("");
			},
			error:function(){
				$().progressDialog.hideDialog("");
				alert('系统错误');
			}
		});
	}
}
</script>
</head>
<body>
    <hdiv-c:url value="/talent/backWithNothingFilled.do" var="backUrl"></hdiv-c:url>
	<form:form commandName="intentionDto" action="${ctx}/talent/completeAddJobIntention.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">求职意向填写/编辑</td>
			</tr>
			<tr>
				<td><common:errorTable path="intentionDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<input class="standardButton" type="submit" value="确认"/>&nbsp;
							<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">期望工作性质：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						<form:radiobutton path="employmentCategory" value="FT" /> &nbsp;全职&nbsp;&nbsp;&nbsp;
						<form:radiobutton path="employmentCategory" value="PT" /> &nbsp;兼职&nbsp;&nbsp;&nbsp;
						<form:radiobutton path="employmentCategory" value="IS" /> &nbsp;实习&nbsp;&nbsp;&nbsp;
						<common:errorSign id="employmentCategory" path="employmentCategory"></common:errorSign>
						</td>
					</tr>
				    <tr >
						<td class="labelColumn">期望工作地点：<span class="mandatoryField">*</span></td>
						<td>
					       <input type="button" class="selectButton" value="选择/修改" onFocus="this.blur()" onclick="popUpCitySelector();">
						   <form:hidden path="expectAddress"/>
						   <common:errorSign id="expectAddress" path="expectAddress"></common:errorSign>
						</td>
						<td id="expectCitiesDisplay" colspan="2"></td>
					</tr>
					<tr >
						<td class="labelColumn">期望从事职业：<span class="mandatoryField">*</span></td>
						<td>
						  <form:select path="expectPosition" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfPositionCategory }" var="positionCategory">
						         <form:option value="${positionCategory.typeCode }" label="${positionCategory.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						  <common:errorSign id="expectPosition" path="expectPosition"></common:errorSign>
						</td>
						<td class="labelColumn">期望从事行业：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="expectIndustry" cssClass="standardSelect" >
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfIndustryCategory }" var="industryCategory">
						         <form:option value="${industryCategory.categoryCode }" label="${industryCategory.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						   <common:errorSign id="expectIndustry" path="expectIndustry"></common:errorSign>
						</td>
					</tr>
					<tr >
					    <td class="labelColumn">期望月薪(税前)：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="expectSalary" cssClass="standardInputTextNoWidth"/>&nbsp;千元/月
						   <common:errorSign id="expectSalary" path="expectSalary"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
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
							<input class="standardButton" type="submit" value="确认"/>&nbsp;
							<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<jsp:include page="citySelector_pop.jsp"></jsp:include>
	</form:form>
</body>
</html>