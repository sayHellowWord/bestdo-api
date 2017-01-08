<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>场馆详情</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!--忽略页面中的数字识别为电话号码-->  
<meta name="format-detection" content="telephone=no" />
<!--忽略页面中的邮箱格式为邮箱-->
<meta name="format-detection" content="email=no"/>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>


<body>
<!--头部公用-->
<div id="header">
	<div class="header">
		<div class="headerCont box">
		<div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
		<div class="headerC boxflex"><p class="font17">${detail.name}</p></div>
		<div class="headerR">
		<#--	<a href="javascript:void(0)" class="addFav addFavOn"></a>-->
		</div>
		</div>
	</div>
</div>
<div class="wrapper">
	<div class="detailinfo">
		<!--场馆图片-->
		<div class="imgshow">
			<div class="imglist"><img src="${detail.thumb}" ></div>
			<div class="imgtxt font20"><p>${detail.name}</p></div>
		</div>

        <!-- 订场须知 -->
		<#if  detail.venue.book_info != "">
        <div class="venuesNote">
            <div class="font15">订场须知</div>
            <p class="font14">${detail.venue.book_info}</p>
        </div>
		</#if>

		<!--场馆信息-->
		<div class="venuesInfo">
			<ul>
				<li class="address font15"><p>${detail.venue.stadium.address}</p></li>
				<li class="tel font15"><p><a href="tel:${detail.venue.stadium.phone}">${detail.venue.stadium.phone}</a></p></li>
			</ul>
		</div>

        <!--时间预定  时段性、日期型-->
		<div class="venuesInfo">
			<ul>
				<li class="date font15"><p>10月11日 周三 18:00</p></li>
			</ul>
		</div>

        <!--场地属性-->
		<div class="venuesInfo">
			<h1 class="font15">场地属性</h1>
			<ul>
				<li class="other font14">
					<div><span>球场类型：</span><span>丘陵球场</span></div>
					<div><span>球洞数量：</span><span>18洞</span></div>
					<div><span>标准杆数：</span><span>72杆</span></div>
					<div><span>球场长度：</span><span>7285码</span></div>
					<div><span>球场灯光：</span><span>有</span></div>
					<div><span>设计师：</span><span>MIKE</span></div>
					<div><span>果岭草种：</span><span>草种名称</span></div>
					<div><span>球道草种：</span><span>草种名称</span></div>
					<div><span>开球时间：</span><span>6:00-16:00</span></div>
					<div><span>占地面积：</span><span>2700亩</span></div>
				</li>
			</ul>
		</div>

        <!--场馆服务-->
		<div class="venuesInfo">
			<h1 class="font15">北京新大都网球馆</h1>
			<ul>
				<li class="moreinfo box font14">
					<div class="icon boxflex font12">
						<span class="i1">操练房</span>
						<span class="i2">灯光费</span>
						<span class="i3">沐浴</span>
						<span class="i4">球童</span>
						<span class="i5">WIFI</span>
						<span class="i6">力量器械</span>
						<span class="i7">衣柜</span>
						<span class="i8">超市</span>
						<span class="i9">乒乓</span>
						<span class="i10">果岭</span>
						<span class="i11">球车</span>
						<span class="i12">住宿</span>
						<span class="i13">儿童乐园</span>
						<span class="i14">中餐</span>
						<span class="i15">餐饮</span>
						<span class="i16">服务费</span>
						<span class="i17">跑步机</span>
						<span class="i18">游泳池</span>
						<span class="i19">储金费</span>
						<span class="i20">教练</span>
						<span class="i21">专卖店</span>
						<span class="i22">球具租赁</span>
						<span class="i23">保险</span>
						<span class="i24">停车场</span>
						<span class="i25">免费餐饮</span>
						<span class="i26">免费灯光</span>
						<span class="i27">免费淋浴</span>
						<span class="i28">免费教练</span>
						<span class="i29">免费wifi</span>
						<span class="i30">免费服务</span>
						<span class="i31">免费停车</span>
						<span class="i32">免费衣柜</span>
						<span class="i33">单人球车</span>
						<span class="i34">双人球车</span>
						<span class="i35">多人球场</span>

					</div>
				</li>
				<li class="moreinfo box font14">
					<span class="tit">场馆介绍：</span>
					<div class="cont boxflex">
						非常炫酷，很炫酷
					</div>
				</li>
			</ul>
		</div>
	</div>

	<!--预订按钮-->
	<div id="venuesBook">
		<div class="venuesBook">
			<div class="venuesBookCont box">
				<div class="venuesBookInfo boxflex2">
					<div class="price box2">
						<p class="p1 font20"><span>￥350</span><i class="font12">起</i></p>
						<P class="p2 font12">门市价￥180</P>
					</div>
					<div class="service box2">
						<p class="font12">18洞72杆</p>
						<p class="font12">(价格包含)</p>
					</div>
				</div>
				<a href="javascript:void(0)" class="venuesBookBtn boxflex font18">预订</a>
			</div>
		</div>
	</div>


</div>
<!--日期控件-->
<div class="dateControl">
	<div class="dateControlBg"></div>
	<div class="dateControlCont">
		<div class="datelist font14">
			<div class="dateline"></div>
			<h1>
				<a href="javascript:void(0)" class="cancle">取消</a>
				<a href="javascript:void(0)" class="sure">确定</a>
				<p class="font17">选择teetime</p>
			</h1>
			<div class="scroll sdate perc1" data-on="2">
				<span>11月14日<i>周一</i></span>
				<span>11月14日<i>周一</i></span>
				<span>11月14日<i>周一</i></span>
				<span>11月14日<i>周一</i></span>
				<span>11月14日<i>周一</i></span>
				<span>11月14日<i>周一</i></span>
			</div>
			<div class="scroll stime perc" data-on="2">
				<span>09</span>
				<span>10</span>
				<span>11</span>
				<span>12</span>
				<span>13</span>
				<span>14</span>
			</div>
			<div class="scroll perc" data-on="0">
				<span>00</span>
				<span>30</span>
			</div>
		</div>
	</div>
</div>
<script src="../js/jquery.js"></script>
<script src="../js/bestdo.js"></script>
<script>
	$(function(){
		$(".addFav").click(function(){
			$(this).promptEve({
				txt:'关注成功'
			})
		});
		$(".date p").dateControl({
			dateparent:".dateControl",
			datecontrol:".scroll",
			datesureBtn:".sure",
			datecancleBtn:".cancle",
		})
	})
</script>
</body>
</html>