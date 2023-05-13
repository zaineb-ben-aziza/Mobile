/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.assurtous;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
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
public class ModifierVehicule extends BaseForm{
    Form current;
    public ModifierVehicule(Resources res, Vehicule c) {
        super("Modifier Vehicule",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Modifier Constat");
        getContentPane().setScrollVisible(false);
        
        //

       TextField marque = new TextField(c.getMarque(), "marque");
        marque.setUIID("TextFieldBlack");
        addStringValue("marque :",marque);
        
        TextField vitesseMax = new TextField(c.getVitesseMax(), "vitesseMax");
        vitesseMax.setUIID("TextFieldBlack");
        addStringValue("vitesseMax :",vitesseMax);
        
        
         TextField chargeMaxsupp = new TextField(String.valueOf(c.getChargeMaxsupp()), "chargeMaxsupp");
        chargeMaxsupp.setUIID("TextFieldBlack");
        addStringValue("chargeMaxsupp :",chargeMaxsupp);
        
        
        TextField autoBatterie = new TextField(c.getAutoBatterie(), "autoBatterie");
        autoBatterie.setUIID("TextFieldBlack");
        addStringValue("autoBatterie :",autoBatterie);
        
          TextField couleur = new TextField(c.getCouleur(), "couleur");
        couleur.setUIID("TextFieldBlack");
        addStringValue("couleur :",couleur);
        
          TextField typeVehicule = new TextField(c.getTypeVehicule(), "typeVehicule");
        typeVehicule.setUIID("TextFieldBlack");
        addStringValue("typeVehicule :",typeVehicule);
        
        
           TextField prix = new TextField(String.valueOf(c.getPrix()),"prix");
        prix.setUIID("TextFieldBlack");
        addStringValue("prix :",prix);

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> new ListVehicules(res).show());
        
        Button btnModifier = new Button("Modifier");
        
        //onclick button event 
        btnModifier.addActionListener((e) -> {
                          
                if(marque.getText().equals("") || vitesseMax.getText().equals("") ) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }else{
                    System.out.println("Je commence !");
                    InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
                    //njibo iduser men session (current user)

                  
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

                    System.out.println("Modifying a vehicule !! ");
                    System.out.println("data  Reponse == "+c);

                    //appelle methode ajouter mt3 service bch nzido données ta3na fi base 
                    VehiculeService.getInstance().ModifierVehicule(c);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    //ba3d l ajout nemchiw lel affichage
                    new ListVehicules(res).show();
                    refreshTheme();//Actualisation
                }
           
        });
        
        Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonContainer.addAll(btnAnnuler, btnModifier);

        Container mainContainer = new Container(new BorderLayout());
        mainContainer.add(BorderLayout.CENTER, buttonContainer);

        // add the mainContainer to the form
        add(mainContainer);

    }


    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
}