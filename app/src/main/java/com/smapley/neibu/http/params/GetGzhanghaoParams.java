package com.smapley.neibu.http.params;


import com.smapley.neibu.util.MyData;

import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class GetGzhanghaoParams extends RequestParams {

    public GetGzhanghaoParams(String zhanghao) {
        super(MyData.URL_getGzhanghao);
        addBodyParameter("zhanghao", zhanghao);
    }
}
