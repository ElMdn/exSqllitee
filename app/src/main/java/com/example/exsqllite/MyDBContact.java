package com.example.exsqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBContact extends SQLiteOpenHelper {

    public static String DB_NAME="DBContact.db ";
    public static String TABLE_NAME="CONTACT ";
    public static String COL1="ID";
    public static String COL2="NOM";
    public static String COL3="PRENOM";
    public static String COL4="EMAIL";
    public static String COL5="TEL";


    public MyDBContact(Context c) {
        super(c, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql ="create table "+ TABLE_NAME + "("+COL1+" integer primary key autoincrement,"+COL2+" TEXT,"+COL3+" TEXT,"+COL4+" TEXT,"+COL5+" TEXT)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql ="DROP TABLE "+ TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }


    public static long insert_contact(SQLiteDatabase sqLiteDatabase , Contact C){
        ContentValues ct = new ContentValues();
        ct.put(COL2,C.getNom());
        ct.put(COL3,C.getPrenom());
        ct.put(COL4,C.getEmail());
        ct.put(COL5,C.getTel());

        return sqLiteDatabase.insert(TABLE_NAME,null,ct);
    }

    public static long update_contact(SQLiteDatabase sqLiteDatabase , Contact C){
        ContentValues ct = new ContentValues();
        ct.put(COL2,C.getNom());
        ct.put(COL3,C.getPrenom());
        ct.put(COL4,C.getEmail());
        ct.put(COL5,C.getTel());

        return sqLiteDatabase.update(TABLE_NAME,ct,"id="+C.getId(),null);
    }

    public static long delete_contact(SQLiteDatabase sqLiteDatabase , int id){

        return sqLiteDatabase.delete(TABLE_NAME,"id="+id,null);

    }

    public static ArrayList<Contact> getAllContact(SQLiteDatabase sqLiteDatabase){

        ArrayList<Contact>cntc = new ArrayList<>();

        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME , null);

        while (cur.moveToNext()){
            Contact c = new Contact();
            c.setId(cur.getInt(0));
            c.setNom(cur.getString(1));
            c.setPrenom(cur.getString(2));
            c.setEmail(cur.getString(3));
            c.setTel(cur.getString(4));
            cntc.add(c);
        }

        return cntc;
    }

    public static Contact getOneContact(SQLiteDatabase sqLiteDatabase , int id) {

        Contact c = null;

        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME+"WHERE id ="+id, null);

        while (cur.moveToNext()) {
            c = new Contact();
            c.setId(cur.getInt(0));
            c.setNom(cur.getString(1));
            c.setPrenom(cur.getString(2));
            c.setEmail(cur.getString(3));
            c.setTel(cur.getString(4));
        }

        return c;
    }
}
