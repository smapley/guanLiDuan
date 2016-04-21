package com.smapley.neibu.http.params;


import com.smapley.neibu.util.MyData;

import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class GetGpankouParams extends RequestParams {

    public GetGpankouParams(String user1, String mi) {
        super(MyData.URL_getGpankou);
        addBodyParameter("user1", user1);
        addBodyParameter("mi", mi);
        try {
            LogUtil.d(toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
