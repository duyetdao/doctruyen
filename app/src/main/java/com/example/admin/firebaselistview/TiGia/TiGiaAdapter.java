package com.example.admin.firebaselistview.TiGia;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.firebaselistview.R;

import java.util.List;


/**
 * Created by ngoct_000 on 3/4/2016.
 */
public class TiGiaAdapter extends ArrayAdapter<TiGia> {
    Activity context;
    int resource;
    List<TiGia> objects;
    public TiGiaAdapter(Activity context, int resource, List<TiGia> objects) {
        super(context, resource, objects);
        this.context=context;
        this.objects=objects;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View item=inflater.inflate(this.resource, null);

        TiGia tiGia=this.objects.get(position);

        ImageView imgHinh= (ImageView) item.findViewById(R.id.imgHinh);
        TextView txtType= (TextView) item.findViewById(R.id.txtType);
        TextView txtMuatm= (TextView) item.findViewById(R.id.txtMuaTm);
        TextView txtBantm= (TextView) item.findViewById(R.id.txtBanTM);
        TextView txtMuack= (TextView) item.findViewById(R.id.txtMuaCK);
        TextView txtBanck= (TextView) item.findViewById(R.id.txtBanCK);

        imgHinh.setImageBitmap(tiGia.getBitmap());
        txtType.setText(tiGia.getType());
        txtBanck.setText(tiGia.getBanck());
        txtBantm.setText(tiGia.getBantienmat());
        txtMuack.setText(tiGia.getMuack());
        txtMuatm.setText(tiGia.getMuatienmat());

        return item;
    }
}
