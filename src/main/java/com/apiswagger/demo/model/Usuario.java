package com.apiswagger.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Rol rol;
	private String nombre;
	private String activo;

	public Usuario(String nombre, String activo, Rol rol) {
		this.nombre = nombre;
		this.activo = activo;
		this.rol  = rol;
	}

	public Usuario(String activo, Rol rol) {
		this.activo = activo;
		this.rol = rol;
	}

	public Usuario(){}
}
