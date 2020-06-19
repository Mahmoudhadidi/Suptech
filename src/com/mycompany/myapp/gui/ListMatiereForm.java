/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import static com.codename1.io.Log.e;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.services.ServiceMatiere;
import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListMatiereForm extends Form{
    
    public Container matiere(Matiere c) {
        Label lbname = new Label("Matière: "+c.getName());
        Label btnom = new Label("Coefficient: "+String.valueOf(c.getCoefficient()));
        Button btnSupMatiere=new Button("Supprimer");
        btnSupMatiere.getAllStyles().setBorder(Border.createEmpty());
        btnSupMatiere.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        btnSupMatiere.getAllStyles().setFgColor(0xff0000);
        
           btnSupMatiere.addActionListener(new ActionListener() {
          
          @Override
            public void actionPerformed(ActionEvent evt) {
                 Matiere t;
              t = new Matiere(c.getId(),c.getCoefficient(),c.getCredit(),c.getName());
                       
                 if( ServiceMatiere.getInstance().SupprimerMatiere(t)){
                     Form previous = null;
                           new ListMatiereForm(previous).show();
                            Dialog.show("Succès", "Connexion acceptée, Matière supprimé", "Ok", "Ok");
                        }
                        else
                           // Dialog.show("ERROR", "Server error", new Command("OK"));
                            Dialog.show("ERREUR", "Erreur du serveur", "Ok", "Ok");
                     
            }
            });
        Container cnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cnt.add(lbname);
        cnt.add(btnom);
       cnt.add(btnSupMatiere);
              
Container cnt2 = BorderLayout.center(cnt);
        return cnt2;
    }
    
    public ListMatiereForm(Form previous) {
         
         setTitle("List Matière");
         Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for(Matiere m:ServiceMatiere.getInstance().getAllMatiere())
            cnt.add(matiere(m));
        
       Button ss = new Button("Faire un screenshot");
          cnt.add(ss);
                ss.addActionListener(e1 -> {

                    Image screenshot = Image.createImage(getWidth(), getHeight());
                    revalidate();
                    setVisible(true);
                    paintComponent(screenshot.getGraphics(), true);

                    String imageFile = "file://C:/Users/hadidi/Documents/NetBeansProjects/JardinDenfant/images/screenshot.png";

                    try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                        ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                    } catch (IOException err) {
                        Log.e(err);
                    }
                });
       
     
         Button devGuide = new Button("Show PDF");
devGuide.addActionListener(e -> {
    FileSystemStorage fs = FileSystemStorage.getInstance();
    String fileName = fs.getAppHomePath() + "api";
    if(!fs.exists(fileName)) {
        Util.downloadUrlToFile("http://127.0.0.1:8000/matiere/api", fileName, true);
    }
    Display.getInstance().execute(fileName);
});
 cnt.add(devGuide);
       add(cnt);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

   
    
}
