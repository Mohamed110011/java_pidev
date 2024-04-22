package tn.star.pi5star.controllers;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.services.ServiceFormation;

import java.io.File;
import java.io.IOException;

public class Ajouterformation {

    @FXML
    private Button ajout;


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

    private final ServiceFormation sf = new ServiceFormation();

    @FXML
    void ajouterF(ActionEvent event) {String nom= titleF.getText();
        String description=descF.getText();



        if (nom.isEmpty()||  description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }
        sf.add(new Formation(nom,description,imageF.getAccessibleText()));

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
            // fichierTextField.setText(file.getAbsolutePath());
        }

    }


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
