package com.alquiler.entidades;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "alquiler")
public class alquiler {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_alquiler;
	private int estado;
	private int numero_dias;
	private String fecha_entrega;
	private String fecha_recibido;
	private int galonesInicio;
	private String Color;
	private int telefono;
	private double total;
	private Date fechaDCompra;
	
	@JoinColumn(name = "fkCliente")
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario fkCliente;

	@JoinColumn(name = "fkVehiculo")
	@ManyToOne(fetch = FetchType.EAGER)
	private vehiculo fkVehiculo;

   


	public int getId_alquiler() {
		return id_alquiler;
	}



	public void setId_alquiler(int id_alquiler) {
		this.id_alquiler = id_alquiler;
	}



	public int getEstado() {
		return estado;
	}



	public void setEstado(int estado) {
		this.estado = estado;
	}



	public int getNumero_dias() {
		return numero_dias;
	}



	public void setNumero_dias(int numero_dias) {
		this.numero_dias = numero_dias;
	}



	public String getFecha_entrega() {
		return fecha_entrega;
	}



	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}



	public String getFecha_recibido() {
		return fecha_recibido;
	}



	public void setFecha_recibido(String fecha_recibido) {
		this.fecha_recibido = fecha_recibido;
	}



	public int getGalonesInicio() {
		return galonesInicio;
	}



	public void setGalonesInicio(int galonesInicio) {
		this.galonesInicio = galonesInicio;
	}



	public String getColor() {
		return Color;
	}



	public void setColor(String color) {
		Color = color;
	}



	public int getTelefono() {
		return telefono;
	}



	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public Date getFechaDCompra() {
		return fechaDCompra;
	}



	public void setFechaDCompra(Date fechaDCompra) {
		this.fechaDCompra = fechaDCompra;
	}



	public Usuario getFkCliente() {
		return fkCliente;
	}



	public void setFkCliente(Usuario fkCliente) {
		this.fkCliente = fkCliente;
	}



	public vehiculo getFkVehiculo() {
		return fkVehiculo;
	}



	public void setFkVehiculo(vehiculo fkVehiculo) {
		this.fkVehiculo = fkVehiculo;
	}




	public alquiler(int id_alquiler, int estado, int numero_dias, String fecha_entrega, String fecha_recibido,
			int galonesInicio, String color, int telefono, double total, Date fechaDCompra, Usuario fkCliente,
			vehiculo fkVehiculo) {
		this.id_alquiler = id_alquiler;
		this.estado = estado;
		this.numero_dias = numero_dias;
		this.fecha_entrega = fecha_entrega;
		this.fecha_recibido = fecha_recibido;
		this.galonesInicio = galonesInicio;
		Color = color;
		this.telefono = telefono;
		this.total = total;
		this.fechaDCompra = fechaDCompra;
		this.fkCliente = fkCliente;
		this.fkVehiculo = fkVehiculo;
	}
	
	public alquiler( int estado, int numero_dias, String fecha_entrega, String fecha_recibido,
			int galonesInicio, String color, int telefono, double total, Date fechaDCompra, Usuario fkCliente,
			vehiculo fkVehiculo) {
		this.estado = estado;
		this.numero_dias = numero_dias;
		this.fecha_entrega = fecha_entrega;
		this.fecha_recibido = fecha_recibido;
		this.galonesInicio = galonesInicio;
		Color = color;
		this.telefono = telefono;
		this.total = total;
		this.fechaDCompra = fechaDCompra;
		this.fkCliente = fkCliente;
		this.fkVehiculo = fkVehiculo;
	}



	public alquiler() {
	}

	
	
	
}
