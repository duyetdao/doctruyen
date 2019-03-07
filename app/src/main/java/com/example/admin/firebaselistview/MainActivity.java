package com.example.admin.firebaselistview;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.example.admin.firebaselistview.Map.MapsActivity;
import com.example.admin.firebaselistview.TiGia.TiGiaActivity;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,NavigationView.OnNavigationItemSelectedListener {

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    EditText nameeditText,tuoiEditText,lopEditText,editTextName,editTexttuoi,editTextlop;
    Button btnsave,buttonUpdateArtist;
    ListView listView;
    FirebaseClient firebaseClient;
    String key;
    Uri uri;
    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    ImageView imagehinh,dogimage;
    int RE = 1;
    SwipeRefreshLayout resetlayout;
    public static FloatingActionButton fab;
    CustomAdapter customAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    Integer vitrikeysua;
    ActionBarDrawerToggle toggle;



    final ArrayList<Doi_Tuong> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        MobileAds.initialize(this, "ca-app-pub-9354058932494488~7519151507");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);

        MobileAds.initialize(this, "ca-app-pub-9354058932494488~7519151507");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9354058932494488/8304492208");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
            }
        });




//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        Profile profile = Profile.getCurrentProfile();
//        String uid;
//        if (profile!= null){
//            uid = profile.getId();
//        }else {
//            uid = user.getUid();
//        }
//        myRef.child("TaiKhoan").child(uid).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                NguoiDung nguoiDung = dataSnapshot.getValue(NguoiDung.class);
//                if (nguoiDung.getEmail().equals("admin@gmail.com")){
//                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                        @Override
//                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                            vitrikeysua = position;
//                            Showmenuitem(view,position);
//                            return true;
//                        }
//                    });
//                    fab.setVisibility(View.VISIBLE);
//                }else {
//
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);

        toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        listView = (ListView) findViewById(R.id.listview);





        MainActivity.this.setTitle("Danh Sách Truyện");
        myRef.child("Truyen").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Doi_Tuong doi_tuong = dataSnapshot.getValue(Doi_Tuong.class);
                doi_tuong.setKey(dataSnapshot.getKey());
                arrayList.add(doi_tuong);
                customAdapter = new CustomAdapter(MainActivity.this,arrayList);
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Doi_Tuong sv = dataSnapshot.getValue(Doi_Tuong.class);
                sv.setKey(dataSnapshot.getKey());
                int index = getItemindex(sv);
                arrayList.set(index, sv);
                customAdapter = new CustomAdapter(MainActivity.this, arrayList);
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Doi_Tuong doi_tuong = dataSnapshot.getValue(Doi_Tuong.class);
                for (Doi_Tuong mq: arrayList){
                    if (mq.getKey().equals(dataSnapshot.getKey())){
                        arrayList.remove(mq);
                        customAdapter = new CustomAdapter(MainActivity.this, arrayList);
                        listView.setAdapter(customAdapter);
                        break;
                    }
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("tentruyen",arrayList.get(position).getTenTruyen());
                intent.putExtra("noidung",arrayList.get(position).getNoiDung());
                startActivity(intent);
            }
        });

         fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    displayDialog();




            }
        });

        resetlayout = (SwipeRefreshLayout)findViewById(R.id.resetlayout);
        resetlayout.setColorSchemeColors(Color.BLACK,Color.BLUE,Color.RED,Color.YELLOW,Color.CYAN);
        resetlayout.setDistanceToTriggerSync(100);
        resetlayout.setSize(SwipeRefreshLayout.LARGE);
        resetlayout.setOnRefreshListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings){
            return true;
        }
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);

    }

    private void displayDialog(){
        final Dialog dialog= new Dialog(this);
        dialog.setTitle("Lưu dữ liệu");
        dialog.setContentView(R.layout.customdialog_layout);
        nameeditText= (EditText)dialog.findViewById(R.id.nameEditText);
        tuoiEditText = (EditText)dialog.findViewById(R.id.tuoiEditText);
        lopEditText = (EditText)dialog.findViewById(R.id.noidungedt);
        btnsave= (Button)dialog.findViewById(R.id.saveBtn);
        imagehinh = (ImageView) dialog.findViewById(R.id.themhinh);
        imagehinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_PICK);
                it.setType("image/*");
                it.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(it,RE);
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateadd()){
                    Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if (uri != null) {
                        Random random = new Random();
                        int ran = random.nextInt(90000000);
                        StorageReference riversRef = mStorageRef.child("images/rivers"+ran+".jpg");
                        riversRef.putFile(uri)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        // Get a URL to the uploaded content


                                        Uri downloadUrl = taskSnapshot.getDownloadUrl();

                                       myRef.child("Truyen").push().setValue(new Doi_Tuong(nameeditText.getText().toString(),tuoiEditText.getText().toString(),
                                               lopEditText.getText().toString(),downloadUrl.toString()));
                                        dialog.dismiss();
                                        Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }else{
                        myRef.child("Truyen").push().setValue(new Doi_Tuong(nameeditText.getText().toString(),tuoiEditText.getText().toString(),
                                lopEditText.getText().toString(),"xcvxcvxcv"));
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        dialog.show();

    }
    private int getItemindex(Doi_Tuong doiTuong){
        int index = -1;
        for (int i = 0; i<arrayList.size(); i++){
            if (arrayList.get(i).getKey().equals(doiTuong.getKey())){
                index = i;
                break;
            }
        }
        return index;
    }

    public void Showmenuitem(final View view, final int vitri){
        PopupMenu popupMenu=new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_item_listview,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.xoa){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setMessage("Bán có muốn xóa!");
                    alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            myRef.child("Truyen").child(arrayList.get(vitri).getKey()).removeValue();

                            Toast.makeText(MainActivity.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialogBuilder.show();
                    return true;


                } else  if(item.getItemId()==R.id.chinhsua){
                            changeialog();
                        editTextName.setText(arrayList.get(vitri).getTenTruyen());
                        editTexttuoi.setText(arrayList.get(vitri).getTacGia());
                        editTextlop.setText(arrayList.get(vitri).getNoiDung());
                        Picasso.with(getApplicationContext()).load(arrayList.get(vitri).getUrl()).placeholder(R.drawable.placeholder).error(R.drawable.noimg).into(imagehinh);


                }
                return false;
            }
        });
        popupMenu.show();
    }
    private void changeialog(){
        final Dialog dialog= new Dialog(this);
        dialog.setTitle("UPDATE");
        dialog.setContentView(R.layout.update_dialog);
        editTextName= (EditText)dialog.findViewById(R.id.editTextName);
        editTexttuoi=(EditText)dialog.findViewById(R.id.editTexttuoi);
        editTextlop=(EditText)dialog.findViewById(R.id.editTextlop) ;
        buttonUpdateArtist=(Button) dialog.findViewById(R.id.buttonUpdateArtist);
        imagehinh = (ImageView) dialog.findViewById(R.id.themhinh);
        imagehinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_PICK);
                it.setType("image/*");
                it.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(it,RE);
            }
        });
        buttonUpdateArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateupdate()){
                    Toast.makeText(MainActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if (uri != null) {
                        Random random = new Random();
                        int ran = random.nextInt(90000000);
                        StorageReference riversRef = mStorageRef.child("images/rivers"+ran+".jpg");
                        riversRef.putFile(uri)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                        // Get a URL to the uploaded content
                                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                        myRef.child("Truyen").child(arrayList.get(vitrikeysua).getKey()).setValue(new Doi_Tuong(editTextName.getText().toString(),editTexttuoi.getText().toString(),editTextlop.getText().toString(),downloadUrl.toString()));
                                        dialog.dismiss();
                                        Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();


                                    }
                                });


                    }else {
                        myRef.child("Truyen").child(arrayList.get(vitrikeysua).getKey()).setValue(new Doi_Tuong(editTextName.getText().toString()
                                ,editTexttuoi.getText().toString(),editTextlop.getText().toString(),arrayList.get(vitrikeysua).getUrl()));
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });

        dialog.show();

    }
    public boolean validateadd() {
        boolean valid = true;

        String tentruyen = nameeditText.getText().toString();
        String tacgia = tuoiEditText.getText().toString();
        String noidung = lopEditText.getText().toString();
        String hinh = imagehinh.toString();
        if(hinh.length()==0){
            Toast.makeText(this, "Thiếu hình", Toast.LENGTH_SHORT).show();
            valid = false;
        }else {

        }
        if (tentruyen.isEmpty()) {
            nameeditText.setError("Nhập tên truyện");
            valid = false;
        } else {
            nameeditText.setError(null);
        }
        if (tacgia.isEmpty()) {
            tuoiEditText.setError("Nhập loại truyện");
            valid = false;
        } else {
            tuoiEditText.setError(null);
        }
        if (noidung.isEmpty()) {
            lopEditText.setError("Nhập nội dung truyện");
            valid = false;
        } else {
            lopEditText.setError(null);
        }




        return valid;
    }
    public boolean validateupdate() {
        boolean valid = true;

        String tentruyen = editTextName.getText().toString();
        String tacgia = editTexttuoi.getText().toString();
        String noidung = editTextlop.getText().toString();
        String hinh = imagehinh.toString();
        if(hinh.length()==0){
            Toast.makeText(this, "Thiếu hình", Toast.LENGTH_SHORT).show();
            valid = false;
        }else {

        }
        if (tentruyen.isEmpty()) {
            editTextName.setError("Nhập tên truyện");
            valid = false;
        } else {
            editTextName.setError(null);
        }
        if (tacgia.isEmpty()) {
            editTexttuoi.setError("Nhập loại truyện");
            valid = false;
        } else {
            editTexttuoi.setError(null);
        }
        if (noidung.isEmpty()) {
            editTextlop.setError("Nhập nội dung truyện");
            valid = false;
        } else {
            editTextlop.setError(null);
        }




        return valid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RE && resultCode == RESULT_OK && data!= null){
            uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imagehinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.filter(newText.trim());
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onRefresh() {
        new CountDownTimer(500,1000){

            @Override
            public void onTick(long millisUntilFinished) {


            }

            @Override
            public void onFinish() {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale_list);
                listView.startAnimation(animation);
                resetlayout.setRefreshing(false);
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_taikhoan) {
            startActivity(new Intent(MainActivity.this, MapsActivity.class));

        } else if (id == R.id.nav_yeuthich) {
            startActivity(new Intent(MainActivity.this, TiGiaActivity.class));

        } else if (id == R.id.nav_danhgia) {
                rateApp();
        } else if (id == R.id.nav_dangxuat) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Bán có muốn đăng xuất!");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseAuth.getInstance().signOut();
                    LoginManager.getInstance().logOut();
                    finish();
                    Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                    startActivity(intent);
                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialogBuilder.show();
            return true;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
public void rateApp()
{
    try
    {
        Intent rateIntent = rateIntentForUrl("details?id=com.fpoly.duyet.doctruyencuoi");
        startActivity(rateIntent);
    }
    catch (ActivityNotFoundException e)
    {
        Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details?id=com.fpoly.duyet.doctruyencuoi");
        startActivity(rateIntent);
    }
}

    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
        {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        }
        else
        {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }
}
