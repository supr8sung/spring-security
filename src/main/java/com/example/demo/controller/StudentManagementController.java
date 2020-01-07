package com.example.demo.controller;

import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("management/api/students")
public class StudentManagementController {

    private final static List<Student> students = Arrays.asList(new Student(1, "Supreet" +
            " Singh"), new Student(2, "Nimrat Khaira"));

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return new ResponseEntity<>(students, OK);
    }

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody Student student) {
        System.out.println(student);
       // students.add(student);
        return ResponseEntity.status(CREATED).build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable (value = "id") Integer id) {
        boolean remove = students.remove(students.get(id));
        if (remove)
            return ResponseEntity.status(NO_CONTENT).build();
        return ResponseEntity.status(NOT_FOUND).build();

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Student> update(@PathVariable(value = "id") Integer id, @Valid @RequestBody Student student){
        students.add(id,student);
        return new ResponseEntity<>(student,CREATED);

    }
}
