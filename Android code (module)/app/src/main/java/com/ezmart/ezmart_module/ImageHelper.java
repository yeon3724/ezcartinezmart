package com.ezmart.ezmart_module;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageHelper {
    public static void UpdateImgView(AppCompatActivity activity, ImageView view, String imgUrl) {
        new Thread(() -> {
            URL url = null;
            try {
                url = new URL(imgUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStream is = connection.getInputStream();
                Bitmap img = BitmapFactory.decodeStream(is);

                activity.runOnUiThread(() -> {
                    view.setImageBitmap(img);
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public static void UpdateImgView(AppCompatActivity activity, ImageView view, int drawableId) {
        activity.runOnUiThread(() -> {
            view.setImageResource(drawableId);
        });
    }

    public void sendImageRequest(String imgUrl, ImageView view) {

        ImageLoadTask task = new ImageLoadTask(imgUrl, view);
        task.execute();

    }

}
