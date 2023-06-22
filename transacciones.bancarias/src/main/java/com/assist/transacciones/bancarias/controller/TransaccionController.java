package com.assist.transacciones.bancarias.controller;

import com.assist.transacciones.bancarias.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TransaccionController {
    @Autowired
    TransaccionService transaccionService;
    @PostMapping("/transaccion")
    public ResponseEntity create(HttpEntity<String> httpEntity){
        System.out.println(httpEntity.getBody());
        return ResponseEntity.ok(transaccionService.createtransaccion(httpEntity.getBody()));
    }
}
