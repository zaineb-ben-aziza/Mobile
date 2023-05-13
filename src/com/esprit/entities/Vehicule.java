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
public class Vehicule {
     private  int id;
    private  String marque;
     private  String vitesseMax;
      private  float chargeMaxsupp;
       private  String autoBatterie;
        private  String couleur;
         private  String typeVehicule;
          private float prix;

    public Vehicule(int id, String marque, float chargeMaxsupp, String autoBatterie, String couleur, String typeVehicule, float prix) {
        this.id = id;
        this.marque = marque;
        this.chargeMaxsupp = chargeMaxsupp;
        this.autoBatterie = autoBatterie;
        this.couleur = couleur;
        this.typeVehicule = typeVehicule;
        this.prix = prix;
    }
    
        public Vehicule( String marque,String vitesseMax, String autoBatterie, String couleur, String typeVehicule) {
    
        this.marque = marque;
        this.autoBatterie = autoBatterie;
        this.couleur = couleur;
        this.typeVehicule = typeVehicule;
     
    }
    
    
        public Vehicule( String marque, float chargeMaxsupp, String autoBatterie, String couleur, String typeVehicule, float prix) {
       
        this.marque = marque;
        this.chargeMaxsupp = chargeMaxsupp;
        this.autoBatterie = autoBatterie;
        this.couleur = couleur;
        this.typeVehicule = typeVehicule;
        this.prix = prix;
    }
          public Vehicule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getVitesseMax() {
        return vitesseMax;
    }

    public void setVitesseMax(String vitesseMax) {
        this.vitesseMax = vitesseMax;
    }

    public float getChargeMaxsupp() {
        return chargeMaxsupp;
    }

    public void setChargeMaxsupp(float chargeMaxsupp) {
        this.chargeMaxsupp = chargeMaxsupp;
    }

    public String getAutoBatterie() {
        return autoBatterie;
    }

    public void setAutoBatterie(String autoBatterie) {
        this.autoBatterie = autoBatterie;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(String typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "id=" + id + ", marque=" + marque + ", vitesseMax=" + vitesseMax + ", chargeMaxsupp=" + chargeMaxsupp + ", autoBatterie=" + autoBatterie + ", couleur=" + couleur + ", typeVehicule=" + typeVehicule + ", prix=" + prix + '}';
    }
          
          
          
    
}
