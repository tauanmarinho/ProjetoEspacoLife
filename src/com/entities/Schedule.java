package com.entities;

public class Schedule extends InformationDate{
    private int id;
    public String name;

    public Schedule(String name, String sunday, String monday, String tuesday, String wednesday, String thursday,
                    String friday, String saturday) {
        super(sunday, monday, tuesday, wednesday, thursday, friday, saturday);
        this.name = name;
    }

    public Schedule(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Schedule{" +
                "name='" + name + '\'' +
                ", sunday='" + sunday + '\'' +
                ", monday='" + monday + '\'' +
                ", tuesday='" + tuesday + '\'' +
                ", wednesday='" + wednesday + '\'' +
                ", thursday='" + thursday + '\'' +
                ", friday='" + friday + '\'' +
                ", saturday='" + saturday + '\'' +
                '}';
    }
}
