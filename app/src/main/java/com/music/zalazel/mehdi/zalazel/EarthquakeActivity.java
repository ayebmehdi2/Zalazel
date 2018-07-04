package com.music.zalazel.mehdi.zalazel;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.music.zalazel.mehdi.zalazel.DYFI.Main;

public class EarthquakeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        final MediaPlayer mm = MediaPlayer.create(EarthquakeActivity.this, R.raw.aa);

        Button quack = findViewById(R.id.quack);
        quack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mm.start();
                Intent i = new Intent(EarthquakeActivity.this, Quack.class);
                startActivity(i);
            }
        });

        Button tsunamii = findViewById(R.id.foot);
        tsunamii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mm.start();
                Intent i = new Intent(EarthquakeActivity.this, tsaunamii.class);
                startActivity(i);
            }
        });

        Button dyfi =  findViewById(R.id.dyfi);
        dyfi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mm.start();
                Intent i = new Intent(EarthquakeActivity.this, Main.class);
                startActivity(i);
            }
        });

    }





}
