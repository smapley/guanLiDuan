package com.smapley.neibu.http.params;


import com.smapley.neibu.util.MyData;

import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class GetMingxiParams extends RequestParams {

    public GetMingxiParams(String user1) {
        super(MyData.URL_getMingxi);
        addBodyParameter("user1", user1);
    }
}
