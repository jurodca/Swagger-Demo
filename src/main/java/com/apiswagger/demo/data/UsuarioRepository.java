package com.apiswagger.demo.data;

import com.apiswagger.demo.model.Usuario;
import com.apiswagger.demo.projections.UsuarioProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	List<Usuario> findByNombreContaining(String nombre);

	@Query("SELECT usr.id as id, usr.nombre as nombre, usr.activo as activo, usr.rol.nombre as rolNombre FROM Usuario usr")
	List<UsuarioProjection> findAllUsers();

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update Usuario usr set usr.activo =:activo, usr.rol.id =:idRol where usr.id =:usrId")
	void updateUsuario(@Param("usrId") Integer usrId, @Param("idRol") Integer idRol, @Param("activo") String activo);
}
