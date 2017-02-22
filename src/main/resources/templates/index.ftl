<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>百动盐城</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no"/>
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>

    <link rel="stylesheet" href="/css/swiper-3.2.7.min.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>

</head>


<body style="background:#ffffff;">

<!--banner-->
<div class="lunbo">
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <div class="swiper-slide" style="background-image: url(images/bg4.jpg);"><a href="/site/index"  style="display:block;height:100%;z-index:9999;"></a>
            </div>
            <div class="swiper-slide" style="background-image: url(images/bg1.jpg);"><a href="/cms/exercisehoop/list" style="display:block;height:100%;z-index:9999;"></a>
            </div>
            <div class="swiper-slide" style="background-image: url(images/bg3.jpg);"><a href="/cms/train/list" style="display:block;height:100%;z-index:9999;"></a>
            </div>
            <div class="swiper-slide" style="background-image: url(images/bg2.jpg);"><a href="/cms/match/list"  style="display:block;height:100%;z-index:9999;"></a>
            </div>
            <div class="swiper-slide" style="background-image: url(images/bg5.jpg);"><a href="/cms/guidance/list" style="display:block;height:100%;z-index:9999;"></a>
            </div>
        </div>
        <!-- 如果需要分页器 -->
        <div class="swiper-pagination"></div>
    </div>
</div>

<!--分类-->
<div class="sportitem font13" style="margin-top:1rem;">
    <div class="sportitemcont www2">
        <div><a href="/site/index" class="i1"><span class="iconfont font16"></span><i></i>场馆预定</a></div>
        <div><a href="/cms/exercisehoop/list" class="i2"><span class="iconfont font16"></span><i></i>十分钟健身圈</a></div>
        <div><a href="/cms/match/list" class="i3"><span class="iconfont font16"></span><i></i>体育赛事</a></div>
        <div><a href="/cms/train/list" class="i4"><span class="iconfont font16"></span><i></i>体育培训</a></div>
        <div><a href="/cms/guidance/list" class="i5"><span class="iconfont font16"></span><i></i>体测指导站</a></div>
        <div><a href="/cms/organization/list" class="i6"><span class="iconfont font16"></span><i></i>体育组织</a></div>
        <div><a href="/cms/information/list" class="i7"><span class="iconfont font16"></span><i></i>体育信息</a></div>
        <div><a href="/contactus" class="i8"><span class="iconfont font16"></span><i></i>联系我们</a></div>
    </div>
</div>

<!-- 标语 -->
<div class="biaoyu">
    <img src="/images/home/slogan.png">
</div>

<!--导航-->
<div id="nav">
    <div class="nav font12">
        <div class="navCont box">
            <a href="/" class="boxflex on"><span></span>首页</a>
            <a href="/odds/index" class="boxflex"><span></span>特惠</a>
            <a href="/user/index" class="boxflex"><span></span>我的</a>
        </div>
    </div>
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