/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceMatiere {

    public ArrayList<Matiere> matiere;
    
    public static ServiceMatiere instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceMatiere() {
         req = new ConnectionRequest();
    }

    public static ServiceMatiere getInstance() {
        if (instance == null) {
            instance = new ServiceMatiere();
        }
        return instance;
    }

   public boolean addMatiere(Matiere t) {
        String url = Statics.BASE_URL + "/matiere/api?nom=" + t.getName() + ""+ "&cof=" + t.getCoefficient()+ "&credit=" + t.getCredit();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

     public boolean SupprimerMatiere(Matiere t) {
        String url = Statics.BASE_URL + "/matiere/api?idMatiere="+t.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   
    public ArrayList<Matiere> parseMatiere(String jsonText){
        try {
            matiere=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> matieresListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)matieresListJson.get("root");
            for(Map<String,Object> obj : list){
                Matiere t = new Matiere();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setCoefficient(((int)Float.parseFloat(obj.get("coefficient").toString())));
                t.setCredit(((int)Float.parseFloat(obj.get("credit").toString())));
                t.setName(obj.get("nom").toString());
                matiere.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return matiere;
    }
    
    public ArrayList<Matiere> getAllMatiere(){
        String url = Statics.BASE_URL+"/matiere/api";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                matiere = parseMatiere(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return matiere;
    }
}
