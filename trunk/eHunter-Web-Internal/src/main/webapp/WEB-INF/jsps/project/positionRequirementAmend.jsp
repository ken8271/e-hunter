<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<hdiv-c:url value="/talent/loadIndustries.do" var="loadIndustriesUrl"></hdiv-c:url>
<script type="text/javascript" src="${scriptPath }/multiSelector.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	getSelectedIndustries();
	displaySelectedIndustries(document.getElementById('industrySelector'));
	
	setCheckedIndicator('LAN' , '${postRequireDto.languageStr}'.split(','));	
});

function popUpSelector(){
	$('#industrySelector').hide();
	setPopUpFramePosition('light',600,300);
	setOverlayDimension('fade');	
	popUpFrame('light','fade');
}

function clearIndustries(){
	$('#industryDisplayPanel tr').remove();
}

function clearSelectedIndustries(){
	$('#selectedIndustries td:gt(0)').remove();
}

function displaySelectedIndustries(selector){
	var str = '';
	
	str = str + "<td>";
	for(var i=0 ; i<selector.length ; i++){
		str = str + "<input type='checkbox' value='"  + selector.options[i].value + "' checked onclick='handleUnselect(this)'/>" + selector.options[i].text + "<br/>";
	}
	str = str + "</td>";
	
	clearSelectedIndustries();
	$(str).appendTo('#selectedIndustries');
}

function getSelectedIndustries(){
	var industrySelector = document.getElementById('industrySelector');
	
	var displayStr = "<span>";
	var displayValue = '';
	for(var i=0 ;  i<industrySelector.length ; i++){
		displayStr = displayStr + industrySelector.options[i].text + "<br/>";
		
		if(i == industrySelector.length-1){
			displayValue = displayValue + industrySelector.options[i].value;
		}else {
			displayValue = displayValue + industrySelector.options[i].value + ',';
		}
	}
	displayStr = displayStr + "</span>";
	
	$('#expectIndustriesDisplay span').remove();
	$(displayStr).appendTo('#expectIndustriesDisplay');
	
	$('#expectIndustries').val(displayValue);
}


function handleUnselect(c){
	var industrySelector = document.getElementById('industrySelector');
	if(!c.checked){
		removeByCode(industrySelector , c.value);
		
		var industry = $('#industryDisplayPanel [value='+ c.value + ']');
		if(industry != null && industry[0] != null){
			industry[0].checked = false;
		}
		
		displaySelectedIndustries(industrySelector);
	}
}

function handleSelect(industry){
	var industrySelector = document.getElementById('industrySelector');
	var c = $(industry).children();
	var label = $(industry).text();
	c[0].checked = !c[0].checked;
	
	if(c[0].checked){
		if(industrySelector.length >= 3){
			alert('最多可添加3个地区');
			c[0].checked = false;
			return ;
		}
		
		if(!hasBeenSelect(industrySelector , c[0].value)){			
			industrySelector.options[industrySelector.length] = new Option(label, c[0].value);
		}else {
		   c[0].checked = false;
		   removeByCode(industrySelector,c[0].value);
		}
	}else {		
		removeByCode(industrySelector,c[0].value);
	}
	
	displaySelectedIndustries(industrySelector);
}

function loadIndustries(){
	clearIndustries();
	var industrySelector = document.getElementById("industryCategorySelector");
	if(industrySelector != null && industrySelector.selectedIndex != 0){
		$().progressDialog.showDialog("");
		var _id = industrySelector.options[industrySelector.selectedIndex].value;
		$.ajax({
			type:'post',
			url:'${loadIndustriesUrl}',
			dataType:'xml',
			data:{'_id':_id},
			success:function(xml){
				var str = '';
				var count = 0;
				$(xml).find('industry').each(function(i , element){
					count++;
					var industryCode = $(this).find("value").text();
					var industryName = $(this).find("label").text();
					if(count%2 == 1){
						str = str + "<tr height='30px'><td width='5%'></td>";
						str = str + "<td style='cursor:pointer; width: 45%; padding-left: 1px;' onclick='handleSelect(this);'><input style='display:none' type='checkbox' value='" + industryCode + "'/>&nbsp;" + industryName +"</td>";
					}

					if(count%2 == 0){
						str = str + "<td width='5%'></td>";
					    str = str + "<td style='cursor:pointer; width: 45%; padding-left: 1px;' onclick='handleSelect(this);'><input style='display:none' type='checkbox' value='" + industryCode + "'/>&nbsp;" + industryName +"</td>";
					    str = str + "</tr>";
					}
				});
				
				$(str).appendTo('#industryDisplayPanel');
				$().progressDialog.hideDialog("");
			},
			error:function(){
				$().progressDialog.hideDialog("");
				alert('系统错误');
			}
		});
	}
}

function clearInput(){
	$("#ageFromStr").val('');
	$("#ageToStr").val('');
	$("#gender").val('');
	$("#majorCategory").val('');
	$("#degree").val('');
	$(":text").val('');
	$(":checkbox").attr('checked',false);
	$(":radio").attr('checked',false);
	$("#duty").val('');
	$("#remark").val('');
	$("#expectIndustries").val('');
	$('#expectIndustriesDisplay span').remove();
	clearSelectedIndustries();
	clearSelector(document.getElementById('industrySelector'));
}
</script>
</head>
<body>
    <hdiv-c:url value="/project/viewProjectDetail.do?_id=${projectDto.systemProjectRefNum }" var="backUrl"></hdiv-c:url>
	<form:form commandName="postRequireDto" action="${ctx}/project/updatePositionRequirement.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">需求职位信息 - 职位要求</td>
			</tr>
			<tr>
				<td><common:errorTable path="postRequireDto"></common:errorTable></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>							   
							   <input class="standardButton" type="submit" value="更新" />&nbsp;
							   <input class="standardButton" type="button" value="重置" onclick="clearInput();"/>&nbsp;
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			<common:standardTableRow />
			<tr>
				<td class="labelColumn">项目编号：</td>
				<td colspan="3">
				   <c:out value="${projectDto.systemProjectRefNum }" escapeXml="true"></c:out>
				</td>
			</tr>
	    </table>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>基本素质要求</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">年龄要求：<span class="mandatoryField">*</span></td>
						<td>
						<form:select path="ageFromStr" cssClass="standardSelectNoWidth">
						   <form:option value="" label="不限"></form:option>
						   <c:forEach begin="20" end="50" step="5" var="age">
						      <form:option value="${age }" label="${age }"></form:option>
						   </c:forEach>
						</form:select>
						&nbsp;&nbsp;-&nbsp;&nbsp;
						<form:select path="ageToStr" cssClass="standardSelectNoWidth">
						   <form:option value="" label="不限"></form:option>
						   <c:forEach begin="20" end="50" step="5" var="age">
						      <form:option value="${age }" label="${age }"></form:option>
						   </c:forEach>
						</form:select>
						<common:errorSign id="ageToStr" path="ageToStr"></common:errorSign>
						</td>
						<td class="labelColumn">性别要求：<span class="mandatoryField">*</span></td>
						<td>
						<form:select path="gender" cssClass="standardSelectNoWidth">
						   <form:option value="" label="不限"></form:option>
						   <form:option value="M" label="男"></form:option>
						   <form:option value="F" label="女"></form:option>
						</form:select>
						<common:errorSign id="gender" path="gender"></common:errorSign>
					</tr>
					<tr>
						<td class="labelColumn">专业要求：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="majorCategory" cssClass="standardSelect" >
						      <form:option value="" label="不限"></form:option>
						      <c:forEach items="${listOfSubjectType}" var="subjectType">
						          <form:option value="${subjectType.typeCode }" label="${subjectType.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						<common:errorSign id="majorCategory" path="majorCategory"></common:errorSign>
						</td>
						<td class="labelColumn">总工作年限：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="workExperienceStr" cssClass="standardInputTextNoWidth" maxlength="2" size="2"/>&nbsp;年以上
						   <common:errorSign id="workExperienceStr" path="workExperienceStr"></common:errorSign>
						</td>
					</tr>
				    <tr >
						<td class="labelColumn">学历要求：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="degree"  cssClass="standardSelect">
						      <form:option value="" label="不限"></form:option>
						      <c:forEach items="${listOfDegree }" var="dgre">
						         <form:option value="${dgre.degreeCode }" label="${dgre.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
					       <common:errorSign id="degree" path="degree"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">语言要求：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						   <input id="LAN_EN" type="checkbox" name="language" value='<form:cipher value="EN" parameter="language"/>'/>&nbsp;英语&nbsp;&nbsp;&nbsp;
						   <input id="LAN_JP" type="checkbox" name="language" value='<form:cipher value="JP" parameter="language"/>'/>&nbsp;日语&nbsp;&nbsp;&nbsp;
						   <input id="LAN_FR" type="checkbox" name="language" value='<form:cipher value="FR" parameter="language"/>'/>&nbsp;法语&nbsp;&nbsp;&nbsp;
						   <input id="LAN_CH" type="checkbox" name="language" value='<form:cipher value="CH" parameter="language"/>'/>&nbsp;普通话&nbsp;&nbsp;&nbsp;
						   <input id="LAN_CT" type="checkbox" name="language" value='<form:cipher value="CT" parameter="language"/>'/>&nbsp;粤语&nbsp;&nbsp;&nbsp;
						   <input id="LAN_OT" type="checkbox" name="language" value='<form:cipher value="OT" parameter="language"/>'/>&nbsp;其他&nbsp;&nbsp;&nbsp;
						   <common:errorSign path="language"></common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>			
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>任职资格相关要求</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">任职资格：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						   <form:textarea path="duty" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>1000){this.value = this.value.substring(0, 1000)}" cssClass="standardInputText"/>
						   <common:errorSign id="duty" path="duty"></common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>期望人选来源行业</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">所属行业：<span class="mandatoryField">*</span></td>
						<td>
						   <input type="button" class="selectButton" value="选择/修改" onFocus="this.blur()" onclick="popUpSelector();">
						   <form:hidden path="expectIndustries"/>
						</td>
						<td id="expectIndustriesDisplay" colspan="2"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>其他</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">补充说明：</td>
						<td colspan="3">
						   <form:textarea path="remark" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>1000){this.value = this.value.substring(0, 1000)}" cssClass="standardInputText"/>
						   <common:errorSign id="remark" path="remark"></common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<jsp:include page="industrySelector_pop.jsp"></jsp:include>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							   <input class="standardButton" type="submit" value="更新" />&nbsp;
							   <input class="standardButton" type="reset" value="重置" onclick="clearInput();"/>&nbsp;
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