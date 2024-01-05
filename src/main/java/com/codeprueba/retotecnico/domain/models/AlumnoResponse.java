package com.codeprueba.retotecnico.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoResponse {

    private String id;
    private String nombre;
    private String apellido;
    private String estado;
    private Integer edad;

}
