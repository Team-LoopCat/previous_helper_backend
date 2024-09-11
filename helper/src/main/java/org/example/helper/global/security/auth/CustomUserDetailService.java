package org.example.helper.global.security.auth;

import lombok.RequiredArgsConstructor;
import org.example.helper.domain.teacher.model.Teacher;
import org.example.helper.domain.teacher.repository.TeacherRepository;
import org.example.helper.domain.user.model.Student;
import org.example.helper.domain.user.repository.StudentRepository;
import org.example.helper.global.error.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByStudentId(String studentId) throws UserNotFoundException {
        Student student = studentRepository.findStudentByStudentId(studentId).orElseThrow();

        return new CustomUserDetail(student.getStudentId(), student.getRole());
    }

    public UserDetails loadUserByTeacherId(String teacherId) throws UserNotFoundException {
        Teacher teacher = teacherRepository.findTeacherByTeacherId(UUID.fromString(teacherId)).orElseThrow();

        return new CustomUserDetail(teacher.getTeacherId().toString(), teacher.getRole());
    }
}
