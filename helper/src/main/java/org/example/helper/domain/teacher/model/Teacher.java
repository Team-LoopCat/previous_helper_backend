package org.example.helper.domain.teacher.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.helper.global.security.auth.Role;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Entity
public class Teacher {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="teacherId", columnDefinition = "BINARY(16)")
    private UUID teacherId;

    @Column(columnDefinition = "varchar(5)")
    private String name;

    @Column(columnDefinition = "varchar(50)")
    private String username;

    @Column()
    private String password;

    @Column(columnDefinition = "varchar(100)")
    private String profile;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
