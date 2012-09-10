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