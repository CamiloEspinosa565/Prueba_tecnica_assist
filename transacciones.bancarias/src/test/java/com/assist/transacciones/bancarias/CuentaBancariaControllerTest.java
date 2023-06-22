package com.assist.transacciones.bancarias;

import com.assist.transacciones.bancarias.controller.CuentaBancariaController;
import com.assist.transacciones.bancarias.dto.UsuarioDto;
import com.assist.transacciones.bancarias.entity.CuentaBancaria;
import com.assist.transacciones.bancarias.entity.Usuario;
import com.assist.transacciones.bancarias.repository.CuentaBancariaRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CuentaBancariaControllerTest extends ApplicationTests {
    @Autowired
    private CuentaBancariaController cuentaBancariaController;

    @MockBean
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Test
    public void getOneTest()  {
        cuentaBancariaRepository =new CuentaBancariaRepository() {
            @Override
            public boolean existsByidCuenta(String idCuenta) {
                return false;
            }

            @Override
            public <S extends CuentaBancaria> S save(S entity) {
                return null;
            }

            @Override
            public <S extends CuentaBancaria> Iterable<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public Optional<CuentaBancaria> findById(String s) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(String s) {
                return false;
            }

            @Override
            public Iterable<CuentaBancaria> findAll() {
                return null;
            }

            @Override
            public Iterable<CuentaBancaria> findAllById(Iterable<String> strings) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(String s) {

            }

            @Override
            public void delete(CuentaBancaria entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends String> strings) {

            }

            @Override
            public void deleteAll(Iterable<? extends CuentaBancaria> entities) {

            }

            @Override
            public void deleteAll() {

            }
        };
        cuentaBancariaController = new CuentaBancariaController();
        CuentaBancaria cuentaBancaria=new CuentaBancaria("3213-2324-5432","10000","10","1");
        Optional<CuentaBancaria> cuentaBancariaOptional = Optional.of(cuentaBancaria);
        when(cuentaBancariaRepository.findById("3213-2324-5432")).thenReturn(cuentaBancariaOptional);

        ResponseEntity<CuentaBancaria> responseMock = cuentaBancariaController.getOne("3213-2324-5432");

        assertNotNull(responseMock);
        assertNotNull(responseMock.getBody());
        assertEquals("3213-2324-5432", responseMock.getBody().getIdCuenta());
       // assertEquals(getClientListDto(), responseMock.getBody().getResponseBody());
    }
    @Test
    public void prueba(){
        int i =1+1;
    }
}
