package com.petland.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tab_animal")
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private  Integer id;

    @Column(length = 50, nullable = false)
    private String nome;
    private LocalDate aniversario;

    @Enumerated(EnumType.STRING)
    private AnimalEspecie especie;
}
