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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;

import com.esprit.entities.Vehicule;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static jdk.nashorn.internal.runtime.Debug.id;


/**
*
* @author ASUS
*/
public class VehiculeService {
public static VehiculeService instance = null ;
    private ArrayList<Vehicule> veh;

public static boolean resultOk;

//initialization connection request 
private ConnectionRequest req;

public static VehiculeService getInstance() {
    if(instance == null )
        instance = new VehiculeService();
    return instance ;
}

public VehiculeService() {
    req = new ConnectionRequest();
}

//add 
public void ajoutVehicule(Vehicule vehicule) {
    System.out.println(vehicule.getMarque() + vehicule.getAutoBatterie());
    String url = Statics.BASE_URL+"/vehicule/addVehiculeJSON?"+"&marque="+vehicule.getMarque()+
              
            "&vitesseMax="+vehicule.getVitesseMax()+
            "&chargeMaxsupp="+vehicule.getChargeMaxsupp()+
            "&autoBatterie="+vehicule.getAutoBatterie()+
            "&couleur="+vehicule.getCouleur()+
            "&typeVehicule="+vehicule.getTypeVehicule()+
            "&prix="+vehicule.getPrix();

    
    System.out.println(url);
    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData()); // Reclamation json hethi lyrinaha fi navigateur 9bila
        System.out.println("data == "+str);
    });

    NetworkManager.getInstance().addToQueueAndWait(req); // execution ta3 request sinon yet3ada chy dima nal9awha
}

public ArrayList<Vehicule> parseVehicule(String jsonText) {
    try {
        veh = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> VehiculeListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        if (VehiculeListJson.containsKey("root")) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) VehiculeListJson.get("root");

            for (Map<String, Object> obj : list) {
                Vehicule t = new Vehicule();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setMarque(obj.containsKey("marque") ? obj.get("marque").toString() : "null");
                t.setVitesseMax(obj.containsKey("vitesseMax") ? obj.get("vitesseMax").toString() : "null");
                t.setAutoBatterie(obj.containsKey("autoBatterie") ? obj.get("autoBatterie").toString() : "null");
                t.setCouleur(obj.containsKey("couleur") ? obj.get("couleur").toString() : "null");
                t.setTypeVehicule(obj.containsKey("typeVehicule") ? obj.get("typeVehicule").toString() : "null");
                t.setPrix(obj.containsKey("prix") ? Float.parseFloat(obj.get("prix").toString()) : 0.0f);
                t.setChargeMaxsupp(obj.containsKey("chargeMaxsupp") ? Float.parseFloat(obj.get("chargeMaxsupp").toString()) : 0.0f);
                
                veh.add(t);
            }
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    return veh;
}


public ArrayList<Vehicule> affichageVehicules(){
    String url = Statics.BASE_URL +"/vehicule/AllVehiculeJson";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            if (req.getResponseData() != null) {
                veh = parseVehicule(new String(req.getResponseData()));
            } else {
               
            }
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return veh;
}



//del

public boolean deleteVehicule(int id) {
    String url = Statics.BASE_URL + "/vehicule/deleterVJSON/" +id;
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

//Update 
public boolean ModifierVehicule(Vehicule r) {



         String url = Statics.BASE_URL+"/vehicule/modifierVJSON/"+r.getId()+
                 "&marque="+r.getMarque()+
            "&vitesseMax="+r.getVitesseMax()+
            "&chargeMaxsupp="+r.getChargeMaxsupp()+
            "&autoBatterie="+r.getAutoBatterie()+
            "&couleur="+r.getCouleur()+
            "&typeVehicule="+r.getTypeVehicule()+
            "&prix="+r.getPrix();

         req.setUrl(url);
          req.setPost(false);

       // req.addArgument("id", String.valueOf(e.getID_event()));
        req.addArgument("marque", r.getMarque());
        req.addArgument("vitesseMax", r.getVitesseMax());
     req.addArgument("chargeMaxsupp", String.valueOf(r.getChargeMaxsupp()));
     req.addArgument("autoBatterie", r.getAutoBatterie());
        req.addArgument("couleur", r.getCouleur());
         req.addArgument("typeVehicule", r.getTypeVehicule());
     
        req.addArgument("prix", String.valueOf(r.getPrix()));

         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                 req.removeResponseListener(this);
             }
         });

         NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
         return resultOk;



}


}