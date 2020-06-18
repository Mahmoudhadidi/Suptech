
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ToastBar;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;

import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.Base64;
import com.esprit.entities.Note;
import com.esprit.services.ServiceNotes;
import java.util.Map;


/**
 *
 * @author chkri
 */
public class AddNoteForm extends Form{

    public AddNoteForm(Form previous) {
        setTitle("Ajouter une note");
        setLayout(BoxLayout.y());
        
        TextField tfNoteCc = new TextField("","Note cc");
        TextField tfNoteDs= new TextField("", "Note ds");
        TextField tfNoteExamun= new TextField("", "Note examen");
        TextField tfCin= new TextField("", "Cin");
        TextField tfNomMatier= new TextField("", "Matière");
        Button btnValider = new Button("Ajouter");
        btnValider.setUIID("RedCard");
        
        /**************************************** SMS *******************************/
                
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             com.codename1.io.rest.Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/AC495c0ce0ede61bdacf81b4a9fc5cc379/Messages.json").
        queryParam("To", "+21626334028").
        queryParam("From","+12015810544").
        queryParam("Body", "Note ajoutée avec succée").
        header("Authorization", "Basic " + Base64.encodeNoNewline(("AC495c0ce0ede61bdacf81b4a9fc5cc379"+ ":" + "0845479e76224c6db8a2c656e13992f0").getBytes())).
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
        });
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNoteCc.getText().length()==0)||(tfNoteDs.getText().length()==0)||(tfNoteExamun.getText().length()==0)||(tfCin.getText().length()==0)||(tfCin.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", "OK","Cancel");
                    
                else
                {
                    try {
                        Note t = new Note(Float.parseFloat(tfNoteCc.getText()), Float.parseFloat(tfNoteDs.getText()),Float.parseFloat(tfNoteExamun.getText()) , Integer.parseInt(tfCin.getText()), (String)tfNomMatier.getText());
                        if( ServiceNotes.getInstance().addTask(t))
                            Dialog.show("Success","Note ajoutée","OK","Cancel");
                        else
                            Dialog.show("ERROR", "Server error ","OK","Cancel");
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number","OK","Cancel");
                    }
                    tfNoteCc.setText("");
                    tfNoteDs.setText("");
                    tfNoteExamun.setText("");
                    tfCin.setText("");
                    tfNomMatier.setText("");
                    
                }
                
                
            }
        });
        
        addAll(tfNoteCc,tfNoteDs,tfNoteExamun,tfCin,tfNomMatier,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
