/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author DELL
 */
public class HomeForm extends Form{
    public HomeForm() {
        
        setTitle("page home");
        setLayout(BoxLayout.y());
        
        Button btnAddEvent = new Button("Ajouter Event");
        Button btnListEvent  = new Button("Liste Event");
        
        
        btnListEvent.addActionListener(e-> new ListEventForm(this).show());
        btnAddEvent.addActionListener(e-> new AddEventForm(this).show());
       
         addAll(btnListEvent,btnAddEvent);
        
       
        
    }
    
    
}
