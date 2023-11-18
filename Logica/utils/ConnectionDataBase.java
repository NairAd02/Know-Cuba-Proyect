package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {
private static Connection connectionDataBase;


public static Connection getConnectionDataBase() {
	return connectionDataBase;
}

public static void establecerConeccion () throws SQLException {
	connectionDataBase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/know_cuba_db", "postgres", "12134588ad");
}

public static void cerrarConeccion () throws SQLException {
	connectionDataBase.close();
}


}
