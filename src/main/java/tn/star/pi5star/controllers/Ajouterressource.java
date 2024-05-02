package tn.star.pi5star.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.models.Ressources;
import tn.star.pi5star.services.ServiceFormation;
import tn.star.pi5star.services.ServiceRessources;
import tn.star.pi5star.utils.Validator;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class Ajouterressource  {

    @FXML
    private Button ajout;

    @FXML
    private Button ajout1;

    @FXML
    private Button choisirvideo;

    @FXML
    private TextField descR;

    @FXML
    private TextField nameR;
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private int formationId;
    ServiceFormation serviceFormation=new ServiceFormation();

    Formation sf = new Formation();

    void getFormationId(int id){
        formationId=id;
        sf=serviceFormation.getFormationById(formationId);
        System.out.println("howwwwwwwwwwww"+sf);










    }








    Ressources ressources = new Ressources();
    @FXML
    void afficherressource(ActionEvent event) {

    }

    @FXML
    void ajouterressource(ActionEvent event) {
        if (Validator.isNonEmpty(nameR) && Validator.isNonEmpty(descR)){
try{
    Ressources ressources = new Ressources();

            // Récupération des informations du formulaire

            ressources.setName(nameR.getText());
            ressources.setDescription(descR.getText());
ressources.setId_formation_id(formationId);
            ressources.setPath_file((mediaView.getMediaPlayer().getMedia().getSource()));







                ServiceRessources serviceRessources=new ServiceRessources();
                serviceRessources.addRessource(ressources);
                showAlert("Ajouter Ressource", "La ressource est ajouter.");
            }catch (Exception eu){
                System.out.println(eu.getMessage());
                showAlert("formation failed", "famma ghalta owwwww.");

            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherformation.fxml"));
            try {
                Parent fxmlLoader = loader.load();
                nameR.getScene().setRoot(fxmlLoader);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else{Validator.showAlert("Error", "Please fill in all the fields");}
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void choisirvideobtn(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une vidéo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers vidéo", "*.mp4", "*.avi", "*.mkv", "*.flv", "*.mov"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String videoPath = selectedFile.toURI().toString();
            Media media = new Media(videoPath);
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        }
    }


}