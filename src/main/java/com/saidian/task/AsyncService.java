package com.saidian.task;

import com.google.common.base.Strings;
import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.web.Btiem.BTiemService;
import com.saidian.web.bean.GoogDetail;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 任务
 */
@Component
public class AsyncService {


    @Autowired
    BTiemService bTiemService;//场馆

    @Async
    public Future<ResultBean<GoogDetail>> getMerItemListByMerId(String cardId,  String merid, String city, String q, String card_type_id,
                                                                String radius, String longitude, String latitude, String sort, String price_sort, Integer page,
                                                                Integer pagesize, Integer district) throws Exception {
        //根据merid获取priceid
        ResultBean resultBean = bTiemService.getMerPriceId(merid, cardId);
        String  mer_price_id = "";
        if (200 == resultBean.getCode()) {
            mer_price_id = new JSONObject(resultBean.getData().toString()).getString("price_id");
        }
        if (Strings.isNullOrEmpty(mer_price_id)){
            return null;
        }
        ResultBean<GoogDetail> goodsDetailResultBean = bTiemService.getMerItemList(merid, "", mer_price_id,
                HttpParams.cityId, "", "", radius, longitude, latitude, sort, "",
                null == page ? HttpParams.DEFAULT_PAGE : page, null == pagesize ? HttpParams.DEFAULT_PAGE_SIZE : pagesize, null == district ? 0 : district);
        return new AsyncResult<ResultBean<GoogDetail>>(goodsDetailResultBean);
    }
}
