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

    public void setId(int id) {
        this.id = id;
    }

    public int getId_formation_id() {
        return id_formation;
    }

    public void setId_formation_id(int id_formation_id) {
        this.id_formation = id_formation_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath_file() {
        return path_file;
    }

    public void setPath_file(String path_file) {
        this.path_file = path_file;
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
