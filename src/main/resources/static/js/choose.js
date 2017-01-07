$(function(){
	/*场片名称随场片滚动*/
	var w = $(".blockinfo").width();
	$(".blockinfoTit").width(w);
	$(".blockinfoCont").scroll(function(){
		var sl = $(".blockinfoCont").scrollLeft();
		$(".blockinfoTitCont").css("margin-left",-sl);
	})
})