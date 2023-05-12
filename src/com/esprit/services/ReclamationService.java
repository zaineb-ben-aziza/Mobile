/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.esprit.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Reclamation;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
*
* @author ASUS
*/
public class ReclamationService {
public static ReclamationService instance = null ;
    private ArrayList<Reclamation> reclam;

public static boolean resultOk;

//initialization connection request 
private ConnectionRequest req;

public static ReclamationService getInstance() {
    if(instance == null )
        instance = new ReclamationService();
    return instance ;
}

public ReclamationService() {
    req = new ConnectionRequest();
}
public void ajoutReclamation(Reclamation reclamation) {
    if (reclamation.getContenu().isEmpty() || reclamation.getNom().isEmpty()) {
        Dialog.show("Erreur", "Veuillez remplir tous les champs obligatoires", "OK", null);
    } else if (!isValidEmail(reclamation.getAdresse())) {
        Dialog.show("Erreur", "Veuillez entrer une adresse e-mail valide", "OK", null);
    } else {
        System.out.println(reclamation.getContenu() + reclamation.getNom());
        String url = Statics.BASE_URL + "/reclamation/new?" +
                "contenu=" + reclamation.getContenu() +
                "&nom=" + reclamation.getNom() +
                "&prenom=" + reclamation.getPrenom() +
                "&adresse=" + reclamation.getAdresse() +
                "&etat=" + reclamation.getEtat();
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData()); // Reclamation json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req); // execution ta3 request sinon yet3ada chy dima nal9awha
        
        // Add code to refresh the current page or update the UI if necessary
    }
}



public boolean isValidEmail(String email) {
    int atIndex = email.indexOf('@');
    int dotIndex = email.lastIndexOf('.');

    // Check if '@' and '.' are present and in the correct positions
    if (atIndex > 0 && dotIndex > atIndex && dotIndex < email.length() - 1) {
        // Check if there are no consecutive dots
        if (!email.contains("..")) {
            return true;
        }
    }
    return false;
}


public ArrayList<Reclamation> affichageReclamations(){
    String url = Statics.BASE_URL +"/reclamation/mobile";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            if (req.getResponseData() != null) {
                reclam = parseReclam(new String(req.getResponseData()));
            } else {
                // Handle the case when the response data is null
                // You might want to display an error message or take appropriate action
            }
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return reclam;
}
public ArrayList<Reclamation> parseReclam(String jsonText) {
    try {
        reclam = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> reclamListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        if (reclamListJson.containsKey("root")) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamListJson.get("root");

            for (Map<String, Object> obj : list) {
                Reclamation t = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setNom(obj.containsKey("nom") ? obj.get("nom").toString() : "null");
                t.setPrenom(obj.containsKey("prenom") ? obj.get("prenom").toString() : "null");
                t.setContenu(obj.containsKey("contenu") ? obj.get("contenu").toString() : "null");
                 t.setAdresse(obj.containsKey("adresse") ? obj.get("adresse").toString() : "null");

              

                if (obj.containsKey("etat")) {
                    float etat = Float.parseFloat(obj.get("etat").toString());
                    t.setEtat((int) etat);
                }

                reclam.add(t);
            }
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    return reclam;
}

//del

public boolean deleteReclamation(int id) {
    String url = Statics.BASE_URL + "/reclamation/deletemobile/" +id;
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setHttpMethod("DELETE");

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            // Handle the response here
            int responseCode = req.getResponseCode();
            if (responseCode == 200) {
                // Deletion was successful
                resultOk = true;
            } else {
                // Deletion failed
                resultOk = false;
            }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
}
/*
  public boolean  deleteReclamation(int id){
       String url = Statics.BASE_URL + "/reclamation/deletemobile/" +id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

      }
 */
//Update
public void ModifierReclamation(Reclamation e) {
   
      String url = Statics.BASE_URL + "/reclamation/editmobile/" + e.getId()+"?nom=" + e.getNom() + "&contenu="+e.getContenu()+"&adresse="+e.getAdresse()+ "?prenom=" + e.getPrenom();
      

        req.setUrl(url);
        req.setPost(false);

        req.addArgument("id", String.valueOf(e.getId()));
        req.addArgument("nom", e.getNom());
        req.addArgument("contenu", e.getContenu());
        req.addArgument("prenom", e.getPrenom());
            req.addArgument("adresse", e.getAdresse());



        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) req.getResponseData();
            String s = new String(data);
            System.out.println("Result: " + s);
        });

        NetworkManager.getInstance().addToQueue(req);
    }
}
