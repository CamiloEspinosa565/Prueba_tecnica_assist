package com.assist.transacciones.bancarias.repository;

import com.assist.transacciones.bancarias.entity.CuentaBancaria;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface CuentaBancariaRepository extends CrudRepository<CuentaBancaria,String> {
    boolean existsByidCuenta(String idCuenta);
}
