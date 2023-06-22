package com.assist.transacciones.bancarias.repository;

import com.assist.transacciones.bancarias.entity.Transacciones;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface TransaccionRepository extends CrudRepository<Transacciones,String> {

}
