package com.example.admin.firebaselistview;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main3Activity extends AppCompatActivity {
    private  LoginButton loginButton;
    private CallbackManager callbackManager;
    String TAG = "Main3Activity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static CardView bntdangnhap;
    public static EditText editemail,editpass;
    TextView txtdangky;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

// cái này của dang kí
EditText editemaildk,editpassdk;
    Button bntdangky;
    TextView link_login;
    EditText _nameText,_reEnterPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main3);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


        AnhXa();
        Main3Activity.this.setTitle("Đăng Nhập");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
         final Profile profile = Profile.getCurrentProfile();
        if (user != null || profile != null){
            startActivity(new Intent(Main3Activity.this,MainActivity.class));
            finish();
        }
        callbackManager =CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.btnlogin);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
                Profile profile1 = Profile.getCurrentProfile();
                myRef.child("TaiKhoan").child(profile1.getId()).child("NguoiDung").setValue(new NguoiDung("example@gmail.com","123456"));
                Intent intent = new Intent(Main3Activity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);

            }
        });
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user   = firebaseAuth.getCurrentUser();
                if(user!=null){
                    Log.d(TAG,"onAuthStateChanger:signed_in" + user.getUid());
                }else {
                    Log.d(TAG,"onAuthStateChanger:signed_out");
                }
            }
        };

        bntdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (!validate()) {
                        Toast.makeText(Main3Activity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        DangNhap();
                        final ProgressDialog progressDialog = new ProgressDialog(Main3Activity.this,
                                R.style.AppTheme_Dark_Dialog);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage("Đăng nhập...");
                        progressDialog.show();



                    }



                }

        });
        txtdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Main3Activity.this);
                dialog.setContentView(R.layout.activity_dang__ky);
                dialog.show();
                dialog.setTitle("Đăng Ký");

                _nameText = (EditText) dialog.findViewById(R.id.input_name);
                _reEnterPasswordText=(EditText)dialog.findViewById(R.id.input_reEnterPassword);
                editemaildk = (EditText)dialog.findViewById(R.id.editemail);
                editpassdk = (EditText)dialog.findViewById(R.id.editpass);
                bntdangky = (Button) dialog.findViewById(R.id.bntdangky);

                bntdangky.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!validate2()) {
                            Toast.makeText(Main3Activity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            DangKy();
                            editemail.setText(editemaildk.getText().toString().trim());
                            editpass.setText(editpassdk.getText().toString().trim());

                            dialog.dismiss();
                        }
                    }
                });

            }
        });

}

    private void DangNhap(){

        final String email = editemail.getText().toString();
        String password = editpass.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(Main3Activity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Main3Activity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        }else {
                            Toast.makeText(Main3Activity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }


    private void AnhXa (){
        bntdangnhap=(CardView) findViewById(R.id.bntdangnhap);
        editemail = (EditText)findViewById(R.id.editemail);
        editpass =(EditText)findViewById(R.id.editpass);
        txtdangky = (TextView)findViewById(R.id.txtdangky);


    }
    private void handleFacebookAccessToken(AccessToken token){
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG,"signInWithCredential:onComplete:"+task.isSuccessful());
                        if(!task.isSuccessful()){
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Main3Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data );
    }
    public boolean validate() {
        boolean valid = true;

        String email = editemail.getText().toString();
        String password = editpass.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemail.setError("Nhập Email");
            valid = false;
        } else {
            editemail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editpass.setError("Nhập mật khẩu lớn hơn 4 kí tự và nhỏ hơn 10");
            valid = false;
        } else {
            editpass.setError(null);
        }

        return valid;
    }
    private void DangKy(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        final String email = editemaildk.getText().toString();
        final String password = editpassdk.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            myRef.child("TaiKhoan").child(user.getUid()).child("NguoiDung").setValue(new NguoiDung(email,password));
                            Toast.makeText(Main3Activity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(Main3Activity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public boolean validate2() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = editemaildk.getText().toString();
        String password = editpassdk.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("Nhập tên");
            valid = false;
        } else {
            _nameText.setError(null);
        }



        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemaildk.setError("Nhập email");
            valid = false;
        } else {
            editemaildk.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editpassdk.setError("Nhập mật khẩu lớn hơn 4 kí tự và nhỏ hơn 10");
            valid = false;
        } else {
            editpassdk.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Nhập lại mât khẩu sai");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }

}
