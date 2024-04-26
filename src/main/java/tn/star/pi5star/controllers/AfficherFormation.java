package tn.star.pi5star.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AfficherFormation implements Initializable {

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
    private Label  titlecard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

