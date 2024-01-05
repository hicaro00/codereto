package com.codeprueba.retotecnico.infrastructure.controllers;

import com.codeprueba.retotecnico.application.exeptions.ApiException;
import com.codeprueba.retotecnico.domain.models.AlumnoRequest;
import com.codeprueba.retotecnico.domain.models.AlumnoResponse;
import com.codeprueba.retotecnico.infrastructure.services.AlumnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;



@RestController
@RequestMapping("/api/alumnos")

public class AlumnoController {


    private static final Logger log = LoggerFactory.getLogger(AlumnoController.class);

    @Autowired
    AlumnoService alumnoService;

    @PostMapping("/record" )
    @ResponseBody
    public Mono<ResponseEntity<Mono<AlumnoRequest>>> recordAlumno(@RequestBody AlumnoRequest alumnoRequest) {
        return alumnoService.recordAlumno(alumnoRequest)
                .map(response -> ResponseEntity.ok().body(Mono.just(response)))
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @GetMapping("/active")
    @ResponseBody
    public Mono<ResponseEntity<List<AlumnoResponse>>> getActiveAlumnos(ServerWebExchange exchange) {
        return alumnoService.getAllAlumno()
                .collectList()
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    log.error("Error al procesar", e);
                    throw new ApiException("error internoal ",HttpStatus.INTERNAL_SERVER_ERROR);
                });
    }
}
