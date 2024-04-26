package tn.star.pi5star.models;


import java.util.Date;

public class Formation {
    private int id,idSpecialiste;
    private String title,description, image;
    private java.util.Date date;



    public Formation(String title, String description, java.sql.Date date, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.date = this.date;

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
