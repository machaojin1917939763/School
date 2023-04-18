package com.school.mybatis.config;

import java.util.List;
import java.util.Map;

public class MyService {
    private String name;
    private List<String> hobby;
    private Map<String,String> skill;

    public Map<String, String> getSkill() {
        return skill;
    }

    public void setSkill(Map<String, String> skill) {
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "MyService{" +
                "name='" + name + '\'' +
                ", hobby=" + hobby +
                ", skill=" + skill +
                '}';
    }
}
