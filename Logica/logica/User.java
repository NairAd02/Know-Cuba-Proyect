package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.UserDAO;
import utils.BusquedaResultado;


public abstract class User implements DUILogic, LikeName {
    // Atributos estaticos para el hash
    public static final int all = 0;
    // Coinciden con los id de los roles en la base de datos
    public static final int manager = 2;
    public static final int dependent = 3;
    public static final int packageDesigner = 4;

    public static final int stateConnected = 5;
    public static final int stateDisconnect = 6;

    public static ArrayList<Integer> getKeys() {
        ArrayList<Integer> keys = new ArrayList<Integer>(4);
        keys.add(dependent);
        keys.add(manager);
        keys.add(packageDesigner);

        return keys;
    }

    // fin
    protected int id;
    protected String userName;
    protected String password;
    protected Rol rol;
    protected LocalDate startDateConnection;
    protected LocalDate lastDateConnection;
    protected boolean connected;
    protected ArrayList<Request> requests; // atributo que define las solicitudes del usuario, Estructura de datos temporal
    protected boolean statePassword; // false es que no se encuentra habilitada true es que se encuentra habilitada
    protected BusquedaResultado busquedaResultado; // atributo para las tareas de busqueda


    public User(int id, String userName, String password, Rol rol, LocalDate startDateConnection,
                LocalDate lastDateConnection, boolean connected, ArrayList<Request> requests, boolean statePassword) { // constructor nivel de base de datos
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.rol = rol;
        this.startDateConnection = startDateConnection;
        this.lastDateConnection = lastDateConnection;
        this.connected = connected;
        this.requests = requests;
        this.statePassword = statePassword;
    }

    public User(String userName, String password, Rol rol) { // constructor nivel de logica
        super();
        this.id = -1;
        this.userName = userName;
        this.password = password;
        this.rol = rol;
        this.startDateConnection = null;
        this.lastDateConnection = null;
        this.connected = false;
        this.requests = new ArrayList<Request>();
        this.statePassword = false;
    }

    public void establecerConexion() throws SQLException { // metodo para indicar que el usuario se logueo al sistema
        if (this.startDateConnection == null)
            this.startDateConnection = LocalDate.now();
        this.lastDateConnection = LocalDate.now();
        this.connected = true;
        this.update(); // se actualiza la informacion del usuario
    }

    public void cerrarConexion() throws SQLException { // metodo para indicar que el usuario cerró sesión
        this.connected = false;
        this.update(); // se actualiza la informacion del usuario
    }

    public void actualizarEstadoPassword(String newPassword) throws SQLException {
        this.password = UserDAO.getInstancie().changeStatePassword(this.id, newPassword, true);
        this.statePassword = true;
    }


    public void delete() throws SQLException { // Metodo para eliminar al usuario en la base de datos
        UserDAO.getInstancie().delete(this.id);
    }

    // Metodos para la gestion de las Solicitudes
    public void addRequest(Request request) throws SQLException {
        request.insert(); // se inserta en la base de datos la solicitud
        this.addRequestLogic(request); // se inserta en la logica del negocio la solicitud
    }

    public void addRequestLogic(Request request) {
        this.requests.add(request);
    }

    public void deleteRequest(Request request) throws SQLException {
        request.delete(); // se elimina la solicitud de la base de datos
        this.deleteRequestLogic(request); // se elimina la solicitud de la logica del negocio
    }

    public void deleteRequestLogic(Request request) {
        this.requests.remove(request);
    }

    // Fin de Metodos para la gestion de las Solicitudes

    // Metodo para comprobar semejanza de nombre
    public boolean isSameName(String name) {
        boolean veredicto = false;
        String nameComparar = "";
        if (!name.equalsIgnoreCase("")) {
            for (int i = 0, j = 0, l = 0; i < this.userName.length() && !veredicto; i++) {

                nameComparar += this.userName.charAt(i);

                j++;
                if (j == name.length()) {
                    if (name.equalsIgnoreCase(nameComparar)) {
                        veredicto = true;
                        this.busquedaResultado = new BusquedaResultado(nameComparar, i - (j - 1), i);
                    } else {
                        nameComparar = "";
                        this.busquedaResultado = null;
                    }
                    j = 0;
                    i = l++;
                }
            }
        } else {
            veredicto = true;
            this.busquedaResultado = null;
        }

        return veredicto;
    }

    // Fin Metodo para comprobar semejanza de nombre


    public String getNameRol() {
        return this.rol.getName();
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Rol getRol() {
        return rol;
    }


    public void setRol(Rol rol) {
        this.rol = rol;
    }


    public LocalDate getStartDateConnection() {
        return startDateConnection;
    }


    public void setStartDateConnection(LocalDate startDateConnection) {
        this.startDateConnection = startDateConnection;
    }


    public LocalDate getLastDateConnection() {
        return lastDateConnection;
    }


    public void setLastDateConnection(LocalDate lastDateConnection) {
        this.lastDateConnection = lastDateConnection;
    }


    public boolean isConnected() {
        return connected;
    }


    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public int getRolId() {
        return this.rol.getId();
    }

    public boolean isStatePassword() {
        return statePassword;
    }

    public void setStatePassword(boolean statePassword) {
        this.statePassword = statePassword;
    }

}
