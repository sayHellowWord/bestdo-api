<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>盐城市羽毛球全民赛</title>
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
            <div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">${detail.name}</p></div>
        </div>
    </div>
</div>
<!--banner-->
<div class="lunbo">
    <div class="swiper-container">
        <div class="swiper-wrapper">
        <#list detail.headImage?split(";") as image>
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
                <li class="ede font15"><p>${detail.name} <i class="font12">(${detail.limitMin}人开赛)</i></p></li>
                <li class="address font14"><p>${detail.address}</p></li>
            </ul>
        </div>

        <div class="venuesInfo">
            <ul>
                <li class="moreinfo box font14">
                    <span class="tit">报名时间：</span>
                    <div class="cont boxflex">
                    ${detail.startDate}- ${detail.endDate}
                    </div>
                </li>
                <li class="moreinfo box font14">
                    <span class="tit">比赛时间：</span>
                    <div class="cont boxflex">
                    ${detail.matchDate}
                    </div>
                </li>
            </ul>
        </div>

        <div class="scrolldate yu">
            <h3 class="biaotiyong font16">赛事动态</h3>
            <div class="scrolldateCont box font12">
            <#if dynamic?exists>
                <#list dynamic as dynamic>
                    <span>
                            <a href="/cms/match/dynamicDetail?id=${dynamic.id}&matchName=${detail.name}" class="sai">
                                <div class="box">
                                    <img src="${dynamic.thumbnail}">
                                    <div>
                                        <h2 class="font16">${dynamic.title}</h2>
                                        <P class="font12 now">${dynamic.createTime}</P>
                                        <P class="font12">${dynamic.eventContext}</P>
                                    </div>
                                </div>
                            </a>
                        </span>
                </#list>
            </#if>
            </div>
        </div>
        <div class="venuesInfo">
            <h1 class="font15">赛事简介</h1>
            <ul>
                <li class="moreinfo box font14">
                    <span class="tit">${detail.matchContext}</span>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="jianju"></div>

<!-- 底部电话 -->
<div class="ditel box">
    <p class="boxflex font15">电话号码：${detail.hotline}</p>
    <a class="font15" href="tel:${detail.hotline}">立即咨询</a>
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