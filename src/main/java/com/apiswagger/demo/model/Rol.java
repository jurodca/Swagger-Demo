package com.apiswagger.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Rol {
	@Id
	private Integer id;
	private String nombre;
}
