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
	connectionDataBase.setAutoCommit(false); // se deshabilitan los auto-commit
}

public static void commit () throws SQLException { // metodo para realizar commit (confirmar los cambios)
	connectionDataBase.commit();
}

public static void roolback () throws SQLException { // metodo para relizar rollback (inhabilitarcambios)
	connectionDataBase.rollback();
}

public static void cerrarConeccion () throws SQLException {
	connectionDataBase.close();
}

}
