<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>体育组织</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no" />
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>


<body>
<!--头部公用-->
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
            <div class="headerC boxflex">
                <p class="font17">体育组织</p>
            </div>
        </div>

    </div>
</div>
</div>
<div class="chooseTab font13">
    <div class="chooseTabCont">
        <a href="javascript:void(0)" data-tab="0">
            行政区<span></span>
        </a>
        <a href="javascript:void(0)" data-tab="1">
            排序<span></span>
        </a>

    </div>
</div>
<!--区域-->
<div class="slidemenu" data-tab="0">
    <div class="slidebg"></div>
    <div class="slidemenuCont font14 nearby">
        <a href="javascript:void(0)" data-value="" class="on">全部区域</a>
        <#--todo 测试数据待删除-->
        <a href="javascript:void(0)" data-value="1935">亭湖区</a>
    </div>
</div>
<!--排序-->
<div class="slidemenu" data-tab="0">
    <div class="slidebg"></div>
    <div class="slidemenuCont font14 sort">
        <a href="javascript:void(0)" class="on">按时间排序</a>
    </div>
</div>
<!--赛事列表-->
<div class="wrapper">
    <!--赛事列表-->
    <div class="venueslist">
        <ul id="list" class="list">
            <#--<li class="box vip">
                <div class="venuesimg"><img src="images/2.png"></div>
                <div class="venuesdetial boxflex">
                    <h2 class="font16">蓝岛大厦健身所</h2>
                    <div class="address add3 font12">
                        <span class="p">组织性质：民间组织课程时间：1月1日-1月3日课程时间：1月1日-1月3日</span>
                    </div>
                    <div class="address add3 font12">
                        <span class="p">最新简介：直门西直门西直门西直门西直直门西直门西直门西直门西直</span>
                    </div>
                    <div class="address add3 font12">
                        <span class="p">最新动态：2015-10-10课程时间：1月1日-1月3日课程时间：1月1日-1月3日</span>
                    </div>
                </div>
            </li>-->
        </ul>

    </div>
    <!--赛事列表结束-->

</div>
<!--城市地位-->



<!--地图图标-->
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script>
    $(function(){

        // 获取行政区字段
        var area = $('.nearby .on').data('value');
        // 获取排序字段
        var order = '';
        // var order = $('.sort .on').data('value');

        // 初始化加载数据
        search(area, order, 1, 15);

        /*var xmlhttp=new XMLHttpRequest();
        if (xmlhttp.readyState <4 || xmlhttp.status != 200){
            $(".loading").hide();
        }
        document.onreadystatechange = completeLoading;
        function completeLoading() {
            if (document.readyState == "complete") {
                $(".loading").hide();
            }
        }*/

        /*筛选条件*/
        /*$(".slidemenuCont a").on("click",function(){
            var ix = $(".slidemenuCont a").index($(this));
        })*/

        $(".chooseTab a").tabEve({
            cls:".slidemenu",
            selected:"on",
            empty:"gray",
            typing:"slidemenu"
        })

        //区域
        $("div.nearby a").click(function(){

            //替换title
            //$("div.chooseTabCont a.geo").html($(this).html() + '<span></span>');
            var ind = $(this).parent().parent().data('tab');
            if ($(this).text() === '全部区域') {
                $(this).text() = '行政区';
            }
            $('div.chooseTabCont a').eq(ind).html($(this).text() + '<span></span>');
            $("body").click();

            $("div.nearby").children('a').each(function(index, item){
                $(item).removeClass('on');
            });

            $(this).addClass('on');

            //scope.district = $(this).attr('data-tag');

            //againReload();
        });

        //排序
        $("div.sort a").click(function(){

            //替换title
            //$("div.chooseTabCont a.geo").html($(this).html() + '<span></span>');
            if ($(this).text() === '按照时间排序') {
                $(this).text() = '排序';
            }
            $('div.chooseTabCont a').eq(ind).html($(this).text() + '<span></span>');

            $("body").click();

            $("div.sort").children('a').each(function(index, item){
                $(item).removeClass('on');
            });

            $(this).addClass('on');

            //scope.district = $(this).attr('data-tag');

            //againReload();
        });

        //距离

        /*$("div.distance a").click(function(){

            //替换title
            $("div.chooseTabCont a.geo").html($(this).html() + '<span></span>');

            $("body").click();

            $("div.distance").children('a').each(function(index, item){
                $(item).removeClass('on');
            });

            $(this).addClass('on');

            scope.district = $(this).attr('data-tag');

            againReload();
        });*/

    })
    function search(area, order, page, rows) {
        $.ajax({
            type: "POST",
            url: "/cms/train/list/yc",
            data: {
                "district": area,
                "time_sort": order,
                "page": page,
                "rows": rows
            },
            success: function (resultData) {
                console.info(resultData);

                resultHandler(resultData);
            }
        });
    }

    function resultHandler(result) {
        if (200 === result.code) {
            var source = $("#template").html();
            var template = Handlebars.compile(source);
            $("#list").append(html);
        } else {
            alert(result.data);
        }
    }
</script>
<script src="/js/handlebars-v4.0.5.js"></script>

<script id="template" type="text/x-handlebars-template">
    {{#each this}}
    <li class="box vip">
        <div class="venuesimg"><img src="{{icon}}"></div>
        <a href="/cms/train//toDetail?id={{id}}">
            <div class="venuesdetial boxflex">
                <h2 class="font16">{{name}}</h2>
                <div class="address add3 font12">
                    <span class="p">组织名称：{{type}}课程时间:{{name}}</span>
                </div>
                <div class="address add3 font12">
                    <span class="p">最新简介：{{name}}</span>
                </div>
            </div>
        </a>
    </li>
    {{/each}}
</script>
</body>
</html>