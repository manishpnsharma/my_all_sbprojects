package com.cacheable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(service.addNewStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> retriveAllStudents(@PathVariable Long id) {
        return new ResponseEntity<>(service.findStudent(id), HttpStatus.OK);
    }
    @GetMapping("/student/radis/{id}")
    public ResponseEntity<?> retriveAllStudentsRadis(@PathVariable Long id) {
        return new ResponseEntity<>(service.radisCache_RadisCache(id), HttpStatus.OK);
    }

    @PutMapping("/student")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        return new ResponseEntity<>(service.updateStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return new ResponseEntity<>(service.deleteStudent(id), HttpStatus.OK);
    }

}