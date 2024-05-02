package tn.star.pi5star.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.models.Ressources;
import tn.star.pi5star.services.ServiceFormation;
import tn.star.pi5star.services.ServiceRessources;

public class CardRessource {

    @FXML
    private HBox boxaff;

    @FXML
    private Label descriptionressource;

    @FXML
    private MediaView mediaViewR;

    @FXML
    private Button modifierressourcebtn;
    @FXML
    private Button likeButton;

    @FXML
    private Button dislikeButton;
    @FXML
    private Label likeCountLabel;

    @FXML
    private Label dislikeCountLabel;

    @FXML
    private Label nameressource;

    @FXML
    private Button supprimerressourcebtn;






    private Ressources ressources;
    private Formation formation;
    private Afficherressource parentController;
    public void setParentController(Afficherressource parentController) { // Ajoutez cette méthode
        this.parentController = parentController;
    }

    public void setData(Ressources ressources){
        this.ressources = ressources;
        nameressource.setText(ressources.getName());
        descriptionressource.setText(ressources.getDescription());
        Media media = new Media( ressources.getPath_file());

        // Create a MediaPlayer with the Media
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Create a MediaView and set its player to the MediaPlayer
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(320); // Set the width of the MediaView (adjust as needed)
        mediaView.setFitHeight(300); // Set the height of the MediaView (adjust as needed)

        // Add the MediaView to your layout
        // Assuming you have a layout named "layout" to which you want to add the MediaView
        boxaff.getChildren().add(mediaView);

        // Play the media
        mediaPlayer.play();
        updateLikeCount();
        updateDislikeCount();


    }





    @FXML
    void modifierRessource(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierressource.fxml"));
            Parent fxmlLoader = loader.load();
            modifierressourcebtn.getScene().setRoot(fxmlLoader);
            Modifierressource modifierressource = loader.getController();
            modifierressource.getRessourceId(ressources.getId());
            System.out.println(ressources.getId());




        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void supprimerRessource(ActionEvent event) {
        ServiceRessources serviceRessources = new ServiceRessources();
        serviceRessources.delete( ressources.getId()); // Call the delete function with the id of the child

        if (parentController != null) {
            parentController.refreshData();
        }
        // Reload the page here
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherformation.fxml"));
            Parent root = loader.load();

            // Get the current scene and set the new root
            supprimerressourcebtn.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    @FXML
    void likeResource(ActionEvent event) {
        if(dislikeButton.isDisable()) {
            dislikeButton.setDisable(false);

        }
        ServiceRessources serviceRessources = new ServiceRessources();

        serviceRessources.incrementLike(ressources.getId());
        updateLikeCount();

        likeButton.setDisable(true);
        dislikeButton.setDisable(true);
    }

    @FXML
    void dislikeResource(ActionEvent event) {
        if(likeButton.isDisable()) {
            likeButton.setDisable(false);
        }
        ServiceRessources serviceRessources = new ServiceRessources();

        serviceRessources.incrementDislike(ressources.getId());

        updateDislikeCount();
        likeButton.setDisable(true);
        dislikeButton.setDisable(true);    }



    private void updateLikeCount() {
        // Obtenir le nombre de likes pour la ressource actuelle
        ServiceRessources serviceRessources = new ServiceRessources();
        int likeCount = serviceRessources.getLikeCount(ressources.getId());
        // Mettre à jour l'affichage du nombre de likes
        likeCountLabel.setText(String.valueOf(likeCount));
    }

    private void updateDislikeCount() {
        ServiceRessources serviceRessources = new ServiceRessources();

        int dislikeCount = serviceRessources.getDislikeCount(ressources.getId());
        // Mettre à jour l'affichage du nombre de dislikes
        dislikeCountLabel.setText(String.valueOf(dislikeCount));
    }

}
