/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.EventService;
import java.util.ArrayList;

/**
 *
 * @author waelb
 */
public class ListEventForm extends Form{
    public ListEventForm(Form previous) {
        setTitle("List Events");
        setLayout(BoxLayout.y());
               Label label = new Label("Liste des Events :");
        add(label);
        ArrayList<Evenement> evenement = EventService.getInstance().getAllEvents();

        for (Evenement a : evenement) {
            addElement(a);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
    public void addElement(Evenement e1) {
        EventService ps = new EventService();
    

        Label Nom= new Label("nomEvent : " + e1.getNom_event());
        Label Lieu = new Label("lieuEvent : " + e1.getLieu_event());
        Label datedebut = new Label("dateDin : " + e1.getDate_debut());
        Label datefin = new Label("dateFin : " + e1.getDate_fin());
        Label type = new Label("typeEvent : " + e1.getType_event());
        Label nbplaces = new Label(" Nombre de places: " + e1.getNb_places());
        Button detail = new Button("Détails");
        detail.addActionListener(e -> {
            Dialog.show("Détails", "NbVues :"+e1.getNb_vues()
                    , "OK", null);
        });
        
        Button supprimer =new Button("Supprimer");
            supprimer.addActionListener(e -> {
               Dialog alert = new Dialog("Confirmation");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette evenement?");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete 
                ok.addActionListener((ActionListener) (ActionEvent evt) -> {
                    ps.deleteEvent(e1.getID_event());
                   
                    alert.dispose();
                    refreshTheme();
                     
               });
                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                
                
                
                
               
             });
            
            
         Button modifier = new Button("Modifier");
        modifier.addActionListener(e-> new EditEventForm(this,e1).show());
        

        
        addAll(Nom,Lieu,datedebut,datefin,type, nbplaces, supprimer, modifier, detail );
        
       

    }
}
