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
import com.esprit.entities.Note;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceNotes {

    public ArrayList<Note> tasks;
    
    public static ServiceNotes instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceNotes() {
         req = new ConnectionRequest();
    }

    public static ServiceNotes getInstance() {
        if (instance == null) {
            instance = new ServiceNotes();
        }
        return instance;
    }

    public boolean addTask(Note n) {
        String url = Statics.BASE_URL+"/api/tasks/new"+"?"+"noteCc="+ n.getNoteCc() + "&" +"noteDs="+ n.getNoteDs()+ "&" +"noteExamun="+ n.getNoteExamun()+"&" +"moyenne="+"null"+"&" +"net="+ "null"+ "&" +"cin="+ n.getCin()+ "&" +"nomMatier="+ n.getNomMatier();
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

    public ArrayList<Note> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Note t = new Note();
                float idNote = Float.parseFloat(obj.get("idNote").toString());
                t.setIdNote((int)idNote);
                t.setNoteDs(((float)Float.parseFloat(obj.get("noteDs").toString())));
                t.setNoteExamun(((float)Float.parseFloat(obj.get("noteExamun").toString())));
              //  t.setMoyenne(((float)Float.parseFloat(obj.get("moyenne").toString())));
              float cin = Float.parseFloat(obj.get("cin").toString());
                t.setCin((int)cin);
                t.setNomMatier(((String)(obj.get("nomMatier").toString())));
               // t.setNote_examun(obj.get("note_examun").toString());
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Note> getAllTasks(){
        String url = Statics.BASE_URL+"/api/tasks/all";
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
    
    public boolean pdf (){

       String url = Statics.BASE_URL+"/note/pdf";
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
}
