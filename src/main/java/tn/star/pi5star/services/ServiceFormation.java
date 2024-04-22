package tn.star.pi5star.services;

import tn.star.pi5star.interfaces.IService;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.utils.Mydatabase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceFormation implements IService<Formation> {


    private Connection cnx ;

    public ServiceFormation(){
        cnx = Mydatabase.getInstance().getCnx();
    }
    @Override
    public void add(Formation formation) {
        String reqA="INSERT INTO `formation`( `title`, `description`, `image`) VALUES (?,?,?)";

        try{
            PreparedStatement preparedStatement = Mydatabase.getInstance().getCnx().prepareStatement(reqA);


            preparedStatement.setString(1, formation.getTitle());
            preparedStatement.setString(2, formation.getDescription());
            preparedStatement.setString(3, formation.getImage());




            int rows=preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("succees");

            }

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList getAll(){
        String reqAF="SELECT * FROM `formation` ";
        try{
            Statement statement = Mydatabase.getInstance().getCnx().createStatement();

            ResultSet rs=statement.executeQuery(reqAF);
            while(rs.next()){
                Formation formation=new Formation(
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image")


                ); System.out.println(formation);
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Formation formation) {
        String reqU="UPDATE `formation` SET" +
                "`title`=?,`description`=?,`image`=? ,`rate`=?WHERE `id`=?";
        try{

            PreparedStatement preparedStatement=Mydatabase.getInstance().getCnx().prepareStatement(reqU) ;
            preparedStatement.setString(1,formation.getTitle() );
            preparedStatement.setString(2,formation.getDescription());

            preparedStatement.setString(3,formation.getImage());
            preparedStatement.setInt(4,formation.getId());

            int rows = preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("formation a été modifié avec succés");
            }

        }catch(SQLException e){
            System.err.println(e.getMessage());
        }

    }








    @Override
    public boolean delete(Formation formation){
        String reqD="DELETE FROM `formation` WHERE `id`=?";

        try{
            PreparedStatement preparedStatement = Mydatabase.getInstance().getCnx().prepareStatement(reqD);

            preparedStatement.setInt(1,formation.getId());

            int rows=preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("La formation a été supprimé avec succés");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return false;
    }


}

