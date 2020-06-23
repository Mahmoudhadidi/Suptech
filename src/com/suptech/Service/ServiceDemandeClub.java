/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suptech.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.suptech.Entity.DemandeClub;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class ServiceDemandeClub {
    
    public ArrayList<DemandeClub> tasks; 
    ArrayList<DemandeClub> demande = new ArrayList<>();
    
    public ArrayList<DemandeClub> parseListTaskJson(String json)
{
        
        try{          
        tasks = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String,Object>>)tasks.get("root");
        for(Map<String,Object> obj : list)
        {           
        DemandeClub a = new DemandeClub();
        
         float id = Float.parseFloat(obj.get("id").toString());
         a.setId((int)id);
         float ide = Float.parseFloat(obj.get("idEtudiant").toString());
         a.setId_etudiant((int)ide);
         a.setNom_club(obj.get("nomClub").toString());
         a.setDomaine(obj.get("domaine").toString());
         a.setDescription(obj.get("description").toString());
         a.setEtat(obj.get("etat").toString());          
                System.out.println(a+"\n ***********************");      
                demande.add(a);
        }    
            
            
        } catch (IOException ex){
                 }
        System.out.println(demande);        
        return demande;
}
        
        
        
        ArrayList<DemandeClub> demandeC = new ArrayList<>();
    public ArrayList<DemandeClub> RecupererDemandes()
{
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GestionEcole/web/app_dev.php/listmobile");
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                demandeC = parseListTaskJson(new String(con.getResponseData()));
                con.removeResponseListener(this);
                System.out.println(demandeC);                      
            }
         });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return demandeC;
}
       
    public void ajouterReclamation(DemandeClub r)
{
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/GestionEcole/web/app_dev.php/ajoutmobile?nom=" + r.getNom_club()+"&description="+r.getDescription()+"&domaine="+r.getDomaine();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener((l) -> {
        String str = new String(con.getResponseData());//Récupération de la réponse du serveur
        System.out.println(str);//Affichage de la réponse serveur sur la console
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            
        }        
}
