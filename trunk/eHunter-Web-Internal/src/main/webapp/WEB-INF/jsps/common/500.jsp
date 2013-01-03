<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内部错误-[猎头管理系统]</title>
<script language="javascript">
function showDetail(){
	var msg = document.getElementById('detail_error_msg');
	if(msg){
		if(msg.style.display=="none"){
			msg.style.display='inline';
		}else{
			msg.style.display='none';
		}
	}
}
</script>
</head>
<body>
<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Exception) request.getAttribute("javax.servlet.error.exception");

	Logger logger = LoggerFactory.getLogger("500.jsp");
	if (ex != null)
		logger.error(ex.getMessage(), ex);
	else
		logger.info("ex is null in 500.jsp.");
	
%>
<table border="0" width="100%">
   <tr><td class="pageTitle">系统错误</td></tr>
</table>
<div class="emptyBlock"></div>
<div align="left" style="width:100%">
   <p><a href="#" onclick="showDetail();">系统管理员请点击这里</a></p>
   <div id="detail_error_msg" style="display:none">
      <pre><%
			if (ex != null)
				ex.printStackTrace(new java.io.PrintWriter(out));
			else
				out.println("ex is null and no stacktrace is available.");
			%>
	  </pre>
   </div>		
</div>
<div class="emptyBlock"></div>
<table align="right" border="0" cellspacing="0" cellpadding="0">
   <tr>
      <td>
         <input type="button" value="关闭" onclick="location.href='${ctx}/index.do'" />
      </td>
   </tr>
</table>	
</body>
</html>