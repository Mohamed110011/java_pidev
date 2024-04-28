package tn.star.pi5star.controllers;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.image.ImageView;
        import javafx.scene.image.Image;
        import javafx.scene.layout.HBox;
        import tn.star.pi5star.models.Formation;
        import tn.star.pi5star.services.ServiceFormation;

public class CardFormation {

    @FXML
    private Button ajouterressourceaff;

    @FXML
    private HBox boxaff;
    @FXML
    private Button afficher_ressource;

    @FXML
    private Label descriptionaff;

    @FXML
    private ImageView imagecard;

    @FXML
    private Button modifieraff;

    @FXML
    private Button supprimeraff;
    @FXML
    private Label dateF;

    @FXML
    private Label titleaff;



    private int formationId;
    ServiceFormation serviceFormation=new ServiceFormation();

    Formation sf = new Formation();

    void getFormationId(int id){
        formationId=id;
        sf=serviceFormation.getFormationById(formationId);
        System.out.println("howwwwwwwwwwww"+sf);










    }



    private Formation formation;
    private AfficherFormation parentController;
    public void setParentController(AfficherFormation parentController) { // Ajoutez cette méthode
        this.parentController = parentController;
    }
    public void setData(Formation formation){
        this.formation = formation;
        titleaff.setText(formation.getTitle());
        descriptionaff.setText(formation.getDescription());
        Image image = new Image("file:/C:/Users/mohamed/Downloads/"+ formation.getImage());
        imagecard.setImage(image);
        imagecard.setCache(true);
        dateF.setText(formation.getDate().toString());
    }



    @FXML
    void modifierFormation(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierformation.fxml"));
            Parent fxmlLoader = loader.load();
            modifieraff.getScene().setRoot(fxmlLoader);
            Modifierformation modifierformation = loader.getController();
            modifierformation.getFormationId(formation.getId());
            System.out.println(formation.getId());




        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @FXML
    void supprimerFormation(ActionEvent event) {
        ServiceFormation serviceFormation = new ServiceFormation();
        serviceFormation.delete( formation.getId()); // Call the delete function with the id of the child

        if (parentController != null) {
            parentController.refreshData();
        }
        // Reload the page here
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherFormation.fxml"));
            Parent root = loader.load();

            // Get the current scene and set the new root
            supprimeraff.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }




    @FXML
    void ajouterRessource(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterressource.fxml"));
            Parent fxmlLoader = loader.load();
            ajouterressourceaff.getScene().setRoot(fxmlLoader);
            Ajouterressource ajouterressource = loader.getController();
            ajouterressource.getFormationId(formation.getId());
            System.out.println(formation.getId());




        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @FXML
    void afficherressourcebtn(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherressource.fxml"));
            Parent fxmlLoader = loader.load();
            modifieraff.getScene().setRoot(fxmlLoader);
            Afficherressource afficherressource = loader.getController();
            afficherressource.getFormationId(formation.getId());
            System.out.println(formation.getId());




        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


}



