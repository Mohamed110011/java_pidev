package tn.star.pi5star.services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import tn.star.pi5star.interfaces.IServiceFormation;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.utils.Mydatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

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




            sendEmailVerification("mohamed.taher@isimg.tn", "Sujet de l'email", "Corps de l'email wwwww");
            int rows=preparedStatement.executeUpdate();

            if(rows>0){
                System.out.println("succees");

            }

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
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
            String reqDResources = "DELETE FROM `ressource` WHERE `id_formation`=?";
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
    @Override
    public void updateFormationRate(int formationId, double newRate) {

        String sql = "UPDATE formation SET rate = (rate * nombre_modifications + ?) / (nombre_modifications + 1), nombre_modifications = nombre_modifications + 1, modificationCount = modificationCount + 1 WHERE id = ?";

        try (PreparedStatement statement = cnx.prepareStatement(sql))
              {

                  statement.setDouble(1, newRate);

                  statement.setInt(2, formationId);
                  System.out.println("newRate"+newRate);
                  statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la notation de la formation: " + e.getMessage());
        }


    }
    @Override
    public int getTotalRatings(int formationId) {
        int nombre_modifications = 0;
        // Code pour récupérer le nombre total de notations pour la formation spécifique dans votre système de stockage

        // Exemple de requête dans une base de données MySQL avec JDBC
        String sql = "SELECT nombre_modifications FROM formation WHERE id = ?";

        try (PreparedStatement statement = cnx.prepareStatement(sql)) {

            statement.setInt(1, formationId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                nombre_modifications = rs.getInt("nombre_modifications");

            }

            System.out.println("nombre_modifications"+nombre_modifications);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du nombre total de notations pour la formation: " + e.getMessage());
        }

        return nombre_modifications;

    }

    @Override
    public double getCurrentRating(int formationId) {
        double modificationCount = 0.0;
        // Code pour récupérer le rating actuel de la formation spécifique dans votre système de stockage

        // Exemple de requête dans une base de données MySQL avec JDBC
        String sql = "SELECT modificationCount FROM formation WHERE id = ?";

        try (PreparedStatement statement = cnx.prepareStatement(sql))  {

            statement.setInt(1, formationId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                modificationCount = rs.getDouble("modificationCount");
            }

            System.out.println("modificationCOUNT"+modificationCount);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du rating actuel de la formation: " + e.getMessage());
        }

        return modificationCount;
    }
    private static final String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String SMTP_HOST_NAME = "ssl0.ovh.net";
    private static final String SMTP_AUTH_USER = "dev@seek4digital.com";
    private static final String SMTP_AUTH_PWD = "S4d@dev2023";

    @Override
    public void sendEmailVerification(String toEmail, String subject, String messageContent) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST_NAME);
        properties.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(properties, auth);

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(SMTP_AUTH_USER));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

        msg.setSubject(subject);
        msg.setContent(messageContent, "text/html");

        Transport.send(msg);
    }
    private class SMTPAuthenticator extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
        }
    }




    }




