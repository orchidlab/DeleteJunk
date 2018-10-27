package com.deletejunk.rabbi;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gigamole.library.PulseView;


public class Splash_Screen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    LinearLayout layout;
    PulseView pulseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        layout=(LinearLayout) findViewById(R.id.layout);
        layout.animate().alpha(1).setDuration(900);
        pulseView = (PulseView) findViewById(R.id.pv);
        pulseView.startPulse();


        new Handler().postDelayed(new Runnable()
        {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run()
            {

                // Start your app main activity after time is over
                Intent i = new Intent(Splash_Screen.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
