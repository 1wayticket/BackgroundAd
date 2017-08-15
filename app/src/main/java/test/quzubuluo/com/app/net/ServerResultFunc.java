package test.quzubuluo.com.app.net;

import io.reactivex.functions.Function;
import test.quzubuluo.com.app.module.BaseResponse;

/**
 * @author Kevin
 *         拦截
 */
public class ServerResultFunc<T> implements Function<BaseResponse<T>, T> {

    @Override
    public T apply(BaseResponse<T> tBaseResponse) throws Exception {
        if (!tBaseResponse.isOk()) {
            //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
            throw new ExceptionHandle.ServerException(Integer.valueOf(tBaseResponse.getResult()),
                    tBaseResponse.getMsg() == null ? "" : tBaseResponse.getMsg());
        }
        //服务器请求数据成功，返回里面的数据实体
        return tBaseResponse.getData();
    }
}
