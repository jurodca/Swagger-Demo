package com.apiswagger.demo.controller;

import com.apiswagger.demo.business.RolBusinessService;
import com.apiswagger.demo.business.UsuarioBusinessService;
import com.apiswagger.demo.model.Usuario;
import com.apiswagger.demo.projections.RolProjection;
import com.apiswagger.demo.projections.UsuarioProjection;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController public class UsersController {

	@Autowired RolBusinessService rolBusinessService;

	@Autowired UsuarioBusinessService usuarioBusinessService;

	@ApiOperation(value = "getAllRoles", notes = "Get all roles from the System")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. The roles load successfully", response = RolProjection.class),
			@ApiResponse(code = 400, message = "Bad Request. Not Found Data", response = String.class),
			@ApiResponse(code = 500, message = "Unexpected Error"),
			@ApiResponse(code = 401, message = "Unauthorized request "), })
	@GetMapping("/allRoles")
	public ResponseEntity<List<RolProjection>> getAllRoles() {
		List<RolProjection> roles = rolBusinessService.getAllRoles();
		if (roles.isEmpty()) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(roles);
		}
	}

	@ApiOperation(value = "getAllUsuarios", notes = "Get all users from the System")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. The users load successfully", response = UsuarioProjection.class),
			@ApiResponse(code = 400, message = "Bad Request. Not Found Data", response = String.class),
			@ApiResponse(code = 500, message = "Unexpected Error"),
			@ApiResponse(code = 401, message = "Unauthorized request "), })
	@GetMapping("/allUsuarios")
	public ResponseEntity<List<UsuarioProjection>> getAllUsuarios() {
		List<UsuarioProjection> usuarios = usuarioBusinessService.getAllUsuarios();
		if (usuarios.isEmpty()) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(usuarios);
		}
	}

	@ApiOperation(value = "createUser", notes = "Create a new user in the system")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. The users was created successfully", response = Usuario.class),
			@ApiResponse(code = 400, message = "Bad Request. There is an user with that name", response = String.class),
			@ApiResponse(code = 500, message = "Unexpected Error"),
			@ApiResponse(code = 401, message = "Unauthorized request "), })
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
		Usuario usr = usuarioBusinessService.saveUsuario(usuario);
		if (usr == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(usr);
		}
	}

	@ApiOperation(value = "searchByName", notes = "Search and user by his name")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. The operation loaded successfully ", response = Usuario.class),
			@ApiResponse(code = 400, message = "Bad Request. There are no data for that name", response = String.class),
			@ApiResponse(code = 500, message = "Unexpected Error"),
			@ApiResponse(code = 401, message = "Unauthorized request "), })
	@GetMapping("/search/{nombre}")
	public ResponseEntity<List<Usuario>> searchByName(@PathVariable String nombre) {
		List<Usuario> users = usuarioBusinessService.getUsuarioByNombre(nombre);
		if (users.isEmpty()) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(users);
		}
	}

	@ApiOperation(value = "deleteById", notes = "Delete an user by his id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK. The usr was deleted", response = String.class),
			@ApiResponse(code = 400, message = "Bad Request. There are no data for that id", response = String.class),
			@ApiResponse(code = 500, message = "Unexpected Error"),
			@ApiResponse(code = 401, message = "Unauthorized request "), })

	@DeleteMapping("/deleteUsr/{id}")
	public ResponseEntity<String> deleteUsr(@PathVariable Integer id) {
		Optional<Usuario> usr = usuarioBusinessService.getUsuarioById(id);
		if (usr.isPresent()) {
			usuarioBusinessService.deleteUsuario(usr.get());
			return ResponseEntity.status(HttpStatus.OK)
					.body(String.format("El usuario con id %d ha sido eliminado", id));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(String.format("El usuario con id %d no existe en la bd", id));
		}
	}

	@ApiOperation(value = "updateUser", notes = "Update a user in the system")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. The users was updated successfully", response = Usuario.class),
			@ApiResponse(code = 400, message = "Bad Request. There is not an user with that id", response = String.class),
			@ApiResponse(code = 500, message = "Unexpected Error"),
			@ApiResponse(code = 401, message = "Unauthorized request "), })
	@PutMapping("/updateUsr")
	public ResponseEntity<Usuario> updateUsuario (@RequestBody Usuario usr) {
		usuarioBusinessService.updateUsuario(usr);
		Optional<Usuario> usuario = usuarioBusinessService.getUsuarioById(usr.getId());

		if(usuario.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(usuario.get());
		}else{
			return ResponseEntity.badRequest().build();
		}
	}
}
