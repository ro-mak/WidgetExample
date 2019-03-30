package ru.makproductions.widgetexample;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Objects;

import timber.log.Timber;

public class WidgetExample extends AppWidgetProvider {

    private static final String GREET_BUTTON_CLICKED = "greetButtonClick";
    private static int count = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Timber.e("onReceive");
        if (Objects.requireNonNull(intent.getAction()).equals(GREET_BUTTON_CLICKED)) {
            count++;
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName widgetExample = new ComponentName(context, WidgetExample.class);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_appwidget);
            views.setTextViewText(R.id.widget_text_view, "Greetings " + count + " times");
            Toast.makeText(context,"Greetings " + count + " times",Toast.LENGTH_SHORT).show();
            appWidgetManager.updateAppWidget(widgetExample, views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Timber.e("onUpdate");
        ComponentName widgetExample = new ComponentName(context,WidgetExample.class);
        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.example_appwidget);
        views.setImageViewResource(R.id.widget_image_view,R.drawable.ic_launcher_background);
        views.setTextViewText(R.id.widget_text_view,"New Text");
        views.setOnClickPendingIntent(R.id.button,onButtonClickPendingIntent(context,GREET_BUTTON_CLICKED));
        appWidgetManager.updateAppWidget(widgetExample,views);
    }

    private PendingIntent onButtonClickPendingIntent(Context context, String action){
        Intent intent = new Intent(context,getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context,0,intent,0);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Timber.e("onDeleted");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Timber.e("onEnabled");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Timber.e("onDisabled");
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
        Timber.e("onRestored");
    }
}
