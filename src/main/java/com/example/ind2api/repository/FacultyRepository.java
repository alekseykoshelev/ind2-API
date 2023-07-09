package com.example.ind2api.repository;

import com.example.ind2api.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findAllByColorOrNameIgnoreCase(String color, String name);

    @Query("select f from Faculty f join Student s on s.faculty.id = f.id where s.id = :studentId")
    Faculty findFacultyByStudentId(long studentId);
}
