package com.deletejunk.rabbi;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.Browser;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.util.List;
import java.util.Random;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    ImageView mainbrush,cache,temp,residue,system;
    TextView maintext,cachetext,temptext,residuetext,systemtext;
    public static ImageView mainbutton;

    int checkvar=0;
    int alljunk;

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    View view;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mainbrush=(ImageView) findViewById(R.id.mainbrush);
        mainbutton=(ImageView) findViewById(R.id.mainbutton);
        cache=(ImageView) findViewById(R.id.cache);
        temp=(ImageView) findViewById(R.id.temp);
        residue=(ImageView) findViewById(R.id.residue);
        system=(ImageView) findViewById(R.id.system);


        maintext=(TextView) findViewById(R.id.maintext);
        cachetext=(TextView) findViewById(R.id.cachetext);
        temptext =(TextView) findViewById(R.id.temptext);
        residuetext =(TextView) findViewById(R.id.residuetext);
        systemtext =(TextView) findViewById(R.id.systemtext);

        MobileAds.initialize(this,
                "ca-app-pub-0954165513199363~7831789587");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-0954165513199363/1785255981");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        try {

            sharedpreferences = getApplicationContext().getSharedPreferences("waseem", Context.MODE_PRIVATE);
            editor = sharedpreferences.edit();
            editor.putString("junk", "1");
            editor.commit();


            if (sharedpreferences.getString("junk", "1").equals("1")) {
                mainbrush.setImageResource(R.drawable.junk_red);
                mainbutton.setImageResource(R.drawable.clean);
                cache.setImageResource(R.drawable.cache);
                temp.setImageResource(R.drawable.temp);
                residue.setImageResource(R.drawable.res);
                system.setImageResource(R.drawable.sys);

                Random ran1 = new Random();
                final int proc1 = ran1.nextInt(20) + 5;

                Random ran2 = new Random();
                final int proc2 = ran2.nextInt(15) + 10;

                Random ran3 = new Random();
                final int proc3 = ran3.nextInt(30) + 15;

                Random ran4 = new Random();
                final int proc4 = ran4.nextInt(25) + 10;

                alljunk = proc1 + proc2 + proc3 + proc4;
                startCountAnimation();
                maintext.setTextColor(Color.parseColor("#F22938"));

                cachetext.setText(proc1 + " MB");
                cachetext.setTextColor(Color.parseColor("#ffffff"));

                temptext.setText(proc2 + " MB");
                temptext.setTextColor(Color.parseColor("#ffffff"));

                residuetext.setText(proc3 + " MB");
                residuetext.setTextColor(Color.parseColor("#ffffff"));

                systemtext.setText(proc4 + " MB");
                systemtext.setTextColor(Color.parseColor("#ffffff"));

            } else {
                mainbrush.setImageResource(R.drawable.junk_blue);
                mainbutton.setImageResource(R.drawable.cleaned);
                cache.setImageResource(R.drawable.cache2);
                temp.setImageResource(R.drawable.temp2);
                residue.setImageResource(R.drawable.res2);
                system.setImageResource(R.drawable.sys2);

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                maintext.setText("CRYSTAL CLEAR");
                maintext.setTextColor(Color.parseColor("#24D149"));

                cachetext.setText(0 + " MB");
                cachetext.setTextColor(Color.parseColor("#24D149"));

                temptext.setText(0 + " MB");
                temptext.setTextColor(Color.parseColor("#24D149"));

                residuetext.setText(0 + " MB");
                residuetext.setTextColor(Color.parseColor("#24D149"));

                systemtext.setText(0 + " MB");
                systemtext.setTextColor(Color.parseColor("#24D149"));
            }


            mainbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (sharedpreferences.getString("junk", "1").equals("1")) {

                        Intent i = new Intent(getApplicationContext(), Sacnning_Junk.class);
                        i.putExtra("junk", alljunk + "");
                        startActivity(i);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms


                                mainbrush.setImageResource(R.drawable.junk_blue);
                                mainbutton.setImageResource(R.drawable.cleaned);
                                cache.setImageResource(R.drawable.cache2);
                                temp.setImageResource(R.drawable.temp2);
                                residue.setImageResource(R.drawable.res2);
                                system.setImageResource(R.drawable.sys2);


                                maintext.setText("CRYSTAL CLEAR");
                                maintext.setTextColor(Color.parseColor("#16306E"));

                                cachetext.setText(0 + " MB");
                                cachetext.setTextColor(Color.parseColor("#24D149"));

                                temptext.setText(0 + " MB");
                                temptext.setTextColor(Color.parseColor("#24D149"));

                                residuetext.setText(0 + " MB");
                                residuetext.setTextColor(Color.parseColor("#24D149"));

                                systemtext.setText(0 + " MB");
                                systemtext.setTextColor(Color.parseColor("#24D149"));


                                editor = sharedpreferences.edit();
                                editor.putString("junk", "0");
                                editor.commit();


                                Intent intent = new Intent(getApplicationContext(), JunkAlaram.class);

                                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0,
                                        intent, PendingIntent.FLAG_ONE_SHOT);

                                AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
                                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (600 * 1000), pendingIntent);

                            }
                        }, 2000);
                    } else {
//                        Toast.makeText(getActivity(), "No Junk Files ALready Cleaned.", Toast.LENGTH_LONG).show();

                        @SuppressLint("RestrictedApi") LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.mytoast, null);

                        ImageView image = (ImageView) layout.findViewById(R.id.image);

                        TextView text = (TextView) layout.findViewById(R.id.textView1);
                        text.setText("No Junk Files Already Cleaned 100%.");

                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 70);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("TAG", "The interstitial wasn't loaded yet.");
                        }
                        toast.show();

                    }
                }
            });


//            Random ran1 = new Random();
//            final int proc1 = ran1.nextInt(20) + 5;
//
//            Random ran2 = new Random();
//            final int proc2 = ran2.nextInt(15) + 10;
//
//            Random ran3 = new Random();
//            final int proc3 = ran3.nextInt(30) + 15;
//
//            Random ran4 = new Random();
//            final int proc4 = ran4.nextInt(25) + 10;
//
//            alljunk=proc1+proc2+proc3+proc4;
//
//            maintext.setText(alljunk+" MB");
//            maintext.setTextColor(Color.parseColor("#F22938"));
//
//            cachetext.setText(proc1+" MB");
//            cachetext.setTextColor(Color.parseColor("#F22938"));
//
//            temptext.setText(proc2+" MB");
//            temptext.setTextColor(Color.parseColor("#F22938"));
//
//            residuetext.setText(proc3+" MB");
//            residuetext.setTextColor(Color.parseColor("#F22938"));
//
//            systemtext.setText(proc4+" MB");
//            systemtext.setTextColor(Color.parseColor("#F22938"));

        }
        catch (Exception e)
        {

        }


    }
    private void startCountAnimation()
    {

        ValueAnimator animator = ValueAnimator.ofInt(0, alljunk);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                maintext.setText(animation.getAnimatedValue().toString()+" MB");

            }
        });
        animator.start();
    }



}




