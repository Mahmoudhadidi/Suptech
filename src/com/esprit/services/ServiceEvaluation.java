/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Evaluation;

import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceEvaluation {

    public ArrayList<Evaluation> tasks;
    
    public static ServiceEvaluation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEvaluation() {
         req = new ConnectionRequest();
    }

    public static ServiceEvaluation getInstance() {
        if (instance == null) {
            instance = new ServiceEvaluation();
        }
        return instance;
    }

    public boolean addTask(Evaluation n) {
        String url = Statics.BASE_URL+"/api/tasks/new1"+"?" +"idEtd="+n.getIdEtd() + "&" +"presence=" + n.getPresence()+ "&" +"rendement="+ n.getRendement()+ "&" +"discipline="+ n.getDiscipline()+ "&" +"idEnseignant="+ n.getIdEnseignant()+ "&" +"nomMatiere="+ n.getNomMatiere();
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

    public ArrayList<Evaluation> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Evaluation t = new Evaluation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                float idEtd = Float.parseFloat(obj.get("idEtd").toString());
                t.setIdEtd((int)idEtd);
                t.setPresence(((String)(obj.get("presence").toString())));
                t.setRendement(((String)(obj.get("rendement").toString())));
               t.setDiscipline(((String)(obj.get("discipline").toString())));
              float idEnseignant = Float.parseFloat(obj.get("idEnseignant").toString());
                t.setIdEnseignant((int)idEnseignant);
                t.setNomMatiere(((String)(obj.get("nomMatiere").toString())));
                              tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Evaluation> getAllTasks(){
        String url = Statics.BASE_URL+"/api/tasks/all1";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
}
