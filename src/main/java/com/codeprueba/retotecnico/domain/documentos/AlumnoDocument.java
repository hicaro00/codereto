package com.codeprueba.retotecnico.domain.documentos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection ="alumno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDocument {

    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String estado;
    private Integer edad;

    }
