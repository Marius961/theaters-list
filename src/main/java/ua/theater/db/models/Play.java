package ua.theater.db.models;

import java.util.List;

public class Play {

    private int id;
    private String name;
    private int theaterId;
    private String date;
    private String productionDirector;
    private String description;

    private List<Actor> actors;

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductionDirector() {
        return productionDirector;
    }

    public void setProductionDirector(String productionDirector) {
        this.productionDirector = productionDirector;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
