package dev.jgo.com.threadcirclebar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar bar;
    private ProgressDialog circleBar;
    private Button b1;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();
    private long jgoAge = 0;
    private static final String TAG = "threadsAndroid";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar)findViewById(R.id.progressBar1);
        b1=(Button)findViewById(R.id.button1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleBar = new ProgressDialog(v.getContext());
                circleBar.setCancelable(true);
                circleBar.setMessage("Testing in progress...");
                circleBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                circleBar.setProgress(0);
                circleBar.setMax(100);
                circleBar.show();
                progressBarStatus = 0;

                jgoAge = 0;
                new Thread(new Runnable() {
                    public void run() {
                        while (progressBarStatus < 100) {

                            progressBarStatus = jgoBirthday();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            progressBarbHandler.post(new Runnable() {
                                public void run() {
                                    circleBar.setProgress(progressBarStatus);
                                }
                            });
                        }

                        if (progressBarStatus >= 100) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            circleBar.dismiss();
                        }
                    }
                }).start();
            }
        });


    }
    public int jgoBirthday(){
        while(jgoAge<=28){
            jgoAge++;


            if(jgoAge==8){
                return 10;
            }
            else if(jgoAge==15){
                return 15;
            }
            else if(jgoAge==20){
                return 50;
            }
            else if(jgoAge==25){
                return 70;
            }

            else if(jgoAge==27){
                return 80;
            }

        }

        return 100;

    }

}
