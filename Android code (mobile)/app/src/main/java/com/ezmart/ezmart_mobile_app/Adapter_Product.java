package com.ezmart.ezmart_mobile_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class Adapter_Product extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Adapter_ProductVO> dataset;
    private LayoutInflater inflater;

    TextView product_name;
    TextView product_price;
    TextView product_cate;
    ImageView product_image;
    Button product_put_btn;

    public Adapter_Product(Context context, int layout, ArrayList<Adapter_ProductVO> dataset) {
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

        product_name = view.findViewById(R.id.basket_name);
        product_price = view.findViewById(R.id.basket_price);
        product_cate = view.findViewById(R.id.product_cate);
        product_image = view.findViewById(R.id.basket_image);
        product_put_btn = view.findViewById(R.id.product_put_btn);

        DecimalFormat myFormatter = new DecimalFormat("###,###");

        product_name.setText(dataset.get(i).getProduct_name()+"");
        product_price.setText(myFormatter.format(dataset.get(i).getProduct_price()) + "???");
        product_cate.setText(dataset.get(i).getProduct_cate()+"");
        sendImageRequest(dataset.get(i).getProduct_image(), product_image);

        Adapter_ProductVO vo = dataset.get(i);

        product_put_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(vo);
            }
        });

        return view;
    }

    void showDialog(Adapter_ProductVO vo) {
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(context)
                .setTitle("????????????")
                .setMessage("??????????????? ????????? ??????????????????????")
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(product_put_btn.getContext(), "??????????????? ???????????????.", Toast.LENGTH_SHORT).show();
                        sendRequest(vo);
                    }
                })
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(product_put_btn.getContext(), "?????? ???????????????.", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }

    public void sendRequest(Adapter_ProductVO vo) {

        // ???????????? ?????? ????????? ???????????? DB ?????? ??????(Controller URL ??? POST ???????????? ?????? ??????)
        AppHelper.requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        String url = "http://172.30.1.33:8083/web/insertbasket.do";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() { //????????? ??? ????????? ??? ??? ???????????? ???????????? ??????
                    @Override
                    public void onResponse(String response) {
                        Log.d("success", "200");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ;
                        Log.e("error", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("mb_id", AppHelper.id);
                params.put("p_seq", vo.getProduct_seq() + "");
                return params;

            }
        };

        request.setShouldCache(false); //?????? ?????? ????????? ?????? ???????????? ????????? ????????????.
        String requestdebug = AppHelper.requestQueue.add(request).toString();
        Log.d("developer", requestdebug);

    }

    public void sendImageRequest(String imgUrl, ImageView view) {

        ImageLoadTask task = new ImageLoadTask(imgUrl, view);
        task.execute();

    }

}
