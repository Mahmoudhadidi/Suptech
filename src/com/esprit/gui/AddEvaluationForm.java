/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.Button;

import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Evaluation;
import com.esprit.services.ServiceEvaluation;
import com.sendgrid.*;
import java.io.IOException;


/**
 *
 * @author bhk
 */
public class AddEvaluationForm extends Form{

    public AddEvaluationForm(Form previous) {
        setTitle("Ajouter une évaluation");
        setLayout(BoxLayout.y());
        
        TextField tfIdEtd = new TextField("","Identifiant d'étudiant");
        TextField tfPresence= new TextField("", "Presence");
        TextField tfRendement= new TextField("", "Rendement");
        TextField tfDiscipline= new TextField("", "Discipline");
         TextField tfNomMatiere= new TextField("", "Nom matière");
        TextField tfIdEnseignant= new TextField("", "Identifiant d'enseignant");
       
        
        Button btnValider = new Button("Ajouter");
        btnValider.setUIID("RedCard");
        
        
         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt ) {
               Email from = new Email("miloud.chokri1@esprit.tn");
    String subject = "Notification";
    Email to = new Email("chokri.miloud123@gmail.com");
    Content content = new Content("text/plain", "Evaluation ajouté avec succée");
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid("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");
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
    System.out.println("message non envoyé");
    }
            }
        });
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfIdEtd.getText().length()==0)||(tfPresence.getText().length()==0)||(tfDiscipline.getText().length()==0)||(tfRendement.getText().length()==0)||(tfIdEnseignant.getText().length()==0)||(tfNomMatiere.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields","OK","Cancel");
                else
                {
                    try {
                        Evaluation t = new Evaluation(Integer.parseInt(tfIdEtd.getText()), tfPresence.getText(), tfRendement.getText(), tfDiscipline.getText(), tfNomMatiere.getText(), Integer.parseInt(tfIdEnseignant.getText()));
                        if( ServiceEvaluation.getInstance().addTask(t))
                            Dialog.show("Success","Connection accepted","OK","Cancel");
                        
                        else
                            Dialog.show("ERROR", "Server error","OK","Cancel");
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number","OK","Cancel");
                    }
                    tfIdEtd.setText("");
                    tfPresence.setText("");
                    tfRendement.setText("");
                    tfNomMatiere.setText("");
                    tfIdEnseignant.setText("");
                    tfDiscipline.setText("");
                }
                
                
            }
        });
        
        addAll(tfIdEtd,tfPresence,tfRendement,tfDiscipline,tfIdEnseignant,tfNomMatiere,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
