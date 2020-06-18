/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;
import com.codename1.components.FloatingHint;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.services.UserService;
import com.esprit.entities.User;


/**
 *
 * @author Amenallah Lounis
 */
public class Register extends Form{
     public Register (Form previous) {
        setTitle("S'authentifier");
        setLayout(BoxLayout.y());
        TextComponent tfUsername = new TextComponent().labelAndHint("Name");
        FontImage.setMaterialIcon(tfUsername.getField().getHintLabel(), FontImage.MATERIAL_PERSON);
        
        TextComponent tfEmail = new TextComponent().labelAndHint("E-mail").constraint(TextArea.EMAILADDR);
        FontImage.setMaterialIcon(tfEmail.getField().getHintLabel(), FontImage.MATERIAL_EMAIL);
        
        TextComponent tfPassword = new TextComponent().labelAndHint("Password").constraint(TextArea.PASSWORD);
        FontImage.setMaterialIcon(tfPassword.getField().getHintLabel(), FontImage.MATERIAL_LOCK);
        
     //   TextField tfUsername = new TextField("", "usernale ");
      //  TextField tfEmail= new TextField("", "Email");
      //   TextField tfPassword= new TextField("", "password");
        
        
        Button btnValider = new Button("Ajouter");
        btnValider.setUIID("InputAvatarImage");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfUsername.getText().length()==0)||(tfEmail.getText().length()==0) ||(tfPassword.getText().length()==0))
                    Dialog.show("Alert", "Remplir tout les champs","OK","Cancel");
                else
                {
                    try {
                        User u= new User(tfUsername.getText(), tfEmail.getText(), tfPassword.getText());
                        if( UserService.getInstance().addUser(u))
                            Dialog.show("Success","Connection accepted","OK","Cancel");
                        else
                            Dialog.show("ERROR", "Server error", "OK","Cancel");
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", "OK","Cancel");
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfUsername,tfEmail,tfPassword, btnValider);
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                 this.getToolbar().addMaterialCommandToSideMenu("Login", FontImage.MATERIAL_AC_UNIT, e-> new Login(previous).show());
       
    }

    
}
