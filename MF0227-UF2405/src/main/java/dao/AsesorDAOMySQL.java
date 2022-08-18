package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Asesor;
import utilidades.ConexionBD;

public class AsesorDAOMySQL implements AsesorDAO {
	
	private ConexionBD conexion;
	private Statement consulta = null;
	private PreparedStatement consultaPreparada = null;
	private ResultSet resultado = null;
	
	public AsesorDAOMySQL() {
		conexion = new ConexionBD();
	}

	@Override
	public List<Asesor> getListaAsesores() {
		List<Asesor> listaAsesors = new ArrayList<Asesor>();
		Connection con = conexion.getConexion();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from asesores");
			while (resultado.next()) {
				String dni = resultado.getString("dni");
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				String contrato = resultado.getString("tipo_contrato");
				boolean coche = resultado.getBoolean("coche");
				boolean hogar = resultado.getBoolean("hogar");
				boolean decesos = resultado.getBoolean("decesos");
				int num = resultado.getInt("num_ventas");
				
				Asesor a = new Asesor(dni, nombre, apellidos, contrato, coche, hogar, decesos, num);
				listaAsesors.add(a);
			}
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre asesores: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}

		
		return listaAsesors;
	}

	@Override
	public Asesor getAsesor(String dni) {
		Connection con = conexion.getConexion();
		Asesor a = null;
		
		try {
			consultaPreparada = con.prepareStatement("select * from asesores"
					+ " where dni = ?");
			consultaPreparada.setString(1, dni);
			resultado=consultaPreparada.executeQuery();
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			if (resultado.next()) {
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				String contrato = resultado.getString("tipo_contrato");
				boolean coche = resultado.getBoolean("coche");
				boolean hogar = resultado.getBoolean("hogar");
				boolean decesos = resultado.getBoolean("decesos");
				int num = resultado.getInt("num_ventas");
				
				a = new Asesor(dni, nombre, apellidos, contrato, coche, hogar, decesos, num);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre un asesor: "
		         +e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return a;
	}



	@Override
	public int eliminarAsesor(String dni) {
		Connection con = conexion.getConexion();
		int resultado=0;
		
		try {
			consultaPreparada = con.prepareStatement("DELETE FROM asesores\r\n"
					+ "WHERE dni = ?");
			
			consultaPreparada.setString(1, dni);
			resultado=consultaPreparada.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar el borrado de un asesor: "+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
	}

	@Override
	public int actualizarAsesor(Asesor a) {
		Connection con = conexion.getConexion();
		int resultado=0;
		
		try {
			consultaPreparada = con.prepareStatement("UPDATE asesores\r\n"
					+ "SET nombre=?, "
					+ "apellidos=?, "
					+ "tipo_contrato=?, "
					+ "coche=?, hogar=?, "
					+ "decesos=?, "
					+ "num_ventas=?\r\n"
					+ "WHERE dni=?");
			

			consultaPreparada.setString(1, a.getNombre());
			consultaPreparada.setString(2, a.getApellidos());
			consultaPreparada.setString(3, a.getContrato());
			consultaPreparada.setBoolean(4, a.isCoche());
			consultaPreparada.setBoolean(5, a.isHogar());
			consultaPreparada.setBoolean(6, a.isDecesos());
			consultaPreparada.setInt(7, a.getNumVentas());
			consultaPreparada.setString(8, a.getDni());
			resultado=consultaPreparada.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar la actualizacion del asesor: "
					+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;

	}
	
	@Override
	public int insertarAsesor(Asesor a) {
		Connection con = conexion.getConexion();
		int resultado=0;
		
		try {
			consulta = con.prepareStatement(
					"insert into asesores values(?,?,?,?,?,?,?,?)");
			

			consultaPreparada.setString(1, a.getNombre());
			consultaPreparada.setString(2, a.getApellidos());
			consultaPreparada.setString(3, a.getContrato());
			consultaPreparada.setBoolean(4, a.isCoche());
			consultaPreparada.setBoolean(5, a.isHogar());
			consultaPreparada.setBoolean(6, a.isDecesos());
			consultaPreparada.setInt(7, a.getNumVentas());
			consultaPreparada.setString(8, a.getDni());
			resultado=consultaPreparada.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al insertar asesor: "
					+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;

	}
	
	

}
