/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.EventService;

/**
 *
 * @author DELL
 */
/*public class EditEvent {
    
}*/


public class EditEventForm extends Form {
    private Evenement evenement;
    private TextField tfNomEvent;
    private Picker datePickerDebut;
    private Picker datePickerFin;
    private TextField tfLieuEvent;
    private TextField tfNbPlaces;
    private TextField tfTypeEvent;
    private TextField tfNbVues;

    public EditEventForm(Form previous, Evenement evenement) {
        setTitle("Modifier Événement");
        setLayout(BoxLayout.y());

        this.evenement = evenement;

        tfNomEvent = new TextField(evenement.getNom_event(), "Nom de l'événement");
        tfLieuEvent = new TextField(evenement.getLieu_event(), "Lieu de l'événement");
        datePickerDebut = new Picker();
        datePickerDebut.setDate(evenement.getDate_debut());
        datePickerFin = new Picker();
        datePickerFin.setDate(evenement.getDate_fin());
        tfTypeEvent = new TextField(evenement.getType_event(), "Type d'événement");
        tfNbPlaces = new TextField(String.valueOf(evenement.getNb_places()), "Nombre de places");

        addAll(tfNomEvent, tfLieuEvent,datePickerDebut, datePickerFin,  tfNbPlaces, tfTypeEvent);

        // Ajouter un bouton pour enregistrer les modifications
        Button modifier = new Button("Modifier");
        modifier.addActionListener(e -> {
            // Mettre à jour les données de l'événement
           evenement.setNom_event(tfNomEvent.getText());
           evenement.setLieu_event(tfLieuEvent.getText());
           evenement.setDate_debut(datePickerDebut.getDate());
           evenement.setDate_fin(datePickerFin.getDate());
           evenement.setType_event(tfTypeEvent.getText());
           evenement.setNb_places(Integer.parseInt(tfNbPlaces.getText()));
            // Enregistrer les modifications dans la base de données
            EventService.getInstance().updateEvenement(evenement);

            // Retourner à la liste des événements
           /* new ListEventForm(previous).showBack();*/
        });
        add(modifier);

        // Ajouter un bouton pour annuler les modifications
        Button retourButton = new Button("retour");
        retourButton.addActionListener(e -> new ListEventForm(previous).showBack());
        add(retourButton);
    }
}