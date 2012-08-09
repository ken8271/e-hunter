<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="actualValue" type="java.lang.String" required="true"%>
<%@ attribute name="comparisonValue" type="java.lang.String" required="true"%>

<c:choose>
   <c:when test="${actualValue == comparisonValue }">
      <img src="${imagePath}/icon/checked.jpg"></img>
   </c:when>
   <c:otherwise>
      <img src="${imagePath}/icon/unchecked.jpg"></img>
   </c:otherwise>
</c:choose>
	
