package com.petland.model.entity;


import com.petland.model.dto.EnderecoDTO;
import com.petland.model.dto.PerfilDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "tab_cadastro")
@Data
public class CadastroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private  Integer id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Embedded
    private PerfilDTO perfilDTO;

    @Embedded
   private EnderecoDTO enderecoDTO;


}
