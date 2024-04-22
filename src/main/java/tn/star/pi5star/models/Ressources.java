package tn.star.pi5star.models;

public class Ressources {


    private int id, id_formation;
    private String name;



    private String description;
    private String path_file;

    public Ressources() {
    }

    public Ressources(int id_formation, String name, String description, String path_file) {
        this.id_formation = id_formation;
        this.name = name;
        this.description = description;
        this.path_file = path_file;
    }

    public Ressources(int id, int id_formation, String name, String description, String path_file) {
        this.id = id;
        this.id_formation = id_formation;
        this.name = name;
        this.description = description;
        this.path_file = path_file;
    }

    public int getId() {
        return id;
    }

    public int getId_formation() {
        return id_formation;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPath_file() {
        return path_file;
    }


    @Override
    public String toString() {
        return "ressources{" +
                "id=" + id +
                ", id_formation=" + id_formation +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", path_file='" + path_file + '\'' +
                '}';
    }
}
