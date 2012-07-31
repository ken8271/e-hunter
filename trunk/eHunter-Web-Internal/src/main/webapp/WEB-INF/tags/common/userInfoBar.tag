<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<security:authentication property="authenticated" var="isAuthenticated"  scope="page"/>
<security:authentication property="principal" var="principal" scope="page"/>
<%@ tag import="com.pccw.ehunter.security.UserDetailServiceImpl" %>
<%@ tag import="com.pccw.ehunter.dto.InternalUserDTO" %>
<%@ tag import="com.pccw.ehunter.dto.InternalRoleDTO" %>
<c:set var="principal" value="${principal}"/>

<%if(com.pccw.ehunter.utility.SecurityUtils.getUser()!=null){ %>
<table border="0" width="100%" cellspacing="0" cellpadding="2">
      <tr>
      	 <td class="normalTextField loginInfo" align="left" width="20%"><c:out value="${screenId}"/></td>
         <td class="normalTextField loginInfo" align="right" width="80%">
            ${principal.cnName} | 
            <%if(((InternalUserDTO)com.pccw.ehunter.utility.SecurityUtils.getUser()).getActiveRoles() != null && ((InternalUserDTO)com.pccw.ehunter.utility.SecurityUtils.getUser()).getActiveRoles().size() != 0){ %>
            <%=(((InternalUserDTO)com.pccw.ehunter.utility.SecurityUtils.getUser()).getActiveRoles().get(0)).getRoleName() %> |
            <%} %>
            <fmt:formatDate type="both" value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd HH:mm:ss"/>  
         </td>
      </tr>
</table>  
<%}else{%>
<!-- session timeout! -->
<%}%>