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


<body class="">
    <div class="seeMask">
    </div>
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

            $('#maskSwiper').remove();

            moveDeleteEvent($('.swiper-slide'),touchTo);

            $('.swiper-slide').off('touchend',MaskHide);

            $('.hideMask').off('touchend',MaskHide);
        }

        function setMargin() {
            var wrapper = $('.swiper-wrapper').find('img');
            for(var i = 0 ; i < wrapper.length; i++ ){
                var heigt = $('.swiper-wrapper').css('height');
                heigt = parseInt(heigt.replace('px',''));
                var imgH = wrapper.eq(i).css('height');
                imgH = parseInt(imgH.replace('px',''));
                heigt = (heigt - imgH)/2;
                wrapper.eq(i).css('margin-top',heigt);
            }
        }

        function touchTo()
        {

            $('.seeMask').show();

            $('body').addClass('overflow');

            var imgSwiper = '<div id="maskSwiper" class="swiper-container swiper-container-horizontal">'+
                                '<div  class="swiper-wrapper">'+
                                '</div>'+
                            '</div>';
            $('.seeMask').append(imgSwiper);

            var divBac = $('.swiper-wrapper .swiper-slide');

            var urlArr = [];

            for(var i = 0 ; i < divBac.length ; i++){
                var dataJson = {};
                var bac = divBac.eq(i).css('background-image');
                var attr = divBac.eq(i).attr('data-swiper-slide-index');
                var clas = divBac.eq(i).attr('class');
                dataJson.src = bac;
                dataJson.attr = attr;
                dataJson.clas = clas;
                urlArr.push(dataJson);
            }

            for(var i = 0 ; i < urlArr.length ; i++){
                var imgSild = '<div class="'+urlArr[i].clas+'" data-swiper-slide-index="'+urlArr[i].attr+'">'+
                              '</div>';
                $('#maskSwiper .swiper-wrapper').append(imgSild);
            }

            for(var i = 0 ; i <urlArr.length; i++){
                $('#maskSwiper .swiper-wrapper .swiper-slide').eq(i).css('background-image',urlArr[i].src);
            }

            setMargin();

            var thisIndex = $(this).attr('data-swiper-slide-index');

            if (thisIndex !== undefined) {
                var mySwiper = new Swiper('#maskSwiper', {
                    loop: true,
                    initialSlide :parseInt(thisIndex)
                })
            }
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
            return src + '?v=1.1';
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
