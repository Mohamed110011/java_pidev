package tn.star.pi5star.models;


import java.util.Date;

public class Formation {
    private int id,idSpecialiste;
    private int modificationCount;
    private int nombre_modifications;

    private String title,description, image ;
    private Double rate;
    private java.util.Date date;



    public Formation(String title, String description, java.sql.Date date, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.date = this.date;

    }

    public Formation(int id, int idSpecialiste, String title, String description, String image, Date date) {
        this.id = id;
        this.idSpecialiste = idSpecialiste;
        this.title = title;
        this.description = description;
        this.image = image;
        this.date = date;
    }

    public Formation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getModificationCount() {
        return modificationCount;
    }

    public void setModificationCount(int modificationCount) {
        this.modificationCount = modificationCount;
    }

    public int getnombre_modifications() {
        return nombre_modifications;
    }

    public void setnombre_modifications(int totalRatings) {
        this.nombre_modifications = totalRatings;

    }

    public int getNombre_modifications() {
        return nombre_modifications;
    }

    public void setNombre_modifications(int nombre_modifications) {
        this.nombre_modifications = nombre_modifications;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                '}';
    }


}
