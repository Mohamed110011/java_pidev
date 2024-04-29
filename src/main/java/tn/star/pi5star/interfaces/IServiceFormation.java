package tn.star.pi5star.interfaces;

import jakarta.mail.MessagingException;
import tn.star.pi5star.models.Formation;
import tn.star.pi5star.models.Ressources;

import java.util.ArrayList;

public interface IServiceFormation<T>{
    void addFormation (T t );
    ArrayList<T> getAllFormations();




    void updateFormation(Formation formation);




    boolean delete (int id);



    Formation getFormationById(int formationId);

    void updateFormationRate(int formationId, double newRate);

    int getTotalRatings(int formationId);

    double getCurrentRating(int formationId);


    void sendEmailVerification(String toEmail, String subject, String messageContent) throws MessagingException;
}
