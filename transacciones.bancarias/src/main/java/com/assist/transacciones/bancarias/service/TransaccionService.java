package com.assist.transacciones.bancarias.service;

import com.assist.transacciones.bancarias.entity.CuentaBancaria;
import com.assist.transacciones.bancarias.entity.Transacciones;
import com.assist.transacciones.bancarias.entity.Usuario;
import com.assist.transacciones.bancarias.repository.CuentaBancariaRepository;
import com.assist.transacciones.bancarias.repository.TransaccionRepository;
import com.assist.transacciones.bancarias.repository.UsuarioRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TransaccionService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CuentaBancariaRepository cuentaBancariaRepository;
    @Autowired
    TransaccionRepository transaccionRepository;
    @Autowired
    CuentaBancariaService cuentaBancariaService;

    public String createtransaccion(String transaccion) {
        JSONObject jsonTransaccion = new JSONObject(transaccion);
        JSONObject user = new JSONObject(jsonTransaccion.get("usuario").toString());
        String valor = jsonTransaccion.get("valor").toString();
        String tipo = jsonTransaccion.get("tipo").toString();
        String cuenta = jsonTransaccion.get("cuenta").toString();
        String origen = jsonTransaccion.get("origen").toString();
        if (tipo.equals("3")) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://ba8jztabje.execute-api.us-east-1.amazonaws.com/sandbox/proceso/transaccion/interbancaria"))
                    .headers("Content-Type", "application/jsom")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonTransaccion.toString()))
                    .build();
            HttpResponse<String> res = null;
            try {
                res = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return res.body();
        } else {

            if (!cuentaBancariaService.existId(cuenta))
                return "La cuenta no exite";
            if (tipo.equals("1")) {
                CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findById(cuenta).get();
                cuentaBancaria.setSaldo(String.valueOf(Integer.parseInt(cuentaBancaria.getSaldo()) + Integer.parseInt(valor)));
                cuentaBancariaRepository.save(cuentaBancaria);
            } else if (tipo.equals("2")) {
                if (!usuarioRepository.existsBycedula(user.get("cedula").toString()))
                    return "el usuario no existe";
                Usuario usuario = usuarioRepository.getByCedula(user.get("cedula").toString());
                CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findById(cuenta).get();
                if ((Integer.parseInt(cuentaBancaria.getSaldo()) - Integer.parseInt(valor)) < 0)
                    return "No tiene fondos suficientes";
                if (!cuentaBancaria.getIdUsuario().equals(usuario.getIdUsuario()))
                    return "No tiene permitidos los retiros de esta cuenta";
                cuentaBancaria.setSaldo(String.valueOf(Integer.parseInt(cuentaBancaria.getSaldo()) - Integer.parseInt(valor)));
                cuentaBancariaRepository.save(cuentaBancaria);
            }
            int randomNum = 0;
            boolean free = false;
            while (free == false) {
                Random rand = new Random();
                randomNum = rand.nextInt((9999999 - 0) + 1) + 0;
                if (!transaccionRepository.existsById(String.valueOf(randomNum)))
                    free = true;
            }
            Transacciones transacciones = new Transacciones();
            transacciones.setIdTransaccion(String.valueOf(randomNum));
            transacciones.setIdCuenta(cuenta);
            transacciones.setOrigen(origen);
            transacciones.setTipo(tipo);
            transacciones.setUsuario(user.toString());
            transacciones.setValor(valor);
            return transaccionRepository.save(transacciones).toString();
        }
    }
}
