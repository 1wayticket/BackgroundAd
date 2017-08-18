package test.quzubuluo.com.app.module;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import test.quzubuluo.com.app.model.PicModel;

/**
 * Created by Administrator on 2017/8/17.
 */

public interface PicService {


    @FormUrlEncoded
    @POST("/server_api/gallery/list")
    Observable<BaseResponse<List<PicModel>>> getPics(@Field("goodsId") String goodsId);
}
