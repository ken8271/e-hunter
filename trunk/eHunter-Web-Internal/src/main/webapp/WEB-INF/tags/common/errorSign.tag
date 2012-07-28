<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="path" type="java.lang.String"%>
	
<img src="${ctx}/images/icon/icon_warning.gif" id="${id}errorSign" style="display:none">
<form:errors htmlEscape="true" path="${path}" cssClass="errorCss" cssStyle="float:left;display:none" id="${id}errorSignSpan"/>
	
 <script type="text/javascript">
 	var error = document.getElementById( "${id}errorSignSpan" );
 	if(error != null){
 		var errorTemplate = document.getElementById( "${id}errorSign" );
 		errorTemplate.style.display = "inline";
 		errorTemplate.title = error.innerHTML;
 		error.style.display="none";
 	}
 </script>