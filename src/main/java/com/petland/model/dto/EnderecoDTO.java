package com.petland.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EnderecoDTO {

    @Column(name="logra",length = 50)
    private String logradouro;

    @Column(name="nr",length = 6)
    private String numero;

}
