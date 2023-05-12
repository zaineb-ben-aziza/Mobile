/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.assurtous;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Reclamation;
import com.esprit.services.ReclamationService;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ListReclamations extends BaseForm{
  private int likeCount = 0;
  Form current;
  
 public ListReclamations(Resources res) {
        super("Response", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        addSideMenu(res);
        getContentPane().setScrollVisible(false);

        Button addButton = new Button("Ajouter");
        addButton.addActionListener(e -> new AjoutReclamation(res).show());

        try {
            System.out.println("Fetching reclamations from database...");
            ArrayList<Reclamation> list = ReclamationService.getInstance().affichageReclamations();
            System.out.println("Found " + list.size() + " reclamations in the database.");

            if (list.isEmpty()) {
                Label noReclamationsLabel = new Label("Aucune réclamation trouvée.");
                noReclamationsLabel.getStyle().setFgColor(0xff0000);
                add(noReclamationsLabel);
            } else {
                
                for (Reclamation c : list) {
                    System.out.println("Adding reclamation to UI: " + c.toString());
                    addButton(c, res);
                            System.out.println("ListReclamations constructor called");

                }
                add(addButton);
            }
            refreshTheme();
        } catch (Exception e) {
            System.err.println("Error while fetching reclamations from database: " + e.getMessage());
            e.printStackTrace();
        }
    }


private void addButton(Reclamation v, Resources res) {
    Container cnt = new Container(new BorderLayout());
  //  Label dateTxt = new Label("Date Reclamation: " + v.getDatecreation(), "NewsTopLine2");
    Label Nom = new Label("Nom: " + v.getNom(), "NewsTopLine2");
    Label Prenom = new Label("Prenom: " + v.getPrenom(), "NewsTopLine2");
    Label Adresse= new Label("Email: " + v.getAdresse(), "NewsTopLine2");
    Label desc = new Label("Contenu: " + v.getContenu(), "NewsTopLine2");

    Button deleteBtn = new Button();
    deleteBtn.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, deleteBtn.getStyle()));
    deleteBtn.addPointerPressedListener(e -> {
        Dialog dig = new Dialog("Suppression");
        if (dig.show("Suppression", "Vous voulez supprimer ce Constat ?", "Annuler", "Oui")) {
            dig.dispose();
        } else {
            dig.dispose();
            if (ReclamationService.getInstance().deleteReclamation(v.getId())) {
                Dialog.show("Alert", "Deleted successfully", "OK", null);
                new ListReclamations(res).show();
            } else {
                Dialog.show("Alert", "Error while connecting to the server", "OK", null);
            }
        }
    });

    Button editBtn = new Button();
    editBtn.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, editBtn.getStyle()));
    editBtn.addPointerPressedListener(e -> {
        new ModifierReclamation(res, v).show();
    });

    Button showBtn = new Button();
    showBtn.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DETAILS, editBtn.getStyle()));
    showBtn.addPointerPressedListener(e -> {
        new ReclamationDetails(res, v).show();
    });

    Style style = cnt.getStyle();
    style.setBgColor(0xFF0000);
    style.setFgColor(0x0000FF);
    style.setBorder(Border.createLineBorder(5));
    cnt.setUIID("constatContainer");
    cnt.add(BorderLayout.CENTER,
            BoxLayout.encloseY(
                    BoxLayout.encloseX( deleteBtn, editBtn, showBtn),
                    Nom,
                    Prenom,
                    Adresse,
                    desc
            ));

    add(cnt);
    // refreshTheme();
}
}