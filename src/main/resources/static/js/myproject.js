/*
* myproject 1.0
* author lizhijun
* projectname 百动网移动端项目
* slidemenu 下拉菜单、单选、选项卡切换
* loadmore  下拉加载更多
* clearfix   position:fixed兼容问题
* dateControl 日期时间控件
*/
(function($){	
	//阻止浏览器默认行为
	var stopWindowDefault = {
		windowdefaultEvent: function(windowflag){
			if(windowflag){
				$("body").removeAttr("style");
			}
			else{
				$("body").css("overflow","hidden");
			}
			window.ontouchmove = function(e){
				e.preventDefault && e.preventDefault();
				e.stopPropagation && e.stopPropagation();
				if(windowflag){
		            e.returnValue=true;
		            return true;	
				}
				else{
			        e.returnValue=false;
			        return false;
				}
			}
		}
	};
	$.fn.extend({
		//下拉菜单、单选、选项卡切换
		slidemenu: function(options){
			var defaults = {
				layername:".slidemenu",
				flag: false,
				type:"tab"
			};
			var ops = $.extend(defaults,options);
			var isflag = false;
			var cself = -1;
			var obj = $(this);
			var items = obj.children();
			var ix = 0;
			$(ops.layername).find("a").click(function(){
				$(this).addClass(ops.subon).siblings().removeClass(ops.subon);
				$(this).parents(ops.layername).slideUp(function(){
					$(ops.layerbg).hide();
					items.removeClass(ops.parenton);
				});
				isflag = false;
				if(ops.flag){
					var str = $(this).text();
					items.eq(ix).html(str+"<span></span>");
				}

				stopWindowDefault.windowdefaultEvent(true);
			})
			$(ops.layerbg).click(function(){
				$(ops.layername).slideUp(function(){
					$(ops.layerbg).hide();
				});
				isflag = false;
				stopWindowDefault.windowdefaultEvent(true);
			})
			items.click(function(){
				ix = items.index($(this));
				if(cself == -1 || (cself!=ix || !isflag)){
					$(ops.layerbg).show();
					$(ops.layername).eq(ix).slideDown().siblings(ops.layername).hide();
					$(this).addClass(ops.parenton).siblings().removeClass(ops.parenton);
					cself = ix;
					isflag = true;
					if(ops.type=="select"){
						stopWindowDefault.windowdefaultEvent(false);
					}
				}
				else if(cself == ix && isflag){
					$(ops.layername).slideUp(function(){
						$(ops.layerbg).hide();
						isflag = false;
						if(ops.type=="select"){
							stopWindowDefault.windowdefaultEvent(true);
						}
					})
				}
				
			})

		},

		//下拉加载更多
		loadmore: function(options){
			var defaults = {
				layername:"#venueslist",
				flag: false,
			};
			var ops = $.extend(defaults,options);	
			var obj = $(this);
			$(window).scroll(function(){
				var sh = $("body").height();
				var ch = obj.height();
				var st = $(this).scrollTop();
				if(st>=ch-sh){
					var data = ops.getData();
					setTimeout(function(){
						obj.children().append(data);
					},100)
				}

			})
		},
		/*position:fixed兼容问题*/
		clearfix: function(options){
			var defaults = {};
			var ops = $.extend(defaults,options);	
			var obj = $(this);
			$("input").focus(function(){
				if(/iphone|ipad|ipod/i.test(navigator.userAgent)){ 
				     obj.css("position", "static"); 
				}
			})
			$("input").blur(function(){
				if(/iphone|ipad|ipod/i.test(navigator.userAgent)){ 
				     obj.removeAttr("style"); 
				}
			})
		},
		//日期时间控件
		dateControl:function(options){
			document.onselectstart=function (){return false;};
			var defaults = {};
			var ops = $.extend(defaults,options);	
			var obj = $(this);
			var sy,my,ey,st,ix,sh,ah,len,objh,olen;
			var flag = false;
			var touchEvents = {
			    touchstart: "touchstart",
			    touchmove: "touchmove",
			    touchend: "touchend",
			    initTouchEvents: function () {
			        if (!(/iphone|ipad|ipod/i.test(navigator.userAgent))) {
			            this.touchstart = "mousedown";
			            this.touchmove = "mousemove";
			            this.touchend = "mouseup";
			        }
			    },
			};
			var scrollEvents = {
			    scrollStart: function(e){
					flag = true;
					sy = e.pageY || e.originalEvent.targetTouches[0].pageY;
					st = parseInt($(this).css("top"));
					ah = $(this).children().eq(0).height();
					len = $(this).children().length-1;
					objh = $(this).height();
			    },
			    scrollMove: function(e){
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
			    },
			    scrollEnd: function(){
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
			}
			obj.on("click",function(){
				$(ops.datecontrol).parent().parent().show();
				$(ops.datecontrol).parent().parent().children().eq(0).slideDown();
				touchEvents.initTouchEvents();
				stopWindowDefault.windowdefaultEvent(false);
				olen = $(ops.datecontrol).length;
				$(ops.datecontrol).each(function(){
					var m = parseInt($(this).attr("data-on"));
					$(this).children().eq(m).addClass("on").siblings().removeClass("on");
					ah = $(this).children().eq(0).height();
					$(this).css("top",ah*2-m*ah);
				})
				$(ops.datecontrol).on(touchEvents.touchstart,scrollEvents.scrollStart);
				$(ops.datecontrol).on(touchEvents.touchmove,scrollEvents.scrollMove);
				$(ops.datecontrol).on(touchEvents.touchend,scrollEvents.scrollEnd);
			});
			$(ops.datesureBtn+","+ops.datecancleBtn).on("click",function(){
				$(ops.datecontrol).parent().slideUp(function(){
					$(ops.datecontrol).parent().parent().hide();
					stopWindowDefault.windowdefaultEvent(true);
					
				});
			})
		}
	})
})(jQuery);














































































