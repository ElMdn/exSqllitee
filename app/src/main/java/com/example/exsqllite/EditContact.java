package com.example.exsqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditContact extends AppCompatActivity {

    Spinner sp ;
    EditText enom, eprenom, eemail,etel;
    MyDBContact db ;
    ArrayList<Contact>cntc;
    ArrayAdapter ad;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        db=new MyDBContact(this);
        sp=findViewById(R.id.spedit);
        enom=findViewById(R.id.editnom);
        eprenom=findViewById(R.id.editprenom);
        eemail=findViewById(R.id.editemail);
        etel=findViewById(R.id.edittel);

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
                enom.setText("Nom : "+c.getNom());
                eprenom.setText("Prenom : "+c.getPrenom());
                eemail.setText("Email : "+c.getEmail());
                etel.setText("Telephone :" +c.getTel());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void modifier(View view) {
        AlertDialog.Builder alter = new AlertDialog.Builder(EditContact.this);
        alter.setTitle("Modifier Contact");
        alter.setMessage("Voulez-vous modifier ce Contact");
        alter.setIcon(R.drawable.updatee);
        
        alter.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Contact c = cntc.get(sp.getSelectedItemPosition());
                
                c.setNom(enom.getText().toString());
                c.setPrenom(eprenom.getText().toString());
                c.setEmail(eemail.getText().toString());
                c.setTel(etel.getText().toString());
                
                if(MyDBContact.update_contact(db.getWritableDatabase(),c)==-1)
                    Toast.makeText(EditContact.this, "Modification echoue", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(EditContact.this, "Modification reussie", Toast.LENGTH_SHORT).show();
            }
        });
        
        alter.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(EditContact.this, "Operation annulee", Toast.LENGTH_SHORT).show();
            }
        });
        alter.show();
    }

    public void supprimer(View view) {
        AlertDialog.Builder alter = new AlertDialog.Builder(EditContact.this);
        alter.setTitle("Suppression Contact");
        alter.setMessage("Voulez-vous Supprimer ce Contact");
        alter.setIcon(R.drawable.deletee);
        
        alter.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Contact c =cntc.get(sp.getSelectedItemPosition());
                
                if(MyDBContact.delete_contact(db.getWritableDatabase(),c.getId())==-1)
                    Toast.makeText(EditContact.this, "suppression echoue", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(EditContact.this, "suppression reussie", Toast.LENGTH_SHORT).show();
                    ad.remove(c.getId()+"-"+c.getNom());
                }
            }
        });

        alter.setNegativeButton("Anuuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(EditContact.this, "operation annulee", Toast.LENGTH_SHORT).show();
            }
        });

        alter.show();
    }
}