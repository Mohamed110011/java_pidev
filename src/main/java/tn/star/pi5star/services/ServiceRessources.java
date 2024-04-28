package tn.star.pi5star.services;

import tn.star.pi5star.interfaces.IServiceRessource;
import tn.star.pi5star.models.Ressources;
import tn.star.pi5star.utils.Mydatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRessources implements IServiceRessource<Ressources> {
    private Connection cnx ;

    public ServiceRessources(){
        cnx = Mydatabase.getInstance().getCnx();
    }
    @Override
    public void addRessource(Ressources ressources){
        String reqA="INSERT INTO `ressource`( id_formation,path_file, name,description) VALUES (?,?,?,?)";

        try{
            PreparedStatement preparedStatement = Mydatabase.getInstance().getCnx().prepareStatement(reqA);

            preparedStatement.setInt(1, ressources.getId_formation_id());
            preparedStatement.setString(2, ressources.getPath_file());
            preparedStatement.setString(3, ressources.getName());
            preparedStatement.setString(4, ressources.getDescription());




            int rows=preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("succees");

            }

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }






    @Override
    public void updateRessource(Ressources ressources){
        String reqU="UPDATE `formation` SET" +
                "`name`=?,`description`=?,`path_file` =?WHERE `id`=?";
        try{

            PreparedStatement preparedStatement=Mydatabase.getInstance().getCnx().prepareStatement(reqU) ;
            preparedStatement.setString(1, reqU);
            preparedStatement.setString(2,reqU);
            preparedStatement.setString(3,reqU);
            preparedStatement.setInt(4,ressources.getId());
            int rows = preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("ressources a été modifié avec succés");
            }

        }catch(SQLException e){
            System.err.println(e.getMessage());
        }

    }



    @Override
    public boolean delete(int id){
        String reqD="DELETE FROM `ressource` WHERE `id`=?";

        try{
            PreparedStatement preparedStatement = Mydatabase.getInstance().getCnx().prepareStatement(reqD);

            preparedStatement.setInt(1,id);

            int rows=preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("La ressources a été supprimé avec succés");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return false;
    }



    @Override
    public ArrayList<Ressources> getRessourcesById(int RessourceId) {
        ArrayList<Ressources> ressourcesList = new ArrayList<>(); // Initialize an ArrayList to store resources
        String query = "SELECT * FROM ressource WHERE id_formation=?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, RessourceId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) { // Use a loop to iterate over all results
                    Ressources ressources = new Ressources(); // Create a new resource object for each result
                    // Set the attributes of the resource object from the database result
                    ressources.setId(resultSet.getInt("id"));
                    ressources.setId_formation_id(resultSet.getInt("id_formation"));
                    ressources.setName(resultSet.getString("name"));
                    ressources.setDescription(resultSet.getString("description"));
                    ressources.setPath_file(resultSet.getString("Path_file"));

                    System.out.println(ressources);
                    ressourcesList.add(ressources); // Add the resource object to the ArrayList
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ressourcesList; // Return the ArrayList of resources
    }


    @Override
    public Ressources getRessourceByIdFormation(int FormationId) {
        String reqAF="SELECT * FROM ressource WHERE 'id_formation'=? ";
        try{
            Statement statement = Mydatabase.getInstance().getCnx().createStatement();

            ResultSet rs=statement.executeQuery(reqAF);
            while(rs.next()){
                Ressources ressource=new Ressources (
                        rs.getInt("id"),
                        rs.getInt("id_formation"),
                        rs.getString("description"),
                        rs.getString("name"),
                        rs.getString("path_file")

                ); System.out.println(ressource);
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }


        return null;
    }



    @Override
    public ArrayList getAllRessources(){
        String reqAF="SELECT * FROM `ressource` ";
        try{
            Statement statement = Mydatabase.getInstance().getCnx().createStatement();

            ResultSet rs=statement.executeQuery(reqAF);
            while(rs.next()){
                Ressources ressource=new Ressources (
                        rs.getInt("id"),
                        rs.getInt("id_formation"),
                        rs.getString("description"),
                        rs.getString("name"),
                        rs.getString("path_file")

                ); System.out.println(ressource);
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }




}
