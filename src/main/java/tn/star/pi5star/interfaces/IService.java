package tn.star.pi5star.interfaces;

import tn.star.pi5star.models.Formation;
import tn.star.pi5star.models.Ressources;

import java.util.ArrayList;

public interface IService<T>{
    void add (T t );
    ArrayList<T> getAll();




    void update(Formation formation);


    void update(Ressources ressources);

    boolean delete (int id);



    Formation getFormationById(int formationId);
}
