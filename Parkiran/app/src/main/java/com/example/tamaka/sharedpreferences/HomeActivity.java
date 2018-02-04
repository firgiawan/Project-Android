package com.example.tamaka.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView txname;
    private ImageView btnparkir, btnlokasi, btnmobil, btnexit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txname = (TextView) findViewById(R.id.tx_username);
        Intent inten = getIntent();
        String username = inten.getStringExtra("username");
        txname.setText(username);
        btnparkir = (ImageView) findViewById(R.id.btnparkir);
        btnmobil = (ImageView) findViewById(R.id.btnmobil);
        btnlokasi = (ImageView) findViewById(R.id.btnlokasi);
        btnexit = (ImageView) findViewById(R.id.btnexit);

        btnparkir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, Parkir.class));
                finish();
            }
        });

        btnmobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, Mobil.class));
                finish();
            }
        });

        btnlokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, Lokasi.class));
                finish();
            }
        });

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}
