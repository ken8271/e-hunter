<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ attribute name="actionFlagStr" required="false"%>
<%@ attribute name="action"  required="false"%>
<script type="text/javascript">
function selectAllByTableId(id){
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined'){
		actionFlag.value = '80';
	}
	setExportToLimit(id, '');
	createHiddenInputFieldsForLimitAndSubmit(id);
}
function onInvokeAction(id){
   var actionFlag = document.getElementById('actionFlag');
   var actionFlagStr = '${actionFlagStr}';
   if(actionFlag != 'undefined' && actionFlagStr != ""){
       actionFlag.value = actionFlagStr;
   }
   setExportToLimit(id, '');
   createHiddenInputFieldsForLimitAndSubmit(id);
}
function onInvokeExportAction(id) {
	var parameterString = createParameterStringForLimit(id);
	location.href = '${action}' + parameterString;
}
</script>