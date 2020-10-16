package co.edu.unal.software_engineering.labs.controller;

import co.edu.unal.software_engineering.labs.model.Association;
import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.model.User;
import co.edu.unal.software_engineering.labs.pojo.CoursePOJO;
import co.edu.unal.software_engineering.labs.repository.AssociationRepository;
import co.edu.unal.software_engineering.labs.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;


@RestController
public class CourseController{

    private final CourseService courseService;
    private final AssociationService associationService;
    private final UserService userService;
    private final RoleService roleService;
    private final PeriodService periodService;

    public CourseController( CourseService courseService, AssociationService associationService, UserService userService,RoleService roleService, PeriodService periodService){
        this.courseService = courseService;
        this.associationService = associationService;
        this.userService = userService;
        this.roleService = roleService;
        this.periodService = periodService;
    }

    @PostMapping( value = {"/profesor/cursos"} )
    public ResponseEntity<Void> createCourse( @RequestBody CoursePOJO coursePojo ){
        Course course = courseService.mapperCourseEntity( coursePojo );

        if( !courseService.isRightCourse( course ) ){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
        return new ResponseEntity<>( HttpStatus.CREATED );
    }

    @GetMapping(value = {"/usuario/courses"})
    public ResponseEntity<?> getCourses( ){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        List<Association> associations = associationService.findAssociation(user);
        return ResponseEntity.ok(associations);
    }

}