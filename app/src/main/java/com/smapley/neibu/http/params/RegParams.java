package com.smapley.neibu.http.params;


import com.smapley.neibu.util.MyData;

import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class RegParams extends RequestParams {

    public RegParams(String phone, String mi) {
        super(MyData.URL_reg);
        addBodyParameter("zhang", phone);
        addBodyParameter("mi", mi);
    }
}
