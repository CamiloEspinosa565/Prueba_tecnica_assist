package com.assist.transacciones.bancarias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransaccionDto {
    private String idTransaccion;
    private String tipo;
    private String valor;
    private String origen;
    private String idCuenta;
    private String usuario;
}
