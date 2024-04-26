package tn.star.pi5star.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CardFormation {

    @FXML
    private Button ajouterressourceaff;

    @FXML
    private HBox boxaff;

    @FXML
    private Label descriptionaff;

    @FXML
    private TextArea descriptioncard;

    @FXML
    private ImageView imagecard;

    @FXML
    private Button modifieraff;

    @FXML
    private Button supprimeraff;

    @FXML
    private Label titleaff;

    @FXML
    private Label titlecard;

    public void setImagecard(Image nomDeFichier){
        imagecard.setImage(nomDeFichier);
    }

}
