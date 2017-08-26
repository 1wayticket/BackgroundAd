package com.quzubuluo.quzulock.module;


import com.quzubuluo.quzulock.model.PicResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/8/17.
 */

public interface PicService {


    @FormUrlEncoded
    @POST("/server_api/gallery/list")
    Observable<BaseResponse<List<PicResponse>>> getPics(@Field("goodsId") String goodsId);
}
