package com.ezmart.ezmart_mobile_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Adapter_Basket extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Adapter_BasketVO> dataset;
    private LayoutInflater inflater;

    TextView basket_name;
    TextView basket_price;
    ImageView basket_image;
    Button basket_delete_btn;

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

        basket_name = view.findViewById(R.id.basket_name);
        basket_price = view.findViewById(R.id.basket_price);
        basket_image = view.findViewById(R.id.basket_image);
        basket_delete_btn = view.findViewById(R.id.basket_delete_btn);

        DecimalFormat myFormatter = new DecimalFormat("###,###");

        basket_name.setText(dataset.get(i).getBasket_name()+"");
        basket_price.setText(myFormatter.format(dataset.get(i).getBasket_price()) + "원");
        sendImageRequest(dataset.get(i).getBasket_image(), basket_image);

        Adapter_BasketVO vo = dataset.get(i);

        basket_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(vo);
            }
        });

        return view;

    }

    void showDialog(Adapter_BasketVO vo) {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(context)
                .setTitle("이지카트")
                .setMessage("상품을 삭제하시겠습니까?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(basket_delete_btn.getContext(), "장바구니에서 상품을 삭제했습니다.", Toast.LENGTH_SHORT).show();
                        sendRequest(vo);
                        Intent intent = ((Activity)context).getIntent();
                        ((Activity)context).finish(); //현재 액티비티 종료 실시
                        ((Activity)context).overridePendingTransition(0, 0); //효과 없애기
                        ((Activity)context).startActivity(intent); //현재 액티비티 재실행 실시
                        ((Activity)context).overridePendingTransition(0, 0); //효과 없애기
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(basket_delete_btn.getContext(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }

    public void sendRequest(Adapter_BasketVO vo) {

        AppHelper.requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        String url = "http://172.30.1.57:8083/web/basketdelete.do";

        StringRequest request = new StringRequest(
                Request.Method.POST, // POST 방식
                url, // "http://172.30.1.57:8083/web/basketdelete.do"
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context.getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context.getApplicationContext(), "삭제 실패", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("b_seq", Integer.toString(vo.getBasket_b_seq()));
                return params;
            }
        };

        request.setShouldCache(false);
        String requestdebug = AppHelper.requestQueue.add(request).toString();
        Log.d("developer", requestdebug);

    }

    public void sendImageRequest(String imgUrl, ImageView view) {

        ImageLoadTask task = new ImageLoadTask(imgUrl, view);
        task.execute();

    }

}
