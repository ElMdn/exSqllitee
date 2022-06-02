package com.example.exsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact>cnct=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cnct.add((new Contact(4,"jabri","hajar","hajar-jabri@gmail.com","0678987654")));
        cnct.add((new Contact(5,"khoucha","fatima","fatima-khocha@gmail.com","0678987654")));



    }

    public void acces(View view) {

        Intent i = null;
        switch (view.getId()){
            case R.id.b1:i=new Intent(MainActivity.this, ListerContact.class); break;
            case R.id.b2:i=new Intent(MainActivity.this, FicheContact.class); break;
            case R.id.b3:i=new Intent(MainActivity.this, AddContact.class); break;
            case R.id.b4:i=new Intent(MainActivity.this, EditContact.class); break;
        }
        startActivity(i);
    }
}