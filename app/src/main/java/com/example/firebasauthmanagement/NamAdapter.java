package com.example.firebasauthmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NamAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Nam> itemList;

    public NamAdapter(Context context, int layout, List<Nam> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        TextView tenkhoahoc = view.findViewById(R.id.tenkhoahoclist);
        TextView tenthuongoi = view.findViewById(R.id.tenthuonggoilist);
        TextView congdung = view.findViewById(R.id.congdunglist);
        ImageView img_order = view.findViewById(R.id.img_order);

        Nam nam = itemList.get(i);
        tenkhoahoc.setText(nam.getTenKhoaHoc());
        tenthuongoi.setText(nam.getTenThuongGoi());
        congdung.setText(nam.getCongDung());
        img_order.setImageResource(nam.getHinhAnh());
        return view;
    }
}
