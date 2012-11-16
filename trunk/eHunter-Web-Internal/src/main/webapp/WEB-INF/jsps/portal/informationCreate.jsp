<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
<script type="text/javascript" src="${scriptPath }/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${scriptPath }/ckeditor/config.js"></script>
<script type="text/javascript">
$().ready(function(){	
	CKEDITOR.replace('content',{
		skin : 'office2003' , 
		toolbar_Full : [['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
		                ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		                ['Link','Unlink','Anchor'],
		                ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'], '/',
		                ['Styles','Format','Font','FontSize'],
		                ['TextColor','BGColor']]
		});
});
</script>
</head>
<body>
<form:form action="${ctx}/portal/releaseInformation.do" method="post">
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">网站资讯发布</td>
		</tr>
	</table>
	<table border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="submit" value="发布" />&nbsp;
						    <input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'"/>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
	   <tr>
	      <td width="15%" class="labelColumn">标题：</td>
	      <td width="85%"><input type="text" class="standardInputText"></td>
	   </tr>
	   <tr valign="top">
	      <td width="15%" class="labelColumn">内容：</td>
	      <td width="85%">
	         <textarea id="content" rows="10" cols="80"></textarea>
	      </td>
	   </tr>
	</table>
	<div class="emptyBlock"></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="submit" value="发布" />&nbsp;
						    <input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'"/>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form:form>
</body>
</html>