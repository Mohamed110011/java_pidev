package tn.star.pi5star.interfaces;

import tn.star.pi5star.models.Formation;
import tn.star.pi5star.models.Ressources;

import java.util.ArrayList;
import java.util.List;

public interface IServiceRessource <T>{
    void addRessource (T t );

    Ressources getRessoById(int ressourceId);

    ArrayList<T> getAllRessources();




    void updateRessource(Ressources ressource);




    boolean delete (int id);





    List<Ressources> getRessourcesById(int RessourceId);

    Ressources getRessourceByIdFormation(int FormationId);

    ////////////////////////
    void incrementLike(int resourceId);

    void incrementDislike(int resourceId);

    //////////////////
    int getLikeCount(int ressourceId);

    int getDislikeCount(int ressourceId);
}

