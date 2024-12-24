package com.example.studentmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class StudentController {
    @Autowired
    private DataManager dataManager;
    @Value("${student.number:0}")
    private int studentNumber;

    @GetMapping("/help")
    public String help() {
        return "getAll: /getStudent\n" +
                "get: /getStudent/{studentId}\n" +
                "add: /createStudent\n" +
                "update: /updateStudent/{studentId}\n" +
                "deleteAll: /deleteStudent\n" +
                "delete: /deleteStudent/{studentId}\n";
    }

    @PostMapping("/createStudent")
    public ResponseEntity createStudent(@RequestBody Student student) {
        if (dataManager.students.size() < studentNumber) {
            dataManager.students.add(student);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).body("out of student number");
        }
    }

    @GetMapping("/getStudent")
    public ArrayList<Student> getStudent() {
        return dataManager.students;
    }

    @GetMapping("/getStudent/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable int studentId) { // search by id
        for (Student s : dataManager.students) {
            if (s.getId() == studentId) {
                return ResponseEntity.status(200).body(s);
            }
        }
        return ResponseEntity.status(404).body(null);
    }

    @PutMapping("/updateStudent/{studentId}")
    public ResponseEntity updateStudent(@PathVariable int studentId, @RequestBody Student student) { // update or create
        for (int i = 0; i < dataManager.students.size(); i++) {
            if (dataManager.students.get(i).getId() == studentId) {
                dataManager.students.set(i, student);
                return ResponseEntity.ok().build();
            }
        }
        if (dataManager.students.size() < studentNumber) {
            dataManager.students.add(student);
            return ResponseEntity.ok().build();
        } else  {
            return ResponseEntity.status(400).body("out of student number");
        }
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable int studentId) {
        for (int i = 0; i < dataManager.students.size(); i++) {
            if (dataManager.students.get(i).getId() == studentId) {
                dataManager.students.remove(i);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(404).body("no such student");
    }
    @DeleteMapping("/deleteStudent")
    public void deleteStudent() {
        dataManager.students.clear();
    }
}
