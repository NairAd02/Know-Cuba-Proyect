package dao;

import java.sql.SQLException;

import logica.User;

public interface UserDAOInterface {
    public void delete(int idUser) throws SQLException; // metodo para eliminar un usuario especifico de la base de datos

    public User select(String userName, String password) throws SQLException; // metodo para obtener el usuario con un nombre de usuario y una contraseña dada

    public User select(String userName) throws SQLException; // metodo para obtener el usuario con un nombre de usuario dado

    public String changeStatePassword(int idUser, String newPassword, boolean state) throws SQLException; // Metodo para actualizar el estado de la contraseña del usuario
}
