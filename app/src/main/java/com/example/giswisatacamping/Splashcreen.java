package com.example.giswisatacamping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splashcreen extends Activity {

    private final int panjang_splash=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashcreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent tampil= new Intent(Splashcreen.this,MainActivity.class);
                Splashcreen.this.startActivity(tampil);
                Splashcreen.this.finish();
            }
        }, panjang_splash);

    }
}
