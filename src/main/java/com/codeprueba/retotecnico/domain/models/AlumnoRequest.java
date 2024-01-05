package com.codeprueba.retotecnico.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoRequest {

    private String id;
    private String nombre;
    private String apellido;
    private String estado;
    private Integer edad;
    }