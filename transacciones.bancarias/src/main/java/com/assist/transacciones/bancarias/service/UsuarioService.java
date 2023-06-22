package com.assist.transacciones.bancarias.service;

import com.assist.transacciones.bancarias.dto.UsuarioDto;
import com.assist.transacciones.bancarias.entity.Usuario;
import com.assist.transacciones.bancarias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Iterable<Usuario> lista() {
        return usuarioRepository.findAll();
    }

    public Usuario getOne(String idUsuario) {
        Usuario usuario=usuarioRepository.findById(idUsuario).get();
        return usuario;
    }

    public Usuario save(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setCedula(dto.getCedula());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setDireccion(dto.getDireccion());
        return usuarioRepository.save(usuario);
    }

    public Usuario Update(UsuarioDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()).get();
        usuario.setCedula(dto.getCedula());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setDireccion(dto.getDireccion());
        return usuarioRepository.save(usuario);
    }
    public void delete(String idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }
    public boolean existId(String idUsuario){
        return usuarioRepository.existsById(idUsuario);
    }
    public boolean existCedula(String cedula){
        return usuarioRepository.existsBycedula(cedula);
    }
}
