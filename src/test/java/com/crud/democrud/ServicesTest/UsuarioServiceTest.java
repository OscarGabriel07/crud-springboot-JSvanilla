package com.crud.democrud.ServicesTest;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.repositories.UsuarioRepository;
import com.crud.democrud.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static  org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioServiceTest {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void testGuardarUsuario(){
        UsuarioModel usuarioModel=new UsuarioModel("aquaman","aqua@gmail.com",99);
        UsuarioModel usuarioModelRegistrado = usuarioRepository.save(usuarioModel);
        assertNotNull(usuarioModelRegistrado);
    }

    @Test
    public void testBuscarUsuarioPorId(){
        Long idBuscado= 4L;
        Optional<UsuarioModel> usuarioModelBuscado = usuarioRepository.findById(idBuscado);
        assertThat(usuarioModelBuscado.get().getId()).isEqualTo(idBuscado);
    }

    @Test
    public void testListarUsuarios(){
        List<UsuarioModel> usuarioModelList=(List<UsuarioModel>) usuarioRepository.findAll();
        assertThat(usuarioModelList).size().isGreaterThan(0);
    }

    @Test
    public void testBuscarUsuarioPorEmail(){
        String emailBuscado = "oscar@mail.com";
        ArrayList<UsuarioModel> usuarioModelBuscado=usuarioRepository.findByEmail(emailBuscado);
        assertThat(usuarioModelBuscado.get(0).getEmail()).isEqualTo(emailBuscado);
    }

    @Test
    public void testEliminarUsuarioPorCorreo(){
        UsuarioModel usuarioEliminar = new UsuarioModel();
        usuarioEliminar.setId(4L);
        usuarioEliminar.setEmail("oscar@mail.com");
        usuarioEliminar.setNombre("Oscar");
        usuarioEliminar.setPrioridad(3);

        usuarioRepository.delete(usuarioEliminar);

        assertNotNull(usuarioEliminar);
    }
}
