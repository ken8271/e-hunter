function hasBeenSelect(selector , value){
	for(var i=0; i<selector.length ; i++){
		if(value == selector.options[i].value){
			return true;
		}
	}	
	return false;
}

function removeByCode(selector , value){
	for(var i=0 ; i<selector.length ; i++){
		if(value == selector.options[i].value){
			selector.options.remove(i);  
			i--;
		}
	}
}

function clearSelectedItems(id){
	$('#'+ id +' td:gt(0)').remove();
}

function showSelectedItems(selectedPanelId , selector , multiColumn , columnCount){
	var str = '';
	
	if(multiColumn){		
		for(var i=0 ; i<columnCount ; i++){
			if(i < selector.length){
				str = str + "<td><input type='checkbox' value='" + selector.options[i].value + "' checked onclick='handleUnselect(this)'/>" + selector.options[i].text +"</td>";
			}else {
				str = str + "<td>&nbsp;</td>";
			}
		}
	}else {
		str = str + "<td>";
		for(var i=0 ; i<selector.length ; i++){
			str = str + "<input type='checkbox' value='"  + selector.options[i].value + "' checked onclick='handleUnselect(this)'/>" + selector.options[i].text + "<br/>";
		}
		str = str + "</td>";
	}
	
	clearSelectedItems(selectedPanelId);
	$(str).appendTo('#' + selectedPanelId);
}

function handleUnselect(selectorId , selectedPanelId , detailPanelId , c , multiColumnIndicator , columnCount){
	var selector = document.getElementById(selectorId);
	if(!c.checked){
		removeByCode(selector , c.value);
		
		var detail = $('#'+ detailPanelId + ' [value='+ c.value + ']');
		if(detail != null && detail[0] != null){
			detail[0].checked = false;
		}
		
		showSelectedItems(selectedPanelId , selector , multiColumnIndicator , columnCount);
	}
}

function handleSelect(selectorId , selectedPanelId , deatilColumn , maxPermitCount , multiColumnIndicator , columnCount){
	var selector = document.getElementById(selectorId);
	var c = $(deatilColumn).children();
	var label = $(deatilColumn).text();
	c[0].checked = !c[0].checked;
	
	if(c[0].checked){
		if(selector.length >= maxPermitCount){
			alert('最多可添加' + maxPermitCount +'项');
			c[0].checked = false;
			return ;
		}
		
		if(!hasBeenSelect(selector , c[0].value)){			
		   selector.options[selector.length] = new Option(label, c[0].value);
		}else {
		   c[0].checked = false;
		   removeByCode(selector , c[0].value);
		}
	}else {		
		removeByCode(selector , c[0].value);
	}
	
	showSelectedItems(selectedPanelId , selector , multiColumnIndicator , columnCount);
}

