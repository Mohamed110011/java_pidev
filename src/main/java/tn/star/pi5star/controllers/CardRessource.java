package tn.star.pi5star.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.models.Ressources;

public class CardRessource {

    @FXML
    private HBox boxaff;

    @FXML
    private static Label descriptionressource;

    @FXML
    private MediaView mediaViewR;

    @FXML
    private Button modifierressourcebtn;

    @FXML
    private static Label nameressource;

    @FXML
    private Button supprimerressourcebtn;






    private Ressources ressources;
    private Afficherressource parentController;
    public void setParentController(Afficherressource parentController) { // Ajoutez cette méthode
        this.parentController = parentController;
    }

    public void setData(Ressources ressources){
        this.ressources = ressources;
        nameressource.setText(ressources.getName());
        descriptionressource.setText(ressources.getDescription());
        Media media = new Media("file:/C:/Users/mohamed/Downloads/" + ressources.getPath_file());

        // Create a MediaPlayer with the Media
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Create a MediaView and set its player to the MediaPlayer
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(400); // Set the width of the MediaView (adjust as needed)
        mediaView.setFitHeight(300); // Set the height of the MediaView (adjust as needed)

        // Add the MediaView to your layout
        // Assuming you have a layout named "layout" to which you want to add the MediaView
        boxaff.getChildren().add(mediaView);

        // Play the media
        mediaPlayer.play();


    }





    @FXML
    void modifierRessource(ActionEvent event) {

    }

    @FXML
    void supprimerRessource(ActionEvent event) {

    }

}
