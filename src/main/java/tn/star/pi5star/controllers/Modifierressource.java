package tn.star.pi5star.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import java.util.List;
import java.util.ResourceBundle;

import static tn.star.pi5star.utils.Validator.showAlert;

public class Modifierressource implements Initializable {

    @FXML
    private Button choisirImage;

    @FXML
    private TextField descRessource;

    @FXML
    private MediaView mediaViewRessource;

    @FXML
    private Button modierRessourcebtn;

    @FXML
    private TextField nameRessource;
    private MediaPlayer mediaPlayer;
    private int ressourceId;
    private int formationId;

    ServiceRessources serviceRessources=new ServiceRessources();

    Ressources sr = new Ressources();


    void getRessourceId(int id){
        ressourceId=id;
        sr=serviceRessources.getRessoById(ressourceId);
        System.out.println("howwwwwwwwwwww55"+sr);

        nameRessource.setText(sr.getName());
        descRessource.setText(sr.getDescription());


    }

    private List<Formation> LoadFormation;

    @FXML
    void choisirImage(ActionEvent event) {
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
            mediaViewRessource.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        }

    }

    @FXML
    void modifierRessource(ActionEvent event) {
        if (Validator.isNonEmpty(nameRessource) && Validator.isNonEmpty(descRessource)){
            try{



                //nameRessource.setText(sr.getName());
                sr.setId(ressourceId);
                //sr.setPath_file(mediaViewRessource.get);
                sr.setName(nameRessource.getText());
                sr.setDescription(descRessource.getText());
                //sf.setDescription(descF.getText());

                System.out.println("--------------------------------------"+sr);

                serviceRessources.updateRessource(sr);
                showAlert("update Formation", "La formation est modifier.");
            }catch (Exception eu){
                System.out.println(eu.getMessage());
                showAlert("formation failed", "famma ghalta owwwww.");

            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherformation.fxml"));
            try {
                Parent fxmlLoader = loader.load();
                nameRessource.getScene().setRoot(fxmlLoader);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            showAlert("Error", "Please fill in all the fields");}

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
