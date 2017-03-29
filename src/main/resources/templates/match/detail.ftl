<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>${detail.name}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no"/>
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>
    <link rel="stylesheet" href="/css/swiper-3.2.7.min.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>


<body>
    <div class="seeMask">
        <div class="hideMask Mtop"></div>
        <div class="hideMask Mbottom"></div>
    </div>
<!--头部公用-->
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">${detail.name}</p></div>
        </div>
    </div>
</div>
<!--banner-->
<div class="lunbo">
    <div class="swiper-container">
        <div class="swiper-wrapper">
        <#list detail.headImage?split(";") as image>
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
                <li class="ede font15"><p>${detail.name} <i class="font12">(${detail.limitMin}人开赛)</i></p></li>
                <li class="address font14"><p id="Noafter">${detail.address}</p></li>
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
        <div class="venuesInfo">
            <h1 class="font15">赛事简介</h1>
            <ul>
                <li id="maxheit" class="moreinfo box font14">
                    <span class="tit">${detail.matchContext}</span>
                </li>
            </ul>
        </div>
        <div class="scrolldate yu">
            <h3 class="biaotiyong font16">赛事动态</h3>
            <div class="scrolldateCont box font12">
            <#if dynamics?exists>
                <#list dynamics as dynamic>
                    <span>
                        <a href="/cms/match/dynamicDetail?id=${dynamic.id}&matchName=${detail.name}" class="sai">
                            <div class="box">
                                <img src="${dynamic.thumbnail?replace(";","")}">
                                <div>
                                    <h2 class="font16">${dynamic.title}</h2>
                                    <P class="font12 now">${dynamic.createTimeStr}</P>
                                  <#--  <P class="font12">${dynamic.eventContext}</P>-->
                                </div>
                            </div>
                        </a>
                    </span>
                </#list>
            </#if>
            </div>
        </div>
    </div>
</div>

<div class="jianju"></div>
<#if detail.hotline??>
    <!-- 底部电话 -->
    <div class="ditel box">
        <p class="boxflex font15">电话号码：${detail.hotline}</p>
        <a class="font15" href="tel:${detail.hotline}">立即咨询</a>
    </div>
</#if>
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/myproject.js"></script>
<script src="/js/swiper-3.2.7.min.js"></script>
<script>
    <#assign  shortIconArr = detail.headImage?split(";")/>
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
