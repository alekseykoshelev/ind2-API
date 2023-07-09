package com.example.ind2api.repository;

import com.example.ind2api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findAllByAgeBetween(int min, int max);

    Collection<Student> findAllByFaculty_Id(long id);
}
