var sy,my,ey,st,ix,sh,ah,len,objh,olen,slen;
var flag = false;
function dateControl(id){
	var obj = document.getElementById(id);
	obj.addEventListener("touchstart",scrollMove);
	obj.addEventListener("touchsmove",scrollMove);
	obj.addEventListener("touchend",scrollEnd);
}
function scrollStart(e){
	flag = true;
	sy = e.pageY || e.originalEvent.targetTouches[0].pageY;
	st = parseInt($(this).css("top"));
	ah = $(this).children().eq(0).height();
	len = $(this).children().length-1;
	objh = $(this).height();
	alert(flag);
}
function scrollMove(e){
	e.stopPropagation;
	if(flag){
		my = e.pageY || e.originalEvent.targetTouches[0].pageY;
		ey = my-sy;
		if(ey+st>=ah*2){
			ix = 0;
		}
		else{
			ix = Math.floor(Math.abs((ah*2-ey-st+ah/2)/ah));
		}
		$(this).children().eq(ix).addClass("on").siblings().removeClass("on");
		$(this).css("top",ey+st);
	}
}
function scrollEnd(){
	flag = false;
	if(my){
		st = parseFloat($(this).css("top"));
		if(st>=ah*2){
			st = ah*2;
		}
		else if(st<=ah*2-len*ah){
			ix = len;
			st = ah*2-len*ah;
		}
		else{
			st = ah*2-ix*ah;
		}
		$(this).attr("data-on",ix);
		$(this).stop().animate({"top":st},100);
		$(this).children().eq(ix).addClass("on").siblings().removeClass("on");
		my = null;
	}
}















