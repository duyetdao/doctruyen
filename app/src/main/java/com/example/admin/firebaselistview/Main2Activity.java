package com.example.admin.firebaselistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {
    TextView  txtTenTruyen, txtTacGia, txtNoiDung;
    ImageView  imganh,imagethunho,imagephongto;
    Integer i = 18;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

//        MobileAds.initialize(this, "ca-app-pub-9354058932494488~7519151507");
//        mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//        mAdView.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                // Code to be executed when an ad finishes loading.
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                // Code to be executed when an ad request fails.
//            }
//
//            @Override
//            public void onAdOpened() {
//                // Code to be executed when an ad opens an overlay that
//                // covers the screen.
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                // Code to be executed when the user has left the app.
//            }
//
//            @Override
//            public void onAdClosed() {
//                // Code to be executed when when the user is about to return
//                // to the app after tapping on an ad.
//            }
//        });
//        AdView adView = new AdView(this);
//        adView.setAdSize(AdSize.BANNER);




        txtTenTruyen = (TextView)findViewById(R.id.txtTenTruyen);
        txtNoiDung = (TextView)findViewById(R.id.txtNoiDung);
        imagethunho = (ImageView)findViewById(R.id.imagethunho) ;
        imagephongto = (ImageView)findViewById(R.id.imagephongto);
        imagethunho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNoiDung.setTextSize(i--);

            }
        });
        imagephongto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNoiDung.setTextSize(i++);
            }
        });

        String tentruyen = getIntent().getStringExtra("tentruyen");
        String noidung= getIntent().getStringExtra("noidung");
        txtTenTruyen.setText(tentruyen);
        txtNoiDung.setText(noidung);
        Main2Activity.this.setTitle("Đang đọc truyện "+tentruyen);
    }
}
