<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ taglib tagdir="/WEB-INF/tags/talent" prefix="talent"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<script type="text/javascript">
$().ready(function(){
	checkFilled();
});

function submitForm(actionFlagStr){
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined' && actionFlagStr != ""){
		actionFlag.value = actionFlagStr;
	}
	document.forms[0].submit();
}

function checkFilled(){
	var content = document.getElementById('resumeDto.selfEvaluationDto.content').value;
	if(content != ''){
		$('#blank').hide();
		$('#ok').show();
	}else{
		$('#ok').hide();
		$('#blank').show();
	}
}
</script>
</head>
<body>
    <hdiv-c:url value="/talent/backToFillResume.do" var="backUrl"></hdiv-c:url>
	<form:form id="resumeForm" commandName="talentDto" action="${ctx}/talent/editResumeActions.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag"/>
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">人才简历编辑</td>
			</tr>
			<tr>
				<td><common:errorTable path="talentDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							   <input class="standardButton" type="button" value="确认" onclick="submitForm('5')"/>&nbsp;
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0"
				cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">简历名称：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="resumeDto.name" cssClass="standardInputText" ></form:input>
						<common:errorSign id="resumeDto.name" path="resumeDto.name"></common:errorSign>
						</td>
						<td class="labelColumn">语言版本：<span class="mandatoryField">*</span></td>
						<td>
						<form:radiobutton path="resumeDto.language" value="cn" /> 中文&nbsp;/
						<form:radiobutton path="resumeDto.language" value="en" /> 英文&nbsp;
						<common:errorSign id="resumeDto.language" path="resumeDto.language"></common:errorSign>
						</td>
					</tr>
				</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="100%"><font face="Arial" size="2"><b>&nbsp;基本信息</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">中文名：</td>
						<td><c:out value="${talentDto.cnName }" escapeXml="true"></c:out></td>
						<td class="labelColumn">英文名：</td>
						<td><c:out value="${talentDto.enName }" escapeXml="true"></c:out> </td>
					</tr>
				</tbody>
			</table>			
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
			    <td width="100%">
			       <font face="Arial" size="2"><b>&nbsp;求职意向</b></font>&nbsp;
			       <c:choose> 
			       <c:when test="${intentionDto != null && intentionDto.expectSalary != null}"><img src="${imagePath}/icon/ok.gif"></img></c:when>
			       <c:otherwise><img src="${imagePath}/icon/blank.gif"></img></c:otherwise>
			       </c:choose>
			    </td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('13')">输入求职意向</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
			    <td width="100%">
			       <font face="Arial" size="2"><b>&nbsp;教育经历</b></font>&nbsp;
			       <talent:checkSign path="${talentDto.resumeDto.eduExpDtos }"></talent:checkSign>
			    </td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('7')">输入教育经历</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr valign="middle">
				<td width="100%">
				   <font face="Arial" size="2"><b>&nbsp;工作经历</b></font>&nbsp;
				   <talent:checkSign path="${talentDto.resumeDto.jobExpDtos }"></talent:checkSign>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('8');">输入工作经历</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="100%">
				   <font face="Arial" size="2"><b>&nbsp;项目经验</b></font>&nbsp;
				   <talent:checkSign path="${talentDto.resumeDto.prjExpDtos }"></talent:checkSign>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('10');">输入项目经验</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="100%"><font face="Arial" size="2"><b>&nbsp;培训经历</b></font>&nbsp;
				<talent:checkSign path="${talentDto.resumeDto.trnExpDtos }"></talent:checkSign>
			</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('9');">输入培训经历</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="100%"><font face="Arial" size="2"><b>&nbsp;语言能力</b></font>&nbsp;
				<talent:checkSign path="${talentDto.resumeDto.languageDtos }"></talent:checkSign>
			</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('12');">输入语言能力</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="100%">
				   <font face="Arial" size="2"><b>&nbsp;专业技能</b></font>&nbsp;
				   <talent:checkSign path="${talentDto.resumeDto.skillDtos }"></talent:checkSign>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('11');">输入专业技能</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="100%">
				   <font face="Arial" size="2"><b>&nbsp;证书</b></font>&nbsp;
				   <talent:checkSign path="${talentDto.resumeDto.certDtos }"></talent:checkSign>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('14');">输入证书</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr valign="middle">
				<td width="100%" nowrap="nowrap">
				   <font face="Arial" size="2"><b>&nbsp;自我评价</b></font>&nbsp;
				   <span id="ok" style="display: none"><img src="${imagePath}/icon/ok.gif" ></img></span>
				   <span id="blank"><img src="${imagePath}/icon/blank.gif" ></img></span>
				</td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">自我评价：</td>
						<td colspan="3">
						<form:textarea path="resumeDto.selfEvaluationDto.content" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>1000){this.value = this.value.substring(0, 1000)}" cssClass="standardInputText" onblur="checkFilled();"/>
						</td>
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
							   <input class="standardButton" type="button" value="确认" onclick="submitForm('6')"/>&nbsp;
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>