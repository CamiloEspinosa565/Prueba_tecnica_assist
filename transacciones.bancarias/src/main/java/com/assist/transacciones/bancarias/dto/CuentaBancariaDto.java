package com.assist.transacciones.bancarias.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CuentaBancariaDto {
    private String idCuenta;
    private String saldo;
    private String idUsuario;
    private String estado;
}
