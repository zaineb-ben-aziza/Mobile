/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.EventService;
import java.util.Date;


/**
 *
 * @author waelb
 */
public class AddEventForm extends Form{
     public AddEventForm(Form previous) {
        setTitle("Ajouter Event");
        setLayout(BoxLayout.y());
        
        Label Nom = new Label("nomEvent :");
        TextField Nome = new TextField("", "Nom");
        
        Label lieu = new Label("Lieu :");
        TextField Lieue = new TextField("", "lieuEvent");
        
        Label datedebut = new Label("Date Debut :");
        Picker pickerDate = new Picker();
        
        Label datefin = new Label("Date Fin:");
        Picker pickerDate1 = new Picker();
        
       Label type = new Label("Type event :");
        TextField typee = new TextField("", "typeEvent");

        Button btnValider = new Button("Ajouter Evenement");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Nome.getText().length()==0)||(Lieue.getText().length()==0) ||(pickerDate1.getText().length()==0)||(pickerDate.getText().length()==0)||(typee.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    Evenement a = new Evenement();
                    a.setNom_event(Nome .getText().toString());
                     a.setDate_debut(pickerDate.getDate() );
                    a.setDate_fin(pickerDate1.getDate());
                    a.setLieu_event(Lieue .getText().toString());
                       a.setType_event(typee.getText().toString());
                       
                     if( EventService.getInstance().addEvent(a))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                }
               new ListEventForm(previous).showBack(); 
                
            }
              });
        
        addAll(Nom,Nome,lieu,Lieue,datedebut, pickerDate,datefin,pickerDate1,type,typee,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    
}
}
