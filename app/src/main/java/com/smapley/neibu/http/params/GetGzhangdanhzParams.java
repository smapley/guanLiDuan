package com.smapley.neibu.http.params;


import com.smapley.neibu.util.MyData;

import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class GetGzhangdanhzParams extends RequestParams {

    public GetGzhangdanhzParams(String user1) {
        super(MyData.URL_getGzhangdanhz);
        addBodyParameter("user1", user1);
        try {
            LogUtil.d(toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
