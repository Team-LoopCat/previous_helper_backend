package org.example.helper.domain.teacher.repository;

import org.example.helper.domain.teacher.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends CrudRepository<Teacher, UUID> {
    Optional<Teacher> findTeacherByTeacherId(UUID teacherId);
}
