/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suptech.GUI;

import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.suptech.Entity.DemandeClub;
import com.suptech.Service.ServiceDemandeClub;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class AjouterDemande {
    Form f;
    TextField Nom;
    TextField Description;
    TextField Domaine;
    TextField tel;
    Button btnvalider;
    
    public AjouterDemande(){
        ServiceDemandeClub service= new ServiceDemandeClub();
        Nom = new TextField("", "nom");
         Description = new TextField("", "description");
         Domaine = new TextField("", "domaine");
         //tel = new TextField("", "tel");
         btnvalider = new Button("Envoyer");
        f = new Form("Faire demande club", new BoxLayout(BoxLayout.Y_AXIS));
        f.add(Nom);
        f.add(Description);
        f.add(Domaine);
        //f.add(tel);
        f.add(btnvalider);
        
        
        btnvalider.addActionListener((ev)-> {
         DemandeClub d = new DemandeClub();
         d.setNom_club(Nom.getText());
         d.setDescription(Description.getText());
         d.setDomaine(Domaine.getText());
         
          Response<Map> v = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + "AC31bcabb2332cc3cb8cb4cfdf28e6babb" + "/Messages.json").
        queryParam("To", "+213552940360").
        queryParam("From", "+12564728930").
        queryParam("Body", "\n Demande de nouveau club \n Nom Club:" +Nom.getText().toString()+"\n Domiane:" +Domaine.getText().toString()+"\n Description:" +Description.getText().toString()).
        basicAuth("AC31bcabb2332cc3cb8cb4cfdf28e6babb", "138c444403bcde3698907ab37814faa8").//header("Authorization", "Basic " + Base64.encodeNoNewline((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes())).
        getAsJsonMap();
       
         service.ajouterReclamation(d);         
         
         Dialog dlg = new Dialog("Abonement");
        Style dlgStyle = dlg.getDialogStyle();
        dlgStyle.setBorder(Border.createEmpty());
        dlgStyle.setBgTransparency(255);
        dlgStyle.setBgColor(0xffffff);

    Label title = dlg.getTitleComponent();
   // title.setIcon(finalDuke.scaledHeight(title.getPreferredH()));
    title.getUnselectedStyle().setFgColor(0xff);
    title.getUnselectedStyle().setAlignment(Component.LEFT);

    dlg.setLayout(BoxLayout.y());
    Label blueLabel = new Label();
    blueLabel.setShowEvenIfBlank(true);
    blueLabel.getUnselectedStyle().setBgColor(0xff);
    blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(blueLabel);
    TextArea ta = new TextArea("Votre Demande sera traité prochainement");
    ta.setEditable(false);
    ta.setUIID("DialogBody");
    ta.getAllStyles().setFgColor(0);
    dlg.add(ta);

    Label grayLabel = new Label();
    grayLabel.setShowEvenIfBlank(true);
    grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(grayLabel);

    Button ok = new Button(new Command("OK"));
    ok.getAllStyles().setBorder(Border.createEmpty());
    ok.getAllStyles().setFgColor(0);
    
    dlg.add(ok);
    dlg.showDialog();   
         
    
    
    
    
    
    
    
    
         ListeDemande l = new ListeDemande();
         l.getF().show();
            
            
        } );
        
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
