/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;
import java.util.Date;
/**
 *
 * @author DELL
 */
public class Evenement {
    
    private int ID_event;
    private String Nom_event;
    private Date Date_debut; 
    private Date Date_fin; 
    private String Lieu_event;
    private int Nb_places ;
    private String Type_event; 
    private int Nb_vues;
    
   
 
    
    
    public Evenement (){
    
}
    public Evenement (int ID_event){ 
        this.ID_event=ID_event;
    
}
    public Evenement(int ID_event, String Nom_event, Date Date_debut, Date Date_fin, String Lieu_event, int Nb_places, String Type_event, int Nb_vues  ) {
        this.ID_event = ID_event;
        this.Nom_event = Nom_event;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.Lieu_event = Lieu_event;
        this.Nb_places = Nb_places;
        this.Type_event = Type_event;
        this.Nb_vues=Nb_vues;
        
    }

    public int getID_event() {
        return ID_event;
    }

    public String getNom_event() {
        return Nom_event;
    }

    public Date getDate_debut() {
        return Date_debut;
    }

    public Date getDate_fin() {
        return Date_fin;
    }

    public String getLieu_event() {
        return Lieu_event;
    }

    public int getNb_places() {
        return Nb_places;
    }

    public String getType_event() {
        return Type_event;
    }

    public int getNb_vues() {
        return Nb_vues;
    }


    public void setID_event(int ID_event) {
        this.ID_event = ID_event;
    }

    public void setNom_event(String Nom_event) {
        this.Nom_event = Nom_event;
    }

    public void setDate_debut(Date Date_debut) {
        this.Date_debut = Date_debut;
    }

    public void setDate_fin(Date Date_fin) {
        this.Date_fin = Date_fin;
    }

    public void setLieu_event(String Lieu_event) {
        this.Lieu_event = Lieu_event;
    }

    public void setNb_places(int Nb_places) {
        this.Nb_places = Nb_places;
    }

    public void setType_event(String Type_event) {
        this.Type_event = Type_event;
    }

    public void setNb_vues(int Nb_vues) {
        this.Nb_vues = Nb_vues;
    }

    

    @Override
    public String toString() {
        return "Evenement{" + "ID_event=" + ID_event + ", Nom_event=" + Nom_event + ", Date_debut=" + Date_debut + ", Date_fin=" + Date_fin + ", Lieu_event=" + Lieu_event + ", Nb_places=" + Nb_places + ", Type_event=" + Type_event + ", Nb_vues=" + Nb_vues + '}';
    }

   
    }

    

