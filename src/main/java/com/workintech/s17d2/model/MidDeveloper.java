package com.workintech.s17d2.model;

public class MidDeveloper extends Developer {
    public MidDeveloper(int id, String name, double salary) {
        super(id, name, salary-salary*0.25, Experience.MID);
    }
}
