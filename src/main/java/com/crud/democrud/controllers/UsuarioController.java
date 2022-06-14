package com.crud.democrud.controllers;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return this.usuarioService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad) {
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }

    /**
     * Se crea endpoint para consultar el usuario por correo
     *
     * @param email recibe el correo del usuario a buscar
     * @return devuelve el o los usuarios que tengan el correo que se envía como parámetro
     */
    @GetMapping("/queries")
    public ArrayList<UsuarioModel> obtenerUsuarioPorEmail(@RequestParam("email") String email){
        return this.usuarioService.obtenerPorEmail(email);
    }

/*
    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok) {
            return "Se eliminó el usuario con id " + id;
        } else {
            return "No pudo eliminar el usuario con id" + id;
        }
    }
 */

    /**
     * Se crea endpoint para eliminar el usuario por correo
     *
     * @param email recibe el email del usuario a eliminar
     * @return devuelve el resultado de la operación
     */
    @DeleteMapping(path = "/{email}")
    public String eliminarPorEmail(@PathVariable("email") String email){
        UsuarioModel usuarioEliminar = this.usuarioService.obtenerPorEmail(email).get(0);
        boolean ok = this.usuarioService.eliminarUsuarioPorEmail(usuarioEliminar);
        if (ok) {
            return "Se eliminó el usuario con email " + email;
        } else {
            return "No se pudo eliminar el usuario con email " + email;
        }
    }

}