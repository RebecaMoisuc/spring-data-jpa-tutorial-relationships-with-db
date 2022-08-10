package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Guardian;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.plaf.PanelUI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
     private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("ana@yahoo.com")
                .firstName("ana")
                .lastName("pop")
              //  .guardianName("george")
              //  .guardianEmail("george@gmail.com")
              //  .guardianMobile("0000000000")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){

        Guardian guardian = Guardian.builder()
                .email("george@gmail.com")
                .name("geroge")
                .mobile("12345")
                .build();

        Student student = Student.builder()
                .firstName("Rebeca")
                .lastName("Moisuc")
                .emailId("rebeca@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        List<Student> studentList =
                studentRepository.findAll();

        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students =
        studentRepository.findByFirstName("Rebeca");
        System.out.println("studentList = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students =
                studentRepository.findByFirstNameContaining("Re");
        System.out.println("studentList = " + students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository
                .findByGuardianName("george");
        System.out.println("studentList = " + students);
    }

    @Test
    public void printgetStudentByEmailAddress(){
        Student student = studentRepository
                .getStudentByEmailAdress("rebeca@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printgetStudentFirstEmailByEmailAddress(){
        String firstName = studentRepository
                .getStudentFirstNameByEmailAdress("rebeca@gmail.com");
        System.out.println("student = " + firstName);
    }

    @Test
    public void printgetStudentByEmailAdressNative(){
        Student student =
                studentRepository.getStudentByEmailAdressNative("rebeca@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void getStudentByEmailAdressNativeNameParam(){
        Student student =
                studentRepository.getStudentByEmailAdressNativeNameParam("rebeca@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentNameByEmailId(){
        studentRepository.updateStudentNameByEmailId(
                "Rebeca M",
                "rebeca@gmail.com  "
        );
    }
}