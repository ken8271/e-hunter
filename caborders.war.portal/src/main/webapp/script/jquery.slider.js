(function($){
    $.fn.slide = function(setting){
	    var ps =$.fn.extend({
	    	slidetag:null,      //滑动元素，默认为子节点
	    	btntag:null,		//滑动按钮元素,不填写则默认增加按钮 <ul class='lbtn'>…</ul>
	    	cur:0,      		//杂志左页
	    	direct:"up",		//滑动的方向”up”,”down”,”left”,”right”
	    	height:null,		//滑动元素的滑动高度，默认为滑动元素的高度
	    	width:null,			//滑动元素的滑动宽度，默认为滑动元素的宽度
	    	btnClass:"hover",	//滑动按钮元素选中的class
	    	title:null,		
	    	length:null,		//滑动的个数,默认为滑动元素的个数
	    	bffunc:function(){},//滑动之前的动作
	    	easing:"",			//滑动时增加的缓动动画，需要jQuery easing插件支持
	    	speed:350,			//滑动速度
	    	time:4000			// 如果等于null  就没有自动滚动
	    }, setting);
		var $self = $(this);
		var events ={
			__init:function(e)
			{
				$self.addClass("direct-"+ps.direct);
				ps.direct = ps.direct.toLowerCase();
				ps.oldslidetag=ps.slidetag;
				ps.slidetag=(ps.slidetag==null)?$self.children():ps.slidetag;
				ps.length=(ps.length==null)?ps.slidetag.length:ps.length;
				ps.height=(ps.height==null)?ps.slidetag.eq(0).height():ps.height;
				ps.width=(ps.width==null)?ps.slidetag.eq(0).width():ps.width;
				$self.wrap(function(){
					return '<div class="slide-wrap" />';
				});
				if(!!ps.title)
				{
					var dock = ps.title.dock = ps.title.dock||'bottom';
					var p =[];
					for(var i = 0;i<ps.length;i++)
					{
						var s = $self.children().eq(i).find("img").attr("title")||Number(i+1);
						var m = [s,mec.substr(s)];
						p.push(m);
					}
					$self.data("title",p);
					var $vs = $("<div class='slider-info'><div class='bacO'></div><p title='"+p[0][0]+"'>"+p[0][1]+"</p></div>");
					$self.parent().append($vs);
					$vs.css({"height":ps.title.height,'top':ps.title.dock=="top"?0:ps.height-ps.title.height});
					$vs.find(".bacO").css({"backgroundColor":ps.title.bgcolor,opacity:ps.title.opacity});
				}
				//控制数字导航
				if(!ps.btntag)
				{
					var s = "<ul class='lbtn'>";
					for(var i=0;i<ps.length;i++){s+="<li><a href='javascript:void(0)'>"+Number(i+1)+"</a></li>";}
					$self.parent().append(s+"</ul>");
					ps.btntag=$self.parent().find('.lbtn');
				}
				
				ps.btntag.children().bind("mouseover", function(){
					m = ps.btntag.find("."+ps.btnClass).index();
					if(m==$(this).index())
					{
						return false;
					}
					events.__addFlag($(this).index());
					events.__slide($(this).index());
				});
				//图片切换方向
				if(ps.direct.toLowerCase()=="right")
				{
					$self.css({
						"width":ps.width*ps.length,
						"left":-ps.width*(ps.length-1)
					});
				}
				if(ps.direct.toLowerCase()=="down")
				{
					var r = [];
					for(var i=0;i<ps.slidetag.length;i++)
					{	
						r.push(ps.slidetag.eq(i).clone());
					}
					$self.html("");
					for(var i=0;i<r.length;i++)
					{	
						$(r[i]).prependTo($self);
					}
					$self.css("top",-ps.height*(ps.length-1));
				}
				events.__addFlag(ps.cur);
				events.__setPosition(ps.cur);
				if(ps.time!=null)
			    {
					events.__addTimelistener();
				}
			},
			__slide:function(next){
				var $k = $self.parent().find(".slider-info");
				var og ={
					"cg" :next - ps.cur,
					"bfuc":function(){},
					"fuc": function(){},
					"scollHei":function(){
						var h;
						if(ps.direct=="up")
						{	
							h=-ps.height*ps.cur;
						}
						else if(ps.direct=="down")
						{	
							h=ps.height*(ps.cur-ps.length+1);
						}
						else 
						{
							h=0;
						}
						return h;
					},
					"scollWid":function(){
						var w;
						if(ps.direct=="left") 
						{	
							w=-ps.width*ps.cur;
						}
						else if(ps.direct=="right")
						{	
							w=ps.width*(ps.cur-ps.length+1);
						}
						else w=0;
						return w;
					}
				};
				if((!next&&next!=0)||(ps.cur==ps.length-1&&next==0))
				{
					//alert(1);
					if(ps.cur ==ps.length-1)
					{
						ps.cur = 0;
						og.bfuc = function(){

							if(ps.direct=="down")
							{
								ps.slidetag.slice(1,ps.length).prependTo($self);
							}
							else
							{
								ps.slidetag.slice(0,ps.length-1).appendTo($self);
							}
							events.__resetPosition();
						};
						og.scollHei= function(){
							var h;
							if(ps.direct=="up") 
							{
								h=-ps.height;
							}
							else if(ps.direct=="down")
							{
								h=-ps.height*(ps.length-2);
							}
							else
							{
								h=0;
							}
							return h;
						};
						og.scollWid= function(){
							var w;
							if(ps.direct=="left") 
							{
								w=-ps.width;
							}
							else if(ps.direct=="right") 
							{
								w=-ps.width*(ps.length-2);
							}
							else w=0;
							return w;
						};
						og.fuc = function(){
							ps.slidetag=(ps.oldslidetag==null)?$self.children():ps.slidetag;
							if(ps.direct=="down")
							{
								ps.slidetag.slice(ps.length-1,ps.length).prependTo($self);
							}
							else
							{
								$self.children().slice(0,1).appendTo($self);
							}
							events.__resetPosition();
						};
					}
					else
					{
						ps.cur++;
					}
					cg=1;
				}
				else
				{
					ps.cur = next;
				}
				og.bfuc();
				ps.bffunc();
				$self.stop().animate({'top':og.scollHei(),'left':og.scollWid()},ps.speed,ps.easing,function(){og.fuc()});
				if(!!ps.title)
				{
					if(!!ps.title.fx)
					{
						$k.stop().animate({'top':ps.title.dock=="top"?-$k.height():ps.height},ps.speed*3/5,"easeInOutSine",function(){
							ct();$k.animate({'top':ps.title.dock=="top"?0:ps.height-$k.height()},ps.speed*3/5,"easeInOutSine")
						});
					}
					else 
					{
						ct();
					}
				}
				events.__addFlag(ps.cur);
				function ct(){
					$k.find("p").attr("title",$self.data("title")[ps.cur][0]).html($self.data("title")[ps.cur][1]);
				}
			},
			__addFlag:function(n){
				ps.btntag.children().eq(n).addClass(ps.btnClass).siblings().removeClass(ps.btnClass);
			},
			__addTimelistener:function(n){
				ps.myScroll = setInterval(function(){events.__slide()},4000);
				ps.btntag.children().add(ps.slidetag).hover(function(){
				  clearInterval(ps.myScroll);							 
				},function(){
				  ps.myScroll = setInterval(function(){events.__slide()},4000);	
				});
			},
			__resetPosition:function(){
				if(ps.direct=="left"||ps.direct=="up")
				{
					$self.css({"top":"0px","left":0});
				}
				else if(ps.direct=="right")
				{
					$self.css({"left":-ps.width*(ps.length-1)});
				}
				else if(ps.direct=="down")
				{
					$self.css({"top":-ps.height*(ps.length-1)});
				}
			},
			__setPosition:function(n){
				n=n||0;
				if(ps.direct=="left")
				{
					$self.css("left",-n*ps.width);
				}
				else if(ps.direct=="right")
				{	
					$self.css("left",-(ps.length-n-1)*ps.width);
				}
				else if(ps.direct=="up")
				{
					$self.css("top",-n*ps.height);
				}
				else if(ps.direct=="down")
				{
					$self.css("top",-(ps.length-n-1)*ps.height);
				}
			}
		};
		var mec = {
			substr:function(s){
				ps.title.overflow = ps.title.overflow||'...';
				if(s.length>ps.title.length)
				{
					return s.substr(0,ps.title.length)+(ps.title.overflow=='ellipsis'?'...':ps.title.overflow);
				}
				else
				{
					return s;
				}
			}
		}
		//最后执行
		events.__init();
		for(var event in events) {
			var e = "slide." + event;
			$(this).unbind(e);
			$(this).bind(e, events[event]);
		}
    };
})(jQuery);
