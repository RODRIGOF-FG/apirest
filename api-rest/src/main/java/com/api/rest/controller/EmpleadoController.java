package com.api.rest.controller;

import com.api.rest.model.Empleado;
import com.api.rest.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleado")
    public ResponseEntity<Iterable<Empleado>> listarTodasLosEmpleados() {
        return new ResponseEntity<>(empleadoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/empleado")
    public ResponseEntity<?> crearEncuesta(@RequestBody Empleado empleado) {
        empleado = empleadoRepository.save(empleado);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI newEmpleadoUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(empleado.getId()).toUri();
        httpHeaders.setLocation(newEmpleadoUri);
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<?> obtenerEmpleado(@PathVariable Long empleadoId) {
        Optional<Empleado> empleado = empleadoRepository.findById(empleadoId);
        if (empleado.isPresent()) {
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/empleado/{empleadoId}")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody Empleado empleado, @PathVariable Long empleadoId) {
        empleado.setId(empleadoId);
        Empleado e = empleadoRepository.save(empleado);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/empleado/{empleadoId}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long empleadoId) {
        empleadoRepository.deleteById(empleadoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
