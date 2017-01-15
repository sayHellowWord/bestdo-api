<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <title>教练介绍</title>
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
            <div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">教练介绍</p></div>
        </div>
    </div>
</div>

<div class="wrapper center vip">
    <div class="centerTop font17 no">
        <div class="centerTopCont box">
            <div class="imgtxt"><img src="images/2.png" ></div>
            <div class="cont box2 boxflex">
                <p>${name}</p>
                <p class="font12 wit">${rank}</p>
            </div>
        </div>
    </div>
    <div class="detailinfo">

        <div class="venuesInfo">
            <ul>

                <li class="moreinfo box font14">
                    <span class="tit">教练等级：</span>
                    <div class="cont boxflex">
                        ${rank}
                    </div>
                </li>
                <li class="moreinfo box font14">
                    <span class="tit">教育背景：</span>
                    <div class="cont boxflex">
                        ${educationalBg}
                    </div>
                </li>
                <li class="moreinfo box font14">
                    <span class="tit">个人简介：</span>
                    <div class="cont boxflex">
                        ${introduction}
                    </div>
                </li>
            </ul>
        </div>

        <div class="venuesInfo">
            <h1 class="font15">详细介绍</h1>
            <ul>
                <li class="moreinfo box font14">
                    <span class="tit">${description}</span>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>