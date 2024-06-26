package tn.star.pi5star.services;

import tn.star.pi5star.interfaces.IServiceRessource;
import tn.star.pi5star.models.Ressources;
import tn.star.pi5star.utils.Mydatabase;

import java.sql.*;
import java.util.ArrayList;

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
        String query = " UPDATE ressource SET  name=?, description=?, path_file =? WHERE id=? ";
        try
            (PreparedStatement statement = cnx.prepareStatement(query)) {

            statement.setString(1, ressources.getName());
            statement.setString(2, ressources.getDescription());
            statement.setString(3, ressources.getPath_file());
            statement.setLong(4, ressources.getId());
            int rows = statement.executeUpdate();
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
    public Ressources getRessoById(int ressourceId) {
        Ressources ressources = null;
        String query = "SELECT * FROM ressource WHERE id=?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setLong(1, ressourceId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ressources = new Ressources();
                    System.out.println("hello");
                    ressources.setId(resultSet.getInt("id"));
                    ressources.setId_formation_id(resultSet.getInt("id_formation"));
                    ressources.setName(resultSet.getString("name"));
                    ressources.setDescription(resultSet.getString("description"));
                    ressources.setPath_file(resultSet.getString("Path_file"));
                    System.out.println(ressources);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ressources;
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





////////////////////////
@Override
    public void incrementLike(int resourceId) {
        String query = "UPDATE ressource SET likee = likee + 1 WHERE id = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, resourceId);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Like count has been incremented for resource id: " + resourceId);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void incrementDislike(int resourceId) {
        String query = "UPDATE ressource SET dislike = dislike + 1 WHERE id = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, resourceId);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Dislike count has been incremented for resource id: " + resourceId);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    //////////////////
    @Override
    public int getLikeCount(int ressourceId) {
        int likeCount = 0;

        String query = " SELECT likee FROM ressource WHERE id = ? ";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, ressourceId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                likeCount = rs.getInt("likee");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return likeCount;
    }


    @Override
    public  int getDislikeCount(int ressourceId) {
        int dislikeCount = 0;
        String query = "SELECT dislike FROM ressource WHERE id = ?";


        try (PreparedStatement statement = cnx.prepareStatement(query)) {

            statement.setInt(1, ressourceId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                dislikeCount = rs.getInt("dislike");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dislikeCount;
    }
}



