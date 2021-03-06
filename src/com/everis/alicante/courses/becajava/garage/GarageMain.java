package com.everis.alicante.courses.becajava.garage;
import java.io.IOException;
import java.util.Scanner;

import com.everis.alicante.courses.becajava.garage.controller.ControladorGaraje;
import com.everis.alicante.courses.becajava.garage.controller.ControladorGarajeImpl;
import com.everis.alicante.courses.becajava.garage.domain.Garaje;
import com.everis.alicante.courses.becajava.garage.interfaces.ClienteDAO;
import com.everis.alicante.courses.becajava.garage.interfaces.PlazaDAO;
import com.everis.alicante.courses.becajava.garage.interfaces.ReservaDAO;
import com.everis.alicante.courses.becajava.garage.interfaces.VehiculoDAO;
import com.everis.alicante.courses.becajava.garage.interfaces.imp.ClienteDAOFileImpl;
import com.everis.alicante.courses.becajava.garage.interfaces.imp.PlazaDAOFileImp;
import com.everis.alicante.courses.becajava.garage.interfaces.imp.ReservaDAOFileImp;
import com.everis.alicante.courses.becajava.garage.interfaces.imp.VehiculoDAOImpl;

public class GarageMain {

		static Garaje garaje;
		static ControladorGaraje controlador;
		
		public static void main(String[] args) throws IOException {	
		
			inicializarComponentes();
		
		//iniciar aplicacion o listar menu
		
			iniciarAplicacion();

	}
	
	private static void iniciarAplicacion() throws IOException {
		
		System.out.println("*******************************************************");
		System.out.println("Bienvenido a nuestro garaje, seleccione una opcion: ");
		System.out.println("*******************************************************");
		System.out.println("1:Listar Plazas Garaje Libre ");
		System.out.println("2:Listar Plazas Garaje Ocupadas ");
		System.out.println("3:Reservar Plazas");
		System.out.println("4:Listar Clientes");
		System.out.println("5:Listar Reservas");
		System.out.println("*******************************************************");
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		Integer opcion = in.nextInt();
	    boolean resultado = true;   
	    
		System.out.println("*******************************************************");
		System.out.println("Ha elegido la opcion :" + opcion);
		System.out.println("-------------------------------------------------------");
				
		switch (opcion) {
			case 1:	
				controlador.listarPlazasLibres();			
				break;
			case 2:			
				controlador.listarPlazasOcupadas();			
				break;
			case 3:			
				resultado=controlador.reservarPlaza();			
				break;
			case 4:			
				controlador.listarClientes();		
				break;
			case 5:			
				controlador.listarReservas();		
				break;
			default:
				System.out.println("Error");
				break;
			}
		
		if(opcion==3&&resultado){
			System.out.println("*******************************************************");
			System.out.println("Se ha reservado su plaza");
			System.out.println("-------------------------------------------------------");

		}else if (opcion==3){
			System.out.println("*******************************************************");
			System.out.println("No hay plazas disponibles");
			System.out.println("-------------------------------------------------------");

		}
			
		iniciarAplicacion();
		
	}

	public static void inicializarComponentes() throws IOException{
		
		garaje= new Garaje();		
		  
		PlazaDAO plazaDao= new PlazaDAOFileImp();	
		@SuppressWarnings("unused")
		ReservaDAO reservaDao= new ReservaDAOFileImp();
		ClienteDAO clienteDao= new ClienteDAOFileImpl();
		VehiculoDAO  vehiculoDao= new VehiculoDAOImpl();
		
		garaje.setPlazas(plazaDao.readPlazas());
		
		garaje.setClientes(clienteDao.readClientes());
		
		garaje.setVehiculos(vehiculoDao.readVehiculos());
		
		garaje.setReservas(reservaDao.readReservas());
		
		controlador= new ControladorGarajeImpl();
	
		
	}

	public static Garaje getGaraje() {
		return garaje;
	}
	
	
}