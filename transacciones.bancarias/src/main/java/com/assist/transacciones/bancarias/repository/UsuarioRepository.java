package com.assist.transacciones.bancarias.repository;

import com.assist.transacciones.bancarias.entity.Usuario;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,String> {
    boolean existsBycedula(String cedula);
    Usuario getByCedula(String cedula);

}
