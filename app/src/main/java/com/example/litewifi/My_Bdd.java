package com.example.litewifi;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;

public class My_Bdd extends SQLiteOpenHelper {
    public static final String DBname ="data1.db";
    public My_Bdd(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table maTable (id INTEGER PRIMARY KEY aUTOINCREMENT, SSID String,level int,frequence int,MacAdr String,debit int,ip String,date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(db);
    }
    public boolean inserer_ligne(String ssid, int level, int frequence, String NomAdrsMac, int debit, String ip, Date Datee){
        String Date;
        SimpleDateFormat simpledateformat;
        Calendar calendar = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date = simpledateformat.format(calendar.getTime());
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("SSID",ssid);
        contentValues.put("level",level);
        contentValues.put("frequence",frequence);
        contentValues.put("MacAdr",NomAdrsMac);
        contentValues.put("debit",debit);
        contentValues.put("ip",ip);
        contentValues.put("date",Date);
        long result=db.insert("maTable",null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList getTousLignes(){
        ArrayList arrayList = new ArrayList<String>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res= db.rawQuery("Select * from maTable ", null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            String t1=res.getString(0);
            String t2=res.getString(1);
            String t3=res.getString(2);
            String t4=res.getString(3);
            String t5=res.getString(4);
            String t6=res.getString(5);
            String t7=res.getString(6);
            String t8=res.getString(7);
            arrayList.add("Name: "+t2+"\nLevel: "+t3+" \nFrequence: "+t4+"\nAdr Mac: "+t5+"\nDébit: "+t6+"\nAdr IP: "+t7+"\nDate: "+t8+"\n\n");
            res.moveToNext();
        }
        return arrayList;
    }
    public void clearDb()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS maTable");
        onCreate(db);
    }
}