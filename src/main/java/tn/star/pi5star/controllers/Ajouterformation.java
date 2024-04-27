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
import javafx.stage.Stage;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.services.ServiceFormation;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;

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
    void ajouterF(ActionEvent event) {
        // Récupération des informations du formulaire

            sf.setTitle(titleF.getText());
            sf.setDescription(descF.getText());
            

            Date date = Date.valueOf(dateF.getValue());
            sf.setDate(date);
            //sf.setImage(imageF.getImage().getUrl());
            

            try{
                ServiceFormation serviceFormation=new ServiceFormation();
                serviceFormation.add(sf);
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
