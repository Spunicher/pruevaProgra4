package com.alquiler.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vehiculo")
public class vehiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_vehiculo;
	private int estado;
	private String marca;
	private String modelo;
	private String matricula;
	private Double costoDiario;
	private String ano;
	private String imagen;
	
	@JsonIgnore
	@OneToMany(mappedBy = "fkVehiculo", cascade = CascadeType.ALL)
	public List<alquiler> fkVehiculo;

   
      


	public int getId_vehiculo() {
		return id_vehiculo;
	}





	public void setId_vehiculo(int id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}





	public int getEstado() {
		return estado;
	}





	public void setEstado(int estado) {
		this.estado = estado;
	}





	public String getMarca() {
		return marca;
	}





	public void setMarca(String marca) {
		this.marca = marca;
	}





	public String getModelo() {
		return modelo;
	}





	public void setModelo(String modelo) {
		this.modelo = modelo;
	}





	public String getMatricula() {
		return matricula;
	}





	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}





	public Double getCostoDiario() {
		return costoDiario;
	}





	public void setCostoDiario(Double costoDiario) {
		this.costoDiario = costoDiario;
	}





	public String getAno() {
		return ano;
	}





	public void setAno(String ano) {
		this.ano = ano;
	}





	public String getImagen() {
		return imagen;
	}





	public void setImagen(String imagen) {
		this.imagen = imagen;
	}





	public List<alquiler> getFkVehiculo() {
		return fkVehiculo;
	}





	public void setFkVehiculo(List<alquiler> fkVehiculo) {
		this.fkVehiculo = fkVehiculo;
	}


  


	public vehiculo(int id_vehiculo, int estado, String marca, String modelo, String matricula, Double costoDiario,
			String ano, String imagen) {
		this.id_vehiculo = id_vehiculo;
		this.estado = estado;
		this.marca = marca;
		this.modelo = modelo;
		this.matricula = matricula;
		this.costoDiario = costoDiario;
		this.ano = ano;
		this.imagen = imagen;
	}



	public vehiculo( int estado, String marca, String modelo, String matricula, Double costoDiario,
			String ano, String imagen) {
		this.estado = estado;
		this.marca = marca;
		this.modelo = modelo;
		this.matricula = matricula;
		this.costoDiario = costoDiario;
		this.ano = ano;
		this.imagen = imagen;
	}

	public vehiculo() {
	}

	

	
	
	
	
	
}
