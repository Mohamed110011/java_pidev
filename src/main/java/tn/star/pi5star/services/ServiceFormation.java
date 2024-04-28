package tn.star.pi5star.services;

import tn.star.pi5star.interfaces.IServiceFormation;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.models.Ressources;
import tn.star.pi5star.utils.Mydatabase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceFormation implements IServiceFormation<Formation> {


    private Connection cnx ;

    public ServiceFormation(){
        cnx = Mydatabase.getInstance().getCnx();
    }
    @Override
    public void addFormation(Formation formation) {
        String reqA="INSERT INTO `formation`( `title`, `description`, `image`,`date`) VALUES (?,?,?,?)";

        try{
            PreparedStatement preparedStatement = Mydatabase.getInstance().getCnx().prepareStatement(reqA);


            preparedStatement.setString(1, formation.getTitle());
            preparedStatement.setString(2, formation.getDescription());
            preparedStatement.setString(3, formation.getImage());
            preparedStatement.setDate(4,new java.sql.Date(formation.getDate().getTime()));





            int rows=preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("succees");

            }

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<Formation> getAllFormations() {
        System.out.println("get all formation");
        ArrayList<Formation> formations = new ArrayList<>();
        String qry ="SELECT * FROM `formation`";
        try {
            Statement stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()){
                Formation e = new Formation();
                e.setId(rs.getInt("id"));
                e.setTitle(rs.getString("title"));

                e.setDescription(rs.getString("description"));
                e.setDate(rs.getDate("date"));

                e.setImage(rs.getString("image"));

                formations.add(e);
            }
            System.out.println(formations);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return formations;

    }

    @Override
    public void updateFormation(Formation formation) {
        String query = "UPDATE formation SET title=?, description=?,  image=?, date=? WHERE id=?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, formation.getTitle());
            statement.setString(2, formation.getDescription());
            statement.setString(3, formation.getImage());
            statement.setDate(4, new java.sql.Date(formation.getDate().getTime()));


            statement.setLong(5, formation.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




    @Override
    public boolean delete(int id) {
        try {
            // Delete associated resources first
            String reqDResources = "DELETE FROM `resource` WHERE `id_formation_id`=?";
            PreparedStatement preparedStatementResources = Mydatabase.getInstance().getCnx().prepareStatement(reqDResources);
            preparedStatementResources.setInt(1, id);
            preparedStatementResources.executeUpdate();
            preparedStatementResources.close();

            // Now delete the formation itself
            String reqDFormation = "DELETE FROM `formation` WHERE `id`=?";
            PreparedStatement preparedStatementFormation = Mydatabase.getInstance().getCnx().prepareStatement(reqDFormation);
            preparedStatementFormation.setInt(1, id);
            int rows = preparedStatementFormation.executeUpdate();
            preparedStatementFormation.close(); // Close the PreparedStatement
            if (rows > 0) {
                System.out.println("La formation a été supprimée avec succès");
                return true; // Return true if deletion is successful
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return false;
    }
    @Override
    public Formation getFormationById(int formationId) {
        Formation formation = null;
        String query = "SELECT * FROM formation WHERE id=?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setLong(1, formationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    formation = new Formation();
                    System.out.println("hello");
                    formation.setId(resultSet.getInt("id"));
                    formation.setTitle(resultSet.getString("title"));
                    formation.setDate(resultSet.getDate("date"));
                    formation.setImage(resultSet.getString("image"));
                    formation.setDescription(resultSet.getString("description"));
                    System.out.println(formation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formation;
    }

}

