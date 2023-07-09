package com.example.ind2api.controller;

import com.example.ind2api.dto.StudentDTO;
import com.example.ind2api.model.Faculty;
import com.example.ind2api.model.Student;
import com.example.ind2api.service.FacultyService;
import com.example.ind2api.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return service.add(faculty);
    }

    @GetMapping
    public Faculty find(@RequestParam long id) {
        return service.get(id);
    }

    @GetMapping("/findByColorOrName")
    public Collection<Faculty> findByColorOrName(@RequestParam(required = false) String color,
                                                 @RequestParam(required = false) String name) {
        return service.findByColorOrName(color, name);
    }

    @DeleteMapping
    public void delete(@RequestParam long id) {
        service.remove(id);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return service.update(faculty);
    }

    @GetMapping("/{id}/students")
    public Collection<StudentDTO> getStudents(@PathVariable long id) {
        return service.findStudentByFaculty(id);
    }

    @GetMapping("/all")
    public Collection<Faculty> all() {
        return service.getAll();
    }
}
