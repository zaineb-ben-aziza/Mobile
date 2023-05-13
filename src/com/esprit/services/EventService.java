/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.mycomany.entities.Evenement;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author DELL
 */
public class EventService {
     public ArrayList<Evenement> events;

    public static EventService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public EventService(){
        req = new ConnectionRequest();
    }
    public static EventService getInstance()
    {
        if(instance==null)
        {
            instance = new EventService();
        }
        return instance ;
    }
    public ArrayList<Evenement> parseEvents(String jsonText) throws ParseException {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Evenement r = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
               r.setID_event((int) id);
                r.setNom_event((String) obj.get("nomEvent".toString()));
                r.setLieu_event((String) obj.get("lieuEvent".toString()));
                r.setType_event((String) obj.get("typeEvent".toString()));

                float nbv = Float.parseFloat(obj.get("nbVues").toString());
                r.setNb_vues((int) nbv);
 float nbp = Float.parseFloat(obj.get("nbPlaces").toString());
                r.setNb_places((int) nbp);
                
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date Dd = format.parse(obj.get("dateDebut").toString());
                Date Df = format.parse(obj.get("dateFin").toString());
                r.setDate_debut(Dd);
                r.setDate_fin(Df);

                events.add(r);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return events;
    }
    
    public ArrayList<Evenement> getAllEvents() {
        String url = Statics.BASE_URL + "/evenement/AllEventsJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    events = parseEvents(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    public boolean addEvent(Evenement e) {
        String Nom_event =e.getNom_event();
        Date Date_debut = e.getDate_debut();
        Date Date_fin=e.getDate_fin();
        String Type_event = e.getType_event();
        String Lieu_event = e.getLieu_event();
        int Nb_places=e.getNb_places();
                int Nb_vues=e.getNb_vues();


        String url = Statics.BASE_URL + "/evenement/addevenementJSON?nomEvent=" + Nom_event + "&dateDebut=" + Date_debut + "&dateFin=" + Date_fin + "&typeEvent="+ Type_event+"&lieuEvent="+Lieu_event +"&nbPlaces=" + Nb_places + "&nbVues=" + Nb_vues ;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public void updateEvenement(Evenement e) {
        String url = Statics.BASE_URL + "/evenement/modifierEveJSON/" + e.getID_event()+"?nomEvent=" + e.getNom_event() + "&dateDebut=" + e.getDate_debut() + "&dateFin=" + e.getDate_fin() + "&typeEvent="+e.getType_event()+"&lieuEvent="+e.getLieu_event()+"&nbPlaces="+e.getNb_places()+"&nbVues="+e.getNb_vues() ;

        req.setUrl(url);
        req.setPost(false);
       
       // req.addArgument("id", String.valueOf(e.getID_event()));
        req.addArgument("nomEvent", e.getNom_event());
        req.addArgument("dateDebut", e.getDate_debut().toString());
        req.addArgument("dateFin", e.getDate_fin().toString());
        req.addArgument("typeEvent", e.getType_event());

        req.addArgument("lieuEvent", e.getLieu_event());
        req.addArgument("nbPlaces", String.valueOf(e.getNb_places()));
        req.addArgument("nbVues", String.valueOf(e.getNb_vues()));


        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) req.getResponseData();
            String s = new String(data);
            System.out.println("Result: " + s);
        });

        NetworkManager.getInstance().addToQueue(req);
    }
    
    public boolean  deleteEvent(int id){
       String url = Statics.BASE_URL + "/evenement/deletereventJSON/" +id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
     
      }
}
