<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <title>体育信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
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
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex">
                <p class="font17">体育信息</p>
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
</div>



<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script>
    // 初始化加载数据
    search(1, 15);

    //只负责查询和追加数据，如果需要刷新页面（如查询）请执行前自己情况list数据
    function search(page, rows) {
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
            Handlebars.registerHelper('if_showImg', function(value, options) {
                return value.split(';')[0];
            });
            var html = template(result.data);
            $("#list").append(html);
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
            <img src="{{#if_showImg icon}} {{icon}} {{/if_showImg}}">
        </div>
        <a href="/cms/information//toDetail?id={{id}}">
            <div class="bodydetail boxflex">
                <h2 class="font16">{{name}}</h2>
                <div class="address font12">
                    <span class="p">盐城体育局 2015-12-12</span>
                </div>
            </div>
        </a>
    </li>
    {{/each}}
</script>
</body>
</html>