package tn.star.pi5star.interfaces;

import tn.star.pi5star.models.Formation;
import tn.star.pi5star.models.Ressources;

import java.util.ArrayList;

public interface IServiceRessource <T>{
    void addRessource (T t );
    ArrayList<T> getAllRessources();




    void updateRessource(Ressources ressource);




    boolean delete (int id);



    Ressources getRessourceById(int RessourceId);
}
