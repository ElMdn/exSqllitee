package com.example.exsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

    EditText e1, e2, e3, e4;
    MyDBContact db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = new MyDBContact(this);

        e1 = findViewById(R.id.enom);
        e2=findViewById(R.id.eprenom);
        e3=findViewById(R.id.eemail);
        e4=findViewById(R.id.etel);
    }

    public void ajouterContact(View view) {

        if(e1.getText().toString().isEmpty()){
            Toast.makeText(this, "Nom vide", Toast.LENGTH_SHORT).show();
            e1.requestFocus();
            return;
        }

        if(e2.getText().toString().isEmpty()){
            Toast.makeText(this, "Prenom vide", Toast.LENGTH_SHORT).show();
            e2.requestFocus();
            return;
        }
        if(e3.getText().toString().isEmpty()){
            Toast.makeText(this, "Email vide", Toast.LENGTH_SHORT).show();
            e3.requestFocus();
            return;
        }
        if(e4.getText().toString().isEmpty()){
            Toast.makeText(this, "Telephone vide", Toast.LENGTH_SHORT).show();
            e4.requestFocus();
            return;
        }
        Contact c = new Contact();

        c.setNom(e1.getText().toString());
        c.setPrenom(e2.getText().toString());
        c.setEmail(e3.getText().toString());
        c.setTel(e4.getText().toString());

        if(MyDBContact.insert_contact(db.getWritableDatabase(),c)==-1)
            Toast.makeText(this, "Insertion echoue", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Insertion reussie", Toast.LENGTH_SHORT).show();
    }

}