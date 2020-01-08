package com.example.demo.student;

import com.example.demo.student.exception.StudenNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private static final List<Student> students = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Harry Potter"),
            new Student(3, "Ron Waesley"));

    @GetMapping(path = "{id}")
    public Student getStudent(@PathVariable("id") Integer id) {

        return students.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(()->new StudenNotFoundException("Student not found "));
    }
}
