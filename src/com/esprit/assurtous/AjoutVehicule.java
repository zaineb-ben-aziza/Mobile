/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.assurtous;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Vehicule;
import com.esprit.services.VehiculeService;

/**
 *
 * @author ASUS
 */
public class AjoutVehicule extends BaseForm{
    Form current;
    public AjoutVehicule(Resources res) {
        super("Ajouter Vehicule",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Ajout Vehicule");
        getContentPane().setScrollVisible(false);
        
        //

        TextField marque = new TextField("", "marque");
        marque.setUIID("TextFieldBlack");
        addStringValue("marque :",marque);
        
        TextField vitesseMax = new TextField("", "vitesseMax");
        vitesseMax.setUIID("TextFieldBlack");
        addStringValue("vitesseMax :",vitesseMax);
        
        
         TextField chargeMaxsupp = new TextField("", "chargeMaxsupp");
        chargeMaxsupp.setUIID("TextFieldBlack");
        addStringValue("chargeMaxsupp :",chargeMaxsupp);
        
        
        TextField autoBatterie = new TextField("", "autoBatterie");
        autoBatterie.setUIID("TextFieldBlack");
        addStringValue("autoBatterie :",autoBatterie);
        
          TextField couleur = new TextField("", "couleur");
        couleur.setUIID("TextFieldBlack");
        addStringValue("couleur :",couleur);
        
          TextField typeVehicule = new TextField("", "typeVehicule");
        typeVehicule.setUIID("TextFieldBlack");
        addStringValue("typeVehicule :",typeVehicule);
        
        
          TextField prix = new TextField("", "prix");
        prix.setUIID("TextFieldBlack");
        addStringValue("prix :",prix);
        
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> new ListVehicules(res).show());
        
        Button btnAjouter = new Button("Ajouter");
        
        //onclick button event 
        btnAjouter.addActionListener((e) -> {
            try {
                
                
                   
                    InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
                    //njibo iduser men session (current user)
                    Vehicule c = new Vehicule();
                    c.setMarque(marque.getText());
                     c.setVitesseMax(vitesseMax.getText());
                     
                     String chargeMaxsuppString = chargeMaxsupp.getText();
float chargeMaxsuppFloat = Float.parseFloat(chargeMaxsuppString);
c.setChargeMaxsupp(chargeMaxsuppFloat);

                     c.setAutoBatterie(autoBatterie.getText());
                    c.setCouleur(couleur.getText());
                    c.setTypeVehicule(typeVehicule.getText());
                    
                    String prixString = prix.getText();
float prixFloat = Float.parseFloat(prixString);
c.setPrix(prixFloat);


                    System.out.println("Adding a new vehicule !! ");
                    System.out.println("data  Constat == "+c);

                    //appelle methode ajouter mt3 service bch nzido données ta3na fi base 
                    VehiculeService.getInstance().ajoutVehicule(c);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    //ba3d l ajout nemchiw lel affichage
                    new ListVehicules(res).show();
                    refreshTheme();//Actualisation
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
        });
        
        add(BoxLayout.encloseY(
        btnAnnuler,
        btnAjouter
));

    }


    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
}