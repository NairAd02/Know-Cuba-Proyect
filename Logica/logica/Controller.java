package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import dao.DependentDAO;
import dao.ManagerDAO;
import dao.PackageDesignerDAO;
import dao.RolDAO;
import utils.AusentFilter;
import utils.FilterUser;

import javax.jws.soap.SOAPBinding;


public class Controller {
    private static Controller controller; // Singlenton
    private User user; // atributo que representa al usuario logeado en el sistema
    private HashMap<Integer, ArrayList<User>> users; // atributo que representa los usuarios del sistema (Solo cargados con el Rol "Administrator")
    private ArrayList<Rol> roles; // atributo que representa los roles del sistema (Solo cargados con el Rol "Administrator")
    private TouristAgency touristAgency;
    private boolean confirmacion; // atributo especifico para obtener la confirmacion del usuario en las operaciones claves del sistema


    private Controller(User user) throws SQLException {
        this.user = user;
        if (this.user instanceof Administrator) {
            this.cargarUsuarios();
            this.cargarRoles();
        }
        this.touristAgency = new TouristAgency();

    }


    public static Controller getInstancie(User user) throws SQLException {
        if (controller == null)
            controller = new Controller(user);

        return controller;
    }

    public static Controller getInstancie() {

        return controller;
    }
    
    public static void destruirInstancie () { // Metodo para destruir la instancia
    	controller = null;
    }

    // Metodos de Carga de Informacion


    public void cargarUsuarios() throws SQLException {
        this.users = new HashMap<Integer, ArrayList<User>>();
        //this.users.put(User.administrator, new ArrayList<User>(AdministratorDAO.getInstancie().selectAll())); // se cargan los usuarios administradores de la base de datos
        this.users.put(User.dependent, new ArrayList<User>(DependentDAO.getInstancie().selectAll())); // se cargan los usuarios dependientes de la base de datos
        this.users.put(User.manager, new ArrayList<User>(ManagerDAO.getInstancie().selectAll())); // se cargan los usuarios managers de la base de datos
        this.users.put(User.packageDesigner, new ArrayList<User>(PackageDesignerDAO.getInstancie().selectAll())); // se cargan los usuarios diseñadores de paquete de la base de datos
    }

    public static HashMap<Integer, ArrayList<User>> hashMapUsersOf() { // Metodo para definir el proceso de inicializacion de un hashMap para la gestion de usuarios
        HashMap<Integer, ArrayList<User>> users = new HashMap<Integer, ArrayList<User>>();
        users.put(User.dependent, new ArrayList<User>()); // se crea la seccion de usuarios dependientes
        users.put(User.manager, new ArrayList<User>()); // se crea la seccion de usuarios gerentes
        users.put(User.packageDesigner, new ArrayList<User>()); // se crea la seccion de usuarios diseñadores de paquetes turisticos

        return users;
    }

    public void cargarRoles() throws SQLException {
        this.roles = new ArrayList<Rol>();
        this.roles = (ArrayList<Rol>) RolDAO.getInstancie().selectAllDiferentAdministrator(); // Se seleccionan todos los roles excepto el de administrador
    }

    // Fin Metodos de Carga de Informacion

    // Metodos de inserccion

    public void addUser(User user) throws SQLException {
        user.insert(); // se inserta al usuario en la base de datos


        if (user instanceof Dependent) {
            this.users.get(User.dependent).add(user); // se inserta al usuario en la logica del negocio
        } else if (user instanceof Manager) {
            this.users.get(User.manager).add(user); // se inserta al usuario en la logica del negocio
        } else if (user instanceof PackageDesigner) {
            this.users.get(User.packageDesigner).add(user); // se inserta al usuario en la logica del negocio
        }


    }

    public void addRol(Rol rol) throws SQLException {
        rol.insert(); // se inserta el rol en la base de datos
        this.roles.add(rol);

    }

    // Fin Metodos de inserccion

    // Metodos de actualizacion

    public void updateUser(User user, String userName) throws SQLException { // temporal
        // se actualiza la informacion del usuario a nivel de logica
        user.setUserName(userName);

        user.update(); // se actualiza la informacion del usuario en la base de datos
    }


    public void updateRol(Rol rol, String name) throws SQLException {
        rol.setName(name); // se actualiza la informacion del rol a nivel de logica
        rol.update(); // se actualiza al rol en la base de datos
    }

    // Fin Metodos de actualizacion

    // Metodos de eliminacion

    public void deleteUser(User user) throws SQLException {
        user.delete(); // se elimina de la base de datos al usuario


        if (user instanceof Dependent) {
            this.users.get(User.dependent).remove(user); // se elimina de la logica del negocio al usuario
        } else if (user instanceof Manager) {
            this.users.get(User.manager).remove(user); // se elimina de la logica del negocio al usuario
        } else if (user instanceof PackageDesigner) {
            this.users.get(User.packageDesigner).remove(user); // se elimina de la logica del negocio al usuario
        }
    }

    public void deleteRol(Rol rol) throws SQLException {
        rol.delete(); // se elimina de la base de datos al rol
        this.roles.remove(rol); // se elimina de la logica del negocio al rol
    }

    // Fin Metodos de eliminacion

    // Metodos de obtencion de datos

    // Metodos para la obtencion de Usuarios

    public HashMap<Integer, ArrayList<User>> getUsers() { // niguno de los tres filtros adquiere valor
        return this.users;
    }

    // Metodo para la obtencion de paquetes turisticos con filtros aplicados
    public HashMap<Integer, ArrayList<User>> getUsers(String name, int typeOfUser, int connected, LocalDate startDateConnectionMin, LocalDate startDateConnectionMax,
                                                      LocalDate lastDateConnectionMin, LocalDate lastDateConnectionMax) {
        HashMap<Integer, ArrayList<User>> users = hashMapUsersOf();

        /*System.out.println("name: " + name);
        System.out.println("typeOfUser: " + typeOfUser);
        System.out.println("connected: " + connected);
        System.out.println("startDateConnectionMin: " + startDateConnectionMin);
        System.out.println("startDateConnectionMax: " + startDateConnectionMax);
        System.out.println("lastDateConnectionMin: " + lastDateConnectionMin);
        System.out.println("lastDateConnectionMax: " + lastDateConnectionMax);*/

        // Se aplican los filtros

        // Filtro tipo de usuario
        if (typeOfUser != FilterUser.allTypes) // si fue seleccionado al menos un tipo
            users.put(typeOfUser, FilterUser.filterTypeUser(this.users, typeOfUser)); // se aplica filtro para el tipo de usuario
        else
            users = this.users;


        // Filtro Name
        if (name != null)
            users = FilterUser.filterName(users, name); // se aplica filtro para el nombre de usuario

        // Filtro estado de la conexion
        if (connected != AusentFilter.stateLess)
            users = FilterUser.filterStateConnection(users, (connected == User.stateConnected)); // se aplica filtro para el estado de la conexion


        // Filtro StartDateConnection
        if (startDateConnectionMin != null && startDateConnectionMax != null)
            users = FilterUser.filterStartDateConnection(users, startDateConnectionMin, startDateConnectionMax); // se aplica filtro para la fecha de conexion inicial en el rango de fechas
        else if (startDateConnectionMin != null)
            users = FilterUser.filterStartDateConnection(users, startDateConnectionMin, LocalDate.MAX); // se aplica filtro para la fecha de conexion inicial en la la fecha minima
        else if (startDateConnectionMax != null)
            users = FilterUser.filterStartDateConnection(users, LocalDate.MIN, startDateConnectionMax); // se aplica filtro para la fecha de conexion inicial en la fecha maxima

        // Filtro LastDateConnection
        if (lastDateConnectionMin != null && lastDateConnectionMax != null)
            users = FilterUser.filterLastDateConnection(users, lastDateConnectionMin, lastDateConnectionMax); // se aplica filtro para la fecha de conexion inicial en el rango de fechas
        else if (lastDateConnectionMin != null)
            users = FilterUser.filterLastDateConnection(users, lastDateConnectionMin, LocalDate.MAX); // se aplica filtro para la fecha de conexion inicial en la la fecha minima
        else if (lastDateConnectionMax != null)
            users = FilterUser.filterLastDateConnection(users, LocalDate.MIN, lastDateConnectionMax); // se aplica filtro para la fecha de conexion inicial en la fecha maxima


            return users;
    }

    // Fin de Metodos para la obtencion de Usuarios

    // Fin Metodos de obtencion de datos


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUsers(HashMap<Integer, ArrayList<User>> users) {
        this.users = users;
    }

    public ArrayList<Rol> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Rol> roles) {
        this.roles = roles;
    }

    public TouristAgency getTouristAgency() {
        return touristAgency;
    }

    public void setTouristAgency(TouristAgency touristAgency) {
        this.touristAgency = touristAgency;
    }


    public static Controller getController() {
        return controller;
    }


    public static void setController(Controller controller) {
        Controller.controller = controller;
    }


    public boolean isConfirmacion() {
        return confirmacion;
    }


    public void setConfirmacion(boolean confirmacion) {
        this.confirmacion = confirmacion;
    }

}
