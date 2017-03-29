<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>我的</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!--忽略页面中的数字识别为电话号码-->
<meta name="format-detection" content="telephone=no" />
<!--忽略页面中的邮箱格式为邮箱-->
<meta name="format-detection" content="email=no"/>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>


<body>
<!--头部公用-->
<div id="header">
	<div class="header">
		<div class="headerCont box">
			<div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
			<div class="headerC boxflex"><p class="font17">我的</p></div>
			
		</div>
	</div>
</div>

<div class="wrapper center vip">
	<div class="centerTop font17">
		<div class="centerTopCont box">
			<div class="imgtxt"><img src="../images/2.png" ></div>
			<div class="cont box2 boxflex">
                <a href="/user/personalprofile"><p>${name!}</p></a>
			</div>
		</div>
	</div>
	<ul class="centerOrder">
		<li class="all font14"><a href="/order/orderLists?status=9">
			<span>全部订单</span>订单
		</a></li>
		<li class="part font12">
			<a href="/order/orderLists?status=0"><span></span><p>待付款</p></a>
			<a href="/order/orderLists?status=5"><span></span><p>确认中</p></a>
			<a href="/order/orderLists?status=3"><span></span><p>待下场</p></a>
			<a href="/order/orderLists?status=7"><span></span><p>已结束</p></a>
		</li>
	</ul>
	<ul class="centerOrder centerOther">
		<#--<li class="all i1 font14"><a href="/user/personalprofile">
			<span></span>设置
		</a></li>-->
            <li class="all i1 font14"><a href="/user/loginout">
                <#--<span></span>-->退出
            </a></li>
</ul>

</div>
<!--导航-->
<div id="nav">
	<div class="nav font12">
		<div class="navCont box">
            <a href="/" class="boxflex"><span></span>首页</a>
            <a href="/odds/index" class="boxflex"><span></span>特惠</a>
            <a href="/user/index" class="boxflex on"><span></span>我的</a>
		</div>
	</div>
</div>


</body>
</html>
