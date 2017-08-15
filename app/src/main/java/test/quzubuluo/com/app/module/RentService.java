package test.quzubuluo.com.app.module;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 趣租赁页面网络请求
 */
public interface RentService {

    @FormUrlEncoded
    @POST("/server_api/goods/list")
    Observable<BaseResponse<List<RentGoodResponse>>> getGoodsList(@Field("count") String count,
                                                                  @Field("offset") String offset,
                                                                  @Field("userId") String userId);

    @POST("/server_api/activity/details")
    Observable<BaseResponse<List<RentBanerResponse>>> getBanner();

}