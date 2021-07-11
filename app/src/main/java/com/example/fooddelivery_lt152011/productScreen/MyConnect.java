package com.example.fooddelivery_lt152011.productScreen;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import com.example.fooddelivery_lt152011.MainActivity;

public class MyConnect extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(periodicUpdate);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implement");
    }

    public boolean isConnection(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnectedOrConnecting()){
           return true;
        } else {
            return false;
        }
    }
    Handler handler = new Handler();
    //periodic = định kì :D
    private Runnable periodicUpdate = new Runnable() {
        @Override
        public void run() {

            handler.postDelayed(periodicUpdate, 1*1000- SystemClock.elapsedRealtime()%1000);
            Intent broadcastIntent =new Intent();
            broadcastIntent.setAction(MainActivity.BroadcastStrngforAction);
            broadcastIntent.putExtra("online_status",""+isConnection(MyConnect.this));
            sendBroadcast(broadcastIntent);
        }
    };
}
