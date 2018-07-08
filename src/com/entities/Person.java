package com.entities;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String timeLine;
    private String medicines;
    private String pain;
    private String surgery;

    public Person(String firstName, String lastName, String timeLine, String medicines, String pain, String surgery) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeLine = timeLine;
        this.medicines = medicines;
        this.pain = pain;
        this.surgery = surgery;
    }

    public Person(){}

    public String getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(String timeLine) {
        this.timeLine = timeLine;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getPain() {
        return pain;
    }

    public void setPain(String pain) {
        this.pain = pain;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", timeLine='" + timeLine + '\'' +
                ", medicines='" + medicines + '\'' +
                ", pain='" + pain + '\'' +
                ", surgery='" + surgery + '\'' +
                '}';
    }
}