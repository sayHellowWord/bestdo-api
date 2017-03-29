<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>监测站点介绍</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no"/>
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>
    <link rel="stylesheet" href="/css/swiper-3.2.7.min.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>


<body class="">
    <div class="seeMask">
        <div class="hideMask Mtop"></div>
        <div class="hideMask Mbottom"></div>
    </div>
<!--头部公用-->
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">${phyContext.name!''}</p></div>
        </div>
    </div>
</div>
<!--banner-->
<div class="lunbo">
    <div class="swiper-container">
        <div class="swiper-wrapper">
        <#list phyContext.headUrl?split(";") as image>
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
                <li class="tou font15"><p>${phyContext.name!''}</p></li>
                <li class="address font15">
                    <a href="/cms/exercisehoop/map?name=${phyContext.name!''}&address=${phyContext.address}&latitude=${phyContext.bdLatitude}&longitude=${phyContext.bdLongitude}">
                        <p>${phyContext.address}</p></a></li>
                <li class="date font15"><p>
                    工作日:${phyContext.phyDate}
                 <#--   周末:${phyContext.restDayStartHour}:${phyContext.restDayStartMin}--${phyContext.restDayEndHour}:${phyContext.restDayEndMin}--></p>
                </li>
            </ul>
        </div>

        <div class="venuesInfo">
            <h1 class="font15">监测站点介绍</h1>
            <ul>
                <li id="maxheit" class="moreinfo box font14">
                    <span class="tit">${phyContext.content}</span>
                </li>
            </ul>
        </div>
    </div>

</div>
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/myproject.js"></script>
<script src="/js/swiper-3.2.7.min.js"></script>
<script>
    <#assign  shortIconArr = phyContext.headUrl?split(";")/>
    <#if shortIconArr?size gt 1>
    var mySwiper = new Swiper('.swiper-container', {
        loop: true,
        autoplay: 3000,
        autoplayDisableOnInteraction: false,
        // 如果需要分页器
        pagination: '.swiper-pagination',
    })
    </#if>
</script>
<script type="text/javascript">
    $(function() {
        var swiperLi = $('.swiper-slide');
        function MaskHide()
        {
            $('.seeMask').hide();

            $('body').removeClass('overflow');

            $('.lunbo').css('margin-top','0%');

            $('#header').after($('.lunbo'));

            moveDeleteEvent($('.swiper-slide'),touchTo);

            $('.swiper-slide').off('touchend',MaskHide);

            $('.hideMask').off('touchend',MaskHide);
        }

        function touchTo()
        {

            $('.seeMask').show();

            $('body').addClass('overflow');

            $('.Mtop').after($('.lunbo'));

            moveDeleteEvent($('.swiper-slide'),MaskHide);

            moveDeleteEvent($('.hideMask'),MaskHide);

            $('.swiper-slide').off('touchend',touchTo);

            var heigt = $('.seeMask').css('height');

            heigt = parseInt(heigt.replace('px',''));

            heigt = (heigt-200)/2;

            $('.hideMask').css('height',heigt+'px');
        }

        function url2src(url)
        {
            src = url.replace('url("','').replace('")','');
            return src;
        }

        function moveDeleteEvent(obj,fn)
        {
            function addtouchend()
            {
                obj.off('touchend',fn);
                obj.on('touchend',fn);
            }

            function touchmo()
            {
                obj.off('touchend',fn);
                obj.off('touchend',addtouchend);
                obj.on('touchend',addtouchend);
            }
            obj.off('touchend',fn);
            obj.on('touchend',fn);
            obj.on('touchmove',touchmo);
        }

        moveDeleteEvent($('.swiper-slide'),touchTo);
    })
</script>
</body>
</html>
