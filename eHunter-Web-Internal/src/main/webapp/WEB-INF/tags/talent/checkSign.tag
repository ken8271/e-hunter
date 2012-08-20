<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="path" type="java.util.List" required="true"%>

<c:choose>
   <c:when test="${not empty path }">
      <img src="${imagePath}/icon/ok.gif"></img>
   </c:when>
   <c:otherwise>
      <img src="${imagePath}/icon/blank.gif"></img>
   </c:otherwise>
</c:choose>
