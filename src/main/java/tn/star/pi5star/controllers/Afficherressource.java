package tn.star.pi5star.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import tn.star.pi5star.models.Formation;
import tn.star.pi5star.models.Ressources;

import tn.star.pi5star.services.ServiceFormation;
import tn.star.pi5star.services.ServiceRessources;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Afficherressource implements Initializable {

    @FXML
    private HBox CardLayout;

    @FXML
    private Button ajouterressource;

    private int formationId;

    private final ServiceFormation serviceFormation = new ServiceFormation();
    private final ServiceRessources serviceRessources = new ServiceRessources();

    private List<Ressources> LoadRessources = new ArrayList<>();

    Formation sf = new Formation();

    void getFormationId(int id) {
        formationId = id;
        System.out.println("aze" + id);
        refreshData(); // Refresh data when the formation ID is set
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // No need to load resources here as they are already loaded in getFormationId
    }

    void refreshData() {
        CardLayout.getChildren().clear(); // Clear existing elements

        LoadRessources = LoadRessources(); // Load resources again
        try {
            for (Ressources ressource : LoadRessources) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/cardressource.fxml"));
                HBox cardBox = fxmlloader.load();
                CardRessource cardRessource = fxmlloader.getController();
                cardRessource.setData(ressource);
                CardLayout.getChildren().add(cardBox);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<Ressources> LoadRessources() {
        List<Ressources> ressourcesArrayList = new ArrayList<>(serviceRessources.getRessourcesById(formationId));

        return ressourcesArrayList;
    }

    @FXML
    void navigationtoajoutR(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherformation.fxml"));
        try {
            Parent fxmlLoader = loader.load();
            ajouterressource.getScene().setRoot(fxmlLoader);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    }
