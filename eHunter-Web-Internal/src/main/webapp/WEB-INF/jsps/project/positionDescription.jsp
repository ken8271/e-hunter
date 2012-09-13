<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<hdiv-c:url value="/customer/loadPositions.do" var="loadPositions"></hdiv-c:url>
<hdiv-c:url value="/project/loadCities.do" var="loadCities"></hdiv-c:url>
<script type="text/javascript" src="${scriptPath }/multiSelector.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	loadPositions();
	setCheckedIndicator('SC' , '${postDescDto.salaryCategoryStr}'.split(','));
	setCheckedIndicator('SW' , '${postDescDto.societyWelfareStr}'.split(','));
	setCheckedIndicator('RW' , '${postDescDto.residentialWelfareStr}'.split(','));
	setCheckedIndicator('AW' , '${postDescDto.annualLeaveWelfareStr}'.split(','));
	
	getSelectedCities();
	displaySelectedCities(document.getElementById('citySelector'));
});

function popUpSelector(){
	$('#citySelector').hide();
	setPopUpFramePosition(600,300);
	setOverlayDimension('fade');	
	popUpFrame('light','fade');
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
	
	$('#cities').val(displayValue);
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

function loadPositions(){
	var postSelector = document.getElementById("positionSelector");
	if(postSelector != null && postSelector.selectedIndex != 0){
		$().progressDialog.showDialog("");
		var code = postSelector.options[postSelector.selectedIndex].value;
		$.ajax({
			type:'post',
			url:'${loadPositions}',
			dataType:'xml',
			data:{'code':code},
			success:function(xml){
				$().progressDialog.hideDialog("");
				var subSelector = document.getElementById("subPostSelector");
				clearSelector(subSelector);
				subSelector.options[subSelector.length] = new Option("--- 请选择 ---", "");
				$(xml).find('position').each(function(i , element){
					var label = $(this).find("label").text();
					var val = ''+$(this).children("value").text()+'';
					subSelector.options[subSelector.length] = new Option(label, val);
				});
				
				$('#subPostSelector').val('${postDescDto.position}');
			},
			error:function(){
				$().progressDialog.hideDialog("");
				alert('系统错误');
			}
		});
	}
}

function clearSelector(selector){
	while(selector.childNodes.length>0){
		selector.removeChild(selector.childNodes[0]);
	}
}
</script>
</head>
<body>
    <hdiv-c:url value="/project/backToProjectInfo.do" var="backUrl"></hdiv-c:url>
	<form:form commandName="postDescDto" action="${ctx}/project/savePositionDescription.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag" />
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">需求职位信息 - 职位描述</td>
			</tr>
			<tr>
				<td><common:errorTable path="postDescDto"></common:errorTable></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>							   
							   <input class="standardButton" type="submit" value="下一步" />&nbsp;
							   <input class="standardButton" type="reset" value="重置" />&nbsp;
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>职位基本描述</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">招聘企业：<span class="mandatoryField">*</span></td>
						<td><c:out value="${customerName }" escapeXml="true"></c:out></td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">职位分类：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select id="positionSelector" path="positionCategory" cssClass="standardSelect" onchange="loadPositions();">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfPositionCategory }" var="positionCategory">
						         <form:option value="${positionCategory.typeCode }" label="${positionCategory.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						</td>
						<td >
						   <form:select id="subPostSelector" path="position" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						   </form:select>
						    <common:errorSign id="position" path="position"></common:errorSign>
						</td>
						<td >&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">职位名称：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="positionName" cssClass="standardInputText" ></form:input>
						<common:errorSign id="positionName" path="positionName"></common:errorSign>
						</td>
						<td class="labelColumn">需求人数：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="expectNumberStr" cssClass="standardInputTextNoWidth" maxlength="4" size="4"></form:input>人
						<common:errorSign id="expectNumberStr" path="expectNumberStr"></common:errorSign>
						</td>
					</tr>
					<tr>
						<td class="labelColumn">所属部门：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="department" cssClass="standardInputText" ></form:input>
						<common:errorSign id="department" path="department"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
				    <tr >
						<td class="labelColumn">汇报对象：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="reportTarget" cssClass="standardInputText"  /> 
					       <common:errorSign id="reportTarget" path="reportTarget"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">工作地点：<span class="mandatoryField">*</span></td>
						<td>
						   <input type="button" class="selectButton" value="选择/修改" onFocus="this.blur()" onclick="popUpSelector();">
						   <form:hidden path="cities"/>
						</td>
						<td id="expectCitiesDisplay" colspan="2"></td>
					</tr>
					<tr>
						<td class="labelColumn">截止日期：<span class="mandatoryField">*</span></td>
						<td>
						   <common:inputDate dateYY="expiryDateDto.year" dateMON="expiryDateDto.month" dateDD="expiryDateDto.day" ></common:inputDate>
						   <common:errorSign id="expiryDateDto.day" path="expiryDateDto.day"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
				</tbody>
			</table>			
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>福利薪酬</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">职位年薪：<span class="mandatoryField">*</span></td>
						<td colspan="2">
						   <form:input path="salaryFromStr" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/>万元 至
					       <form:input path="salaryToStr" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/>万元（年薪单位：万元人民币）
					       <common:errorSign id="salaryToStr" path="salaryToStr"></common:errorSign>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">薪资构成：<span class="mandatoryField">*</span></td>
					    <td colspan="3">
					        <c:forEach items="${listOfSalaryCategory }" var="salaryCategory">
					           <input id="SC_${salaryCategory.categoryCode }" type="checkbox" name="salaryCategory" value='<form:cipher value="${salaryCategory.categoryCode }" parameter="salaryCategory"/>'/>&nbsp;${salaryCategory.displayName }&nbsp;&nbsp;&nbsp;
					        </c:forEach>
					    </td>
					</tr>
					<tr >
						<td class="labelColumn">社会福利：</td>
						<td colspan="3">
					        <c:forEach items="${listOfSocietyWelfare }" var="societyWelfare">
					           <input id="SW_${societyWelfare.welfareCode  }" type="checkbox" name="societyWelfare" value='<form:cipher value="${societyWelfare.welfareCode }" parameter="societyWelfare"/>'/>&nbsp;${societyWelfare.displayName }&nbsp;&nbsp;&nbsp;
					        </c:forEach>
					    </td>
					</tr>
					<tr >
						<td class="labelColumn">居住福利：</td>
						<td colspan="3">
					        <c:forEach items="${listOfResidentialWelfare }" var="residentialWelfare">
					           <input id="RW_${residentialWelfare.welfareCode  }" type="checkbox" name="residentialWelfare" value='<form:cipher value="${residentialWelfare.welfareCode }" parameter="residentialWelfare"/>'/>&nbsp;${residentialWelfare.displayName }&nbsp;&nbsp;&nbsp;
					        </c:forEach>
					    </td>
					</tr>
					<tr >
						<td class="labelColumn">年假福利：</td>
						<td colspan="3">
					        <c:forEach items="${listOfAnnualLeaveWelfare }" var="annualLeaveWelfare">
					           <input id="AW_${annualLeaveWelfare.welfareCode  }" type="checkbox" name="annualLeaveWelfare" value='<form:cipher value="${annualLeaveWelfare.welfareCode }" parameter="annualLeaveWelfare"/>'/>&nbsp;${annualLeaveWelfare.displayName }&nbsp;&nbsp;&nbsp;
					        </c:forEach>
					    </td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>职位关键词</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">关键词：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						   <form:input path="keyWords[0]" cssClass="standardInputTextNoWidth" maxlength="5" size="10"/>&nbsp;&nbsp;<common:errorSign id="keyWords[0]" path="keyWords[0]"></common:errorSign>
						   <form:input path="keyWords[1]" cssClass="standardInputTextNoWidth" maxlength="5" size="10"/>&nbsp;&nbsp;<common:errorSign id="keyWords[1]" path="keyWords[1]"></common:errorSign>
						   <form:input path="keyWords[2]" cssClass="standardInputTextNoWidth" maxlength="5" size="10"/>&nbsp;&nbsp;<common:errorSign id="keyWords[2]" path="keyWords[2]"></common:errorSign>
						   <form:input path="keyWords[3]" cssClass="standardInputTextNoWidth" maxlength="5" size="10"/>&nbsp;&nbsp;<common:errorSign id="keyWords[3]" path="keyWords[3]"></common:errorSign>
						   <form:input path="keyWords[4]" cssClass="standardInputTextNoWidth" maxlength="5" size="10"/>&nbsp;&nbsp;<common:errorSign id="keyWords[4]" path="keyWords[4]"></common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>工作角色与职责</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">职责描述：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						   <form:textarea path="dutyDescription" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>1000){this.value = this.value.substring(0, 1000)}" cssClass="standardInputText"/>
						   <common:errorSign id="dutyDescription" path="dutyDescription"></common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<jsp:include page="citySelector_pop.jsp"></jsp:include>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							   <input class="standardButton" type="submit" value="下一步" />&nbsp;
							   <input class="standardButton" type="reset" value="重置" />&nbsp;
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'"/>
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