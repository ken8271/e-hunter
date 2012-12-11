<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh-decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="sitemesh-page"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../commons/meta.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><sitemesh-decorator:title></sitemesh-decorator:title></title>
<sitemesh-decorator:head></sitemesh-decorator:head>
<script type="text/javascript">
$(document).ready(function(){
	$( "input[type=submit] , input[type=reset] , input[type=button]" ).button();
});
</script>
</head>
<body>
	<div id="pageAll">
		<div class="pageTop">
		  <common:top></common:top>
		</div>
		<div id="pageMain">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top" width="168px">
						<common:menu></common:menu>
					</td>
					<td valign="top" width="10">&nbsp;</td>
					<td valign="top" align="left">
					   <div style="margin-right: 5px">
					      <sitemesh-decorator:body></sitemesh-decorator:body>
					   </div>
					</td>
				</tr>
			</table>
			<div style="clear: both;padding:1px"></div>
		   	<div id="footer">
		      	<common:footer/>
		   	</div>
		</div>
	</div>
</body>
</html>