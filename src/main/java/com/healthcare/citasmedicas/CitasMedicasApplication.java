package com.healthcare.citasmedicas;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CitasMedicasApplication {

	public static void main(String[] args) {
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";
		final String ANSI_UNDERLINE = "\u001B[4m";
		final String ANSI_RESET = "\u001B[0m";
		SpringApplication.run(CitasMedicasApplication.class, args);
		Citas patientCheck = new Citas();
		Scanner sc = new Scanner(System.in);
		String cleaningCache="";
		String menu;
		int IDIns = 0;
		String fechaString;
		java.sql.Date fecha = null;
		String nombre="";
		String estado="";
		boolean salir = true;

		while (salir) {
			System.out.println(ANSI_GREEN + ANSI_UNDERLINE + "Menu de Health Care:" + ANSI_RESET + "\n"
					+ "1.- LISTAR CITAS DE PACIENTES" + "\n" + "2.- AGREGAR CITA NUEVA" + "\n"
					+ "3.- ELIMINAR REGISTRO DE CITAS" + "\n" + "4.- ACTUALIZAR REGISTRO DE CITAS" + "\n"
					+ "5.- SALIR DEL PROGRAMA" + "\n" + "Escriba un numero del 1 al 5:");
			menu = sc.nextLine();

			switch (menu) {
			case "1":
				patientCheck.listarRegistro();
				break;

			case "2":

				System.out.print("Ingrese una fecha (en formato yyyy-MM-dd): ");
				fechaString = sc.nextLine();

				System.out.println("Ahora escriba el nombre completo del paciente: ");
				nombre = sc.nextLine();

				System.out.println("Por ultimo, establezca un estado para su cita (Pendiente, Confirmada, Cancelada): ");
				estado = sc.nextLine();


				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date parsedDate = dateFormat.parse(fechaString);
					fecha = new java.sql.Date(parsedDate.getTime());

				} catch (ParseException e) {
					System.out.println("Error al convertir fecha");
				}

				patientCheck.insertarRegistro(fecha, nombre, estado);

				break;

			case "3":

				try {
					System.out.print("Para eliminar un registro inserte el ID de la cita: ");
					IDIns = sc.nextInt();
					cleaningCache=sc.nextLine();
				} catch (NumberFormatException e) {
					System.out.println("Por favor, inserte un valor numerico entero para el ID.");
				}

				patientCheck.eliminarRegistro(IDIns);
				IDIns = 0;
				break;

			case "4":
				try {
					System.out.print("Para actualizar un registro inserte el ID de la cita a modificar: ");
					IDIns = sc.nextInt();
					cleaningCache=sc.nextLine();
					System.out.print("Ingrese una fecha (en formato yyyy-MM-dd): ");
					fechaString = sc.nextLine();
					System.out.println("Ahora escriba el nombre completo del paciente: ");
					nombre = sc.nextLine();
					System.out.println("Por ultimo, establezca un estado para su cita (Pendiente, Confirmada, Cancelada):");
					estado = sc.nextLine();

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date parsedDate = dateFormat.parse(fechaString);
					fecha = new java.sql.Date(parsedDate.getTime());

				} catch (NumberFormatException e) {
					System.out.println("Por favor, inserte un valor numerico entero para el ID.");
				} catch (ParseException e) {
					System.out.println("Error al convertir fecha");
				}
				
				patientCheck.modificarRegistro(IDIns, fecha, nombre, estado);
				IDIns = 0;
				break;

			case "5":
				salir = false;
				System.out.println(ANSI_RED + "Saliendo del sistema, espere por favor." + ANSI_RESET);
				sc.close();
				break;

			default:
				break;
			}
		}
// Obtener datos de los usuarios:

	}
}
