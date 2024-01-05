package com.codeprueba.retotecnico.application.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.codeprueba.retotecnico.domain.documentos.AlumnoDocument;
import com.codeprueba.retotecnico.domain.models.AlumnoRequest;
import com.codeprueba.retotecnico.domain.models.AlumnoResponse;
import com.codeprueba.retotecnico.infrastructure.repositoris.AlumnoRepo;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ContextConfiguration(classes = {AlumnoServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AlumnoServiceImplTest {
    @MockBean
    private AlumnoRepo alumnoRepo;

    @Autowired
    private AlumnoServiceImpl alumnoServiceImpl;

    /**
     * Method under test: {@link AlumnoServiceImpl#recordAlumno(AlumnoRequest)}
     */
    @Test
    void testRecordAlumno() {
        // Arrange
        Mono<Boolean> justResult = Mono.just(true);
        when(alumnoRepo.existsByApellido(Mockito.<String>any())).thenReturn(justResult);

        // Act
        alumnoServiceImpl.recordAlumno(new AlumnoRequest());

        // Assert
        verify(alumnoRepo).existsByApellido(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AlumnoServiceImpl#recordAlumno(AlumnoRequest)}
     */
    @Test
    void testRecordAlumno2() {
        // Arrange
        Mono<Boolean> mono = mock(Mono.class);
        Mono<Object> justResult = Mono.just("Data");
        when(mono.flatMap(Mockito.<Function<Boolean, Mono<Object>>>any())).thenReturn(justResult);
        when(alumnoRepo.existsByApellido(Mockito.<String>any())).thenReturn(mono);

        // Act
        Mono<ResponseEntity<Object>> actualRecordAlumnoResult = alumnoServiceImpl.recordAlumno(new AlumnoRequest());

        // Assert
        verify(alumnoRepo).existsByApellido(Mockito.<String>any());
        verify(mono).flatMap(Mockito.<Function<Boolean, Mono<Object>>>any());
        assertSame(justResult, actualRecordAlumnoResult);
    }

    /**
     * Method under test: {@link AlumnoServiceImpl#getAllAlumno()}
     */
    @Test
    void testGetAllAlumno() {
        // Arrange
        Flux<AlumnoDocument> fromIterableResult = Flux.fromIterable(new ArrayList<>());
        when(alumnoRepo.findAll()).thenReturn(fromIterableResult);

        // Act
        alumnoServiceImpl.getAllAlumno();

        // Assert
        verify(alumnoRepo).findAll();
    }

    /**
     * Method under test: {@link AlumnoServiceImpl#getAllAlumno()}
     */
    @Test
    void testGetAllAlumno2() {
        // Arrange
        DirectProcessor<AlumnoDocument> createResult = DirectProcessor.create();
        when(alumnoRepo.findAll()).thenReturn(createResult);

        // Act
        alumnoServiceImpl.getAllAlumno();

        // Assert
        verify(alumnoRepo).findAll();
    }

    /**
     * Method under test: {@link AlumnoServiceImpl#getAllAlumno()}
     */
    @Test
    void testGetAllAlumno3() {
        // Arrange
        Flux<AlumnoDocument> flux = mock(Flux.class);
        Flux<AlumnoDocument> fromIterableResult = Flux.fromIterable(new ArrayList<>());
        when(flux.filter(Mockito.<Predicate<AlumnoDocument>>any())).thenReturn(fromIterableResult);
        when(alumnoRepo.findAll()).thenReturn(flux);

        // Act
        alumnoServiceImpl.getAllAlumno();

        // Assert
        verify(alumnoRepo).findAll();
        verify(flux).filter(Mockito.<Predicate<AlumnoDocument>>any());
    }

    /**
     * Method under test: {@link AlumnoServiceImpl#getAllAlumno()}
     */
    @Test
    void testGetAllAlumno4() {
        // Arrange
        Flux<AlumnoDocument> flux = mock(Flux.class);
        Flux<Object> fromIterableResult = Flux.fromIterable(new ArrayList<>());
        when(flux.map(Mockito.<Function<AlumnoDocument, Object>>any())).thenReturn(fromIterableResult);
        Flux<AlumnoDocument> flux2 = mock(Flux.class);
        when(flux2.filter(Mockito.<Predicate<AlumnoDocument>>any())).thenReturn(flux);
        when(alumnoRepo.findAll()).thenReturn(flux2);

        // Act
        alumnoServiceImpl.getAllAlumno();

        // Assert
        verify(alumnoRepo).findAll();
        verify(flux2).filter(Mockito.<Predicate<AlumnoDocument>>any());
        verify(flux).map(Mockito.<Function<AlumnoDocument, Object>>any());
    }

    /**
     * Method under test: {@link AlumnoServiceImpl#getAllAlumno()}
     */
    @Test
    void testGetAllAlumno5() {
        // Arrange
        Flux<Object> flux = mock(Flux.class);
        Flux<Object> fromIterableResult = Flux.fromIterable(new ArrayList<>());
        when(flux.onErrorResume(Mockito.<Function<Throwable, Publisher<Object>>>any())).thenReturn(fromIterableResult);
        Flux<AlumnoDocument> flux2 = mock(Flux.class);
        when(flux2.map(Mockito.<Function<AlumnoDocument, Object>>any())).thenReturn(flux);
        Flux<AlumnoDocument> flux3 = mock(Flux.class);
        when(flux3.filter(Mockito.<Predicate<AlumnoDocument>>any())).thenReturn(flux2);
        when(alumnoRepo.findAll()).thenReturn(flux3);

        // Act
        Flux<AlumnoResponse> actualAllAlumno = alumnoServiceImpl.getAllAlumno();

        // Assert
        verify(alumnoRepo).findAll();
        verify(flux3).filter(Mockito.<Predicate<AlumnoDocument>>any());
        verify(flux2).map(Mockito.<Function<AlumnoDocument, Object>>any());
        verify(flux).onErrorResume(Mockito.<Function<Throwable, Publisher<Object>>>any());
        assertSame(fromIterableResult, actualAllAlumno);
    }

}
