package org.example.helper.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
public class GradeInfoId implements Serializable {
    private String grade;
    private String classroom;
}
