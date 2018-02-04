package com.example.tamaka.sharedpreferences;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tamaka on 31/01/18.
 */

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "parkiran.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table parkir(no integer primary key, nama_mobil text null, jenis_mobil text null, plat text null, jam_masuk text null, jam_keluar text null, lokasi text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO parkir (no, nama_mobil, jenis_mobil, plat, jam_masuk, jam_keluar, lokasi) VALUES ('1', 'Pajero Sport', 'SUV', 'E 6 OIS','10:30','11:30', 'A1-VIP');";
        db.execSQL(sql);

        String sql1 = "create table mobil(no integer primary key, nama_mobil text null, jenis_mobil text null);";
        Log.d("Data", "onCreate: " + sql1);
        db.execSQL(sql1);
        sql1 = "INSERT INTO mobil (no, nama_mobil, jenis_mobil) VALUES ('1', 'Pajero Sport', 'SUV');";
        db.execSQL(sql1);

        String sql2 = "create table lokasi(no integer primary key, lokasi text null, keterangan);";
        Log.d("Data", "onCreate: " + sql2);
        db.execSQL(sql2);
        sql2 = "INSERT INTO lokasi (no, lokasi, keterangan) VALUES ('1', 'A1-VIP', 'Parkiran VIP');";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}
