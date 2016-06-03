package com.daffpunks.riodejaneiro2016.Items;

/**
 * Created by User on 25.04.2016.
 */
public class Event {

    long    id;
    String  name;
    int     day;
    String  time;
    long    sport_id;

    public Event(long id, String name, int day, String time, long sport_id) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.time = time;
        this.sport_id = sport_id;
    }

    public Event(String name, int day, String time, long sport_id) {
        this.name = name;
        this.day = day;
        this.time = time;
        this.sport_id = sport_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
