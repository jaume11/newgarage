package com.everis.alicante.courses.becajava.garage.domain;

import com.everis.alicante.courses.becajava.garage.GarageMain;

public class Plaza extends GarageMain {
	
	private Cliente cliente;
	
	private double precio;
	
	private int numeroPlaza;
	
	private Boolean libre;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public int getNumeroPlaza() {
		return numeroPlaza;
	}

	public void setNumeroPlaza(int numeroPlaza) {
		this.numeroPlaza = numeroPlaza;
	}
	
	@Override
	public String toString() {
		return  "Numero de Plaza= " + numeroPlaza +  ", cliente= "+ cliente + ", precio = " + precio + ", libre= " + libre ;
	}

	public Boolean getLibre() {
		if (cliente == null) {
			return true;
		} else {
			return false;

		}
	}

}
