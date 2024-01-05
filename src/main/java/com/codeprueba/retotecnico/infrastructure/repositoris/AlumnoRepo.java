package com.codeprueba.retotecnico.infrastructure.repositoris;

import com.codeprueba.retotecnico.domain.documentos.AlumnoDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public interface AlumnoRepo extends ReactiveMongoRepository<AlumnoDocument,String> {


    Mono<Boolean> existsByApellido(String apellido);

}
