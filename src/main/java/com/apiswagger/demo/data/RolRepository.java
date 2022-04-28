package com.apiswagger.demo.data;

import com.apiswagger.demo.model.Rol;
import com.apiswagger.demo.projections.RolProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol,Integer> {
	@Query("SELECT rol.id as id, rol.nombre as nombre FROM Rol rol")
	List<RolProjection> findAllRoles();
}
