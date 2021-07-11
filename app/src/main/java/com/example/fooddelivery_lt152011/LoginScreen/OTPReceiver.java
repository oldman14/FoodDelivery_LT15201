package com.example.fooddelivery_lt152011.LoginScreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;

public class OTPReceiver extends BroadcastReceiver {
    private static EditText edt1;
    private static EditText edt2;
    private static EditText edt3;
    private static EditText edt4;
    private static EditText edt5;
    private static EditText edt6;

    public void setText(EditText edt1,EditText edt2,EditText edt3,EditText edt4,EditText edt5,EditText edt6)
    {
        OTPReceiver.edt1 = edt1;
        OTPReceiver.edt2 = edt2;
        OTPReceiver.edt3 = edt3;
        OTPReceiver.edt4 = edt4;
        OTPReceiver.edt5 = edt5;
        OTPReceiver.edt6 = edt6;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (SmsMessage sms : messages)
        {
            String message = sms.getMessageBody();
            String otp = message.split("is ")[1];
            char otp0 = otp.charAt(0);
            char otp1 = otp.charAt(1);
            char otp2 = otp.charAt(2);
            char otp3 = otp.charAt(3);
            char otp4 = otp.charAt(4);
            char otp5 = otp.charAt(5);
            edt1.setText(String.valueOf(otp0));
            edt2.setText(String.valueOf(otp1));
            edt3.setText(String.valueOf(otp2));
            edt4.setText(String.valueOf(otp3));
            edt5.setText(String.valueOf(otp4));
            edt6.setText(String.valueOf(otp5));
        }
    }
}
