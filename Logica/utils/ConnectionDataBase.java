package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Deque;
import java.util.LinkedList;

public class ConnectionDataBase {
    private static Connection connectionDataBase;

    private static final Deque<Savepoint> savepoints = new LinkedList<Savepoint>();


    public static Connection getConnectionDataBase() {
        return connectionDataBase;
    }

    public static void establecerConeccion() throws SQLException {
        connectionDataBase = DriverManager.getConnection("jdbc:postgresql://localhost:5432/know_cuba_db_3", "postgres", "12134588ad");
        connectionDataBase.setAutoCommit(false); // se deshabilitan los auto-commit
    }

    public static void commit() throws SQLException { // metodo para realizar commit (confirmar los cambios)
        connectionDataBase.commit();
    }

    public static void roolback() throws SQLException { // metodo para relizar rollback (inhabilitarcambios)
        connectionDataBase.rollback();
    }

    public static void roolback(Savepoint savepoint) throws SQLException { // metodo para relizar rollback (inhabilitarcambios)
        connectionDataBase.rollback(savepoint);
    }

    public static void cerrarConexion() throws SQLException {
        connectionDataBase.close();
    }

    public static void pushSavePoint(String nameSavePoint) throws SQLException { // metodo para crear y empilar un savePoint
        savepoints.push(connectionDataBase.setSavepoint(nameSavePoint));
    }

    public static void popSavePoint() throws SQLException { // metodo para deapilar y eliminar un savePoint
        connectionDataBase.releaseSavepoint(savepoints.pop());
    }

    public static Savepoint peekSavePoint() { // Metodo para obtener el elemento del tope de la pila
        return savepoints.peek();
    }


}
