package com.smapley.neibu.http.service;


import com.smapley.neibu.http.callback.SimpleCallback;
import com.smapley.neibu.http.params.GetGzhangdanhzParams;

import org.xutils.x;

/**
 * Created by smapley on 15/12/18.
 */
public abstract class GetGzhangdanhzService {

    public void load(GetGzhangdanhzParams params) {

        x.http().post(params, new SimpleCallback() {
            @Override
            public void Success(final String data) {
                Succ(data);
            }
        });
    }


    public abstract void Succ(String data);
}