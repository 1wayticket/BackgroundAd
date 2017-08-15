package test.quzubuluo.com.app.net;

import io.reactivex.Observer;
import test.quzubuluo.com.app.utils.LogUtil;


public abstract class BaseObserver<T> implements Observer<T> {

    public BaseObserver() {
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.d(e.getLocalizedMessage());
    }

    @Override
    public void onComplete() {
    }

}
