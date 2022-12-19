package com.temperature_batch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "weather")
public class Weather {
    @Id
    @GeneratedValue

    private Long id;
    private String location;
    private Date date;
    private Integer temperature;

}
