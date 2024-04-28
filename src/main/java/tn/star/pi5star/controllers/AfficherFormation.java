package tn.star.pi5star.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.services.ServiceFormation;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherFormation implements Initializable {

    @FXML
    private HBox CardLayoout;

    @FXML
    private Button ajouterformation;

    private final ServiceFormation serviceFormation= new ServiceFormation();

    private List<Formation> LoadFormation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadFormation = new ArrayList<>(LoadFormation());

        try {
            for (int i = 0; i < LoadFormation.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/cardformation.fxml"));
                HBox cardBox = fxmlloader.load();
                 CardFormation formationCard = fxmlloader.getController();
                formationCard.setData(LoadFormation.get(i));
                CardLayoout.getChildren().add(cardBox);

            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    void refreshData() {
        CardLayoout.getChildren().clear(); // Effacer les éléments existants

        LoadFormation = new ArrayList<>(LoadFormation());
        try {
            for (int i = 0; i < LoadFormation.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/cardformation.fxml"));
                HBox cardBox = fxmlloader.load();
                CardFormation formationCard = fxmlloader.getController();
                formationCard.setData(LoadFormation.get(i));
                CardLayoout.getChildren().add(cardBox);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private List<Formation> LoadFormation(){
        List<Formation> formations = new ArrayList<>(serviceFormation.getAllFormations());
        return formations;
    }


    @FXML
    void navigationtoajout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterformation.fxml"));
        try {
            Parent fxmlLoader = loader.load();
            ajouterformation.getScene().setRoot(fxmlLoader);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    }



