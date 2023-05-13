/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.assurtous;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Vehicule;

/**
 *
 * @author ASUS
 */
public class VehiculeDetails extends BaseForm{
    Form current;
    public VehiculeDetails(Resources res, Vehicule c) {
        super("Details Vehicule",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
                    Container cnt = new Container(new BorderLayout());

        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Ajout Vehicule");
        getContentPane().setScrollVisible(false);
        
        //
        Label marque = new Label("marque: " +c.getMarque(),"NewsTopLine2");
        Label vitessemax = new Label("vitessemax: " +c.getVitesseMax(),"NewsTopLine2");
        Label chargeMaxsupp = new Label("chargeMaxsupp : "+c.getChargeMaxsupp(),"NewsTopLine2");
         Label AutoBatterie = new Label("autoBatterie: " +c.getAutoBatterie(),"NewsTopLine2");
        Label couleur = new Label("couleur: " +c.getCouleur(),"NewsTopLine2");
         Label TypeVehicule = new Label("TypeVehicule: " +c.getTypeVehicule(),"NewsTopLine2");
        Label prix = new Label("prix: " +c.getPrix(),"NewsTopLine2");
      
        

        
       
        
        Button btnAnnuler = new Button("Retour");
        btnAnnuler.addActionListener(e -> new ListVehicules(res).show());
        
        Button btnModifier = new Button("Modifier");
        //btnModifier.addActionListener(e -> new ModifierConstatForm(res,c).show());
       
        
        
        Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonContainer.addAll(btnAnnuler, btnModifier);

        Container mainContainer = new Container(new BorderLayout());
        mainContainer.add(BorderLayout.CENTER, buttonContainer);

        // add the mainContainer to the form
        

  
        cnt.add(BorderLayout.CENTER, 
                BoxLayout.encloseY(
                        marque,
                        vitessemax,
                        chargeMaxsupp,
                        AutoBatterie,
                        couleur,
                        TypeVehicule,
                        prix 
            ));
        

            add(cnt);
            add(mainContainer);
    }
}