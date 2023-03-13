package com.mongoDbWithSpringBoot.mongoWithSpringBoot;

import com.mongoDbWithSpringBoot.mongoWithSpringBoot.core.utils.Gender;
import com.mongoDbWithSpringBoot.mongoWithSpringBoot.entities.Address;
import com.mongoDbWithSpringBoot.mongoWithSpringBoot.entities.Student;
import com.mongoDbWithSpringBoot.mongoWithSpringBoot.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongoWithSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoWithSpringBootApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate) {
        return args -> {
            Address address = new Address("Turkey", "34890", "İstanbul");

            Student student = new Student("Furkan", "Baysan", "furkan.baysan@gmail.com",
                    Gender.MALE, address, List.of("Computer Science", "Back-End Development"),
                    BigDecimal.TEN,
                    LocalDateTime.now());

            // usingMongoTemplateAndQuery(studentRepository, mongoTemplate, student, email);

            studentRepository.findStudentByEmail(student.getEmail()) //learner => ogrenci
                    .ifPresentOrElse(learner -> {
                        System.out.println(student + " already exist");
                    }, () -> {
                        System.out.println("Inserting student " + student);
                        studentRepository.insert(student);
                    });

        };

    }

    private static void usingMongoTemplateAndQuery(StudentRepository studentRepository, MongoTemplate mongoTemplate, Student student, String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        List<Student> students = mongoTemplate.find(query, Student.class);

        if (students.size() >= 1) { // aynı mail'le bir student eklenilmek istenirse bu çalışır.
            throw new IllegalStateException("found many students with related email " + email);
        }

        if (students.isEmpty()) { // ilk kez ekleniyorsa veya farklı bir mail'i olan student eklenilmek isteniyorsa bu çalışır.
            System.out.println("Inserting student " + student);
            studentRepository.insert(student);
        } else {
            System.out.println(student + " already exist");
        }
    }
}
