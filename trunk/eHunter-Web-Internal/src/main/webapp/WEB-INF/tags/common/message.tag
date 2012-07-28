<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ attribute name="code" required="true" type="java.lang.String"%>
<%@ attribute name="text" required="false" type="java.lang.String"%>
<%@ attribute name="needSuccSign" required="false" type="java.lang.Boolean"%>
<c:choose>
	<c:when test="${needSuccSign != null && needSuccSign == true}">
		<img src="${ctx}/images/icon/tick_icon.gif" />
		<span class="succmsg">
		  <spring:message code="${code}" text="${text}"></spring:message>
		</span>
	</c:when>
	<c:otherwise>
		<spring:message code="${code}" text="${text}"></spring:message>
	</c:otherwise>
</c:choose>