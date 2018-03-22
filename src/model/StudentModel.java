package model;

public class StudentModel {
    private int id;
    private String name;
    private String country; //uses options in jsp page
    private String gender;  //uses radio button in jsp page
    private String[] hobbies; //uses checkbox in jsp pages
    private String displayHobbies;



    public StudentModel() {
    }

    public StudentModel(String name, String country, String gender, String[] hobbies) {
        this.name = name;
        this.country = country;
        this.gender = gender;
        this.hobbies = hobbies;
    }

    public StudentModel(int id, String name, String country, String gender, String[] hobbies) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.gender = gender;
        this.hobbies = hobbies;
    }

    public StudentModel(int id, String name, String country, String gender, String displayHobbies) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.gender = gender;
        this.displayHobbies = displayHobbies;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public String getDisplayHobbies() {
        return displayHobbies;
    }

    public void setDisplayHobbies(String displayHobbies) {
        this.displayHobbies = displayHobbies;
    }
}
