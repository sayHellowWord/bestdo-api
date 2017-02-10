<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>青少年游泳基础培训班</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no"/>
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>
    <link rel="stylesheet" href="/css/swiper-3.2.7.min.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>


<body>
<!--头部公用-->
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">${train.name}</p></div>

        </div>
    </div>
</div>
<!--banner-->
<div class="lunbo">
    <div class="swiper-container">
        <div class="swiper-wrapper">
        <#list train.shortIcon?split(";") as image>
            <#if image?? && image !="" && image?length gt 2>
             <div class="swiper-slide" style="background-image: url(${image});"></div>
            </#if>
        </#list>
        </div>

    </div>
</div>


<div class="wrapper">
    <div class="detailinfo">

        <!--场馆信息-->
        <div class="venuesInfo">
            <ul>
                <li class="ede font15"><p>${train.name}<i class="font12">(${train.minNum}人开赛)</i></p></li>
                <li class="address font14"><p>
                    <#--<a href="/cms/exercisehoop/map?name=${train.name}&address=${train.address}&latitude=${train.bdLatitude}&longitude=${train.bdLongitude}">-->
                        ${train.adress}
                    <#--</a>-->
                </p></li>
            </ul>
        </div>


        <div class="scrolldate yu">
            <h3 class="biaotiyong font16">教练风采</h3>
            <div id="scrolldateCont" class="scrolldateCont box font12">
            <#list coaches as item>
                <span>
					<a href="/cms/train/coach/toDetail?id=${item.id}" class="sai">
						<div class="box">
							<img src="${item.photoIcon?replace(";","")}">
							<div>
								<h2 class="font16">${item.name}</h2>
								<P class="font12 now">${item.rank}</P>
								<P class="font12">${item.experience}</P>
							</div>
						</div>
					</a>
				 </span>
            </#list>
            </div>
        </div>

        <div class="venuesInfo">
            <h1 class="font15">培训介绍</h1>
            <ul>
                <li class="moreinfo box font14">
                    <span class="tit">${train.description}</span>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="jianju"></div>

<!-- 底部电话 -->
<div class="ditel box">
    <p class="boxflex font15">电话号码：${train.phone}</p>
    <a class="font15" href="tel:${train.phone}">立即咨询</a>
</div>

<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/myproject.js"></script>
<script src="/js/swiper-3.2.7.min.js"></script>
<script>
    var mySwiper = new Swiper('.swiper-container', {
        loop: true,
        autoplay: 3000,

        // 如果需要分页器
        pagination: '.swiper-pagination',
    })
</script>
</body>
</html>