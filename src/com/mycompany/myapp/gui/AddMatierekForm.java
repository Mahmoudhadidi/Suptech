/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.components.ToastBar;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.Base64;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.services.ServiceMatiere;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import java.io.IOException;
import java.util.Map;
import com.sendgrid.*;

/**
 *
 * @author bhk
 */
public class AddMatierekForm extends Form{

    public AddMatierekForm(Form previous) {
        setTitle("Ajouter matière");
        setLayout(BoxLayout.y());
        
        TextField NomMatiere = new TextField("","Nom matière");
        TextField Coefficient= new TextField("", "Coefficient");
        TextField Credit= new TextField("", "Credit");
        Button btnValider = new Button("Ajouter matière");
        
        
        /*********************************************** MAIL ***************************************/
            btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               Email from = new Email("meriem.bader1@esprit.tn");
    String subject = "Notification";
    Email to = new Email("mahmoudhadidi2017@gmail.com");
    Content content = new Content("text/plain", "Matiere ajouté avec succée");
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid("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");
    Request request = new Request();
    try {
       request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
                   com.sendgrid.Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
    System.out.println("message non envoyé");
    }
            }
        });
        
        
         
         /*    btnValider.addActionListener(new ActionListener() {
         @Override
            public void actionPerformed(ActionEvent evt) {
             com.codename1.io.rest.Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/AC869ac8a9af77536d0605082c037f9a21/Messages.json").
        queryParam("To", "+21654982088").
        queryParam("From","+12015810544").
        queryParam("Body", "matiere ajouter avec succes ").
        header("Authorization", "Basic " + Base64.encodeNoNewline(("AC869ac8a9af77536d0605082c037f9a21"+ ":" + "ea15979919d7a0e066b84ed26d988b18").getBytes())).
        getAsJsonMap();
        if(result.getResponseData() != null) {
        String error = (String)result.getResponseData().get("error_message");
            if(error != null) {
            ToastBar.showErrorMessage(error);
            }
        } 
        else {
        ToastBar.showErrorMessage("Error sending SMS: " + result.getResponseCode());
        }
            }
        });*/
        
        btnValider.addActionListener(new ActionListener() {
            String msj="Connection accepted";
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((NomMatiere.getText().length()==0)||(Coefficient.getText().length()==0)||(Credit.getText().length()==0))
                   // Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
                    Dialog.show("Alerte", "Veuillez remplir tous les champs", "ok", "ok");
                else
                {
                    try {
                        Matiere t = new Matiere(Integer.parseInt(Coefficient.getText()),Integer.parseInt(Credit.getText()), NomMatiere.getText());
                        if( ServiceMatiere.getInstance().addMatiere(t)){
                           
                            NomMatiere.setText("");
                            Coefficient.setText("");
                            Credit.setText("");
                             /* Form previous = null;
                              LocalNotification n = new LocalNotification();
                                n.setId("demo-notification");
                                n.setAlertBody("Votre Don a été envoyé avec succés ");
                                n.setAlertTitle("Succés!");
                                n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


                                Display.getInstance().scheduleLocalNotification(
                                        n,
                                        System.currentTimeMillis() + 10 * 1000 , // fire date/time
                                        LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
                                );
                        new ListMatiereForm(previous).show();*/
                            Dialog.show("Succès", "Connexion acceptée, Matière ajouté", "Ok", "Ok");
                            
                       
                         }
                        else
                           // Dialog.show("ERROR", "Server error", new Command("OK"));
                            Dialog.show("ERREUR", "Erreur du serveur", "Ok", "Ok");
                    } catch (NumberFormatException e) {
                        Dialog.show("Erreur", "Le statut doit être un nombre", "ok", "ok");
                        //Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(NomMatiere,Coefficient,Credit,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
