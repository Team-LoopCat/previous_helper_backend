package org.example.helper.domain.user.repository;

import org.example.helper.domain.user.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, String> {
    Optional<Student> findStudentByStudentId(String studentId);
}
