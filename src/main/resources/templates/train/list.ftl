<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>体育赛事</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!--忽略页面中的数字识别为电话号码-->  
<meta name="format-detection" content="telephone=no" />
<!--忽略页面中的邮箱格式为邮箱-->
<meta name="format-detection" content="email=no"/>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>


<body>
<!--头部公用-->
<div id="header">
	<div class="header">
		<div class="headerCont box">
			<div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
			<div class="headerC boxflex">
				<p class="font17">体育赛事</p>
			</div>
			</div>
		</div>
	</div>
</div>
<div class="chooseTab www25 font13">
	<div class="chooseTabCont">
		<a href="javascript:void(0)" data-tab="0">
			行政区<span></span>
		</a>
		<a href="javascript:void(0)" data-tab="1">
			排序<span></span>
		</a>
		<a href="javascript:void(0)" data-tab="2">
			距离<span></span>
		</a>
	</div>
</div>
<!--区域-->
<div class="slidemenu">
	<div class="slidebg"></div>
	<div class="slidemenuCont font14 nearby">
		<a href="javascript:void(0)" class="on">亭湖区</a>
		<a href="javascript:void(0)">盐都区</a>
		<a href="javascript:void(0)">大丰区</a>
	</div>
</div>
<!--排序-->
<div class="slidemenu">
	<div class="slidebg"></div>
	<div class="slidemenuCont font14 sort">
		<a href="javascript:void(0)" class="on">按时间排序</a>
	</div>
</div>
<!--距离-->
<div class="slidemenu">
	<div class="slidebg"></div>
	<div class="slidemenuCont font14 distance">
		<a href="javascript:void(0)" class="on">不限距离</a>
		<a href="javascript:void(0)">距离最近</a>
		<a href="javascript:void(0)">附近5km</a>
		<a href="javascript:void(0)">附近10km</a>
	</div>
</div>
<!--赛事列表-->
<div class="wrapper">
	<!--赛事列表-->
	<div class="venueslist">
		<ul class="list">
			<li class="box vip">
				<div class="venuesimg"><img src="images/2.png"></div>
				<div class="venuesdetial boxflex">
					<h2 class="font16">盐城羽毛球培训班 </h2>
					<div class="address add3 font12">
						<span class="p">报名时间：1月1日-1月3日</span>
					</div>
					<div class="address add3 font12">
						<span class="p">课程时间：1月1日-1月3日</span>
					</div>
					<div class="address add3 font12">
						<span class="p">培训地点：西直门西直门西直门西直门西直门西直门西直门</span>
					</div>
				</div>
				<div class="xiajia"><p class="font14">报名中</p></div>
			</li>
			<li class="box vip">
				<div class="venuesimg"><img src="images/2.png"></div>
				<div class="venuesdetial boxflex">
					<h2 class="font16">盐城羽毛球培训班 </h2>
					<div class="address add3 font12">
						<span class="p">报名时间：1月1日-1月3日</span>
					</div>
					<div class="address add3 font12">
						<span class="p">课程时间：1月1日-1月3日</span>
					</div>
					<div class="address add3 font12">
						<span class="p">培训地点：西直门西直门西直门西直门西直门西直门西直门</span>
					</div>
				</div>
				<div class="xiajia"><p class="font14 on">报名关闭</p></div>
			</li>
		</ul>
	</div>
</div>





<!--地图图标-->
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script>
$(function(){
	var xmlhttp=new XMLHttpRequest();
	if (xmlhttp.readyState <4 || xmlhttp.status != 200){
	   $(".loading").hide(); 
	}
    document.onreadystatechange = completeLoading;
    function completeLoading() {
        if (document.readyState == "complete") {
           $(".loading").hide(); 
        }
    }
	/*筛选条件*/
	$(".slidemenuCont a").on("click",function(){
		var ix = $(".slidemenuCont a").index($(this));
	})
	
	$(".chooseTab a").tabEve({
		cls:".slidemenu",
		selected:"on",
		empty:"gray",
		typing:"slidemenu"
	})

	//区域
		$("div.nearby a").click(function(){

			//替换title
			$("div.chooseTabCont a.geo").html($(this).html() + '<span></span>');
			
			$("body").click();

			$("div.nearby").children('a').each(function(index, item){
				$(item).removeClass('on');
			});

			$(this).addClass('on');

			scope.district = $(this).attr('data-tag');

		    againReload();
		});

		//排序
		$("div.sort a").click(function(){

			//替换title
			$("div.chooseTabCont a.geo").html($(this).html() + '<span></span>');
			
			$("body").click();

			$("div.sort").children('a').each(function(index, item){
				$(item).removeClass('on');
			});

			$(this).addClass('on');

			scope.district = $(this).attr('data-tag');

		    againReload();
		});
	//距离
		
		$("div.distance a").click(function(){

			//替换title
			$("div.chooseTabCont a.geo").html($(this).html() + '<span></span>');
			
			$("body").click();

			$("div.distance").children('a').each(function(index, item){
				$(item).removeClass('on');
			});

			$(this).addClass('on');

			scope.district = $(this).attr('data-tag');

		    againReload();
		});

	
})
</script>
</body>
</html>