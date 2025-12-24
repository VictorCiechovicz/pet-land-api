package com.petland.model.entity;

import com.petland.model.AtendimentoStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tab_atendimento")
@Data
public class AtendimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(length = 255)
    private String descricao;

    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AtendimentoStatus status;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private AnimalEntity animal;

    @ManyToOne
    @JoinColumn(name = "cadastro_id")
    private CadastroEntity cliente;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private ProdutoServicoEntity servico;
}

