package tn.star.pi5star.services;

import tn.star.pi5star.interfaces.IService;
import tn.star.pi5star.models.Ressources;
import tn.star.pi5star.utils.Mydatabase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceRessources implements IService<Ressources> {
    private Connection cnx ;

    public ServiceRessources(){
        cnx = Mydatabase.getInstance().getCnx();
    }
    @Override
    public void add(Ressources ressources){
        String reqA="INSERT INTO `formation`(`id`, 'id_formation','description', 'name','path_file') VALUES (?,?,?,?,?)";

        try{
            PreparedStatement preparedStatement = Mydatabase.getInstance().getCnx().prepareStatement(reqA);

            preparedStatement.setInt(1, ressources.getId());
            preparedStatement.setInt(2, ressources.getId_formation());
            preparedStatement.setString(3, ressources.getDescription());
            preparedStatement.setString(4, ressources.getName());
            preparedStatement.setString(5, ressources.getPath_file());


            int rows=preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("succees");

            }

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    public void update(Ressources ressources){
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
    public boolean delete(Ressources ressources){
        String reqD="DELETE FROM `ressources` WHERE `id`=?";

        try{
            PreparedStatement preparedStatement = Mydatabase.getInstance().getCnx().prepareStatement(reqD);

            preparedStatement.setInt(1,ressources.getId());

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
    public ArrayList getAll(){
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
