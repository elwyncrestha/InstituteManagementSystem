package model;

public class CourseModel {
    private int id;
    private String title;
    private float price;
    private String duration;

    public CourseModel(){
    }

    public CourseModel(int id, String title, float price, String duration) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.duration = duration;
    }

    public CourseModel(String title, float price, String duration) {
        this.title = title;
        this.price = price;
        this.duration = duration;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
