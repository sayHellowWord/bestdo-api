package com.saidian.web.platform;

import com.saidian.bean.ResultBean;
import com.saidian.config.AccessServices;
import com.saidian.config.HttpParams;
import com.saidian.utils.HttpResultUtil;
import com.saidian.utils.HttpUtil;
import com.saidian.web.bean.GoogDetail;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/4.
 */
@Service
public class PlatformService {

    private String REGION_GET_CHILDREN = "region/api/getChildren";

   /* public ResultBean regionGetChildren(String region_id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("region_id", region_id);

        String result = HttpUtil.doPost(HttpParams.url + REGION_GET_CHILDREN, jsonObject.toString(), HttpParams.key);

        System.out.println(result);

        ResultBean<GoogDetail> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {
            System.out.println(resultBean.getData());
        }
        return resultBean;
    }*/

}
