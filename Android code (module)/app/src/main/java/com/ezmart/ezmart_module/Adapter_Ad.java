package com.ezmart.ezmart_module;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Adapter_Ad extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Adapter_AdVO> dataset;
    private LayoutInflater inflater;

    public Adapter_Ad(Context context, int layout, ArrayList<Adapter_AdVO> dataset) {
        this.context = context;
        this.layout = layout;
        this.dataset = dataset;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public Object getItem(int i) {
        return dataset.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(layout, null);

        TextView ad_name = view.findViewById(R.id.ad_name);
        TextView ad_event = view.findViewById(R.id.ad_event);
        ImageView ad_img = view.findViewById(R.id.ad_img);

        ad_name.setText("   "+dataset.get(i).getAd_name()+"");
        ad_event.setText("  "+dataset.get(i).getAd_event() + "");
        sendImageRequest(dataset.get(i).getAd_img(), ad_img);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog settingsDialog = new Dialog(context);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                settingsDialog.setContentView(inflater.inflate(R.layout.item_adpup, null));
                settingsDialog.show();
                ImageHelper.UpdateImgView(
                        (AppCompatActivity)context,
                        settingsDialog.findViewById(R.id.ad_pop),
                        //R.drawable.check
                        dataset.get(i).getAd_img()
                );
            }
        });

        return view;
    }

    public void sendImageRequest(String imgUrl, ImageView view) {

        ImageLoadTask task = new ImageLoadTask(imgUrl, view);
        task.execute();

    }

}