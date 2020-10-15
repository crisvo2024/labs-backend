package co.edu.unal.software_engineering.labs.controller;

import co.edu.unal.software_engineering.labs.pojo.CoursePOJO;
import co.edu.unal.software_engineering.labs.repository.CourseRepository;
import co.edu.unal.software_engineering.labs.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController{

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping( value = "/profesor/crear-curso")
    public ResponseEntity<?> createCourse(@RequestBody CoursePOJO coursePOJO){
        CourseService courseService = new CourseService(courseRepository);

        courseService.save(courseService.mapperCourseEntity(coursePOJO));
        return ResponseEntity.ok("Curso creado");
    }
}
