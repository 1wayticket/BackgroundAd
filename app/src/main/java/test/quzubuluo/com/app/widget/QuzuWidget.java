package test.quzubuluo.com.app.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;


import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import test.quzubuluo.com.app.App;
import test.quzubuluo.com.app.R;
import test.quzubuluo.com.app.SplashActivity;
import test.quzubuluo.com.app.net.RxSchedulers;
import test.quzubuluo.com.app.utils.LogUtil;

/**
 * Created by Administrator on 2017/8/15.
 */

public class QuzuWidget extends AppWidgetProvider {
    private static final String ACTION_REFESH = "android.appwidget.action.ACTION_REFRESH";
    public static final String ACTION_SHOW = "android.appwidget.action.ACTION_SHOW";

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ComponentName provider = new ComponentName(context, QuzuWidget.class);
        switch (intent.getAction()) {
            case ACTION_SHOW:
                LogUtil.d("chen show");
                switchImage(context, manager.getAppWidgetIds(provider), manager, App.getBitmap());
                break;
            case ACTION_REFESH:
                AppWidgetManager appWidgetManager = manager;
                onUpdate(context, appWidgetManager, appWidgetManager.getAppWidgetIds(provider));
                break;
            default:
                super.onReceive(context, intent);
        }

    }

    private void switchImage(final Context context, final int[] appWidgetIds, final AppWidgetManager appWidgetManager, Bitmap bitmap) {
        int length = appWidgetIds.length;
        for (int i = 0; i < length; i++) {
            final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_quzu);
            remoteViews.setImageViewBitmap(R.id.iv_ad, bitmap);
            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        LogUtil.d("onUpdate");
        context.startService(new Intent(context, AppWidgetService.class));
    }

    private void addClickIntent(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        int length = appWidgetIds.length;
        for (int i = 0; i < length; i++) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_quzu);
            Intent refreshIntent = new Intent();
            refreshIntent.setAction(ACTION_REFESH);
            PendingIntent pRefreshIntent = PendingIntent.getBroadcast(context, 0, refreshIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.tv_refresh, pRefreshIntent);

            Intent enterIntent = new Intent(context, SplashActivity.class);
            PendingIntent pEnterIntent = PendingIntent.getActivity(context, 0, enterIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.iv_ad, pEnterIntent);
            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
        }
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
        AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
        addClickIntent(context, widgetManager, widgetManager.getAppWidgetIds(new ComponentName(context, QuzuWidget.class)));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        LogUtil.d("onDisabled");
    }

}
