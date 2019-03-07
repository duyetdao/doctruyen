package com.example.admin.firebaselistview;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * Created by Admin on 5/26/2017.
 */

public class FirebaseClient  {

    Context c;
    String DB_URL;
    ListView listView;
     Firebase firebase;
   public static ArrayList<Doi_Tuong> dogies= new ArrayList<>();
public static     CustomAdapter customAdapter;


    public  FirebaseClient(Context c, String DB_URL, ListView listView)
    {
        this.c= c;
        this.DB_URL= DB_URL;
        this.listView= listView;


        Firebase.setAndroidContext(c);
        firebase=new Firebase(DB_URL);
    }


    public  void refreshdata()
    {
        firebase.child("Truyen").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void getupdates(DataSnapshot dataSnapshot){




            Doi_Tuong d= new Doi_Tuong();
            d.setTenTruyen(dataSnapshot.getValue(Doi_Tuong.class).getTenTruyen());
            d.setTacGia(dataSnapshot.getValue(Doi_Tuong.class).getTacGia());
            d.setNoiDung(dataSnapshot.getValue(Doi_Tuong.class).getNoiDung());
            d.setUrl(dataSnapshot.getValue(Doi_Tuong.class).getUrl());
            d.setKey(dataSnapshot.getKey());
            dogies.add(d);

        if(dogies.size()>0)
        {
            customAdapter=new CustomAdapter(c, dogies);
            listView.setAdapter((ListAdapter) customAdapter);
        }else
        {
            Toast.makeText(c, "No data", Toast.LENGTH_SHORT).show();
        }
    }
    //them
    public  void savedata(String tentruyen, String tacgia, String noidung,String url) {
        Doi_Tuong d= new Doi_Tuong();
        d.setTenTruyen(tentruyen);
        d.setTacGia(tacgia);
        d.setNoiDung(noidung);
        d.setUrl(url);

        firebase.child("Truyen").push().setValue(d);


    }
    //tsua
    public void updateData(String tentruyen, String tacgia, String noidung,String url,String key){
        Doi_Tuong doi_tuong=new Doi_Tuong();
        doi_tuong.setTenTruyen(tentruyen);
        doi_tuong.setTacGia(tacgia);
        doi_tuong.setNoiDung(noidung);
        doi_tuong.setUrl(url);
        firebase.child("Truyen").child(key).setValue(doi_tuong);
    }
    public void deleteData(String key){

        firebase.child("Truyen").child(key).removeValue();
    }

}
