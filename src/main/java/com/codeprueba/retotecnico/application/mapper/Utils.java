package com.codeprueba.retotecnico.application.mapper;

import com.codeprueba.retotecnico.domain.documentos.AlumnoDocument;
import com.codeprueba.retotecnico.domain.models.AlumnoRequest;
import com.codeprueba.retotecnico.domain.models.AlumnoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static AlumnoResponse entityToDto(AlumnoDocument alumnoDocument) {
        return modelMapper.map(alumnoDocument, AlumnoResponse.class);
    }

    public static AlumnoDocument dtoToEntity(AlumnoRequest alumnoResponse) {
        return modelMapper.map(alumnoResponse, AlumnoDocument.class);
    }

}
