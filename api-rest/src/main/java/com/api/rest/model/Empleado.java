package com.api.rest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "empleado_seq", sequenceName = "empleado_sequence", initialValue = 1, allocationSize = 1)

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empleado_seq")
    @Column(name = "empleado_id")
    private Long id;

    @Column(name = "nombres")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El campo 'nombres' solo debe contener letras")
    private String nombres;

    @Column(name = "apellidos")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El campo 'apellidos' solo debe contener letras")
    private String apellidos;

    @Column(name = "telefono")
    @Size(max = 9, message = "El campo 'telefono' debe tener un máximo de 9 caracteres")
    private String telefono;

    @Column(name = "estado")
    @Pattern(regexp = "^(ACTIVO|INACTIVO)$", message = "El campo 'estado' debe ser 'ACTIVO' o 'INACTIVO'")
    private String estado;

    @Column(name = "sueldo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El campo 'sueldo' debe ser un número mayor que cero")
    private Double sueldo;

    @Column(name = "tipoEmpleado")
    @Pattern(regexp = "^(PRACTICANTE|JUNIOR|SEMI-SENIOR|SENIOR)$", message = "El campo 'tipoEmpleado' debe ser 'PRACTICANTE', 'JUNIOR', 'SEMI-SENIOR' o 'SENIOR'")
    private String tipoEmpleado;

    @AssertTrue(message = "El campo 'estado' debe ser 'ACTIVO' o 'INACTIVO'")
    private boolean isEstadoValid() {
        return estado != null && (estado.equals("ACTIVO") || estado.equals("INACTIVO"));
    }
}

