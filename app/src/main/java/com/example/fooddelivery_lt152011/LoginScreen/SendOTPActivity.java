package com.example.fooddelivery_lt152011.LoginScreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery_lt152011.MainActivity;
import com.example.fooddelivery_lt152011.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {
    DbHelper dbHelper;
    public  static String phone = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_send_otpactivity);
        EditText inputMobile = findViewById( R.id.inputMobile);
        Button buttonGetOTP = findViewById( R.id.buttonGetOTP);
        dbHelper = new DbHelper(this);
        ProgressBar progressBar = findViewById( R.id.progressBar);
      //  checkuser();

        buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputMobile.getText().toString().trim().isEmpty()){
                    Toast.makeText(SendOTPActivity.this, "Enter Mobile", Toast.LENGTH_SHORT).show();

                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                buttonGetOTP.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber("+84"+inputMobile.getText().toString(),60, TimeUnit.SECONDS, SendOTPActivity.this,new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                    @Override
                    public void onVerificationCompleted(@NonNull  PhoneAuthCredential phoneAuthCredential) {
                        progressBar.setVisibility(View.GONE);
                        buttonGetOTP.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull  FirebaseException e) {
                        progressBar.setVisibility(View.GONE);
                        buttonGetOTP.setVisibility(View.INVISIBLE);
                        Toast.makeText(SendOTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d( "Log1","ádasd"+ e.getMessage());
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull  PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        progressBar.setVisibility(View.GONE);
                        buttonGetOTP.setVisibility(View.INVISIBLE);
                        Intent i =new Intent(getApplicationContext(), VerifyOTPActivity.class);
                        i.putExtra("mobile", inputMobile.getText().toString());
                        phone=inputMobile.getText().toString();
                        i.putExtra("verifycationId",s);
                        startActivity(i);
                    }
                });

            }
        });
    }
    public void checkuser(){
        if (dbHelper.getUser().getUserPhone()!=0){
            Intent i = new Intent(SendOTPActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}