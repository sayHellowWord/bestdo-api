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
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>


<body style="background:#fff;">
<!--头部公用-->
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">${news.title}</p></div>
        </div>
    </div>
</div>

<!-- 赛事具体内容 -->
<div class="saishi">
    <p class="font20">${news.subTitle}</p>
    <span class="font12">${news.createDate!}&nbsp&nbsp${news.author}</span>
    <#list news.icon?split(";") as image>
        <img src="${image}">
    </#list>
</div>

</body>
</html>