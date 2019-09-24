package com.example.ajisaputrars.mystackwidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final List<Bitmap> widgetItems = new ArrayList<>();
    private final Context context;

    StackRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        widgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.darth_vader));
        widgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.star_wars_logo));
//        widgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.storm_trooper));
//        widgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.starwars));
//        widgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.falcon));

        String path = "https://res.cloudinary.com/demo/image/upload/w_200/lady.jpg";
        String path2 = "https://docs.imagga.com/static/images/docs/sample/xjapan-605234_1280.jpg.pagespeed.ic.agiseKCSZD.jpg";

        try {
            Bitmap bitmap = Glide.with(context)
                    .asBitmap()
                    .load(path)
                    .submit(512, 512)
                    .get();

            widgetItems.add(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Bitmap bitmap = Glide.with(context)
                    .asBitmap()
                    .load(path2)
                    .submit(512, 512)
                    .get();

            widgetItems.add(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Glide.with(context)
//                .asBitmap()
//                .load(path2)
//                .into(new CustomTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        widgetItems.add(resource);
//                        Log.d("onResourceReady", "onResourceReady dijalankan");
//                        widgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.storm_trooper));
//                        widgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.starwars));
//                        widgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.falcon));
//                        Log.d("onResourceReady", "Jumlah widgetItem adalah " +  widgetItems.size());
//                    }
//
//                    @Override
//                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                        super.onLoadFailed(errorDrawable);
//                        Log.d("onLoadFailed", "onLoadFailed dijalankan");
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//                        Log.d("onLoadCleared", "onLoadCleared dijalankan");
//                    }
//                });
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return widgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        remoteViews.setImageViewBitmap(R.id.imageView, widgetItems.get(i));

        Bundle extras = new Bundle();
        extras.putInt(ImageBannerWidget.EXTRA_ITEM, i);

        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        remoteViews.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
