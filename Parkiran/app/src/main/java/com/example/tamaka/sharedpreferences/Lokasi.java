package com.example.tamaka.sharedpreferences;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Lokasi extends AppCompatActivity {

    String[] daftar;
    ListView ListView02;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static Lokasi lok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi);

        Button btn1= (Button) findViewById(R.id.btnkembali);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Lokasi.this, HomeActivity.class);
                startActivity(i);
            }
        });

        Button btn=(Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent inte = new Intent(Lokasi.this, BuatLokasi.class);
                startActivity(inte);
            }
        });
        lok = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM lokasi",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView02 = (ListView)findViewById(R.id.listView2);
        ListView02.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView02.setSelected(true);
        ListView02.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Lokasi", "Update Lokasi", "Hapus Lokasi"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Lokasi.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), LihatLokasi.class);
                                i.putExtra("lokasi", selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(getApplicationContext(), UpdateLokasi.class);
                                in.putExtra("lokasi", selection);
                                startActivity(in);
                                break;
                            case 2 :
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from lokasi where lokasi = '"+selection+"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView02.getAdapter()).notifyDataSetInvalidated();
    }
}
