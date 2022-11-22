package com.formacion.BS7_2.role.domain;

import com.formacion.BS7_2.role.infraestructure.dto.RoleInputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Role(RoleInputDto roleInputDto){
        this.id = roleInputDto.getId();
        this.name = roleInputDto.getName();
    }
}
