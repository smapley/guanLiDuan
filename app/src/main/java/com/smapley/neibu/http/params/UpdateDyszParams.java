package com.smapley.neibu.http.params;


import com.smapley.neibu.util.MyData;

import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class UpdateDyszParams extends RequestParams {

    public UpdateDyszParams(String user1, String mi,String zhanghao,String ming,String beizhu,String xinyong,
                            String zhancheng,String mima,String erdpei,String sandpei,
                            String sidpei,String erxpei,String sanxpei,String sixpei) {
        super(MyData.URL_updateDysz);
        addBodyParameter("user1", user1);
        addBodyParameter("mi", mi);
        addBodyParameter("zhanghao", zhanghao);
        addBodyParameter("ming", ming);
        addBodyParameter("beizhu", beizhu);
        addBodyParameter("xinyong", xinyong);
        addBodyParameter("bili",zhancheng);
        addBodyParameter("mima", mima);
        addBodyParameter("erd", erdpei);
        addBodyParameter("sand", sandpei);
        addBodyParameter("sid", sidpei);
        addBodyParameter("erx", erxpei);
        addBodyParameter("sanx", sanxpei);
        addBodyParameter("six", sixpei);
        try {
            LogUtil.d(toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
