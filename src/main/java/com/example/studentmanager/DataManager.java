package com.example.studentmanager;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataManager {
    public ArrayList<Student> students;

    @PostConstruct
    public void init() {
        students = new ArrayList<>();
    }
}
