package com.quzubuluo.quzulock.module;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

import com.quzubuluo.quzulock.model.PicResponse;
import com.quzubuluo.quzulock.net.RetrofitHelper;

/**
 * Created by Administrator on 2017/8/17.
 */

public class PicReposity implements PicService {
    private static PicReposity instance;
    private static PicService source;

    public static PicReposity getInstance() {
        if (instance == null) {
            synchronized (PicReposity.class) {
                if (instance == null) {
                    instance = new PicReposity();
                }
            }
        }
        source = RetrofitHelper.createApi(PicService.class);
        return instance;
    }

    @Override
    public Observable<BaseResponse<List<PicResponse>>> getPics(@Field("goodsId") String goodsId) {
        return source.getPics(goodsId);
    }
}
