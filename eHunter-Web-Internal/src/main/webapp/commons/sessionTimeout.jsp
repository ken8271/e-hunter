<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html>
<head>
	<title>Session Timeout</title>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
</head>
<script type="text/javascript">
window.location="${ctx}/logout.do?key=session_t_o";
</script>
<body>
</body>
</html>
