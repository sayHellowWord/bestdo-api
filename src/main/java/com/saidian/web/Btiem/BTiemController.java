package com.saidian.web.Btiem;

import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/1/5.
 */
@RequestMapping(value = "btiem")
@RestController
public class BTiemController {

    @Autowired
    BTiemService bTiemService;

    @RequestMapping(value = "test")
    public ResultBean accountRegister() throws Exception {

      bTiemService.getGoodsByCardId(HttpParams.cardId);

     /*   return bTiemService.getMerItemList("", "", "", "", "", HttpParams.cardId,
                "", "", "", "", "", 1, 10, 0);
*/
       /* return bTiemService.getMerchandiseDetail("1020004");

        return bTiemService.showBookDays("10200031000009");*/

       // return bTiemService.getValidPriceTime("10200031000009","15","2017-01-08");

        return bTiemService.lists("","","","","","",
                "","","","",1,10);

    }


}
