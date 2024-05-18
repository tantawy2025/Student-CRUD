package com.example.demo.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.Month;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StudentRepositoryTest {

    private final StudentRepository studentRepository;

    private final TestEntityManager entityManager;
    @Autowired
    public StudentRepositoryTest(StudentRepository studentRepository, TestEntityManager entityManager) {
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
    }

    @Test
    public void testCreateStudent(){
        Student student = new Student(
                "ahmed",
                "ahmed@gmail.com",
                LocalDate.of(1999, Month.JANUARY,12)
        );

        Student savedStudent = studentRepository.save(student);

        Student existStudent = entityManager.find(Student.class,savedStudent.getId());

        if ((savedStudent.equals(existStudent))) {
            System.out.println("equals entities ");
        } else {
            System.out.println("NOt equals entities");
        }
    }
}
