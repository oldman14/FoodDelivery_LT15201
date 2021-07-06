package com.example.shipper_lt15201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Thread thread;
    Button btnnext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        btnnext=findViewById( R.id.next );

   thread = new Thread( new Runnable() {
            @Override
            public void run() {
                try
                {
                    Thread.sleep(5000);

                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
    });

    thread.start();

    btnnext.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(MainActivity.this,HomeActivity.class);
            startActivity( i );
        }
    } );


    }
}