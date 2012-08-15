<%@ include file="/commons/taglibs.jsp"%>
<%@attribute name="dateYY"  type="java.lang.String" required ="true"%>
<%@attribute name="dateMON"  type="java.lang.String" required ="true"%>
<%@attribute name="dateDD"  type="java.lang.String" required ="true"%>
<%@attribute name="readOnly" type="java.lang.String" required ="false"%>

<c:if test="${readOnly == ''}" > 
	${readOnly} = "false"
</c:if>

<form:input cssClass="standardInputTextNoWidth" path="${dateYY}"  maxlength="4"  size="4"  readonly="${readOnly}" onkeyup="tabForInputText(this,'${dateMON}',4,event)" onblur="addZero(this,4);"/>-
<form:input cssClass="standardInputTextNoWidth" path="${dateMON}"  maxlength="2" size="2"  readonly="${readOnly}" onkeyup="tabForInputText(this,'${dateDD}',2,event)" onblur="addZero(this,2);"/>-
<form:input cssClass="standardInputTextNoWidth" path="${dateDD}" maxlength="2"  size="2"  readonly="${readOnly}" onblur="addZero(this,2);"/>
<c:set var="dateFuncName" value="${fn:replace(dateYY,'.','')}"/>
<c:if test="${readOnly != 'true'}" > 
<img id="${dateYY}_button" title="Date selector" style="VERTICAL-ALIGN: bottom; CURSOR: pointer;onmouseout="this.style.background=''" src="${ctx}/script/jscalendar/img.gif">&nbsp;&nbsp;
<script type=text/javascript>
   function update${dateFuncName}(cal){
	   var date = cal.date;
	   var mm = document.getElementById("${dateMON}");
	   var dd = document.getElementById("${dateDD}");
	   mm.value = date.print("%m");
	   dd.value = date.print("%d");
   }	    			   
   Calendar.setup({inputField     :    "${dateYY}",
			       ifFormat       :    "%Y",
				   button         :    "${dateYY}_button",
				   align          :    "Tl",
				   singleClick    :    true,
				    onUpdate	  :	update${dateFuncName}});
</script>            

<script language=JavaScript>
	function tabForInputText(self,next,size,event){
		var nextElemnet=document.getElementById(next);
		
		
		if(event.shiftKey||event.keyCode==16||event.keyCode==9){
			return;
		}
		
		if(self.value.length==size &&event.keyCode!=37&&event.keyCode!=39){
			spaceTrim(self);
			
			nextElemnet.focus();
		}
	}

	function spaceTrim(self){
		self.value= self.value.replace(/(^\s*)|(\s*$)/g,   "");
		
	}
	function addZero(self,size){
		if(self.value.length>0&&self.value!=" "){
		for(var i=0;self.value.length<size;i++){
			self.value='0'+self.value;
		}
		
		}
		spaceTrim(self);
	}
</script>
</c:if>

 

