<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<hdiv-c:url value="/talent/loadIndustries.do" var="loadIndustriesUrl"></hdiv-c:url>
<hdiv-c:url value="/talent/loadPositions.do" var="loadPositionsUrl"></hdiv-c:url>
<script type="text/javascript" src="${scriptPath }/multiSelector.js"></script>
<script type="text/javascript">
$().ready(function(){
	if('${clearField}' == 'Y'){
		clearInputFields();
	}else if('${clearField}' == 'N'){
		loadIndustries();
		getSelectedPositions();
		displaySelectedPositions(document.getElementById('positionSelector'));
	}
});

function clearInputFields(){
	document.getElementById('beginTimeDto.year').value='';
	document.getElementById('beginTimeDto.month').value='';
	document.getElementById('beginTimeDto.day').value='';
	document.getElementById('endTimeDto.year').value='';
	document.getElementById('endTimeDto.month').value='';
	document.getElementById('endTimeDto.day').value='';
	document.getElementById('companyName').value='';
	document.getElementById('industrySelector').value='';
	document.getElementById('industry').value='';
	document.getElementById('positions').value='';
}

function loadIndustries(){
	var industrySelector = document.getElementById("industrySelector");
	if(industrySelector != null && industrySelector.selectedIndex != 0){
		$().progressDialog.showDialog("");
		var _id = industrySelector.options[industrySelector.selectedIndex].value;
		$.ajax({
			type:'post',
			url:'${loadIndustriesUrl}',
			dataType:'xml',
			data:{'_id':_id},
			success:function(xml){	
				$().progressDialog.hideDialog("");
				var industry = document.getElementById("industry");
				clearSelector(industry);
				industry.options[industry.length] = new Option("--- 请选择 ---", "");
				$(xml).find('industry').each(function(i , element){
					var label = $(this).find("label").text();
					var val = ''+$(this).children("value").text()+'';
					industry.options[industry.length] = new Option(label, val);
				});
				if('${clearField}' == 'N'){
					document.getElementById('industry').value = '${employmentHistoryDto.industry}';
				}
			},
			error:function(){
				$().progressDialog.hideDialog("");
				alert('系统错误');
			}
		});
	}else{
		var industry = document.getElementById("industry");
		clearSelector(industry);
		industry.options[industry.length] = new Option("--- 请选择 ---", "");
	}
}

function loadPositions(){
	clearPositions();
	var positionSelector = document.getElementById("positionCategorySelector");
	if(positionSelector != null && positionSelector.selectedIndex != 0){
		$().progressDialog.showDialog("");
		var _id = positionSelector.options[positionSelector.selectedIndex].value;
		$.ajax({
			type:'post',
			url:'${loadPositionsUrl}',
			dataType:'xml',
			data:{'_id':_id},
			success:function(xml){
				var str = '';
				var count = 0;
				$(xml).find('position').each(function(i , element){
					count++;
					var positionCode = $(this).find("value").text();
					var positionName = $(this).find("label").text();
					if(count%2 == 1){
						str = str + "<tr height='30px'><td width='5%'></td>";
						str = str + "<td style='cursor:pointer; width: 45%; padding-left: 1px;' onclick='handleSelect(this);'><input style='display:none' type='checkbox' value='" + positionCode + "'/>&nbsp;" + positionName +"</td>";
					}

					if(count%2 == 0){
						str = str + "<td width='5%'></td>";
					    str = str + "<td style='cursor:pointer; width: 45%; padding-left: 1px;' onclick='handleSelect(this);'><input style='display:none' type='checkbox' value='" + positionCode + "'/>&nbsp;" + positionName +"</td>";
					    str = str + "</tr>";
					}
				});
				
				$(str).appendTo('#positionDisplayPanel');
				$().progressDialog.hideDialog("");
			},
			error:function(){
				$().progressDialog.hideDialog("");
				alert('系统错误');
			}
		});
	}
}

function popUpPositionSelector(){
	$('#positionSelector').hide();
	setPopUpFramePosition('position_light',600,300);
	setOverlayDimension('position_fade');	
	popUpFrame('position_light','position_fade');
}

function getSelectedPositions(){
	var positionSelector = document.getElementById('positionSelector');
	
	var displayStr = "<span>";
	var displayValue = '';
	for(var i=0 ;  i<positionSelector.length ; i++){
		displayStr = displayStr + positionSelector.options[i].text + "&nbsp;&nbsp;&nbsp;&nbsp;";
		
		if(i == positionSelector.length-1){
			displayValue = displayValue + positionSelector.options[i].value;
		}else {
			displayValue = displayValue + positionSelector.options[i].value + ',';
		}
	}
	displayStr = displayStr + "</span>";
	
	$('#positionDisplay span').remove();
	$(displayStr).appendTo('#positionDisplay');
	
	$('#positions').val(displayValue);
}

function displaySelectedPositions(positionSelector){
	var str = '';
	for(var i=0 ; i<3 ; i++){
		if(i < positionSelector.length){
			str = str + "<td><input type='checkbox' value='" + positionSelector.options[i].value + "' checked onclick='handleUnselect(this)'/>" + positionSelector.options[i].text +"</td>";
		}else {
			str = str + "<td>&nbsp;</td>";
		}
	}
	
	clearSelectedPositions();
	$(str).appendTo('#selectedPositions');
}

function clearPositions(){
	$('#positionDisplayPanel tr').remove();
}

function clearSelectedPositions(){
	$('#selectedPositions td:gt(0)').remove();
}

function handleUnselect(c){
	var positionSelector = document.getElementById('positionSelector');
	if(!c.checked){
		removeByCode(positionSelector , c.value);
		
		var position = $('#positionDisplayPanel [value='+ c.value + ']');
		if(position != null && position[0] != null){
			position[0].checked = false;
		}
		
		displaySelectedPositions(positionSelector);
	}
}

function handleSelect(position){
	var positionSelector = document.getElementById('positionSelector');
	var c = $(position).children();
	var label = $(position).text();
	c[0].checked = !c[0].checked;
	
	if(c[0].checked){
		if(positionSelector.length >= 3){
			alert('最多可添加3个职位');
			c[0].checked = false;
			return ;
		}
		
		if(!hasBeenSelect(positionSelector , c[0].value)){			
			positionSelector.options[positionSelector.length] = new Option(label, c[0].value);
		}else {
		   c[0].checked = false;
		   removeByCode(positionSelector,c[0].value);
		}
	}else {		
		removeByCode(positionSelector,c[0].value);
	}
	
	displaySelectedPositions(positionSelector);
}

function complete(actionFlagStr){
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined' && actionFlagStr != ""){
		actionFlag.value = actionFlagStr;
	}
	var form = document.getElementById('empHistoryForm');
	form.submit();
}
</script>
</head>
<body>
    <hdiv-c:url value="/talent/backToPreviousStep.do" var="backUrl"></hdiv-c:url>
	<form:form id="empHistoryForm" commandName="employmentHistoryDto" action="${ctx}/talent/addEmploymentHistoryActions.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag"/>
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">职业概况</td>
			</tr>
			<tr>
				<td><common:errorTable path="employmentHistoryDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">入职时间：<span class="mandatoryField">*</span></td>
						<td>
						<common:inputDate dateYY="beginTimeDto.year" dateMON="beginTimeDto.month" dateDD="beginTimeDto.day" ></common:inputDate>
						<common:errorSign id="beginTimeDto.day" path="beginTimeDto.day"></common:errorSign>
						</td>
						<td class="labelColumn">离职时间：<br>（离职时间不填表示至今）</td>
						<td>
						<common:inputDate dateYY="endTimeDto.year" dateMON="endTimeDto.month" dateDD="endTimeDto.day" ></common:inputDate>
						<common:errorSign id="endTimeDto.day" path="endTimeDto.day"></common:errorSign>
					    </td>
					</tr>
				    <tr >
						<td class="labelColumn">企业名称：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="companyName" cssClass="standardInputText"  /> 
					       <common:errorSign id="companyName" path="companyName"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">行业类别：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select id="industrySelector" path="industryCategory" cssClass="standardSelect" onchange="loadIndustries();">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfIndustryCategory }" var="industryCategory">
						         <form:option value="${industryCategory.categoryCode }" label="${industryCategory.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						</td>
						<td >
						   <form:select path="industry" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						   </form:select>
						   <common:errorSign id="industry" path="industry"></common:errorSign>
						</td>
						<td >&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">职位类别：<span class="mandatoryField">*</span></td>
						<td>
					       <input type="button" class="selectButton" value="选择/修改" onFocus="this.blur()" onclick="popUpPositionSelector();">
						   <form:hidden path="positions"/>
						   <common:errorSign id="positions" path="positions"></common:errorSign>
						</td>
						<td id="positionDisplay" colspan="2"></td>
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
							<input class="standardButton" type="button" value="添加" onclick="complete('8');" />&nbsp;
							<input class="standardButton" type="button" value="清除" onclick="clearInputFields();" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
	<jsp:include page="positionSelector_pop.jsp"></jsp:include>
	<div class="emptyBlock"></div>
		<table class="contentTableBody2" cellspacing="1" width="100%">
		   <tr class="contentTableTitle">
		      <td width="5%" align="center">序号</td>
		      <td width="25%">时间</td>
		      <td width="20%">行业</td>
		      <td width="20%">企业名称</td>
		      <td width="20%">职位</td>
		      <td width="10%" align="center">操作</td>
		   </tr>
		   <c:if test="${not empty talentDto.employmentHistoryDtos }">
		      <c:forEach items="${talentDto.employmentHistoryDtos }" var="history" varStatus="status">
		         <tr class="contentTableRow">
		            <td align="center">${status.index+1 }</td>
		            <td>
		            <c:out value="${history.beginTimeDto.year }" escapeXml="true"/>/
		            <c:out value="${history.beginTimeDto.month }" escapeXml="true"/>&nbsp;-&nbsp;
		            <c:if test="${empty history.endTimeDto.day }"><c:out value="至今" escapeXml="true"/></c:if>
		            <c:if test="${not empty history.endTimeDto.day }">
		               <c:out value="${history.endTimeDto.year }" escapeXml="true"/>/
		               <c:out value="${history.endTimeDto.month }" escapeXml="true"/>
		            </c:if>
		            </td>
		            <td><c:out value="${history.industryDto.displayName }" escapeXml="true"></c:out></td>
		            <td>
		               <c:out value="${history.companyName }" escapeXml="true"></c:out>
		            </td>
		            <td>
		               <c:forEach items="${history.positionDtos }" var="positionDto">
		                 <c:out value="${positionDto.displayName }" escapeXml="true" /><br/> 
		               </c:forEach>
		            </td>
		            <td align="center"> 
		            <hdiv-c:url value="/talent/preEditEmploymentHistory.do?_id=${status.index }" var="editUrl"></hdiv-c:url>
		            <input class="standardButton" type="button" value="编辑" onclick="location.href='${editUrl}'" />&nbsp;
		            </td>
		         </tr>
		      </c:forEach>
		   </c:if>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<input class="standardButton" type="button" value="下一步" onclick="complete('6');" />&nbsp;
							<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'"/>&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>