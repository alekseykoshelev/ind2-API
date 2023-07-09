package com.example.ind2api.service;

import com.example.ind2api.dto.FacultyDTO;
import com.example.ind2api.dto.StudentDTO;
import com.example.ind2api.model.Faculty;
import com.example.ind2api.model.Student;
import com.example.ind2api.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty add(Faculty faculty) {
        return repository.save(faculty);
    }

    public Faculty update(Faculty faculty) {
        return repository.findById(faculty.getId())
                .map(e -> {
                    e.setColor(faculty.getColor());
                    e.setName(faculty.getName());
                    return repository.save(e);
                })
                .orElse(null);
    }

    public Faculty get(long id) {
        return repository.findById(id).orElse(null);
    }

    public void remove(long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    public Collection<StudentDTO> findStudentByFaculty(long id) {
        return repository.findById(id)
                .map(f -> {
                    var studentDtos = new ArrayList<StudentDTO>();
                    for (Student student : f.getStudents()) {
                        var facDto = new FacultyDTO(student.getFaculty().getId(), student.getFaculty().getName(), student.getFaculty().getColor());
                        var dto = new StudentDTO(student.getId(), student.getName(), student.getAge(), facDto);
                        studentDtos.add(dto);
                    }
                    return studentDtos;
                })
                .orElse(new ArrayList<>());
    }

    public Collection<Faculty> getAll() {
        return repository.findAll();
    }

    public Collection<Faculty> findByColorOrName(String color, String name) {
        return repository.findAllByColorOrNameIgnoreCase(color, name);
    }

}
