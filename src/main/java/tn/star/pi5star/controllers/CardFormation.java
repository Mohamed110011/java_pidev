package tn.star.pi5star.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.services.ServiceFormation;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;

public class CardFormation {
    Connection con=null;
    private Formation formation;
    @FXML
    private Button ajouterressourceaff;

    @FXML
    private HBox boxaff;

    @FXML
    private Label descriptionaff;

    @FXML
    private ImageView imageaff;

    @FXML
    private Button modifieraff;

    @FXML
    private Button supprimeraff;

    @FXML
    private Label titleaff;



    public void setData(Formation formation) {

    }
        }



