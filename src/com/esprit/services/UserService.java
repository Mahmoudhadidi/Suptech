/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;
import com.esprit.entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.esprit.gui.HomeForm;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Amenallah Lounis
 */
public class UserService {
    
      ArrayList<User> usersLogin = new ArrayList<User>();
    
    public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public String username;
    public String password;
    

    public UserService() {
         req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


    public boolean addUser(User u ) {
        String url = Statics.BASE_URL + "/api/tasks/newUser"+"?" +"username="+u.getUsername() + "&" +"email=" + u.getEmail()+ "&" +"password="+ u.getPassword();
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
    
    public boolean loginUser(String username,String pass ) {
    String url = Statics.BASE_URL + "/api/tasks/login?username="+username+"&password="+pass;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                String response=new String(req.getResponseData());
                if(response.equals("1"))
                {
                  //  Dialog.show("Alert","Succées","OK","Cancel");
                  
            //      new HomeForm().show();
                 HomeForm home=new HomeForm();
                 home.show();
                 }
                else{
                 Dialog.show("Alert","Vérifier vos données","OK","Cancel");
                }
            }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
          return resultOK;
                }
}
    
      
                
    
