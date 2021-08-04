package registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	public static Connection getConexion(){
			
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/escuela","root","");
			return con;
		} catch(SQLException e) {
			System.out.println(e.toString());
			return null;
		}
	}
}
