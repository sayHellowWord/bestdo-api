<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>十分钟健身圈</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no"/>
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>


<body>
<!--头部公用-->
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
            <div class="headerC boxflex">
                <div class="search box font14">
                    <div class="searchInput boxflex">
                        <span class="icon"></span>
                        <input type="text" class="font14" placeholder="输入场馆名称、场地名称">
                    </div>
                    <a href="javascript:void(0)" class="btn">搜索</a>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="chooseTab www25 font13">
    <div class="chooseTabCont">
        <a href="javascript:void(0)" data-tab="0" class="gray">
            区域<span></span>
        </a>
        <a href="javascript:void(0)" data-tab="1">
            排序<span></span>
        </a>
        <a href="javascript:void(0)" data-tab="2">
            距离<span></span>
        </a>
    </div>
</div>
<!--区域-->
<div class="slidemenu">
    <div class="slidebg"></div>
    <div class="slidemenuCont font14">
        <a href="javascript:void(0)" class="on">不限距离</a>
        <a href="javascript:void(0)">附近1km</a>
        <a href="javascript:void(0)">附近2km</a>
        <a href="javascript:void(0)">附近5km</a>
        <a href="javascript:void(0)">附近10km</a>
    </div>
</div>
<!--排序-->
<div class="slidemenu">
    <div class="slidebg"></div>
    <div class="slidemenuCont font14">
        <a href="javascript:void(0)" class="on">不限距离</a>
        <a href="javascript:void(0)">距离最近</a>
        <a href="javascript:void(0)">优惠最多</a>
        <a href="javascript:void(0)">附近5km</a>
        <a href="javascript:void(0)">附近10km</a>
    </div>
</div>
<!--距离-->
<div class="slidemenu">
    <div class="slidebg"></div>
    <div class="slidemenuCont font14">
        <a href="javascript:void(0)" class="on">不限距离</a>
        <a href="javascript:void(0)">距离最近</a>
        <a href="javascript:void(0)">优惠最多</a>
        <a href="javascript:void(0)">附近5km</a>
        <a href="javascript:void(0)">附近10km</a>
    </div>
</div>
<!--场馆列表-->
<div class="wrapper">
    <!--场馆列表-->
    <div class="venueslist">
        <ul class="list">
            <li class="box vip">
                <div class="venuesimg"><img src="images/2.png"></div>
                <div class="venuesdetial boxflex">
                    <h2 class="font16">蓝岛大厦健身所 </h2>

                    <div class="address add2 font12">
                        <span class="d">1.0km</span>

                        <span class="p">西直门西直门西直门西直门西直门西直门西直门西直门西直门</span>
                    </div>
                </div>
            </li>
            <li class="box vip">
                <div class="venuesimg"><img src="images/2.png"></div>
                <div class="venuesdetial boxflex">
                    <h2 class="font16">蓝岛大厦健身所 </h2>

                    <div class="address add2 font12">
                        <span class="d">1.0km</span>

                        <span class="p">西直门西直门西直门西直门西直门西直门西直门西直门西直门</span>
                    </div>
                </div>
            </li>
            <li class="box vip">
                <div class="venuesimg"><img src="images/2.png"></div>
                <div class="venuesdetial boxflex">
                    <h2 class="font16">蓝岛大厦健身所 </h2>

                    <div class="address add2 font12">
                        <span class="d">1.0km</span>

                        <span class="p">西直门西直门西直门西直门西直门西直门西直门西直门西直门</span>
                    </div>
                </div>
            </li>
            <li class="box vip">
                <div class="venuesimg"><img src="images/2.png"></div>
                <div class="venuesdetial boxflex">
                    <h2 class="font16">蓝岛大厦健身所 </h2>

                    <div class="address add2 font12">
                        <span class="d">1.0km</span>

                        <span class="p">西直门西直门西直门西直门西直门西直门西直门西直门西直门</span>
                    </div>
                </div>
            </li>
            <li class="box vip">
                <div class="venuesimg"><img src="images/2.png"></div>
                <div class="venuesdetial boxflex">
                    <h2 class="font16">蓝岛大厦健身所 </h2>

                    <div class="address add2 font12">
                        <span class="d">1.0km</span>

                        <span class="p">西直门西直门西直门西直门西直门西直门西直门西直门西直门</span>
                    </div>
                </div>
            </li>


        </ul>
        <div class="load-container font12">
            <div class="loader"></div>
            数据加载中...
        </div>
    </div>
    <!--场馆列表结束-->
    <div class="empty">
        <div class="icon"></div>
        <p class="font14">暂无相关相关场地信息</p>
    </div>
</div>
<!--城市地位-->

<div class="loading">
    <div class="loadCont"></div>
    <div class="loadBg"></div>
</div>


<!--地图图标-->
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script>
    $(function () {
        var xmlhttp = new XMLHttpRequest();
        if (xmlhttp.readyState < 4 || xmlhttp.status != 200) {
            $(".loading").hide();
        }
        document.onreadystatechange = completeLoading;
        function completeLoading() {
            if (document.readyState == "complete") {
                $(".loading").hide();
            }
        }

        /*筛选条件*/
        $(".chooseTab a").tabEve({
            cls: ".slidemenu",
            selected: "on",
            empty: "gray",
            typing: "slidemenu"
        })
        /*下拉加载更多*/

        var i = 0;
        $(".list").loadmore({
            getData: function (obj) {
                var sh = $(window).height();
                var ch = obj.height();
                var st = $(window).scrollTop();
                if (st > ch - sh && $(".load-container").is(":hidden")) {
                    $(".load-container").show();
                    i++;
                    var str = "<li>" + i + "</li>";
                    //return str;
                }
                else {
                    return false;
                }
            }
        });

        var beforeST = $(window).scrollTop();
        var i = 0;
        $(window).scroll(function () {
            var sh = $(window).height();
            var ch = $(".venueslist").height();
            var afterST = $(window).scrollTop();
            var t;
            var str = "";
            var x;
            var a
            var t2 = setInterval(function () {
                a = $("body").attr("a");
            }, 1);
            if (afterST - beforeST > 0 && afterST > ch - sh) {
                $("body").attr("a", "down");
                $(".load-container").css("visibility", "visible");
                t = setTimeout(function () {
                    i++;
                    $(".load-container").css("visibility", "hidden");
                    $("body").attr("a", "0");
                    if (a != 0) {
                        str += '<li>' + i + '</li>';
                        $(".list").append(str);
                    }
                }, 3000)
            }
            if (st > ch - sh) {
                $(".load-container").css("visibility", "visible");
                t = setTimeout(function () {
                    $(".load-container").css("visibility", "hidden");
                }, 3000)
            }
            beforeST = afterST;
        })
    })
</script>
</body>
</html>