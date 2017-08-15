package test.quzubuluo.com.app.module;


import java.util.List;

import io.reactivex.Observable;
import test.quzubuluo.com.app.net.RetrofitHelper;

/**
 * 趣租赁页面网络请求
 */
public class RentRemoteDataSource implements RentDataSource {
    private RentService service;

    public RentRemoteDataSource() {
        this.service = RetrofitHelper.createApi(RentService.class);
    }

    @Override
    public Observable<BaseResponse<List<RentGoodResponse>>> getGoodsList(String count, String offset, String userId) {
        return service.getGoodsList(count, offset, userId);
    }

    @Override
    public Observable<BaseResponse<List<RentBanerResponse>>> getBanner() {
        return service.getBanner();
    }
}
