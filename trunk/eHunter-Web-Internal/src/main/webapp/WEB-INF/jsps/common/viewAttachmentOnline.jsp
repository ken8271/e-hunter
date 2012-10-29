<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/meta.jsp"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<html>
<head>
<title>View Attachment</title>
<script type="text/javascript">
	var swfVersionStr = "10.0.0";
	var xiSwfUrlStr = "playerProductInstall.swf";
	var flashvars = {
		SwfFile : '${ctx}/swf/${swfFileName}',
		Scale : 0.6,
		ZoomTransition : "easeOut",
		ZoomTime : 0.5,
		ZoomInterval : 0.1,
		FitPageOnLoad : false,
		FitWidthOnLoad : true,
		PrintEnabled : true,
		FullScreenAsMaxWindow : false,
		ProgressiveLoading : true,
		PrintToolsVisible : true,
		ViewModeToolsVisible : true,
		ZoomToolsVisible : true,
		FullScreenVisible : false,
		NavToolsVisible : true,
		CursorToolsVisible : true,
		SearchToolsVisible : true,
		localeChain : "en_US"
	};

	var params = {};
	params.quality = "high";
	params.bgcolor = "#ffffff";
	params.allowscriptaccess = "sameDomain";
	params.allowfullscreen = "true";
	var attributes = {};
	attributes.id = "FlexPaperViewer";
	attributes.name = "FlexPaperViewer";
	swfobject.embedSWF("${ctx}/swf/FlexPaperViewer.swf", "flashContent","950", "700", swfVersionStr, xiSwfUrlStr, flashvars, params,attributes);
	swfobject.createCSS("#flashContent", "display:block;text-align:left;");
</script>
</head>
<body>
	<div id="flashContent" class="flashContent" align="center"></div>
</body>
</html>
