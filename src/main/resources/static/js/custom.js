/*
 场馆列表搜索
 */
function venueSearch(merid, mer_item_ids, mer_price_id, city, q, card_type_id, radius, longitude, latitude, sort, price_sort,
                     page, pagesize, district) {

    $.ajax({
        type: "POST",
        url: "../site/search",
        data: {
            "merid": merid,
            "mer_item_ids": mer_item_ids,
            "mer_price_id": mer_price_id,
            "city": city,
            "q": q,
            "card_type_id": card_type_id,
            "radius": radius,
            "longitude": longitude,
            "sort": sort,
            "price_sort": price_sort,
            "page": page,
            "pagesize": pagesize,
            "district": district
        },
        success: function (result) {
            if (200 === result.code) {

            }
        }
    });
}