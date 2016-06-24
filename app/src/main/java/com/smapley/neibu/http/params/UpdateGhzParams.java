package com.smapley.neibu.http.params;


import com.smapley.neibu.util.MyData;

import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class UpdateGhzParams extends RequestParams {

    public UpdateGhzParams(String user1, String erdj, String sandj, String sidj,
                           String erxj, String sanxj, String sixj) {
        super(MyData.URL_updateGhz);
        addBodyParameter("user1", user1);
        addBodyParameter("erdj", erdj);
        addBodyParameter("sandj", sandj);
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
