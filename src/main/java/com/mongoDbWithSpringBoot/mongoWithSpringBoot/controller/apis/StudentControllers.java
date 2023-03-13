package com.mongoDbWithSpringBoot.mongoWithSpringBoot.controller.apis;


import com.mongoDbWithSpringBoot.mongoWithSpringBoot.business.StudentService;
import com.mongoDbWithSpringBoot.mongoWithSpringBoot.entities.Student;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentControllers {

    private StudentService studentService;


    @Autowired
    public StudentControllers(StudentService studentService) { // DI
        this.studentService = studentService;
    }


    @GetMapping("/get-all-students")
    public List<Student> getAllStudents() {
        return this.studentService.getAllStudents();
    }
}
