package test.quzubuluo.com.app.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.RemoteViews;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.disposables.Disposable;
import test.quzubuluo.com.app.R;
import test.quzubuluo.com.app.module.BaseResponse;
import test.quzubuluo.com.app.module.RentGoodResponse;
import test.quzubuluo.com.app.module.RentRemoteDataSource;
import test.quzubuluo.com.app.module.RentRepository;
import test.quzubuluo.com.app.net.BaseObserver;
import test.quzubuluo.com.app.net.RxManage;
import test.quzubuluo.com.app.net.RxSchedulers;
import test.quzubuluo.com.app.utils.LogUtil;

/**
 * Created by Administrator on 2017/8/15.
 */

public class QuzuWidget extends AppWidgetProvider {
    private static RxManage rxManage = new RxManage();

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d("onReceive");
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_quzu);
        remoteViews.setImageViewResource(R.id.iv_good, R.drawable.start_page);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        LogUtil.d("onUpdate");
        super.onUpdate(context, appWidgetManager, appWidgetIds);

    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        LogUtil.d("onAppWidgetOptionsChanged");
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        LogUtil.d("onDeleted");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        LogUtil.d("onEnabled");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        LogUtil.d("onDisabled");
    }
}
