package com.codeprueba.retotecnico.application.services;

import com.codeprueba.retotecnico.application.mapper.Utils;
import com.codeprueba.retotecnico.domain.documentos.AlumnoDocument;
import com.codeprueba.retotecnico.domain.models.AlumnoRequest;
import com.codeprueba.retotecnico.domain.models.AlumnoResponse;
import com.codeprueba.retotecnico.infrastructure.repositoris.AlumnoRepo;
import com.codeprueba.retotecnico.infrastructure.services.AlumnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnoServiceImpl implements AlumnoService {


    private static final Logger log = LoggerFactory.getLogger(AlumnoServiceImpl.class);

    @Autowired
    AlumnoRepo alumnoRepo;


    @Override

    public Mono<ResponseEntity<Object>> recordAlumno(AlumnoRequest alumnoRequest) {
        // Validar que el ID no exista

        return alumnoRepo.existsByApellido(alumnoRequest.getApellido())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.just(ResponseEntity.badRequest().build()); // Apellido duplicado
                    } else {
                        return saveNewAlumno(alumnoRequest)
                                .map(id -> ResponseEntity.ok().build())
                                .onErrorResume(e -> {
                                    log.error("Error al procesar la solicitud", e);
                                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                                });
                    }
                });
    }

    private Mono<String> saveNewAlumno(AlumnoRequest alumnoRequest) {
        AlumnoDocument alumno = Utils.dtoToEntity(alumnoRequest);
        return alumnoRepo.save(alumno)
                .map(AlumnoDocument::getId);
    }


    @Override
    public void updateAlumno() {
        // TODO document why this method is empty
    }

    @Override
    public void deleteAlumno() {
        // TODO document why this method is empty
    }


    @Override
    public Flux<AlumnoResponse> getAllAlumno() {
        return alumnoRepo.findAll()
                    .filter(alumno -> "activo".equals(alumno.getEstado()))
                    .map(Utils::entityToDto)
                    .onErrorResume(e -> {
                        log.error("Error al obtener la lista de alumnos", e);
                        return Flux.error(new RuntimeException("Error al obtener la lista de alumnos"));
                    });
        }

}
