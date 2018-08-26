package com.entities;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String telephony;
    private String cellphone;
    private String address;
    private String addressNumber;
    private String profession;
    private String dateOfBirth;
    private String pilatesIndication;
    private String activities;
    private String painActivity;
    private String medicines;
    private String smoke;
    private String disease;
    private String surgery;
    private String accident;
    private String pain;
    private String goal;
    private String timeLine;




    public Person(String firstName, String lastName, String telephony, String timeLine, String medicines, String pain, String surgery) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephony = telephony;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getTelephony() {
        return telephony;
    }

    public void setTelephony(String telephony) {
        this.telephony = telephony;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPilatesIndication() {
        return pilatesIndication;
    }

    public void setPilatesIndication(String pilatesIndication) {
        this.pilatesIndication = pilatesIndication;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getPainActivity() {
        return painActivity;
    }

    public void setPainActivity(String painActivity) {
        this.painActivity = painActivity;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getAccident() {
        return accident;
    }

    public void setAccident(String accident) {
        this.accident = accident;
    }

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }
}
