package com.assist.transacciones.bancarias.service;

import com.assist.transacciones.bancarias.dto.CuentaBancariaDto;
import com.assist.transacciones.bancarias.entity.CuentaBancaria;
import com.assist.transacciones.bancarias.repository.CuentaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CuentaBancariaService {
    @Autowired
    CuentaBancariaRepository cuentaBancariaRepository;

    public Iterable<CuentaBancaria> lista() {
        Iterable<CuentaBancaria> lista = cuentaBancariaRepository.findAll();
        lista.forEach(cuentaBancaria -> {
            String mask = cuentaBancaria.getIdCuenta().split("-")[0]+"******"+cuentaBancaria.getIdCuenta().split("-")[cuentaBancaria.getIdCuenta().split("-").length-1];
            cuentaBancaria.setIdCuenta(mask);
        });

        return lista;
    }

    public CuentaBancaria getOne(String idCuenta) {
        CuentaBancaria cuentaBancaria=cuentaBancariaRepository.findById(idCuenta).get();
        String mask = cuentaBancaria.getIdCuenta().split("-")[0]+"******"+cuentaBancaria.getIdCuenta().split("-")[cuentaBancaria.getIdCuenta().split("-").length-1];
        cuentaBancaria.setIdCuenta(mask);
        return cuentaBancaria;
    }

    public CuentaBancaria save(CuentaBancariaDto dto) {
        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setIdCuenta(dto.getIdCuenta());
        cuentaBancaria.setEstado(dto.getEstado());
        cuentaBancaria.setIdUsuario(dto.getIdUsuario());
        cuentaBancaria.setSaldo(dto.getSaldo());
        return cuentaBancariaRepository.save(cuentaBancaria);
    }

    public CuentaBancaria Update(CuentaBancariaDto dto) {
        CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findById(dto.getIdCuenta()).get();
        cuentaBancaria.setEstado(dto.getEstado());
        cuentaBancaria.setIdUsuario(dto.getIdUsuario());
        cuentaBancaria.setSaldo(dto.getSaldo());
        return cuentaBancariaRepository.save(cuentaBancaria);
    }
    public void delete(String idCuenta){
        cuentaBancariaRepository.deleteById(idCuenta);
    }
    public boolean existId(String idCuenta){
        return cuentaBancariaRepository.existsById(idCuenta);
    }
}
