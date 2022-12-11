package com.example.Telikoshazelcastcacheasidepoc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@ToString
@Table("Person")
@Builder
@AllArgsConstructor
public class PersonDto {
    @Id
    @Column("id")
    private Integer id;
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
    @Column("birthdate")
    private LocalDate birthdate;
}
