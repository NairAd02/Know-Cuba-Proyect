package main;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

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
