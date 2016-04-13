package com.smapley.neibu.http.params;


import com.smapley.neibu.util.MyData;

import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class Reg1Params extends RequestParams {

    public Reg1Params(String user1,String smi,String zhang,String mi,String type) {
        super(MyData.URL_reg1);
        addBodyParameter("user1", user1);
        addBodyParameter("smi", smi);
        addBodyParameter("zhang", zhang);
        addBodyParameter("mi", mi);
        addBodyParameter("type", type);
    }
}
