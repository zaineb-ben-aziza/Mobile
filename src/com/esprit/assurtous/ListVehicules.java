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
import com.esprit.entities.Vehicule;
import com.esprit.services.VehiculeService;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ListVehicules extends BaseForm{
  private int likeCount = 0;
  Form current;
  
 public ListVehicules(Resources res) {
        super("Response", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        addSideMenu(res);
        getContentPane().setScrollVisible(false);

        Button addButton = new Button("Ajouter");
        addButton.addActionListener(e -> new AjoutVehicule(res).show());

        try {
            System.out.println("Fetching vehicule from database...");
            ArrayList<Vehicule> list = VehiculeService.getInstance().affichageVehicules();
            System.out.println("Found " + list.size() + " vehicule in the database.");

            if (list.isEmpty()) {
                Label noReclamationsLabel = new Label("Aucune véhicule trouvée.");
                noReclamationsLabel.getStyle().setFgColor(0xff0000);
                add(noReclamationsLabel);
            } else {
                
                for (Vehicule c : list) {
                    System.out.println("Adding Vehicule to UI: " + c.toString());
                    addButton(c, res);
                            System.out.println("ListVehicule constructor called");

                }
                add(addButton);
            }
            refreshTheme();
        } catch (Exception e) {
            System.err.println("Error while fetching vehicule from database: " + e.getMessage());
            e.printStackTrace();
        }
    }


private void addButton(Vehicule v, Resources res) {
    Container cnt = new Container(new BorderLayout());
    Label marque = new Label("marque: " +v.getMarque(),"NewsTopLine2");
        Label vitessemax = new Label("vitessemax: " +v.getVitesseMax(),"NewsTopLine2");
        Label chargeMaxsupp = new Label("chargeMaxsupp : "+v.getChargeMaxsupp(),"NewsTopLine2");
         Label AutoBatterie = new Label("autoBatterie: " +v.getAutoBatterie(),"NewsTopLine2");
        Label couleur = new Label("couleur: " +v.getCouleur(),"NewsTopLine2");
         Label TypeVehicule = new Label("TypeVehicule: " +v.getTypeVehicule(),"NewsTopLine2");
        Label prix = new Label("prix: " +v.getPrix(),"NewsTopLine2");

    Button deleteBtn = new Button();
    deleteBtn.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, deleteBtn.getStyle()));
    deleteBtn.addPointerPressedListener(e -> {
        Dialog dig = new Dialog("Suppression");
        if (dig.show("Suppression", "Vous voulez supprimer cette Véhicule ?", "Annuler", "Oui")) {
            dig.dispose();
        } else {
            dig.dispose();
            if (VehiculeService.getInstance().deleteVehicule(v.getId())) {
                Dialog.show("Alert", "Deleted successfully", "OK", null);
                new ListVehicules(res).show();
            } else {
                Dialog.show("Alert", "Error while connecting to the server", "OK", null);
            }
        }
    });

    Button editBtn = new Button();
    editBtn.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, editBtn.getStyle()));
    editBtn.addPointerPressedListener(e -> {
        new ModifierVehicule(res, v).show();
    });

    Button showBtn = new Button();
    showBtn.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DETAILS, editBtn.getStyle()));
    showBtn.addPointerPressedListener(e -> {
        new VehiculeDetails(res, v).show();
    });

    Style style = cnt.getStyle();
    style.setBgColor(0xFF0000);
    style.setFgColor(0x0000FF);
    style.setBorder(Border.createLineBorder(5));
    cnt.setUIID("constatContainer");
    cnt.add(BorderLayout.CENTER,
            BoxLayout.encloseY(
                    BoxLayout.encloseX(marque, deleteBtn, editBtn, showBtn),
                    vitessemax,
                    chargeMaxsupp,
                    AutoBatterie,
                    couleur,
                    TypeVehicule,
                    prix
                    
            ));

    add(cnt);
    // refreshTheme();
}
}