package com.example.tamaka.sharedpreferences;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateParkir extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5, text6, text7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_parkir);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.nomor);
        text2 = (EditText) findViewById(R.id.nama);
        text3 = (EditText) findViewById(R.id.jenis);
        text4 = (EditText) findViewById(R.id.plat);
        text5 = (EditText) findViewById(R.id.jam_masuk);
        text6 = (EditText) findViewById(R.id.jam_keluar);
        text7 = (EditText) findViewById(R.id.lokasi);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM parkir WHERE nama_mobil = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
            text6.setText(cursor.getString(5).toString());
            text7.setText(cursor.getString(6).toString());
        }

        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update parkir set nama_mobil='"+ text2.getText().toString() +
                        "', jenis_mobil='" + text3.getText().toString()+
                        "', plat='"+ text4.getText().toString() +
                        "', jam_masuk='" + text5.getText().toString() +
                        "', jam_keluar='" + text6.getText().toString() +
                        "', lokasi='" + text7.getText().toString() +
                        "' where no='" + text1.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                Parkir.par.RefreshList();
                finish();
            }
        });
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                finish();
            }
        });
    }
}
