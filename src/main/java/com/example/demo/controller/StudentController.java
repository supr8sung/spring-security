package com.example.demo.controller;

import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private final static List<Student> students = Arrays.asList(new Student(1, "Supreet" +
            " Singh"), new Student(2, "Nimrat Khaira"));

    @GetMapping(path = "{studentId}")

    public ResponseEntity<Student> getOne(@PathVariable("studentId") Integer studentId) {
        return new ResponseEntity<>(students.stream()
                .filter(student -> studentId.equals(student.getId()))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Student not found")),
                HttpStatus.OK);
    }
}
