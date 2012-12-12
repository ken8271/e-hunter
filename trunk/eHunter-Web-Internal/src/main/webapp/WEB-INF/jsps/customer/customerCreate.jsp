<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-CUST-0001]</title>
<hdiv-c:url value="/customer/loadCustomerGroup.do" var="loadGroupUrl"></hdiv-c:url>
<hdiv-c:url value="/customer/loadPositions.do" var="loadPositions"></hdiv-c:url>
<script type="text/javascript">
	$(document).ready(function(){
		changeCustType();
		
		if('SUB' == '${customerDto.groupIndicator}'){			
		   $('#group').val('${customerDto.custGroup.systemGroupRefNum}');
		}
		$('#groupFullName').val("${customerDto.custGroup.fullName}");
		$('#groupShortName').val("${customerDto.custGroup.shortName}");
		
		//loadPositions();
	});

	function changeCustType(){
		var custType = document.getElementById("custType");
		if(custType != null){
			var selectedIndex = custType.selectedIndex;
			if(selectedIndex == 1){
				//group
				$('#group').hide();
				$('#group').val('');
				$('#groupFullName').val('');
				$('#groupShortName').val('');
				$('#systemGroupRefNum').val('');
				$('#groupFullName').attr('readonly' , false);
				$('#groupShortName').attr('readonly' , false);
			}else if (selectedIndex == 2){
				//sub
				$('#group').show();
				$('#groupFullName').val('');
				$('#groupShortName').val('');
				$('#group').val('');
				$('#groupFullName').attr('readonly' , true);
				$('#groupShortName').attr('readonly' , true);
			}else {
				$('#group').hide();
				$('#group').val('');
				$('#groupFullName').val('');
				$('#groupShortName').val('');
				$('#systemGroupRefNum').val('');
				$('#groupFullName').attr('readonly' , true);
				$('#groupShortName').attr('readonly' , true);
			}
		}
	
	}

	function clearSelector(selector){
		while(selector.childNodes.length>0){
			selector.removeChild(selector.childNodes[0]);
		}
	}
	
	function loadGroupInfo(){
		var group = document.getElementById("group");
		if(group != null && group.selectedIndex != 0){
			$().progressDialog.showDialog("");
			var _id = group.options[group.selectedIndex].value;
			$.ajax({
				type:'post',
				url:'${loadGroupUrl}',
				dataType:'xml',
				data:{'_id':_id},
				success:function(xml){
					var groupId =  $(xml).find('id').text();
					var groupFullName =  $(xml).find('fullName').text();
					var groupShortName = $(xml).find('shortName').text();
					
					$('#systemGroupRefNum').val(groupId);
					$('#groupFullName').val(groupFullName);
					$('#groupShortName').val(groupShortName);
					
					$().progressDialog.hideDialog("");
				},
				error:function(){
					$().progressDialog.hideDialog("");
					alert('系统错误');
				}
			});
		}else{
			$('#systemGroupRefNum').val("");
			$('#groupFullName').val("");
			$('#groupShortName').val("");
		}
		
	}
	
	function submitRegtForm(){
		var custType = document.getElementById("custType");
		if(custType != null){
			var selectedIndex = custType.selectedIndex;
			if(selectedIndex == 2){
				var group = document.getElementById("group");
				if(group != null && group.selectedIndex == 0){
					//$('#systemGroupRefNum').val('');
				}
			}
		}
	}
	
function clearInput(){
	$("#custType").val('');
	changeCustType();
	$("#type").val('');
	$("#size").val('');
	$("#grade").val('');
	$("#status").val('');
	$("#postSelector").val('');
	loadPositions();
	$(":text").val('');
}
</script>
</head>
<body>
	<form:form commandName="customerDto" action="${ctx}/customer/saveCustCoInfo.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">客户公司资料填写</td>
			</tr>
			<tr>
				<td><common:errorTable path="customerDto"></common:errorTable></td>
			</tr>
		</table>
		<table align="right" border="0" cellspacing="0" cellpadding="0">
		   <tr>
		      <td>							   
		         <input type="submit" value="下一步" />&nbsp;
		         <input type="button" value="重置" onclick="clearInput();" />&nbsp;
		         <input type="button" value="结束" onclick="location.href='${ctx}/index.do'" />
		      </td>
		   </tr>
		</table>
		<div class="emptyBlock"></div>
		<table border="0" cellspacing="5" cellpadding="0" width="100%" class="ui-widget-content ui-corner-all">
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td  class="form-label">客户类型：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select id="custType" cssClass="form-select" onchange="changeCustType();" path="groupIndicator">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <form:option value="GRP" label="集团客户"></form:option>
						      <form:option value="SUB" label="非集团客户（集团旗下子公司）"></form:option>
						      <form:option value="IND" label="非集团客户（独立公司）"></form:option>
						   </form:select><common:errorSign path="groupIndicator"></common:errorSign>
						</td>
						<td>
						   &nbsp;
						   <select id="group" class="form-select" onchange="loadGroupInfo();" style="display: none">
						      <option>--- 请选择 ---</option>
						      <c:forEach items="${listOfGroups}" var="group">
						        <c:choose>
						          <c:when test="${group.value == customerDto.custGroup.systemGroupRefNum}">
						            <option value="${group.value }" selected="selected">${group.label}</option>
						          </c:when>
						          <c:otherwise>
						            <option value="${group.value }" >${group.label}</option>
						          </c:otherwise>
						        </c:choose>
						      </c:forEach>
						   </select>
						</td>
						<td>						   
						<form:input id="systemGroupRefNum" path="custGroup.systemGroupRefNum" cssStyle="display:none" />
						<common:errorSign path="custGroup.systemGroupRefNum" id="custGroup.systemGroupRefNum">
						</common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width=44><font face="Arial" size="2"><b>Part I</b></font></td>
				<td width="703"><font face="Arial" size="2"><b>集团资料</b></font></td>
			</tr>
		</table>
		<div style="height:5px"></div>
		<table border="0" cellspacing="5" cellpadding="0" width="100%" class="ui-widget-content ui-corner-all">
		   <tr>
		      <td  class="form-label">集团名称：</td>
		      <td>
		         <form:input id="groupFullName" path="custGroup.fullName" cssClass="form-text" ></form:input>
		         <common:errorSign id="custGroup.fullName" path="custGroup.fullName"></common:errorSign>
		      </td>
		      <td class="form-label">集团简称：</td>
		      <td>
		         <form:input id="groupShortName" path="custGroup.shortName" cssClass="form-text" /> 
		         <common:errorSign id="custGroup.shortName" path="custGroup.shortName"></common:errorSign>
		      </td>
		   </tr>
		</table>	
		<div class="emptyBlock"></div>		
		<table width="100%">
			<tr>
				<td width=44><font face="Arial" size="2"><b>Part II</b></font></td>
				<td width="703"><font face="Arial" size="2"><b>客户公司资料</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table border="0" cellspacing="5" cellpadding="0" width="100%" class="ui-widget-content ui-corner-all">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="form-label">公司名称：<span class="mandatoryField">*</span></td>
						<td colspan="2">
						<form:input path="fullName" cssClass="form-text"></form:input>
						<common:errorSign id="fullName" path="fullName"></common:errorSign>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="form-label">公司简称：<span class="mandatoryField">*</span></td>
						<td colspan="2">
						<form:input path="shortName" cssClass="form-text"></form:input>
						<common:errorSign id="shortName" path="shortName"></common:errorSign>
						</td>
					    <td>&nbsp;</td>
					</tr>
					<tr >
						<td class="form-label">官方网址：</td>
						<td>
						<form:input path="offcialSite" cssClass="form-text"></form:input>
						<common:errorSign id="offcialSite" path="offcialSite"></common:errorSign>
						</td>
						<td class="form-label">公司总机：</td>
						<td>
						   <form:input path="telExchangeDto.regionCode" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/> - 
					       <form:input path="telExchangeDto.phoneNumber" cssClass="standardInputTextNoWidth" maxlength="8" size="8"/>
					        <common:errorSign id="telExchangeDto.phoneNumber" path="telExchangeDto.phoneNumber"></common:errorSign>
					    </td>
					</tr>
					<tr >
						<td class="form-label">公司性质：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="type" cssClass="form-select">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						      <form:option value="SE" label="国有企业"></form:option>
						      <form:option value="EF" label="外商独资/外企办事处"></form:option>
						      <form:option value="JV" label="中外合营(合资/合作)"></form:option>
						      <form:option value="IE" label="私营/民营企业"></form:option>
						      <form:option value="NA" label="政府机关/非盈利机构"></form:option>
						      <form:option value="JE" label="股份制企业"></form:option>
						      <form:option value="QC" label="上市公司"></form:option>
						      <form:option value="PI" label="事业单位"></form:option>
						      <form:option value="OT" label="其他"></form:option>
						   </form:select>
						   <common:errorSign id="type" path="type"></common:errorSign>
						</td>
					    <td class="form-label">公司规模：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="size" cssClass="form-select">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						      <form:option value="001" label="1-49人"></form:option>
						      <form:option value="002" label="50-99人"></form:option>
						      <form:option value="003" label="100-499人"></form:option>
						      <form:option value="004" label="500-999人"></form:option>
						      <form:option value="005" label="1000-2000人"></form:option>
						      <form:option value="006" label="2000-5000人"></form:option>
						      <form:option value="007" label="5000-10000人"></form:option>
						      <form:option value="008" label="10000人以上"></form:option>
						   </form:select>
						   <common:errorSign id="size" path="size"></common:errorSign>
						</td>
					</tr>
					<tr >
						<td class="form-label">客户等级：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="grade" cssClass="form-select">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						      <form:option value="IR" label="有需求有意向"></form:option>
						      <form:option value="NI" label="有需求无意向"></form:option>
						      <form:option value="NR" label="无需求有意向"></form:option>
						      <form:option value="NN" label="无需求无意向"></form:option>
						   </form:select>
						   <common:errorSign id="grade" path="grade"></common:errorSign>
						</td>
						<td class="form-label">客户状态：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="customerStatus" cssClass="form-select">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						      <form:option value="SGN" label="已签约客户"></form:option>
						      <form:option value="PTL" label="潜力客户"></form:option>
						      <form:option value="OTH" label="其它"></form:option>
						   </form:select>
						   <common:errorSign id="customerStatus" path="customerStatus"></common:errorSign>
						</td>
					</tr>
					<tr>
					    <td class="form-label">客户介绍：<span class="mandatoryField">*</span></td>
					    <td colspan="3">
					       <form:textarea path="customerDescription" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px"  cssClass="form-textarea"/>
						   <common:errorSign id="customerDescription" path="customerDescription"></common:errorSign>
					    </td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							   <input type="submit" value="下一步" />&nbsp;
							   <input type="button" value="重置" onclick="clearInput();" />&nbsp;
							   <input type="button" value="结束" onclick="location.href='${ctx}/index.do'" />
							</td>
						</tr>
					</table>
		<div class="emptyBlock"></div>
	</form:form>
</body>
</html>