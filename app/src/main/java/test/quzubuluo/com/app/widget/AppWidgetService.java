package test.quzubuluo.com.app.widget;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import test.quzubuluo.com.app.App;
import test.quzubuluo.com.app.model.PicModel;
import test.quzubuluo.com.app.module.BaseResponse;
import test.quzubuluo.com.app.module.PicReposity;
import test.quzubuluo.com.app.net.BaseObserver;
import test.quzubuluo.com.app.net.RxSchedulers;
import test.quzubuluo.com.app.utils.LogUtil;

/*
 * @author : skywang <wangkuiwu@gmail.com>
 * description : 周期性更新AppWidget的服务
 */

public class AppWidgetService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        PicReposity.getInstance().getPics("15")
                .compose(RxSchedulers.<BaseResponse<List<PicModel>>>io_main())
                .subscribe(new BaseObserver<BaseResponse<List<PicModel>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<List<PicModel>> value) {
                        if (value.getResult() == 0) {
                            int size = value.getData().size();
                            Random random = new Random();
                            loadBitmap(value.getData().get(random.nextInt(size)).getImgUrl());
                        }
                    }
                });
    }

    private void loadBitmap(final String thumbUrl) {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                Bitmap bitmap = Glide.with(AppWidgetService.this).load("http://192.168.188.35:8088/api_images/" + thumbUrl).asBitmap().into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
                e.onNext(bitmap);
            }
        }).compose(RxSchedulers.<Bitmap>io_main())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Bitmap value) {
                        Intent intent = new Intent();
                        App.setBitmap(value);
                        intent.setAction(QuzuWidget.ACTION_SHOW);
                        AppWidgetService.this.sendBroadcast(intent);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        finishService();
                    }
                });
    }

    private void finishService() {
        finishService();
    }
}