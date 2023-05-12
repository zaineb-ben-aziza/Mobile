package com.esprit.assurtous;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.assurtous.AjoutReclamation;
import com.esprit.assurtous.ListReclamations;

public class Home extends Form {
    public Home(){
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("choose an option "));
        Button addReclamationButton = new Button("Add Reclamation");
        Button listReclamationsButton = new Button("List Reclamations");
        
        addReclamationButton.addActionListener(l -> new AjoutReclamation(Resources.getGlobalResources()).show());
        listReclamationsButton.addActionListener(l -> new ListReclamations(Resources.getGlobalResources()).show());
        addAll(addReclamationButton, listReclamationsButton);
                
    }
}
