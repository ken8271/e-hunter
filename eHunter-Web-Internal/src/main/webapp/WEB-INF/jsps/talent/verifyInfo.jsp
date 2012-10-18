<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" href="${ctx}/style/verify.css" type="text/css" />
</head>
<body>
    <hdiv-c:url value="/talent/backToFillResume.do" var="backUrl"></hdiv-c:url>
	<form:form method="post" name="talentDto" action="${ctx}/talent/submitTalent.do">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">人才资料确认</td>
			</tr>
			<tr>
				<td><common:errorTable path="talentDto"></common:errorTable>
				</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="确认" />&nbsp;
								<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table width="100%">
		    <tr>
		    <td align="right"><span class="textCh8" ><u>仅限公司内部查看/使用</u></span><br /></td>
		    </tr>
			<tr>
				<td align="center">
				<span class="titleEn12">e-Hunter System</span><br /> 
				<span class="titleCh11">电子猎头系统</span><br /> <br />
				<span class="titleEn12">Registration Form for Talent</span><br />
				<span class="titleCh11">人才资料登记表格</span><br /> <br />
				</td>
			</tr>
		</table>
		<br />
		<br />
		<div>
			<span class="titleCh10">&nbsp;第一部分：</span> <span class="titleCh10">人才基本资料</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" width="100%" cellpadding="5px">
			<tr>
				<td width="20%"><span class="textCh8">中文名</span></td>
				<td width="30%"><span class="showCh"><c:out value="${talentDto.cnName}" escapeXml="true" /></span></td>
				<td width="20%"><span class="textCh8">英文名</span></td>
				<td width="30%"><span class="showCh"><c:out value="${talentDto.enName}" escapeXml="true" /></span></td>
			</tr>
			<tr>
				<td><span class="textCh8">性别</span></td>
				<td>
				   <span class="showCh">
				      <c:if test="${talentDto.gender == 'M'}"><c:out value="男" escapeXml="true"></c:out></c:if> 
				      <c:if test="${talentDto.gender == 'F'}"><c:out value="女" escapeXml="true"></c:out></c:if>
				   </span>
				</td>
				<td><span class="textCh8">婚姻状况</span><br /></td>
				<td>
				   <span class="showCh">
				      <c:if test="${talentDto.maritalStatus == '01'}"><c:out value="未婚" escapeXml="true"></c:out></c:if> 
				      <c:if test="${talentDto.maritalStatus == '02'}"><c:out value="已婚" escapeXml="true"></c:out></c:if>
				   </span>
				</td>
			</tr>
			<tr>
				<td><span class="textCh8">出生日期</span><br /></td>
				<td>  
				<span class="showCh">
				   <c:out value="${talentDto.birthDateDto.year }"escapeXml="true" />/
				   <c:out value="${talentDto.birthDateDto.month }" escapeXml="true" />/
				   <c:out value="${talentDto.birthDateDto.day }" escapeXml="true" />
				</span>
				</td>
				<td><span class="textCh8">籍贯</span></td>
				<td>
				<span class="showCh">
			       <c:out value="${talentDto.nativePlace }" escapeXml="true"></c:out>
				</span>
				</td>
			</tr>
			<tr>
				<td><span class="textCh8">曾居地</span></td>
				<td><span class="showCh"><c:out value="${talentDto.onceLivePlace }" escapeXml="true"></c:out></span></td>
				<td><span class="textCh8">现居地</span></td>
				<td><span class="showCh"><c:out value="${talentDto.nowLivePlace }" escapeXml="true"></c:out></span></td>
			</tr>
			<tr>
				<td><span class="textCh8">最高学历</span></td>
				<td><span class="showCh"><c:out value="${talentDto.degreeDto.displayName }" escapeXml="true"></c:out></span></td>
			    <td></td>
			    <td></td>
			</tr>
		</table>
		<br />
		<div>
			<span class="titleCh10">&nbsp;第二部分：</span> <span class="titleCh10">人才联系资料</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" width="100%" cellpadding="5px">
			<tr>
				<td width="20%"><span class="textCh8">家庭电话</span></td>
				<td width="30%">
				   <span class="showCh">
				      <c:if test="${talentDto.homeNumberDto != null && talentDto.homeNumberDto.phoneNumber != null && talentDto.homeNumberDto.phoneNumber != ''}">
				         <c:out value="${talentDto.homeNumberDto.regionCode}" escapeXml="true" />&nbsp;-&nbsp;
				         <c:out value="${talentDto.homeNumberDto.phoneNumber}" escapeXml="true" />
				      </c:if>  
				   </span>
				</td>
				<td width="20%"><span class="textCh8">公司电话</span></td>
				<td width="30%">
				   <span class="showCh">
				      <c:if test="${talentDto.companyNumberDto != null && talentDto.companyNumberDto.phoneNumber != null && talentDto.companyNumberDto.phoneNumber != ''}">
				         <c:out value="${talentDto.companyNumberDto.regionCode}" escapeXml="true" />&nbsp;-&nbsp;
				         <c:out value="${talentDto.companyNumberDto.phoneNumber}" escapeXml="true" />
				      </c:if>
				   </span>
				</td>
			</tr>
			<tr>
				<td><span class="textCh8">手机</span></td>
				<td>
				   <span class="showCh">
				      <c:out value="${talentDto.mobilePhoneDto1.phoneNumber}" escapeXml="true" /><br/>
				      <c:out value="${talentDto.mobilePhoneDto2.phoneNumber}" escapeXml="true" />
				   </span>
				</td>
				<td><span class="textCh8">邮箱</span><br /></td>
				<td>
				   <span class="showCh">
				     <c:out value="${talentDto.email}" escapeXml="true" />
				   </span>
				</td>
			</tr>
			<tr>
				<td><span class="textCh8">QQ</span></td>
				<td>
				   <span class="showCh">
				      <c:out value="${talentDto.QQ}" escapeXml="true" />
				   </span>
				</td>
				<td><span class="textCh8">MSN</span><br /></td>
				<td>
				   <span class="showCh">
				     <c:out value="${talentDto.msn}" escapeXml="true" />
				   </span>
				</td>
			</tr>
			<tr>
				<td><span class="textCh8">家庭地址</span><br /></td>
				<td colspan="3">  
				<span class="showCh">
				   <c:out value="${talentDto.homeAddress }"escapeXml="true" />
				</span>
				</td>
			</tr>
		</table>
		<br />
		<div>
			<span class="titleCh10">&nbsp;第三部分：</span> <span class="titleCh10">职业概况</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" cellSpacing="0" width="100%" cellpadding="5px">
			<tr>
			  <td width="5%" align="center">序号</td>
		      <td width="25%">时间</td>
		      <td width="20%">行业</td>
		      <td width="20%">企业名称</td>
		      <td width="20%">职位</td>
			</tr>
			<c:if test="${not empty talentDto.employmentHistoryDtos }">
			<c:forEach items="${talentDto.employmentHistoryDtos }" var="history" varStatus="status">
			<tr>
				<td><span class="textCn8"><c:out value="${status.index + 1 }"></c:out></span></td>
				<td>
				   <span class="textCn8">
				    <c:out value="${history.beginTimeDto.year }" escapeXml="true"/>/
		            <c:out value="${history.beginTimeDto.month }" escapeXml="true"/>&nbsp;-&nbsp;
		            <c:if test="${empty history.endTimeDto.day }"><c:out value="至今" escapeXml="true"/></c:if>
		            <c:if test="${not empty history.endTimeDto.day }">
		               <c:out value="${history.endTimeDto.year }" escapeXml="true"/>/
		               <c:out value="${history.endTimeDto.month }" escapeXml="true"/>
		            </c:if>
				   </span>
				</td>
				<td><span class="textCn8"><c:out value="${history.industryDto.displayName }" escapeXml="true"></c:out></span></td>
				<td><span class="textCn8"><c:out value="${history.companyName }" escapeXml="true"></c:out></span></td>
				<td>
				   <span class="textCn8"> 
				      <c:forEach items="${history.positionDtos }" var="positionDto">
		                 <c:out value="${positionDto.displayName }" escapeXml="true" /><br/> 
		              </c:forEach>
		           </span>
		        </td>
			</tr>
			</c:forEach>
			</c:if>
		</table>
		<br />
		<div>
			<span class="titleCh10">&nbsp;第四部分：</span><span class="titleCh10">人才简历</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" cellSpacing="0" width="100%" cellpadding="5px">
			<tr>
				<td align="center" width="15%"><span class="textCh8">序号</span><br /> <br /></td>
				<td align="center" width="30%"><span class="textCh8">简历版本</span><br /> <br /></td>
				<td align="center" width="15%"><span class="textCh8">简历名称</span><br /><br /></td>
				<td align="center" width="25%"><span class="textCh8">完整度</span><br /><br /></td>
				<td align="center" width="15%"><span class="textCh8">操作</span><br /><br /></td>
			</tr>
			<c:forEach items="${talentDto.resumeDtos }" var="resume" varStatus="status">
			<tr>
				<td><span class="textCn8"><c:out value="${status.index + 1 }"></c:out></span></td>
				<td>
				   <span class="textCn8">
				      <c:if test="${resume.language == 'cn'}">中文</c:if>
		              <c:if test="${resume.language == 'en' }">英文</c:if>
				   </span>
				</td>
				<td><span class="textCn8"><c:out value="${resume.name }"></c:out></span></td>
				<td><span class="textCn8"></span></td>
				<td align="center">
				    <hdiv-c:url value="/talent/pop/viewResume.do?_id=${status.index }" var="viewUrl"></hdiv-c:url>
					<input class="tableButton" type="button" value="预览" onclick="var resumeWindow = window.open('${viewUrl}','resumeWindow', 'directories=no,height=550,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680,top=100,left=200');"/>
				</td>
			</tr>
			</c:forEach>
		</table>
		<br />
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="确认" />&nbsp;
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
