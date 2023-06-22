package com.assist.transacciones.bancarias.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDto {

    private String idUsuario;
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
}
