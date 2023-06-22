package com.assist.transacciones.bancarias.controller;

import com.assist.transacciones.bancarias.dto.UsuarioDto;
import com.assist.transacciones.bancarias.entity.Usuario;
import com.assist.transacciones.bancarias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @GetMapping("/usuarios")
    public ResponseEntity<Iterable<Usuario>> list(){
        return ResponseEntity.ok(usuarioService.lista());
    }
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Usuario> getOne(@PathVariable("idUsuario") String idUsuario){
        if(!usuarioService.existId(idUsuario))
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(usuarioService.getOne(idUsuario));
    }
    @PostMapping("/usuarios/create")
    public ResponseEntity<Usuario> create(@RequestBody UsuarioDto usuarioDto){
        if(usuarioService.existId(usuarioDto.getIdUsuario()))
            return new ResponseEntity("Ya existe", HttpStatus.BAD_REQUEST);
        if(usuarioService.existCedula(usuarioDto.getCedula()))
            return new ResponseEntity("La cedula ya se encuentra registrada", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(usuarioService.save(usuarioDto));
    }
    @PutMapping("/usuarios/update")
    public ResponseEntity<Usuario> update(@RequestBody UsuarioDto usuarioDto){
        if(!usuarioService.existId(usuarioDto.getIdUsuario()))
            return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(usuarioService.Update(usuarioDto));
    }
    @DeleteMapping ("/usuarios/delete/{idUsuario}")
    public ResponseEntity<Usuario> delete(@PathVariable("idUsuario") String idUsuario){
        if(!usuarioService.existId(idUsuario))
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        usuarioService.delete(idUsuario);
        return new ResponseEntity("Usuario eliminado",HttpStatus.OK);
    }
}
