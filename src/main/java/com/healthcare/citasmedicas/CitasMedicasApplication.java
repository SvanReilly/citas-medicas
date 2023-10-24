package com.healthcare.citasmedicas;

import java.sql.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CitasMedicasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasMedicasApplication.class, args);
	
	
	
	}
	// Alejandro Ortega Maldonado
	public class Citas {
		 int id;
		 Date fecha;
		 String nombrePaciente;
		 String estado;
		

		public Citas(int id, Date fecha, String nombrePaciente, String estado) {
			
			this.id = id;
			this.fecha = fecha;
			this.nombrePaciente = nombrePaciente;
			this.estado = estado;
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public Date getFecha() {
			return fecha;
		}


		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}


		public String getNombrePaciente() {
			return nombrePaciente;
		}


		public void setNombrePaciente(String nombrePaciente) {
			this.nombrePaciente = nombrePaciente;
		}


		public String getEstado() {
			return estado;
		}


		public void setEstado(String estado) {
			this.estado = estado;
		}
		
		
	}
	
}
