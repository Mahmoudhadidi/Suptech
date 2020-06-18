
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;


import com.codename1.ui.Button;


import com.codename1.ui.Dialog;

import com.codename1.ui.Form;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


import com.esprit.entities.User;
import com.esprit.services.UserService;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.BoxLayout;



/**
 *
 * @author Chokri
 */
public class Login extends Form{
public Login (Form previous) {
  
        setTitle("Login");
        setLayout(BoxLayout.y());
        
        TextComponent tfUsername = new TextComponent().labelAndHint("Name");
        FontImage.setMaterialIcon(tfUsername.getField().getHintLabel(), FontImage.MATERIAL_PERSON);
       // TextField tfUsername = new TextField("", "username ");
     TextComponent tfPassword = new TextComponent().labelAndHint("Password").constraint(TextArea.PASSWORD);
     FontImage.setMaterialIcon(tfPassword.getField().getHintLabel(), FontImage.MATERIAL_LOCK);
       //  TextField tfPassword= new TextField("", "password");
        
        
        Button btnValider = new Button("login");
        btnValider.setUIID("InputAvatarImage");
       
       
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfUsername.getText().length()==0)||(tfPassword.getText().length()==0))
                  
                 Dialog.show("Alert", "Remplir tout les champs","OK","Cancel");
                else
                {
                    try {
                        User u= new User(tfUsername.getText(), tfPassword.getText());
                        if( UserService.getInstance().loginUser(tfUsername.getText(),tfPassword.getText())){
        

                        } else{
                      Dialog.show("Error","Erreur","OK","Cancel");}
                        
                      
                    } catch (NumberFormatException e) {
                        Dialog.show("Error","Erreur","OK","Cancel");
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfUsername,tfPassword, btnValider);
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
         this.getToolbar().addMaterialCommandToSideMenu("Créer un compte", FontImage.MATERIAL_AC_UNIT, e-> new Register(previous).show());       
    }

   public Login(){}
    
}
