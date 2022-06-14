package com.crud.democrud.repositories;

import com.crud.democrud.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    public abstract ArrayList<UsuarioModel> findByPrioridad(Integer prioridad);

    /**
     * Se crea función abstracta en el repositorio para obtener los usuarios por correo
     *
     * @param email recibe el email del usuario a buscar
     * @return devuelve una lista de usuarios que tengan el correo pasado como parámetro
     */
    public abstract ArrayList<UsuarioModel> findByEmail(String email);

}