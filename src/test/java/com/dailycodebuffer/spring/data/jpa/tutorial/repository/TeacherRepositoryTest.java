package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Course;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

        Course courseMCM = Course.builder()
                .title("MCM")
                .credit(6)
                .build();

        Course courseJava = Course.builder()
                .title("Java")
                .credit(4)
                .build();

        Teacher teacher =
                Teacher.builder()
                        .firstName("Andrei")
                        .lastName("Popescu")
                       // .courses(List.of(courseMCM, courseJava))
                        .build();
        teacherRepository.save(teacher);
    }
}