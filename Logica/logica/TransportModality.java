package logica;


import dao.VehicleDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class TransportModality extends Modality {
    protected ArrayList<Vehicle> vehicles;
    protected String typeTransportModality;

    public TransportModality(int id, Contract contract, String typeOfModality, ArrayList<Vehicle> vehicles,
                             String typeTransportModality) { // constructor a nivel de base de datos
        super(id, contract, typeOfModality);
        this.vehicles = vehicles;
        this.typeTransportModality = typeTransportModality;
    }

    public TransportModality(Contract contract, ArrayList<Vehicle> vehicles) { // constructor a nivel de logica
        super(contract);
        this.vehicles = vehicles;
        this.typeOfModality = "Transport Modality";
    }

    public TransportModality(ArrayList<Vehicle> vehicles) { // constructor a nivel de logica (proceso de creacion del objeto)
        super();
        this.vehicles = vehicles;
        this.typeOfModality = "Transport Modality";
    }

    public TransportModality() { // constructor para crear una instancia temporal
        super();
        this.id = -1; // se marca al objeto como temporal
        this.vehicles = new ArrayList<Vehicle>();
        this.typeOfModality = "Transport Modality";
    }


    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public String getTypeTransportModality() {
        return typeTransportModality;
    }

    public void setTypeTransportModality(String typeTransportModality) {
        this.typeTransportModality = typeTransportModality;
    }

    // Metodos de carga de informacion

    public void actualizarDatos () throws SQLException {
        this.actualizarVehicles();
    }

    private void actualizarVehicles () throws SQLException {
        this.vehicles = (ArrayList<Vehicle>) VehicleDAO.getInstancie().selectIntoTransportModality(this.id); // se obtienen desde la base de datos todos los vehiculos de la modalidad de transporte
    }

    // Fin de Metodos de carga de informacion

    // Metodos para la gestion de los vehiculos

    public void addVehicle(Vehicle vehicle) throws SQLException {
        vehicle.insertIntoTransportModality(this.id);
        this.addVehicleLogic(vehicle); // Se elimina al vehiculo de la logica del negocio
    }

    public void addVehicleLogic(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle) throws SQLException {
        vehicle.deleteFromTransportModality(this.id);
        this.deleteVehicleLogic(vehicle); // se elimina el vehiculo de la logica del negocio
    }

    public void deleteVehicleLogic(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
    }

    public void insertVehiclesIntoTransportModality() throws SQLException {
        // Se insertan todos los vehiculos como parte de la modalidad de transporte en la base de datos
        for (Vehicle vehicle : this.vehicles) {
            vehicle.insertIntoTransportModality(this.id);
        }
    }

    // Fin Metodos para la gestion de los vehiculos


}
