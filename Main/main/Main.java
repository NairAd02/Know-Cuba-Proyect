package main;


import java.sql.SQLException;


import utils.ConnectionDataBase;

public class Main {

	public static void main(String[] args) {
		// Se establece coneccion con la base de datos
		try {
			ConnectionDataBase.establecerConeccion();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

	}

}
