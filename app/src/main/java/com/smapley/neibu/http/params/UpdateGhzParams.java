package com.smapley.neibu.http.params;


import com.smapley.neibu.util.MyData;

import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class UpdateGhzParams extends RequestParams {

    public UpdateGhzParams(String user1, String erdj1,
                           String erdj2,
                           String erdj3,
                           String erdj4,
                           String erdj5,
                           String erdj6,
                           String sandj1,
                           String sandj2,
                           String sandj3,
                           String sandj4,String sidj,
                           String erxj, String sanxj, String sixj) {
        super(MyData.URL_updateGhz);
        addBodyParameter("user1", user1);
        addBodyParameter("erdj1", erdj1);
        addBodyParameter("erdj2", erdj2);
        addBodyParameter("erdj3", erdj3);
        addBodyParameter("erdj4", erdj4);
        addBodyParameter("erdj5", erdj5);
        addBodyParameter("erdj6", erdj6);
        addBodyParameter("sandj1", sandj1);
        addBodyParameter("sandj2", sandj2);
        addBodyParameter("sandj3", sandj3);
        addBodyParameter("sandj4", sandj4);
        addBodyParameter("sidj", sidj);
        addBodyParameter("erxj", erxj);
        addBodyParameter("sanxj", sanxj);
        addBodyParameter("sixj", sixj);
        try {
            LogUtil.d(toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
