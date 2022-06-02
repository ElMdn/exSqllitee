package com.example.exsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListerContact extends AppCompatActivity {

    MyDBContact db ;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister_contact);

        db=new MyDBContact(this);
        lst=findViewById(R.id.lst);

        ArrayList<Contact> cnct =MyDBContact.getAllContact(db.getReadableDatabase());

        ArrayList<String> nomconct = new ArrayList<>();
        for(Contact cc :cnct)
            nomconct.add((cc.getId()+"-"+cc.getNom()));

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,nomconct);

        lst.setAdapter(ad);
    }
}