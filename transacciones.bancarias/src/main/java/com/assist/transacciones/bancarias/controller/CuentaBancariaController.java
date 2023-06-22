package com.assist.transacciones.bancarias.controller;

import com.assist.transacciones.bancarias.dto.CuentaBancariaDto;
import com.assist.transacciones.bancarias.entity.CuentaBancaria;
import com.assist.transacciones.bancarias.service.CuentaBancariaService;
import com.assist.transacciones.bancarias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CuentaBancariaController {
    @Autowired
    CuentaBancariaService cuentaBancariaService;
    @Autowired
    UsuarioService usuarioService;
    @GetMapping("/cuentas")
    public ResponseEntity<Iterable<CuentaBancaria>> list(){
        return ResponseEntity.ok(cuentaBancariaService.lista());
    }
    @GetMapping("/cuenta/{idCuenta}")
    public ResponseEntity<CuentaBancaria> getOne(@PathVariable("idCuenta") String idCuenta){
        if(!cuentaBancariaService.existId(idCuenta))
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(cuentaBancariaService.getOne(idCuenta));
    }
    @PostMapping("/cuentas/create")
    public ResponseEntity<CuentaBancaria> create(@RequestBody CuentaBancariaDto cuentaBancariaDto){
        System.out.println(usuarioService.existId(cuentaBancariaDto.getIdUsuario()));
        if(cuentaBancariaService.existId(cuentaBancariaDto.getIdCuenta()))
            return new ResponseEntity("Ya existe", HttpStatus.BAD_REQUEST);
        if(!usuarioService.existId(cuentaBancariaDto.getIdUsuario()))
            return new ResponseEntity("El usuario no existe", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(cuentaBancariaService.save(cuentaBancariaDto));
    }
    @PutMapping ("/cuentas/update")
    public ResponseEntity<CuentaBancaria> update(@RequestBody CuentaBancariaDto cuentaBancariaDto){
        if(!cuentaBancariaService.existId(cuentaBancariaDto.getIdCuenta()))
            return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(cuentaBancariaService.Update(cuentaBancariaDto));
    }
    @DeleteMapping ("/cuentas/delete/{idCuenta}")
    public ResponseEntity<CuentaBancaria> delete(@PathVariable("idCuenta") String idCuenta){
        if(!cuentaBancariaService.existId(idCuenta))
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        cuentaBancariaService.delete(idCuenta);
        return new ResponseEntity("Cuenta eliminada",HttpStatus.OK);
    }
}
