package org.example.helper.domain.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.helper.global.security.auth.Role;

@Getter
@Entity(name = "student")
@AllArgsConstructor
public class Student {
    @Id()
    @Column(nullable = false, unique = true, columnDefinition = "char(4)")
    private String studentId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, targetEntity = GradeInfo.class)
    @JoinColumns({
            @JoinColumn(name = "grade", referencedColumnName = "grade"),
            @JoinColumn(name = "classroom", referencedColumnName = "classroom")
    })
    private GradeInfo gradeInfo;

    // todo: subject 엔티티 추가시 외래키 추가할 것

    @Column(nullable = false, unique = true, columnDefinition = "varchar(40)")
    private String id;

    @Column(nullable = false, columnDefinition = "varchar(40)")
    private String nickname;

    @Column(nullable = false, columnDefinition = "varchar(225)")
    private String password;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String profile;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String email;

    @Column()
    @Enumerated(EnumType.STRING)
    private Role role;

    protected Student () {}
}
