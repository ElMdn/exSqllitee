package com.example.exsqllite;

import java.io.Serializable;

public class Contact implements Serializable {

    private int id;
    private String Nom;
    private String Prenom;
    private String email;
    private String tel;

    public Contact() {
    }

    public Contact(int id, String nom, String prenom, String email, String tel) {
        this.id = id;
        Nom = nom;
        Prenom = prenom;
        this.email = email;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
