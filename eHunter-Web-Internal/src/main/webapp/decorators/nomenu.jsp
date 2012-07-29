<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="sitemesh-decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page"
	prefix="sitemesh-page"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<jsp:include page="../commons/meta.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><sitemesh-decorator:title></sitemesh-decorator:title>
</title>
<sitemesh-decorator:head></sitemesh-decorator:head>
</head>
<body>
<sitemesh-decorator:body></sitemesh-decorator:body>
</body>
</html>