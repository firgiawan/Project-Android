package com.example.tamaka.sharedpreferences;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class BuatLokasi extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2, ton3;
    EditText text1, text2, text3;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_lokasi);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.nomor);
        text2 = (EditText) findViewById(R.id.lokasi);
        text3 = (EditText) findViewById(R.id.keterangan);
        ton1 = (Button) findViewById(R.id.simpan);
        ton2 = (Button) findViewById(R.id.kembali);
        ton3 = (Button) findViewById(R.id.take);
        mImageView = (ImageView) findViewById(R.id.imgPreview);


        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into lokasi(no, lokasi, keterangan) values('" +
                        text1.getText().toString() + "','" +
                        text2.getText().toString() + "','" +
                        text3.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                Lokasi.lok.RefreshList();
                finish();
            }
        });

        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                finish();
            }
        });

        ton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
            }
        });

        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(), "Camera di device anda tidak tersedia", Toast.LENGTH_LONG).show();
        }

    }

    private void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(takePictureIntent, 200);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        if (requestCode == 200 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setVisibility(View.VISIBLE);
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // jika device ini punya kamera
            return true;
        } else {
            // jika device ini tidak punya kamera
            return false;
        }
    }
}
