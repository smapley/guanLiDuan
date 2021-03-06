package com.smapley.neibu.http.service;


import android.util.Log;

import com.smapley.neibu.http.callback.SimpleCallback;
import com.smapley.neibu.http.params.GetMingxiParams;

import org.xutils.common.util.LogUtil;
import org.xutils.x;

/**
 * Created by smapley on 15/12/18.
 */
public abstract class GetMingxiService {

    public void load(GetMingxiParams params) {

        x.http().post(params, new SimpleCallback() {
            @Override
            public void Success(final String data) {
                LogUtil.e(data);
                Succ(data);
            }
        });
    }


    public abstract void Succ(String data);
}