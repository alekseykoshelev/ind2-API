package com.example.ind2api.controller;

import com.example.ind2api.dto.FacultyDTO;
import com.example.ind2api.model.Faculty;
import com.example.ind2api.model.Student;
import com.example.ind2api.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.add(student);
    }

    @GetMapping
    public Student find(@RequestParam long id) {
        return service.get(id);
    }

    @GetMapping("/findByAgeBetween")
    public Collection<Student> findByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        return service.findByAgeBetween(minAge, maxAge);
    }

    @DeleteMapping
    public void delete(@RequestParam long id) {
        service.remove(id);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return service.update(student);
    }

    @GetMapping("/{id}/faculty")
    public FacultyDTO findFaculty(@PathVariable long id) {
        return service.findFaculty(id);
    }

    @GetMapping("/all")
    public Collection<Student> all() {
        return service.getAll();
    }
}
