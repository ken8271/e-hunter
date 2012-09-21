function getClientWidth(){
	var winWidth = 0;
	
	if (window.innerWidth){		
		winWidth = window.innerWidth;
	}else if ((document.body) && (document.body.clientWidth)){		
		winWidth = document.body.clientWidth;
	}

	return winWidth;
}

function getClientHeight(){
	var winHeight = 0;
	if (window.innerHeight){		
		winHeight = window.innerHeight;
	}else if ((document.body) && (document.body.clientHeight)){		
		winHeight = document.body.clientHeight;
	}

	return winHeight;
}

function setOverlayDimension (fade_id){
	 var overlay = document.getElementById(fade_id);
	 var _11 = AJS.getWindowSize();
		if (AJS.isMozilla() || AJS.isOpera()) {
			AJS.setWidth(overlay, "100%");
		} else {
			AJS.setWidth(overlay, _11.w);
		}
		var _12 = Math.max(AJS.getScrollTop() + _11.h, AJS.getScrollTop()
				+ this.height);
		if (_12 < AJS.getScrollTop()) {
			AJS.setHeight(overlay, _12);
		} else {
			AJS.setHeight(overlay, AJS.getScrollTop() + _11.h);
		}
}

function hideAllObject(){
	var e = document.getElementsByTagName('select');

	if(e == null){
		return;
	}
	for(var i=0;i<e.length;i++){
		e[i].style.display = "none";
	}
}

function showAllObject(){
	var e = document.getElementsByTagName('select'); 
	if(e == null){
		return;
	}
	for(var i=0;i<e.length;i++){
		e[i].style.display = "block";
	}
}

function popUpFrame(lightDivId, fadeDivId) {
	document.getElementById(lightDivId).style.display = 'block';
	document.getElementById(fadeDivId).style.display = 'block'
}

function setPopUpFramePosition(width , height) {
	var leftOffset = (getClientWidth() - width) / 2;
	var topOffset = (getClientHeight() - height) / 2;
	$('#light').css( {
		"position" : "absolute",
		"height" : height + "px",
		"width" : width + "px",
		"left" : leftOffset + "px",
		"top" : topOffset + "px"
	});
}

function setCheckedIndicator(prefix , tokens){
	if(tokens == null) return ;
	
	for (var i = 0; i < tokens.length; i++) {
		if(tokens[i] != '') {
			var checkBox = document.getElementById(prefix + '_' +tokens[i]);
			if(checkBox != null){
				checkBox.checked = true;
			}
		}
	}
}

function clearSelector(selector){
	while(selector.childNodes.length>0){
		selector.removeChild(selector.childNodes[0]);
	}
}