package com.healthcare.citasmedicas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Citas {
	private static final String DRIVER = "org.h2.Driver";
    private static final String URL_CONEXION = "jdbc:h2:C:/data/datosDefinitivo";
    private static final String usuario = "Svan";
    private static final String password = "12345";
	int id;
	Date fecha;
	String nombrePaciente;
	String estado;
	Connection dbConnection = null;
    java.sql.Statement statement = null;
    Connection connection = null;
    PreparedStatement preparedStatement=null;
    
	public Citas() {

	}

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

	// M�todos espec�ficos de clase Citas (Listar, insertar, eliminar y actualizar datos en la tabla)
	public void listarRegistro() {
    try {
        Class.forName(DRIVER);
        dbConnection = DriverManager.getConnection(URL_CONEXION, usuario, password);
        String selectTableSQL = "SELECT * FROM RESERVAS";
        statement = dbConnection.createStatement();
        ResultSet resultado = statement.executeQuery(selectTableSQL);
        System.out.println("LISTA DE PACIENTES:" +
        		 "\n");
        while (resultado.next()) {
            setId(resultado.getInt("ID"));
            setFecha(resultado.getDate("FECHA"));
            setNombrePaciente(resultado.getString("NOMBRE_PACIENTE"));
            setEstado(resultado.getString("ESTADO"));
           
            System.out.println("ID: " + getId() + "\n"
    				+ "Fecha cita: " + getFecha() + "\n"
					+ "Nombre del paciente: " + getNombrePaciente() + "\n"
					+ "Estado de la cita: " + getEstado() + "\n");

        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
        System.out.println(e.getMessage());
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
}
	//INSERTAR REGISTRO DE CITA
	public String insertarRegistro(Date fechaIns, String nombre_pacienteIns, String estado_citaIns) {
	    Citas citaIngresada = new Citas();
		
	    citaIngresada.setFecha(fechaIns);
	    citaIngresada.setNombrePaciente(nombre_pacienteIns);
	    citaIngresada.setEstado(estado_citaIns);
		
	    try {
	        Class.forName(DRIVER);
	        connection = DriverManager.getConnection(URL_CONEXION, usuario, password);
	        String insertTableSQL = "INSERT INTO RESERVAS (FECHA, NOMBRE_PACIENTE, ESTADO) VALUES ( ?, ?, ?)";
	        
	        
	        preparedStatement = connection.prepareStatement(insertTableSQL);
	      
	        preparedStatement.setDate(1, citaIngresada.getFecha());
	        preparedStatement.setString(2, citaIngresada.getNombrePaciente());
	        preparedStatement.setNString(3, citaIngresada.getEstado());
	        
      
	        int filasAfectadas = preparedStatement.executeUpdate();

	        //Verificamos si la consulta SQL se ejecuta correctamente
	        if (filasAfectadas > 0){
	            System.out.println("Se ha ejecutado correctamente la secuencia SQL. Total de filas afectadas: " + filasAfectadas);
	        }
	        else{
	            System.out.println("No se ha modificado ninguna fila de la BBDD.");
	        }
	        
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } catch (ClassNotFoundException e) {
	        System.out.println(e.getMessage());
	    } finally {
	        try {
	            if (preparedStatement != null) {
	            	preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
		return "La siguiente cita ha sido registrada satisfactoriamente: \n" + citaIngresada.toString();
		
	}
//ELIMINAR REGISTRO DE CITA
	public String eliminarRegistro(int IDIns) {
	    Citas citaIngresada = new Citas();
		
	    citaIngresada.setId(IDIns);
	
		
	    try {
	        Class.forName(DRIVER);
	        connection = DriverManager.getConnection(URL_CONEXION, usuario, password);
	        String insertTableSQL = "DELETE FROM RESERVAS WHERE ID=?";
	        
	        
	        preparedStatement = connection.prepareStatement(insertTableSQL);
	      
	        preparedStatement.setInt(1, citaIngresada.getId());

	        
      
	        int filasAfectadas = preparedStatement.executeUpdate();

	        //Verificamos si la consulta SQL se ejecuta correctamente
	        if (filasAfectadas > 0){
	            System.out.println("Se ha ejecutado correctamente la secuencia SQL. Total de filas afectadas: " + filasAfectadas);
	        }
	        else{
	            System.out.println("No se ha modificado ninguna fila de la BBDD.");
	        }
	        
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } catch (ClassNotFoundException e) {
	        System.out.println(e.getMessage());
	    } finally {
	        try {
	            if (preparedStatement != null) {
	            	preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
		return "La cita con ID: " + citaIngresada.getId() + " ha sido eliminada del registro. \n";
		
	}
	
	
	//ACTUALIZAR REGISTROS DE CITA
		public String modificarRegistro(int IDIns, Date fechaIns , String nombre_pacienteIns, String estado_citaIns) {
		    Citas citaIngresada = new Citas();
			
		    citaIngresada.setId(IDIns);
		    citaIngresada.setFecha(fechaIns);
		    citaIngresada.setNombrePaciente(nombre_pacienteIns);
		    citaIngresada.setEstado(estado_citaIns);
			
		    try {
		        Class.forName(DRIVER);
		        connection = DriverManager.getConnection(URL_CONEXION, usuario, password);
		        String insertTableSQL = "UPDATE RESERVAS SET FECHA = ?, NOMBRE_PACIENTE = ?, ESTADO= ? WHERE ID = ?";
		        
		        
		        preparedStatement = connection.prepareStatement(insertTableSQL);
		      
		        
		        preparedStatement.setDate(1, citaIngresada.getFecha());
		        preparedStatement.setString(2, citaIngresada.getNombrePaciente());
		        preparedStatement.setNString(3, citaIngresada.getEstado());
		        preparedStatement.setInt(4, citaIngresada.getId());
	      
		        int filasAfectadas = preparedStatement.executeUpdate();

		        //Verificamos si la consulta SQL se ejecuta correctamente
		        if (filasAfectadas > 0){
		            System.out.println("Se ha ejecutado correctamente la secuencia SQL. Total de filas afectadas: " + filasAfectadas);
		        }
		        else{
		            System.out.println("No se ha modificado ninguna fila de la BBDD.");
		        }
		        
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		    } catch (ClassNotFoundException e) {
		        System.out.println(e.getMessage());
		    } finally {
		        try {
		            if (preparedStatement != null) {
		            	preparedStatement.close();
		            }
		            if (connection != null) {
		                connection.close();
		            }
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		    }
			return "La cita con ID: " + citaIngresada.getId() + " ha sido actualizada con nuevos datos. \n";
			
		}
	
	@Override
	public String toString() {
		return "ID : "+ id + "\n"
				+ "Fecha cita: " + fecha + "\n"
						+ "Nombre del paciente: " + nombrePaciente + "\n"
								+ "Estado de la cita: " + estado + "\n";
	}

}
