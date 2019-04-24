package com.zhengzangw.gesturedetect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;

public class DemoActivity extends AppCompatActivity {

    private Button btnConnect;
    private ExecutorService mThreadPool;
    private Socket socket;
    BufferedReader in;

    @Override
    protected void onCreate(Bundle SavedInstanceStates){
        super.onCreate(SavedInstanceStates);
        setContentView(R.layout.activity_demo);

        mThreadPool = Executors.newCachedThreadPool();

        btnConnect = (Button) findViewById(R.id.Connect);
        btnConnect.setOnClickListener(clickListener);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.Connect:
                    mThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                socket = new Socket("192.168.1.101", 8989);
                                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    });
            }
        }
    };
}
