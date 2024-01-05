package com.codeprueba.retotecnico.infrastructure.services;

import com.codeprueba.retotecnico.domain.models.AlumnoRequest;
import com.codeprueba.retotecnico.domain.models.AlumnoResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


public interface AlumnoService {

  public Mono recordAlumno(AlumnoRequest alumnoRequest);

  public void updateAlumno();

  public void deleteAlumno();

  public Flux<AlumnoResponse> getAllAlumno();


}
