package com.example.shipper_lt15201.LoginScreen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.shipper_lt15201.HomeActivity;
import com.example.shipper_lt15201.Notification.SharedPreference;
import com.example.shipper_lt15201.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class VerifyOTPActivity extends AppCompatActivity implements Serializable {
    EditText input1, input2, input3, input4, input5, input6;
    String verifycationId;
    ShipperDAO dao;
    DbHelper dbHelper;
    TextView textMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_verify_otpactivity);

         textMobile = findViewById(R.id.textMobile);
        textMobile.setText(String.format("+84-%s", getIntent().getStringExtra("mobile")));
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button buttonVerify = findViewById(R.id.buttonVerify);
        TextView resendOTP = findViewById(R.id.textResendOTP);
        input1 = findViewById(R.id.inputCode1);
        input2 = findViewById(R.id.inputCode2);
        input3 = findViewById(R.id.inputCode3);
        input4 = findViewById(R.id.inputCode4);
        input5 = findViewById(R.id.inputCode5);
        input6 = findViewById(R.id.inputCode6);
        requestSMSPermission();
        new OTPReceiver().setText(input1,input2,input3,input4,input5,input6);

        dao = new ShipperDAO(this);
        dbHelper = new DbHelper(this);
        setupOTPInputs();
        verifycationId = getIntent().getStringExtra("verifycationId");
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input1.getText().toString().trim().isEmpty() || input2.getText().toString().trim().isEmpty() || input3.getText().toString().trim().isEmpty() || input4.getText().toString().trim().isEmpty() || input5.getText().toString().trim().isEmpty() || input6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(VerifyOTPActivity.this, "Please Enter Valid Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code  = input1.getText().toString()+input2.getText().toString()+input3.getText().toString()+input4.getText().toString()+input5.getText().toString()+input6.getText().toString();
                if (verifycationId!=null){
                    progressBar.setVisibility(View.VISIBLE);
                    buttonVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verifycationId, code);
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            buttonVerify.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()){
                                final String nPhone =getIntent().getStringExtra("mobile");
                                final String token = SharedPreference.getInstance(VerifyOTPActivity.this).getDeviceToken();
                                Log.d("Log12","aaa"+token);
                                Log.d("Log1234","aa21a"+nPhone);
                                ModelStatusShipper status = dao.loginRegisDevice( Integer.parseInt( nPhone ), token );
                                Toast.makeText(VerifyOTPActivity.this, status.getMessage(), Toast.LENGTH_SHORT).show();
//                                if (status.getError()==false){
//                                    ModelShipper newUser = dao.getUser(nPhone);
//                                    dbHelper.addUser(newUser);
//                                }

                                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            }else {
                                Toast.makeText(VerifyOTPActivity.this, "The verificaton code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+84"+getIntent().getStringExtra("mobile"),60, TimeUnit.SECONDS, VerifyOTPActivity.this,new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                    @Override
                    public void onVerificationCompleted(@NonNull  PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull  PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        verifycationId = s;
                        Toast.makeText(VerifyOTPActivity.this, "OTP send", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setupOTPInputs() {
        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    input2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    input3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    input4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    input5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    input6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void requestSMSPermission()
    {
        String permission = Manifest.permission.RECEIVE_SMS;

        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED)
        {
            String[] permission_list = new String[1];
            permission_list[0] = permission;

            ActivityCompat.requestPermissions(this, permission_list,1);
        }
    }

}