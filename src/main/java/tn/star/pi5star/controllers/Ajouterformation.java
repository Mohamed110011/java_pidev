package tn.star.pi5star.controllers;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import okhttp3.*;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.services.ServiceFormation;
import tn.star.pi5star.services.ServiceRessources;
import tn.star.pi5star.utils.Validator;
import java.io.*;
import java.sql.Date;



import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;





public class Ajouterformation {






    @FXML
    private DatePicker dateF;


    @FXML
    private Button afficherF;

    @FXML
    private TextField descF;

    @FXML
    private ImageView imageF;

    @FXML
    private TextField titleF;

    Formation sf = new Formation();

    @FXML
    void ajouterF(ActionEvent event) throws MessagingException, IOException {




        if (Validator.isNonEmpty(titleF) && Validator.isNonEmpty(descF)){

        // Récupération des informations du formulaire

            sf.setTitle(titleF.getText());
            sf.setDescription(descF.getText());


            Date date = Date.valueOf(dateF.getValue());
            sf.setDate(date);
            //sf.setImage(imageF.getImage().getUrl());



            try{
                ServiceFormation serviceFormation=new ServiceFormation();
                serviceFormation.addFormation(sf);
                showAlert("Ajouter Formation", "La formation est ajouter.");
            }catch (Exception eu){
                System.out.println(eu.getMessage());
                showAlert("formation failed", "famma ghalta owwwww.");

            }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherformation.fxml"));
        try {
            Parent fxmlLoader = loader.load();
            titleF.getScene().setRoot(fxmlLoader);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        }
    else{Validator.showAlert("Error", "Please fill in all the fields");}



        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("from", "mohamed <mohamed.taher@isimg.tn>")
                .addFormDataPart("subject", "Free trial")
                .addFormDataPart("to", "{\"to\":\"mohamed.taher@isimg.tn\",\"placeholders\":{\"firstName\":\"Mohamed\"}}")
                .addFormDataPart("text", "Hi {{firstName}},"+titleF.getText()+" Une formation est ajoutee. Have a nice day!")
                .build();
        Request request = new Request.Builder()
                .url("https://n8l642.api.infobip.com/email/3/send")
                .method("POST", body)
                .addHeader("Authorization", "App 58d686143795cf706f21de210afef7f2-25437752-2a11-4b4b-a6f9-89d829f1eba3")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();



    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();


    }

    @FXML
    void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(null);
        if (file != null)
        {
            String imagePath = file.toURI().toString();
            Image image = new Image(imagePath);
            imageF.setImage(image);
            sf.setImage(file.getName());
            //fichierTextField.setText(file.getAbsolutePath());
        }}




    @FXML
    void afficherF(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherformation.fxml"));
        ServiceRessources serviceRessources=new ServiceRessources();
        System.out.println("******************************************");
        serviceRessources.getRessourcesById(120);
        System.out.println("******************************************");

        try {
            Parent fxmlLoader = loader.load();
            titleF.getScene().setRoot(fxmlLoader);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }





    public void initData(Formation formation) {
    }
}
