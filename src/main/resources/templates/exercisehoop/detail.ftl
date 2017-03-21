<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <title>十分钟详情</title>
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
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">${tenMinSite.name}</p></div>

        </div>
    </div>
</div>
<!--banner-->
<div class="lunbo">
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <#list tenMinSite.pics?split(";") as image>
                <#if image?? && image !="" && image?length gt 2>
                    <div class="swiper-slide" style="background-image: url(${image});"></div>
                </#if>
            </#list>
        </div>
        <div class="textready">
            <span class="left font15">${tenMinSite.name}</span>
            <span class="right font12">${tenMinSite.secondType}</span>
        </div>
    </div>
</div>


<div class="wrapper">
    <div class="detailinfo">

        <!--场馆信息-->
        <div class="venuesInfo">
            <ul>
                <li class="address font15"><p><a href="/cms/exercisehoop/map?name=${tenMinSite.name}&address=${tenMinSite.address}&latitude=${tenMinSite.bdLatitude}&longitude=${tenMinSite.bdLongitude}">${tenMinSite.address}</a></p></li>
              <#--  <li class="date font15">
                    <p>
                        工作日：${tenMinSite.workDayStartHour}: ${tenMinSite.workDayStartMin}--${tenMinSite.workDayEndHour}: ${tenMinSite.workDayEndMin}
                        周末：${tenMinSite.restDayStartHour}: ${tenMinSite.restDayStartMin}--${tenMinSite.restDayEndHour}: ${tenMinSite.restDayEndMin}
                    </p></li>-->
            </ul>
        </div>


        <div class="venuesInfo">
            <h1 class="font15">健身圈介绍</h1>
            <ul>
                <li class="moreinfo box font14">
                    <span class="tit">${tenMinSite.content}</span>
                </li>
            </ul>
        </div>
    </div>

</div>
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/myproject.js"></script>
<script src="/js/swiper-3.2.7.min.js"></script>
<script>
    <#assign  shortIconArr = tenMinSite.pics?split(";")/>
    <#if shortIconArr?size gt 1>
    var mySwiper = new Swiper('.swiper-container', {
        loop: true,
        autoplay: 3000,
        // 如果需要分页器
        pagination: '.swiper-pagination',
    })
    </#if>
</script>
</body>
</html>