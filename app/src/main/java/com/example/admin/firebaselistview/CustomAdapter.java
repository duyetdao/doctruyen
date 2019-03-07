package com.example.admin.firebaselistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Admin on 5/26/2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context c;
    public static ArrayList<Doi_Tuong> doi_tuong;
    LayoutInflater inflater;

      ArrayList<Doi_Tuong> arsearch;


    public CustomAdapter(Context c, ArrayList<Doi_Tuong> doi_tuong) {
        this.c = c;
        this.doi_tuong = doi_tuong;
        this.arsearch = new ArrayList<>();
        arsearch.addAll(doi_tuong);
    }




    @Override
    public int getCount() {
        return doi_tuong.size();
    }

    @Override
    public Object getItem(int i) {
        return doi_tuong.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {

        if (inflater== null)
        {
            inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } if(convertview==null)
        {
            convertview= inflater.inflate(R.layout.listview_layout,viewGroup,false);

        }

        MyHolder holder= new MyHolder(convertview);
        holder.nameTxt.setText(doi_tuong.get(i).getTenTruyen());
        holder.nameTxt2.setText(doi_tuong.get(i).getTacGia());
        PicassoClient.downloadimg(c,doi_tuong.get(i).getUrl(),holder.img);

        Animation animation = AnimationUtils.loadAnimation(c,R.anim.scale_list);
        convertview.startAnimation(animation);
        return convertview;
    }
    public  void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());

        doi_tuong.clear();
        if ( charText.trim().equals("")&& charText.contains("")){
            doi_tuong.addAll(arsearch);
        }
        else
            for (Doi_Tuong sv :arsearch){
                if (sv.getTenTruyen().toLowerCase(Locale.getDefault()).contains(charText)){
                    doi_tuong.add(sv);
                }
            }
        notifyDataSetChanged();
    }
}
