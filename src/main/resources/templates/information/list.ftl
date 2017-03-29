<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>体育信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
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
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex">
                <p class="font17">体育信息</p>
                <#--<div class="search box font14">
                    <div class="searchInput boxflex">
                        <span class="icon"></span>
                        <input id="search-keyword" type="text" class="font14" placeholder="输入信息名称或内容">
                    </div>
                    <a id="btn-search" href="javascript:void(0)" class="btn">搜索</a>
                </div>-->
            </div>
        </div>
    </div>
</div>
</div>

<!--体测列表-->
<div class="wrapper">
    <div class="bodylist">
        <ul id="list" class="list2 list3">

        </ul>
    </div>
    <!--场馆列表结束-->
    <div id="no-result" class="empty">
        <div class="icon"></div>
        <p class="font14">暂无相关相关场地信息</p>
    </div>
</div>


<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script>
    // 初始化加载数据
    search(1, 15);

    //只负责查询和追加数据，如果需要刷新页面（如查询）请执行前自己情况list数据
    function search(page, rows) {
        //没有结果提示隐藏
        $("#no-result").hide();
        $.ajax({
            type: "POST",
            url: "/cms/information/list/cms",
            data: {
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

            Handlebars.registerHelper('if_showImg', function (value, options) {
                return value.split(';')[0];
            });

            Handlebars.registerHelper("prettifyDate", function (timestamp) {
                var da = new Date(timestamp);
                var year = da.getFullYear();
                var month = da.getMonth() + 1;
                var date = da.getDate();
                return [year, month, date].join('-');
            });

            //当前未分页所以这样做 TODO
            if (result.data.length > 0) {
                var html = template(result.data);
                $("#list").append(html);
            } else {
                $("#no-result").show();
            }

        } else {
            alert(result.data);
        }
    }
</script>

<script src="/js/handlebars-v4.0.5.js"></script>

<script id="template" type="text/x-handlebars-template">
    {{#each this}}
    <li class="box">
        <div class="bodyimg">
            <a href="/cms/information//toDetail?id={{id}}">
                <img height="100%" src="{{#if_showImg icon}} {{icon}} {{/if_showImg}}">
            </a>
        </div>
        <div class="bodydetail boxflex">
            <a href="/cms/information//toDetail?id={{id}}">
                <h2 class="font16" id="normal">{{title}}</h2>
                <div class="address font12" id="martop7">
                    <span class="p">{{author}} {{prettifyDate createDate}}</span>
                </div>
            </a>
        </div>
    </li>
    {{/each}}
</script>
</body>
</html>
