package com.formacion.BS7_2.role.infraestructure.dto;

import com.formacion.BS7_2.role.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleOutputDto {
    private Long id;
    private String name;

    public RoleOutputDto (Role role){
        this.id = role.getId();
        this.name = role.getName();

    }


}
