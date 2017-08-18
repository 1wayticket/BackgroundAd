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
import test.quzubuluo.com.app.net.RxSchedulers;
import test.quzubuluo.com.app.utils.LogUtil;

/**
 * Created by Administrator on 2017/8/15.
 */

public class QuzuWidget extends AppWidgetProvider {
    private static final String ACTION_REFESH = "android.appwidget.action.ACTION_REFRESH";
    public static final String ACTION_SHOW = "android.appwidget.action.ACTION_SHOW";
    private int[] ids = new int[]{R.drawable.ad1, R.drawable.ad2, R.drawable.ad3};
    private static int sPosition = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ComponentName provider = new ComponentName(context, QuzuWidget.class);
        switch (intent.getAction()) {
            case ACTION_SHOW:
                LogUtil.d("chen");
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
        sPosition++;
        sPosition = sPosition % 3;
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
        LogUtil.d("onUpdate" + sPosition);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), ids[sPosition]);
//        switchImage(context, appWidgetIds, appWidgetManager,bitmap);
        context.startService(new Intent(context, AppWidgetService.class));
    }

    private void addClickIntent(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        int length = appWidgetIds.length;
        for (int i = 0; i < length; i++) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_quzu);
            Intent refrehsIntent = new Intent();
            refrehsIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            refrehsIntent.setAction(ACTION_REFESH);
            PendingIntent refreshIntent = PendingIntent.getBroadcast(context, 0, refrehsIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.tv_refresh, refreshIntent);
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
        sPosition = 0;
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        LogUtil.d("onDisabled");
    }

}
