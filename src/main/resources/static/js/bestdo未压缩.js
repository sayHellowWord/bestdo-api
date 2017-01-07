/*
* myproject 1.0
* author lizhijun
* projectname 百动网移动端项目
* tabEve 下拉菜单、单选、复选、选项卡切换
* loadmore  下拉加载更多
* inputEve   输入框效果
* dateControl 日期时间控件
* promptEve 提示弹层
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
		/*position:fixed兼容问题  输入框效果*/
		inputEve: function(options){
			var defaults = {
				cls:''
			};
			var ops = $.extend(defaults,options);	
			$("input").focus(function(){
				if(/iphone|ipad|ipod/i.test(navigator.userAgent)){ 
				     $(ops.cls).css("position", "static"); 
				}
			});
			$("input").blur(function(){
				if(/iphone|ipad|ipod/i.test(navigator.userAgent)){ 
				     $(ops.cls).removeAttr("style"); 
				}
			});
			$("input").keyup(function(){
				var str = $(this).val();
				if(str!=''){
					$(this).siblings().show();
					$(this).parent().siblings(".btn").removeClass("gray");
				}
				else{
					$(this).siblings().hide();
					$(this).parent().siblings(".btn").addClass("gray");
				}
			})
			$(ops.clearVal).on("click",function(){
				$(this).siblings().val('');
				$(this).hide();
				$(this).parent().siblings(".btn").addClass("gray");
			})
		},
		//下拉菜单、选项卡
		tabEve:function(options){
			var defaults = {
				typing:"tab"//效果类型
			};
			var ops = $.extend(defaults,options);
			var isflag = false;
			var cself =-1;
			var obj = $(this);
			$(this).on("click",function(event){
				event.stopPropagation();
				var typing = ops.typing;
				var i = parseInt($(this).attr("data-tab"));
				//下拉菜单
				if(typing=="slidemenu"){
					if($(ops.cls).eq(i).is(":hidden")){
						$(this).addClass(ops.selected).siblings().removeClass(ops.selected);
						$(ops.cls).eq(i).slideDown().siblings(ops.cls).hide();
						stopWindowDefault.windowdefaultEvent(false);
					}
					else{
						$(this).removeClass(ops.selected);
						$(ops.cls).eq(i).slideUp();
						stopWindowDefault.windowdefaultEvent(true);
					}
				}
				//选项卡
				if(typing=="tab"){
					$(this).addClass(ops.selected).siblings().removeClass(ops.selected);
					$(ops.cls).eq(i).show().siblings(ops.cls).hide();
				}
				//单选
				if(typing=="radio"){
					$(this).addClass(ops.selected).siblings().removeClass(ops.selected);
				}
				//复选
				if(typing=="checkbox"){
					if($(this).hasClass(ops.selected)){
						$(this).removeClass(ops.selected);
					}
					else{
						$(this).addClass(ops.selected);
					}
				}

			});
			$("body").on("click",function(){
				var typing = ops.typing;
				if(typing=="slidemenu"){
					obj.removeClass(ops.selected);
					$(ops.cls).slideUp();
					stopWindowDefault.windowdefaultEvent(true);
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
				var sh = $(window).height();
				var ch = obj.height();
				var st = $(this).scrollTop();
				if(st>=ch-sh){
					var data = ops.getData();
					setTimeout(function(){
						obj.append(data);
					},100)
				}
			})
		},
		//弹层提示
		promptEve:function(options){
			var defaults = {
				txt:""//弹层提示文本
			};
			var ops = $.extend(defaults,options);
			var str = "<div id='prompt'><div class='prompt box2'><div class='promptCont font14'><p>"+ops.txt+"</p></div></div></div>"
			$("body").append(str);
			$("#prompt").fadeIn();
			var t = setTimeout(function(){
				$("#prompt").fadeOut(1000);
			},1000);
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
				$(ops.datecontrol).parent().slideDown();
				$(ops.dateparent).show();

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
				var txt = "";
				$(ops.datecontrol).parent().slideUp(function(){
					$(ops.dateparent).hide();
					stopWindowDefault.windowdefaultEvent(true);
					olen = $(ops.datecontrol).length;
					for(i=0;i<olen;i++){
						txt = txt + $(ops.datecontrol).eq(i).find(".on").html();
						obj.html(txt);
					}
				});
			})
		}
	})
})(jQuery);














































































