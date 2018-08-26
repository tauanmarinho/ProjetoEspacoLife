package com.entities;

import java.util.ArrayList;
import java.util.List;

public class InformationTime {
    public List<String> hour = new ArrayList<String>();
    public List <String> min = new ArrayList<String>();

    public InformationTime() {
    }

    public List<String> getHour() {
        return hour;
    }

    public void setHour(List<String> hour) {
        this.hour = hour;
    }

    public List<String> getMin() {
        return min;
    }

    public void setMin(List<String> min) {
        this.min = min;
    }
}
