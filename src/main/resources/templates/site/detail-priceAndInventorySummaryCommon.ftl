<div class="scrolldate">
    <div  id="priceAndInventorySummaryCommon-list" class="scrolldateCont box font12">
			<#--	<span class="on"><a href="/site/toOneDayMerItemPrice">
					<div class="tit  font14">11月13日<i>今日</i></div>
					<div class="price font13">
						<em>￥3510起</em><i></i>
					</div>
					<P>门市价￥180</P>
				</a></span>
        <span><a href="javascript:void(0)">
					<div class="tit  font14">11月13日<i>今日</i></div>
					<div class="price font13">
						<em>￥3510起</em><i></i>
					</div>
					<P>门市价￥180</P>
				</a></span>
        <span class="gray"><a href="javascript:void(0)">
					<div class="tit  font14">11月13日<i>今日</i></div>
					<div class="noprice">不可预订</div>
				</a></span>-->
    </div>
</div>
<script src="/js/jquery.js"></script>
<script>
    $(function () {
        var mer_item_id = '${mer_item_id}';
        var mer_price_id = '${mer_price_id}';
        var cid = '${cid}';

        loadBookDays(mer_item_id,mer_price_id,cid);
    })

    //获取此场馆可预订的日期
    function loadBookDays(mer_item_id,mer_price_id,cid) {
        $.ajax({
            type: "POST",
            url: "/site/priceAndInventorySummaryCommon",
            data: {
                "mer_item_id": mer_item_id,
                "mer_price_id": mer_price_id,
                "cid": cid
            },
            success: function (result) {
                if (200 === result.code) {
                    var source = $("#priceAndInventorySummaryCommon-template").html();
                    var template = Handlebars.compile(source);
                    var html = template(result.lists);
                   $("#priceAndInventorySummaryCommon-list").append(html);
                } else {
                    alert(result.msg);
                }
            }
        })
    }
</script>


<script src="/js/handlebars-v4.0.5.js"></script>

<script id="priceAndInventorySummaryCommon-template" type="text/x-handlebars-template">
    {{#each this}}
        <span  {{#if inventory_summaray}} class="on"  {{/if}}><a href="/site/toOneDayMerItemPrice?mer_item_id={{mer_item_id}}&mer_price_id={{mer_price_id}}&cid={{cid}}&day={{priceSummaray.day}}">
					<div class="tit  font14">{{formatDay}}<i>{{week}}</i></div>
                    {{#if inventory_summaray}}
                        <div class="price font13">
                            <em>￥{{priceSummaray.min_price}}起</em><i></i>
                        </div>
                        <P>门市价￥{{priceSummaray.max_price}}</P>
                    {{else}}
                       <div class="noprice">不可预订</div>
                    {{/if}}
        </a></span>
    {{/each}}
</script>