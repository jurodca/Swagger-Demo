package com.apiswagger.demo.business;

import com.apiswagger.demo.data.RolRepository;
import com.apiswagger.demo.projections.RolProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component public class RolBusinessService {

	@Autowired private RolRepository rolRepository;

	/**
	 * Get all the roles of the system
	 *
	 * @return A list with all the role objects
	 */
	public List<RolProjection> getAllRoles() {
		return rolRepository.findAllRoles();
	}
}
