package test.quzubuluo.com.app.module;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/7/24.
 * 趣租赁页面网络请求
 */
public interface RentDataSource {

    Observable<BaseResponse<List<RentGoodResponse>>> getGoodsList(String count, String offset, String userId);

    Observable<BaseResponse<List<RentBanerResponse>>> getBanner();
}
