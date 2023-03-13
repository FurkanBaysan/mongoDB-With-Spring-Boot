package com.mongoDbWithSpringBoot.mongoWithSpringBoot.business;

import com.mongoDbWithSpringBoot.mongoWithSpringBoot.entities.Student;
import com.mongoDbWithSpringBoot.mongoWithSpringBoot.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {

    private StudentRepository studentRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository) { //DI
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }
}
