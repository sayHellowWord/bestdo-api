<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <title>青少年游泳基础培训班</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no" />
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>
    <link rel="stylesheet" href="/css/swiper-3.2.7.min.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>


<body>
<!--头部公用-->
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">${train.name}</p></div>

        </div>
    </div>
</div>
<!--banner-->
<div class="lunbo">
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <#list tenMinSite.showImg?split(";") as image>
                <div class="swiper-slide" style="background-image: url(${image});"></div>
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
                <li class="address font14"><p>${train.adress}</p></li>
            </ul>
        </div>



        <div class="scrolldate yu">
            <h3 class="biaotiyong font16">教练风采</h3>
            <div class="scrolldateCont box font12">
				<#--<span >
					<a href="javascript:void(0)" class="sai">
						<div class="box">
							<img src="images/2.png">
							<div >
								<h2 class="font16">姓名</h2>
								<P class="font12 now">高级游泳教练</P>
								<P class="font12">擅长蛙泳</P>
							</div>
						</div>

					</a>
				</span>-->span
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
    var mySwiper = new Swiper ('.swiper-container', {
        loop: true,
        autoplay: 3000,

        // 如果需要分页器
        pagination: '.swiper-pagination',
    })


    // 处理 coaches 教练数据
    var source = $("#template").html();
    var template = Handlebars.compile(source);
    var html = template(coaches);
    $("#scrolldateCont").append(html);

</script>
<script src="/js/handlebars-v4.0.5.js"></script>

<script id="template" type="text/x-handlebars-template">
    {{#each this}}
    <span>
        <a href="javascript:void(0)" class="sai">
            <div class="box">
                <img src="{{photoIcon}}">
                <div >
                    <h2 class="font16">{{name}}</h2>
                    <P class="font12 now">{{rank}}</P>
                    <P class="font12">{{introduction}}</P>
                </div>
            </div>
        </a>
    </span>
    {{/each}}
</script>
</body>
</html>