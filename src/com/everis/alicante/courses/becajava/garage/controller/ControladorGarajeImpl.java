package com.everis.alicante.courses.becajava.garage.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.everis.alicante.courses.becajava.garage.GarageMain;
import com.everis.alicante.courses.becajava.garage.domain.Camion;
import com.everis.alicante.courses.becajava.garage.domain.Cliente;
import com.everis.alicante.courses.becajava.garage.domain.Coche;
import com.everis.alicante.courses.becajava.garage.domain.Garaje;
import com.everis.alicante.courses.becajava.garage.domain.Motocicleta;
import com.everis.alicante.courses.becajava.garage.domain.Plaza;
import com.everis.alicante.courses.becajava.garage.domain.Reserva;
import com.everis.alicante.courses.becajava.garage.domain.Vehiculo;
import com.everis.alicante.courses.becajava.garage.interfaces.Aparcable;
import com.everis.alicante.courses.becajava.garage.interfaces.ReservaDAO;
import com.everis.alicante.courses.becajava.garage.interfaces.imp.ReservaDAOFileImp;

public class ControladorGarajeImpl implements ControladorGaraje{

	@Override
	public void listarPlazasLibres() {
		
		List<Plaza> plazaslibres= new ArrayList<Plaza>();
		
		List<Plaza> plazas=GarageMain.getGaraje().getPlazas();
		
		for (Plaza plaza : plazas) {
			
			if(plaza.getLibre()){
				plazaslibres.add(plaza);			
			}		
		
		}
		
		//listar por pantalla
		for (Plaza plaza1 : plazaslibres) {			
				System.out.println(plaza1);			
			}
		
		}


	@Override
	public void listarPlazasOcupadas() {
		
		List<Plaza> plazasOcupadas= new ArrayList<Plaza>();
		
		List<Plaza> plazas=GarageMain.getGaraje().getPlazas();
		
		for (Plaza plaza : plazas) {
			
			if(!plaza.getLibre()){
				plazasOcupadas.add(plaza);	
			}		
		
		}
		
		//listar por pantalla
		for (Plaza plaza1 : plazasOcupadas) {			
				System.out.println(plaza1);			
			}
		
	}

	@Override
	public boolean reservarPlaza() throws IOException {
		
		//logica de crear cliente
		
		Cliente cliente= new Cliente();
		
		ReservaDAO dao= new ReservaDAOFileImp();
		
		//vamos a escribir por pantalla un menu para meter los datos del cliente
		
		System.out.println("Inserte el nombre completo del Cliente");
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);		
		cliente.setNombreCompleto(in.nextLine());
		
		System.out.println("Insert el nif del cliente");		
		in = new Scanner(System.in);
		cliente.setNif(in.nextLine());
		
		Vehiculo vehiculo = null;
		
		System.out.println("Tipo de vehiculo del propietario:");	
		System.out.println("1: Coche:");	
		System.out.println("2: Moto");	
		System.out.println("3: Camion");
		
		in = new Scanner(System.in);
		
		
		switch (in.nextInt()) {
			case 1:
				 vehiculo= new Coche();
				break;
			case 2:
				 vehiculo= new Motocicleta();
				break;
			case 3:
				vehiculo= new Camion();
				break;
	
			default:
				break;
		}
		
		System.out.println("Inserte la matricula del vehiculo:");
		in = new Scanner(System.in);
		vehiculo.setMatricula(in.nextLine());
				
		cliente.setVehiculo(vehiculo);
		
		boolean hayplaza=false;
		Garaje garaje=GarageMain.getGaraje();		
		List<Plaza> plazas=garaje.getPlazas();
		
		for (Plaza plaza : plazas) {
			
			if (plaza.getLibre()&&vehiculo instanceof Aparcable) {				
				plaza.setCliente(cliente);				
				hayplaza=true;
				
				Reserva reserva= new Reserva();
				reserva.setCliente(cliente);
				reserva.setPlaza(plaza);
				reserva.setFechaReserva(Calendar.getInstance().getTime());
				reserva.setCodigoReserva("AUN NO PODEMOS");
				
				dao.createReserva(reserva);				
				
				return hayplaza;
			}		
		}
				
		return hayplaza;
		
	}

	@Override
	public void listarClientes() {
		
		Map<String, Cliente> clientes = GarageMain.getGaraje().getClientes();
				
		Collection<Cliente> collection = clientes.values();
		
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = collection.iterator(); iterator.hasNext();) {
			Cliente cliente = (Cliente) iterator.next();
			
			System.out.println(cliente.getNombreCompleto());
			
		}
		
//		System.out.println(clientes.keySet());
		
//		System.out.println("--------------------------------");
		
//		System.out.println(clientes.values());
		
//		System.out.println("--------------------------------");
		
//		clientes.values().contains("PEPE");
//		
//		Cliente cliente = clientes.get("45826664L");
//		
//		System.out.println(cliente);
		
	}


	@Override
	public void listarReservas() throws IOException {
	
		
		ReservaDAO reservaDao= new ReservaDAOFileImp();
		
		Map<String, Reserva> reservas = reservaDao.readReservas();
		
		 Collection<Reserva> listaReservas = reservas.values();
		 
		 for (Reserva reserva : listaReservas) {
			 
			 System.out.println("numero de plaza reservada: " +reserva.getPlaza().getNumeroPlaza());
			 System.out.println("cliente: " +reserva.getCliente().getNombreCompleto());
			 System.out.println("vehiculo: " +reserva.getCliente().getVehiculo().getMatricula() +" - " + reserva.getCliente().getVehiculo().getTipoVehiculo());
			
		}
		
	}

}