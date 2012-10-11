<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ taglib tagdir="/WEB-INF/tags/customer" prefix="customer"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-CUST-0001]</title>
<hdiv-c:url value="/customer/loadPositions.do" var="loadPositions"></hdiv-c:url>
<script type="text/javascript">
$().ready(function(){
	loadPositions();
});

function loadPositions(){
	var postSelector = document.getElementById("postSelector");
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

				$("#subPostSelector").val('${responsePersonDto.positionType}');
			},
			error:function(){
				$().progressDialog.hideDialog("");
				alert('系统错误');
			}
		});
	}else {
		subSelector = document.getElementById("subPostSelector");
		clearSelector(subSelector);
		subSelector.options[subSelector.length] = new Option("--- 请选择 ---", "");
	}
}
</script>
</head>
<body>
    <c:if test="${module == '1' }"><hdiv-c:url value="/customer/fillMultiResponsePerson.do" var="backUrl"></hdiv-c:url></c:if>
    <c:if test="${module == '2' }"><hdiv-c:url value="/customer/preEditMultiResponsePerson.do" var="backUrl"></hdiv-c:url></c:if>
	<form:form id="rpForm" commandName="responsePersonDto" action="${ctx}/customer/updateResponsePerson.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">客户联系人资料修改</td>
			</tr>
			<tr>
				<td><common:errorTable path="responsePersonDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							   <input class="standardButton" type="submit" value="确定" />&nbsp;
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'"/>&nbsp;
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
				<td class="labelColumn">姓名：<span class="mandatoryField">*</span></td>
				<td>
				   <form:input path="name" cssClass="standardInputText"></form:input> 
				   <common:errorSign id="name" path="name"></common:errorSign>
				</td>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td class="labelColumn">职位类型：<span class="mandatoryField">*</span></td>
				<td>
				   <form:select id="postSelector" path="positionCategory" cssClass="standardSelect" onchange="loadPositions();">
				      <form:option value="" label="--- 请选择  ---"></form:option>
					  <c:forEach items="${listOfPositionCategory}" var="positionCategory">
					     <form:option value="${positionCategory.typeCode }" label="${positionCategory.displayName }"></form:option>
					  </c:forEach>
				   </form:select>
				</td>
				<td>
				   <form:select id="subPostSelector" path="positionType" cssClass="standardSelect">
				      <form:option value="" label="--- 请选择 ---"></form:option>
				   </form:select> 
				   <common:errorSign id="positionType" path="positionType"></common:errorSign>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="labelColumn">职位名称：<span class="mandatoryField">*</span></td>
				<td colspan="2">
				   <form:input path="positionName" cssClass="standardInputText"></form:input> 
				   <common:errorSign id="positionName" path="positionName"></common:errorSign>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="labelColumn">手机：<span class="mandatoryField">*</span></td>
				<td>
				   <form:input path="telephoneDto.phoneNumber" cssClass="standardInputText" maxlength="11"></form:input> 
				   <common:errorSign id="telephoneDto.phoneNumber" path="telephoneDto.phoneNumber"></common:errorSign>
				</td>
				<td class="labelColumn">邮箱：<span class="mandatoryField">*</span></td>
				<td>
				   <form:input path="email" cssClass="standardInputText" maxlength="50"></form:input> 
				   <common:errorSign id="email" path="email"></common:errorSign>
				</td>
			</tr>
			<tr>
				<td class="labelColumn">状态：<span class="mandatoryField">*</span></td>
				<td>
				   <form:select path="status" cssClass="standardSelect">
				      <form:option value="" label="--- 请选择 ---"></form:option>
				      <form:option value="IS" label="在职"></form:option>
				      <form:option value="OS" label="已离职"></form:option>
				   </form:select> 
				   <common:errorSign id="status" path="status"></common:errorSign>
				</td>
				<td colspan="2">
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							   <input class="standardButton" type="submit" value="确定" />&nbsp;
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'"/>&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>