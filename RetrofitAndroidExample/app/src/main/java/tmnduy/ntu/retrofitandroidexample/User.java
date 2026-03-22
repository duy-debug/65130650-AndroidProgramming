package tmnduy.ntu.retrofitandroidexample;

import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private boolean isActive;
    private Job job;
    private ArrayList<Favorite> favorites;

    public User(int id, String name, boolean isActive, Job job, ArrayList<Favorite> favorites) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.job = job;
        this.favorites = favorites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public ArrayList<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Favorite> favorites) {
        this.favorites = favorites;
    }
}
