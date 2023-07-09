package com.example.ind2api.service;

import com.example.ind2api.dto.FacultyDTO;
import com.example.ind2api.model.Faculty;
import com.example.ind2api.model.Student;
import com.example.ind2api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student add(Student student) {
        return repository.save(student);
    }

    public Student update(Student student) {
        return repository.findById(student.getId())
                .map(entity -> {
                    entity.setAge(student.getAge());
                    entity.setName(student.getName());
                    entity.setFaculty(student.getFaculty());
                    return repository.save(entity);
                })
                .orElse(null);
    }

    public Student get(long id) {
        return repository.findById(id).orElse(null);
    }

    public void remove(long id) {
        repository.deleteById(id);
    }

    public Collection<Student> getAll() {
        return repository.findAll();
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        return repository.findAllByAgeBetween(minAge, maxAge);
    }

    public FacultyDTO findFaculty(long id) {
        return repository.findById(id).map(student -> {
            FacultyDTO dto = new FacultyDTO();
            dto.setId(student.getFaculty().getId());
            dto.setName(student.getFaculty().getName());
            dto.setColor(student.getFaculty().getColor());
            return dto;
        }).orElse(null);
    }


}
