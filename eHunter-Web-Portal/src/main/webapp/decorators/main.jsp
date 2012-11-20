<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh-decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="sitemesh-page"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ taglib tagdir="/WEB-INF/tags/solutions" prefix="solutions"%>
<html>
<head>
<jsp:include page="../commons/meta.jsp"></jsp:include>
<title><sitemesh-decorator:title></sitemesh-decorator:title></title>
<sitemesh-decorator:head></sitemesh-decorator:head>
</head>
<body>
   <common:top></common:top>
   
   <div id="content">
      <solutions:menu></solutions:menu>
      
      <div class="contents">
         <div class="office_list">
	        <div style="font-size:13px; letter-spacing:1px; line-height:20px; margin-top:10px;">
	           <sitemesh-decorator:body></sitemesh-decorator:body>
	        </div>
	     </div>		
      </div>
      
      <common:footer></common:footer>
   </div>
</body>
</html>