<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ taglib tagdir="/WEB-INF/tags/customer" prefix="customer"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" href="${ctx}/style/verify.css" type="text/css" />
</head>
<body>
	<form:form method="post" name="projectDto" action="${ctx}/project/submitProject.do">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">猎头项目资料确认</td>
			</tr>
			<tr>
				<td><common:errorTable path="projectDto"></common:errorTable>
				</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="确认" />&nbsp;
								<input class="standardButton" type="button" value="返回" >
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
				<span class="titleEn12">Registration Form for Project</span><br />
				<span class="titleCh11">猎头项目登记表格</span><br /> <br />
				</td>
			</tr>
		</table>
		<br />
		<br />
		<div>
			<span class="titleCh10">&nbsp;第一部分：</span> <span class="titleCh10">项目基本信息</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" width="100%" cellpadding="5px">
			<tr>
				<td width="17%"><span class="textCh8">项目名称</span></td>
				<td width="36%"><span class="showCh"><c:out value="${projectDto.projectName}" escapeXml="true" /> </span></td>
				<td width="17%"><span class="textCh8">项目负责人</span><br /></td>
				<td width="30%"><span class="showCh"><c:out value="${projectDto.adviserDto.cnName}" escapeXml="true" /></span></td>
			</tr>
			<tr>
				<td width="17%"><span class="textCh8">客户编号</span></td>
				<td width="36%"><span class="showCh"><c:out value="${projectDto.customerDto.systemCustRefNum}" escapeXml="true" /> </span></td>
				<td width="17%"><span class="textCh8">项目负责人</span><br /></td>
				<td width="30%"><span class="showCh"><c:out value="${projectDto.customerDto.fullName}" escapeXml="true" /></span></td>
			</tr>
		</table>
		<br />
		<div>
			<span class="titleCh10">&nbsp;第二部分：</span> <span class="titleCh10">需求职位描述</span>
		</div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" width="100%" cellpadding="5px">
			<tr>
				<td><span class="textCh8">职位名称</span></td>
				<td><span class="showCh"><c:out value="${project.postDescDto.positionName}" escapeXml="true" /></span></td>
			</tr>
		</table>
		<br />
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="确认" />&nbsp;
								<input class="standardButton" type="button" value="返回">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

	</form:form>
</body>
</html>
