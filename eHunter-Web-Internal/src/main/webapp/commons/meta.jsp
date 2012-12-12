<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link rel="stylesheet" type="text/css" href="${ctx}/style/layout.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/style/default.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/style/ehunter.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/style/jmesa.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/style/jquery.validate.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/style/flexpaper.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/script/jscalendar/calendar-blue.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/script/greybox/gb_styles.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/style/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/style/form.css" />
<script type="text/javascript">
   var GB_ROOT_DIR = "${ctx}/script/greybox/";
</script>
<script type="text/javascript">
   var BAR_ROOT_DIR = "${ctx}/script/progressbar/";
</script>
<script type="text/javascript" src="${ctx}/script/ehunter.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery-ui.js"></script>
<script type="text/javascript" src="${ctx}/script/progressbar/jquery.processing.plugin.js"></script>
<script type="text/javascript" src="${ctx}/script/flexpaper/flexpaper.js"></script>
<script type="text/javascript" src="${ctx}/script/flexpaper/flexpaper_handlers.js"></script>
<script type="text/javascript" src="${ctx}/script/flexpaper/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/script/jmesa.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery.jmesa.js"></script>
<script type="text/javascript" src="${ctx}/script/jscalendar/calendar.js"></script>
<script type="text/javascript" src="${ctx}/script/jscalendar/lang/calendar-en.js"></script>
<script type="text/javascript" src="${ctx}/script/jscalendar/calendar-setup.js"></script>
<script type="text/javascript" src="${ctx}/script/greybox/AJS.js"></script>
<script type="text/javascript" src="${ctx}/script/greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${ctx}/script/greybox/gb_scripts.js"></script>

<!-- 解决JQuery与其他JS冲突 -->
<script type="text/javascript">
if ($ != jQuery) {
    $ = jQuery.noConflict();
}
</script>