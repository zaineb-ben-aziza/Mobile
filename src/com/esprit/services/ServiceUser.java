/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.user;
import com.mycomany.utils.Statics;
import com.mycompany.gui.MakeNewPass;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.SignInForm;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author sarra
 */
public class ServiceUser {
    //singleton 

    public static ServiceUser instance = null;

    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public ServiceUser() {
        req = new ConnectionRequest();

    }
    //Signup

public void signup(TextField nom, TextField prenom, TextField region, TextField telephone, Picker dateNaissancePicker, TextField email, TextField password, ComboBox<String> genre, Resources res)  {
dateNaissancePicker.setType(Display.PICKER_TYPE_DATE);

// ...

// Get the selected date
Date selectedDate = dateNaissancePicker.getDate();

// Create a Calendar instance and set its time to the selected date
Calendar calendar = Calendar.getInstance();
calendar.setTime(selectedDate);

// Extract the year, month and day from the calendar
int year = calendar.get(Calendar.YEAR);
int month = calendar.get(Calendar.MONTH) + 1; // Add 1 because month is 0-based
int day = calendar.get(Calendar.DAY_OF_MONTH);
String formattedDate = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
String url = Statics.BASE_URL + "/register?nom=" + nom.getText().toString()
                 + "&prenom=" + prenom.getText().toString()
            + "&email=" + email.getText().toString()
            + "&region=" + region.getText().toString()
            + "&tel=" + telephone.getText().toString()
            + "&genre=" + genre.getSelectedItem()
            + "&date_nais="+formattedDate
            + "&password=" + password.getText().toString()
            + "&roles=ROLE_USER";

        req.setUrl(url);

        //Control saisi
        if (nom.getText().equals("") && password.getText().equals("") && email.getText().equals("")) {

            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);

        }

        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e) -> {

            //njib data ly7atithom fi form 
            byte[] data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 

            //    System.out.println("data ===>" + responseData);
        }
        );

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public void signin(TextField email, TextField password, Resources rs) {
        

        String url = Statics.BASE_URL + "/log?email=" + email.getText().toString() + "&password=" + password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json == "failed") {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    //System.out.println("data ==" + json);
                   
                    user user1 = new user();
                    Map<String, Object> mapuser = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                 
                    //   System.out.println(mapuser);

                    //Session 
                    if (mapuser.size() == 1) // l9a user
                    {
                        Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                    }

                    // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    {
                        user1.setNom((String) mapuser.get("nom"));
                        user1.setPrenom((String) mapuser.get("prenom"));
                        user1.setEmail((String) mapuser.get("email"));
                        user1.setMotdepasse((String) mapuser.get("password"));
                        user1.setId(Double.valueOf((double) mapuser.get("id")).intValue());
                        Storage.getInstance().writeObject("myObjectKey", user1);
                        /*   user myObject = (user) Storage.getInstance().readObject("myObjectKey");
                        System.out.println("test"+myObject);*/

                        new ProfileForm(rs).show();
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

  public void edit(int id, TextField nom, TextField prenom, ComboBox<String> roles, TextField email, TextField password, Resources rs) {
    // Create a JSON object with the updated user data
   
 

    // Send the request to the server
    String url = "http://127.0.0.1:8000/edit";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(true);
    req.addRequestHeader("Content-Type", "application/json");


    req.addResponseListener((e) -> {
        // Handle the server response here
        if (req.getResponseCode() == 200) {
            // User information was updated successfully
            new ProfileForm(rs).show();
        } else {
            // An error occurred while updating user information
            Dialog.show("Error", "An error occurred while updating user information", "OK", null);
        }
    });

    // Send the request
    NetworkManager.getInstance().addToQueueAndWait(req);
}



    public void delete(int id, Resources rs) {

        String url = Statics.BASE_URL + "/delete?id=" + id;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    //System.out.println("data ==" + json);

                    new SignInForm(rs).show();

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void forgetpassword(String email, Resources rs) {

        String url = Statics.BASE_URL + "/api/Forget?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    //System.out.println("data ==" + json);

                    new MakeNewPass(rs).show();

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public void makenwpassword(String email,String password,String code, Resources rs) {

        String url = Statics.BASE_URL + "/api/MakeNewPassword?email="+email+"&password="+password+"&code="+code;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        System.out.println(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    //System.out.println("data ==" + json);

                    new SignInForm(rs).show();

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}
