package com.formacion.BS7_2.teacher.infraestructure.dto.output;


import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link Teacher} entity
 */
@Data
@NoArgsConstructor
public class TeacherOutputDto implements Serializable {
    private  String id_teacher;
    private Integer id_person;
    private  String comments;
    private  String branch;

    public TeacherOutputDto(Teacher teacher){
       setId_teacher(teacher.getId_teacher());
       setId_person(teacher.getPerson().getId_person());
       setComments(teacher.getComments());
       setBranch(teacher.getBranch());
    }

}