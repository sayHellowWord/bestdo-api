<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <title>${news.title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no" />
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>
    <link rel="stylesheet" href="/css/swiper-3.2.7.min.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>

<body style="background:#fff;" class="">
    <div class="seeMask">
        <div class="hideMask Mtop"></div>
        <div class="hideMask Mbottom"></div>
    </div>
<!--头部公用-->
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">新闻详情</p></div>
        </div>
    </div>
</div>



<!-- 赛事具体内容 -->
<div class="saishi">
    <p class="font20">${news.title}</p>
    <p class="font14" style="color:#666">${news.subTitle}</p>
    <span class="font12" id="Sinsert">${news.createDate?string('yyyy年MM月dd HH:mm:ss')}&nbsp&nbsp${news.author}</span>
    <!--banner-->
    <div class="lunbo">
        <div class="swiper-container">
            <div class="swiper-wrapper">
            <#-- <div class="swiper-slide" style="background-image: url(/images/bg1.png);"></div>
             <div class="swiper-slide" style="background-image: url(/images/bg2.jpg);"></div>-->
            <#list news.icon?split(";") as image>
                <#if image?? && image !="" && image?length gt 2>
                <#-- <img src="${image}">-->
                    <div class="swiper-slide" style="background-image: url(${image});"></div>
                </#if>
            </#list>
            </div>
        </div>
    </div>

    <div>
    ${news.description}
    </div>
</div>
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/myproject.js"></script>
<script src="/js/swiper-3.2.7.min.js"></script>
<script>
    <#assign  shortIconArr = news.icon?split(";")/>
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

            $('#Sinsert').after($('.lunbo'));

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
