package com.mongoDbWithSpringBoot.mongoWithSpringBoot.repositories;

import com.mongoDbWithSpringBoot.mongoWithSpringBoot.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    //DAL

    public Optional<Student> findStudentByEmail(String email);


    /*@Query("") //CRUD Operations can be apply.
    void doTest();*/
}
