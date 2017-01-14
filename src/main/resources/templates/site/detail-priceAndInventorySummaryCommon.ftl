<div class="scrolldate">
    <div  id="priceAndInventorySummaryCommon-list" class="scrolldateCont box font12">
				<span class="on"><a href="/site/toOneDayMerItemPrice">
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

        loadBookDays(mer_item_id,mer_price_id);

        //订场日期选中
        $(".scrolldate").on("click", "#priceAndInventorySummaryCommon-list span", function () {
            alert("!11");
            //下面所有a移除 class="on"
     /*       $("#showBookDays-list a").removeClass("on");
            $(this).find("a").addClass("on");
            var date = $(this).data("day");
            predeterminedPriceInformation(mer_item_id, mer_price_id, date);*/
        })

    })

    //获取此场馆可预订的日期
    function loadBookDays(mer_item_id,mer_price_id) {
        $.ajax({
            type: "POST",
            url: "/siteinfo/priceAndInventorySummaryCommon",
            data: {
                "mer_item_id": mer_item_id,
                "mer_price_id": mer_price_id
            },
            success: function (result) {
                if (200 === result.code) {
                    console.info(result);
                    var source = $("#priceAndInventorySummaryCommon-template").html();
                    var template = Handlebars.compile(source);
                    var html = template(result.lists);
                    $("#priceAndInventorySummaryCommon-list").append(html);
                    //第一个元素点击，相当于第一个选中
                    $("#showBookDays-list span:first").click();
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
    <#-- <span><a href="javascript:void(0)"><div class="tit  font14">11月13日<i>今日</i></div><div class="price font13"><em>￥3510起</em><i></i></div><P>门市价￥180</P></a></span>
    -->
    {{#if name}}
    <span class="on"><a style='height:60px;'> <div class="tit  font14">{{show_date}}<i>周{{show_week}}</i></div><div
            class="price font13"><em>￥{{min_price}}/100}起</em><i></i></div>{{#if min_price}}<P>门市价{{min_price}}/100}起</P>{{/if}}</a></span>
    {{else}}
    <span class="gray"><a href="javascript:void(0)"><div class="tit  font14">{{show_date}}<i>周{{show_week}}</i></div><div
            class="noprice">不可预订</div></a></span>
    {{/if}}

    {{/each}}

    <#--  {if !empty($ps['price_summaray']) && $ps['inventory_summaray'] > 0}
      <span class="on">
                                  <a style='height:60px;'
                                     href="{url action='orders/base/createOrder'}?mer_item_id={$mer_item_id}&mer_id={$mer_item['merid']}&book_day={$day}&cid={$mer_item['cid']}">
                                      <div class="tit  font14">{$ps['show_date']}<i>周{$ps['show_week']}</i></div>
                                      <div class="price font13">
                                          <em>￥{$ps['price_summaray']['min_price']/100}起</em><i></i>
                                      </div>
                                      {if isset($show_price_summarays[$day]['price_summaray']['min_price'])}<P>门市价{$show_price_summarays[$day]['price_summaray']['min_price']/100}起</P>{/if}
                                  </a>
                              </span>
      {else}
      <span class="gray">
                                  <a href="javascript:void(0)">
                                     <div class="tit  font14">{$ps['show_date']}<i>周{$ps['show_week']}</i></div>
                                     <div class="noprice">不可预订</div>
                                  </a>
                              </span>
      {/if}-->
</script>