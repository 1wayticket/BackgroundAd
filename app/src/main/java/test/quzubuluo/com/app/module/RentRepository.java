package test.quzubuluo.com.app.module;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/7/24.
 * 趣租赁页面网络请求
 */
public class RentRepository {
    private static RentRepository instance;
    private static RentDataSource source;

    public static RentRepository getInstance(RentDataSource source) {
        if (instance == null) {
            synchronized (RentRepository.class) {
                if (instance == null) {
                    instance = new RentRepository();
                }
            }
        }
        RentRepository.source = source;
        return instance;
    }

    public Observable<BaseResponse<List<RentGoodResponse>>> getGoodsList(String count, String offset, String userId) {
        return source.getGoodsList(count, offset, userId);
    }

    public Observable<BaseResponse<List<RentBanerResponse>>> getBanner() {
        return source.getBanner();
    }
}
