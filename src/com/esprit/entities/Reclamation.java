/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Reclamation {
   private int id;
      private String nom;
   private String prenom;
   private String adresse;

   private String contenu;
   private Date datecreation;
   private int etat;
   private int idV;

    public Reclamation(int id, String nom, String prenom, String adresse, String contenu, Date datecreation, int etat, int idV) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.contenu = contenu;
        this.datecreation = datecreation;
        this.etat = etat;
        this.idV = idV;
    }

    public Reclamation(String nom, String prenom, String adresse, String contenu, Date datecreation, int etat, int idV) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.contenu = contenu;
        this.datecreation = datecreation;
        this.etat = etat;
        this.idV = idV;
    }

    public Reclamation() {
    }

    public Reclamation(String nom, String prenom, String adresse, String contenu, Date datecreation, int etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.contenu = contenu;
        this.datecreation = datecreation;
        this.etat = etat;
    }

    public Reclamation(String toString, String toString0, String format, int i, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getContenu() {
        return contenu;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public int getEtat() {
        return etat;
    }

    public int getIdV() {
        return idV;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setIdV(int idV) {
        this.idV = idV;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", contenu=" + contenu + ", datecreation=" + datecreation + ", etat=" + etat + ", idV=" + idV + '}';
    }
      
}
