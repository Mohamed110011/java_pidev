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
    private Button ajout;
    @FXML
    private DatePicker dateF;


    @FXML
    private Button ajout1;

    @FXML
    private Button ajout11;

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
                showAlert("Event Added", "The event has been successfully added.");
            }catch (Exception eu){
                System.out.println(eu.getMessage());
                showAlert("Event failed", "famma ghalta owwwww.");

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
        try {
            ServiceFormation serviceFormation=new ServiceFormation();
            System.out.println(serviceFormation.getAll());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cardformation.fxml"));
            Parent parent = loader.load();
            Ajouterformation displayFormationController = loader.getController();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }


    }

    @FXML
    void modifierF(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierformation.fxml"));
            Parent parent = loader.load();
            Modifierformation displayFormationController = loader.getController();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

    }

    public void initData(Formation formation) {
    }
}
