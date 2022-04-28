package com.apiswagger.demo.business;

import com.apiswagger.demo.data.UsuarioRepository;
import com.apiswagger.demo.model.Usuario;
import com.apiswagger.demo.projections.UsuarioProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component public class UsuarioBusinessService {

	@Autowired UsuarioRepository usuarioRepository;

	public List<UsuarioProjection> getAllUsuarios() {
		return usuarioRepository.findAllUsers();
	}

	public List<Usuario> getUsuarioByNombre(String nombre) {
		return usuarioRepository.findByNombreContaining(nombre);
	}

	public Usuario saveUsuario(Usuario usuario) {
		List<Usuario> usersBd = getUsuarioByNombre(usuario.getNombre());
		if (!usersBd.isEmpty()) {
			return null;
		} else {
			return usuarioRepository.save(usuario);
		}
	}

	public Optional<Usuario> getUsuarioById(Integer id){
		return Optional.of(usuarioRepository.findById(id).get());
	}

	public void updateUsuario(Usuario usuario) {
		Optional<Usuario> usr = getUsuarioById(usuario.getId());

		if(usr.isPresent()) {
			try {
				usuarioRepository.updateUsuario(usuario.getId(), usuario.getRol().getId(), usuario.getActivo());
			}catch(PersistenceException e){
				e.printStackTrace();
			}
		}
	}

	public void deleteUsuario(Usuario usuario) {
		try {
			usuarioRepository.delete(usuario);
		}catch(PersistenceException e){
			e.printStackTrace();
		}
	}
}

