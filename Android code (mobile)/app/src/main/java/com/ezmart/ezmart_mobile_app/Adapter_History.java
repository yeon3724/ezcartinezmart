package com.ezmart.ezmart_mobile_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_History extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Adapter_HistoryVO> dataset;
    private LayoutInflater inflater;

    TextView history_cate;
    TextView history_date;
    TextView history_name;
    TextView history_total;
    ImageView history_img;

    public Adapter_History(Context context, int layout, ArrayList<Adapter_HistoryVO> dataset) {
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(layout, null);

        history_cate = view.findViewById(R.id.history_cate);
        history_date = view.findViewById(R.id.history_date);
        history_name = view.findViewById(R.id.history_name);
        history_total = view.findViewById(R.id.history_total);
        history_img = view.findViewById(R.id.history_img);

        DecimalFormat myFormatter = new DecimalFormat("###,###");

        history_cate.setText(dataset.get(i).getHistory_cate()+"");
        String datestr = dataset.get(i).getHistory_date();
        String[] datearr = datestr.split(" ");
        String datestr1 = datearr[0];
        String[] datearr1_1 = datestr1.split("-");
        int dateyear = Integer.parseInt(datearr1_1[0]);
        int datemonth = Integer.parseInt(datearr1_1[1]);
        int dateday = Integer.parseInt(datearr1_1[2]);
        String datestr2 = datearr[1];
        String[] datearr2_1 = datestr2.split(":");
        int datehour = Integer.parseInt(datearr2_1[0]);
        int datemin = Integer.parseInt(datearr2_1[1]);
        history_date.setText(dateyear + "년 " + datemonth + "월 " + dateday + "일 " + datehour + "시 " + datemin + "분 ");
        history_name.setText(dataset.get(i).getHistory_name()+"");
        history_total.setText(myFormatter.format(dataset.get(i).getHistory_total())+"원");
        sendImageRequest(dataset.get(i).getHistory_img(), history_img);

        return view;

    }

    public void sendImageRequest(String imgUrl, ImageView view) {

        ImageLoadTask task = new ImageLoadTask(imgUrl, view);
        task.execute();

    }

}



