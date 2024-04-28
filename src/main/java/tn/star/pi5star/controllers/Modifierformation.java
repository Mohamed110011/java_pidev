package tn.star.pi5star.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.services.ServiceFormation;
import tn.star.pi5star.utils.Validator;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Modifierformation implements Initializable {

    @FXML
    private Button ajout11;

    @FXML
    private Text esmFormation;

    @FXML
    private TextField descF;

    @FXML
    private ImageView imageF;
    @FXML
    private DatePicker dateF;

    @FXML
    private TextField titleF;
    private int formationId;
    ServiceFormation serviceFormation=new ServiceFormation();

    Formation sf = new Formation();

    void getFormationId(int id){
        formationId=id;
        sf=serviceFormation.getFormationById(formationId);
        System.out.println("howwwwwwwwwwww"+sf);

        titleF.setText(sf.getTitle());
        descF.setText(sf.getDescription());








    }

    private List<Formation> LoadFormation;

    @FXML
    void ajouterF(ActionEvent event) {
        if (Validator.isNonEmpty(titleF) && Validator.isNonEmpty(descF)){
            try{



                titleF.setText(sf.getTitle());
                sf.setId(formationId);
                Date date = Date.valueOf(dateF.getValue());
                sf.setDate(date);
                sf.setTitle(titleF.getText());
                sf.setDescription(descF.getText());

                System.out.println("--------------------------------------"+sf);

                serviceFormation.updateFormation(sf);
                showAlert("update Formation", "La formation est modifier.");
            }catch (Exception eu){
                System.out.println(eu.getMessage());
                showAlert("formation failed", "famma ghalta owwwww.");

            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherformation.fxml"));
            try {
                Parent fxmlLoader = loader.load();
                titleF.getScene().setRoot(fxmlLoader);
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
    void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(null);
        if (file != null)
        {
            String imagePath = file.toURI().toString();
            Image image = new Image(imagePath);
            imageF.setImage(image);
            sf.setImage(file.getName());
            //fichierTextField.setText(file.getAbsolutePath());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}