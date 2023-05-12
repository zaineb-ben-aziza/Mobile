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
import com.esprit.entities.Reclamation;
import com.esprit.services.ReclamationService;


/**
 *
 * @author ASUS
 */
public class ModifierReclamation extends BaseForm{
    Form current;
   public ModifierReclamation(Resources res, Reclamation c) {
    super("Modifier Reponse", BoxLayout.y());

    Toolbar tb = new Toolbar(true);
    current = this;
    setToolbar(tb);
    getTitleArea().setUIID("Container");
    getContentPane().setScrollVisible(false);

    TextField nom = new TextField("", "Nom", 20, TextField.ANY);
    nom.setUIID("TextFieldBlack");
    nom.setText(c.getNom());
    addStringValue("Nom :", nom);
 TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
   prenom.setUIID("TextFieldBlack");
    prenom.setText(c.getPrenom());
    addStringValue("Contenu :", prenom);
     TextField adresse = new TextField("", "adresse", 20, TextField.ANY);
    adresse.setUIID("TextFieldBlack");
    adresse.setText(c.getAdresse());
    addStringValue("Contenu :", adresse);
    
    TextField desc = new TextField("", "Contenu", 20, TextField.ANY);
    desc.setUIID("TextFieldBlack");
    desc.setText(c.getContenu());
    addStringValue("Contenu :", desc);

    Button btnAnnuler = new Button("Annuler");
    btnAnnuler.addActionListener(e -> new ListReclamations(res).show());

    Button btnModifier = new Button("Modifier");
    btnModifier.addActionListener((e) -> {
        if (nom.getText().equals("") || desc.getText().equals("")) {
            Dialog.show("Veuillez vérifier les données", "", "Annuler", "OK");
        } else {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog iDialog = ip.showInfiniteBlocking();

            c.setNom(nom.getText());
            c.setContenu(desc.getText());

            ReclamationService.getInstance().ModifierReclamation(c);

            iDialog.dispose();
            new ListReclamations(res).show();
            refreshTheme();
        }
    });

    Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
    buttonContainer.addAll(btnAnnuler, btnModifier);

    Container mainContainer = new Container(new BorderLayout());
    mainContainer.add(BorderLayout.CENTER, buttonContainer);

    add(mainContainer);
}

private void addStringValue(String s, Component v) {
    add(BorderLayout.west(new Label(s, "PaddedLabel")).add(BorderLayout.CENTER, v));
    add(createLineSeparator(0xeeeeee));
}

}
