package org.example.helper.domain.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity(name = "gradeInfo")
@IdClass(GradeInfoId.class)
@AllArgsConstructor
public class GradeInfo {
    @Id()
    @Column(nullable = false, columnDefinition = "char(1)")
    private String grade;

    @Id()
    @Column(nullable = false, columnDefinition = "char(1)")
    private String classroom;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Major major;

    protected GradeInfo () {}
}
