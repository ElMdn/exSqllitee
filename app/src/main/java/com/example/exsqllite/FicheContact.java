package com.example.exsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class FicheContact extends AppCompatActivity {

    Spinner sp ;
    TextView tnom, tprenom,temail,ttel;
    MyDBContact db;
    ArrayList <Contact> cntc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_contact);

        db=new MyDBContact(this);
        sp=findViewById(R.id.spfiche);
        tnom=findViewById(R.id.fichenom);
        tprenom=findViewById(R.id.ficheprenom);
        temail=findViewById(R.id.ficheemail);
        ttel=findViewById(R.id.fichetel);

        cntc=MyDBContact.getAllContact(db.getReadableDatabase());

        ArrayList<String> nomcnct = new ArrayList<>();
        for(Contact ct :cntc)
            nomcnct.add(ct.getId()+ "-" +ct.getNom());

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,nomcnct);

        sp.setAdapter(ad);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Contact c = cntc.get(i);
                tnom.setText("Nom : "+c.getNom());
                tprenom.setText("Prenom : "+c.getPrenom());
                temail.setText("Email : "+c.getEmail());
                ttel.setText("Telephone :" +c.getTel());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}