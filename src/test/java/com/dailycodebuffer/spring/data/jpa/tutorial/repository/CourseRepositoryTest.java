package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Course;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses =
                courseRepository.findAll();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Bianca")
                .lastName("Pop")
                .build();

        Course course =
                Course.builder()
                        .title("Python")
                        .credit(5)
                        .teacher(teacher)
                        .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        PageRequest firstPageWithThreeRecords =
                 PageRequest.of(0,3);

        PageRequest secondPageWithThreeRecords =
                PageRequest.of(1, 2);

        List<Course> courses =
                courseRepository.findAll(firstPageWithThreeRecords).getContent();

        long totalElements =
                courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();

        long totalPages =
                courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();

        System.out.println("courses =" + courses );
        System.out.println("totalElements = "+ totalElements);
        System.out.println("totalPages = "+ totalPages);

        List<Course> courses2 =
                courseRepository.findAll(secondPageWithThreeRecords).getContent();

        long totalElements2 =
                courseRepository.findAll(secondPageWithThreeRecords).getTotalElements();

        long totalPages2 =
                courseRepository.findAll(secondPageWithThreeRecords).getTotalPages();

        System.out.println("courses =" + courses2 );
        System.out.println("totalElements = "+ totalElements2);
        System.out.println("totalPages = "+ totalPages2);
    }

    @Test
    public void findAllWithSorting(){
        Pageable sortByTitle =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                );
        Pageable sortByCredit =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("credit")
                );

        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                                .descending()
                                .and(Sort.by("credit"))
                );
        List<Course> courses =
                courseRepository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void printfindByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(
                        0,
                        10
                );
        List<Course> courses =
                courseRepository.findByTitleContaining(
                        "J",
                        firstPageTenRecords).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){

        Teacher teacher =
                Teacher.builder()
                        .firstName("Mihai")
                        .lastName("Enache")
                        .build();
        Student student =
                Student.builder()
                        .firstName("Ion")
                        .lastName("Ionescu")
                        .emailId("ion@gmail.com")
                        .build();

        Course course =
                Course.builder()
                        .title("AI")
                        .credit(6)
                        .teacher(teacher)
                        .build();
        course.addStudents(student);

        courseRepository.save(course);
    }
}