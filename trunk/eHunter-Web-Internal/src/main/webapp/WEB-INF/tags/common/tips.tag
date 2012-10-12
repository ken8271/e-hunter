<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" type="java.lang.String" required="true"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
	
<img src="${imagePath }/icon/tips.gif" title="${title }" style="vertical-align: middle;cursor: pointer;" onclick="var win = window.open('${url}','win', 'directories=no,height=550,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680,top=100,left=200');"/>