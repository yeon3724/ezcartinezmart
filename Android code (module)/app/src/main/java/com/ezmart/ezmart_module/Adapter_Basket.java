package com.ezmart.ezmart_module;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_Basket extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Adapter_BasketVO> dataset;
    private LayoutInflater inflater;

    public Adapter_Basket(Context context, int layout, ArrayList<Adapter_BasketVO> dataset) {
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

        TextView p_name = view.findViewById(R.id.p_name);
        TextView p_price = view.findViewById(R.id.p_price);
        TextView p_count = view.findViewById(R.id.p_count);
        Button del_btn = view.findViewById(R.id.del_btn);
        ImageButton up_btn = view.findViewById(R.id.up_btn);
        ImageButton down_btn = view.findViewById(R.id.down_btn);

        DecimalFormat myFormatter = new DecimalFormat("###,###");

        if(dataset.get(i).getB_check()==0) {
            p_name.setText(" - (찜 목록) " + dataset.get(i).getP_name());
            p_name.setTextColor(Color.parseColor("#808080"));
            p_price.setText(myFormatter.format(dataset.get(i).getP_price()));
            p_price.setTextColor(Color.parseColor("#808080"));
            p_count.setText("");
        } else {
            p_name.setText(" - " + dataset.get(i).getP_name());
            p_price.setText(myFormatter.format(dataset.get(i).getP_price()));
            p_count.setText(dataset.get(i).getP_count() + "");
        }

        p_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog settingsDialog = new Dialog(context);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                settingsDialog.setContentView(inflater.inflate(R.layout.item_popup, null));
                settingsDialog.show();
                ImageHelper.UpdateImgView(
                        (AppCompatActivity)context,
                        settingsDialog.findViewById(R.id.po_pop),
                        //R.drawable.check
                        dataset.get(i).getP_image()
                );
            }
        });

        DatabaseReference mDatabase; // 파이어베이스 데이터베이스 저장 객체 선언
        mDatabase = FirebaseDatabase.getInstance().getReference();

        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataset.get(i).getB_check()==1) {
                    int count = dataset.get(i).getP_count();
                    int result = count + 1;
                    mDatabase.child("module20001").child(dataset.get(i).getP_barcode()).child("p_count").setValue(result);
                }
            }
        });

        down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataset.get(i).getB_check()==1) {
                    int count = dataset.get(i).getP_count();
                    int result = count == 1 ? 1 : count - 1;
                    mDatabase.child("module20001").child(dataset.get(i).getP_barcode()).child("p_count").setValue(result);
                }
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("module20001").child(dataset.get(i).getP_barcode()).setValue(null);
            }
        });

        return view;
    }
}