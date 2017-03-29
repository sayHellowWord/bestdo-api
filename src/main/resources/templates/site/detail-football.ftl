<div class="scrolldate">
    <div id="priceAndInventorySummaryCommon-list" class="scrolldateCont box font12">

    </div>
</div>
<script src="/js/jquery.js"></script>
<script>
    $(function () {
        var mer_item_id = '${mer_item_id}';
        var mer_price_id = '${mer_price_id}';
        var cid = '${cid}';

        loadBookDays(mer_item_id, mer_price_id, cid);
    })

    //获取此场馆可预订的日期
    function loadBookDays(mer_item_id, mer_price_id, cid) {
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

                    Handlebars.registerHelper('if_price', function (value, options) {
                        return Math.floor(value) / 100;
                    });

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
    <span {{#if inventory_summaray}} class="on" {{/if}}>
    {{#if inventory_summaray}}
    <a href="/site/toOneDayMerItemPricefootball?mer_item_id={{mer_item_id}}&mer_price_id={{mer_price_id}}&cid={{cid}}&day={{priceSummaray.day}}">
        {{else}}
        <a href="javascript:void(0)">
            {{/if}}
            <div class="tit  font14">{{formatDay}}<i>{{week}}</i></div>
            {{#if inventory_summaray}}
            <div class="price font13">
                <em>￥{{#if_price priceSummaray.min_price}} {{priceSummaray.min_price}} {{/if_price}}起</em><i></i>
            </div>
            <P>门市价￥{{#if_price priceSummaray.min_price}} {{priceSummaray.max_price}} {{/if_price}}</P>
            {{else}}
            <div class="noprice">不可预订</div>
            {{/if}}
        </a></span>
        {{/each}}
</script>
