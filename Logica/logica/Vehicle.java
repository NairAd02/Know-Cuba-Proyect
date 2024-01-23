package logica;

import java.sql.SQLException;
import java.time.LocalDate;

import dao.VehicleDAO;
import utils.BusquedaResultado;

public class Vehicle implements DUILogic, LikeName {
    private int id;
    private String lock;
    private String brand;
    private int capacityWithoutLuggage;
    private int capacityWithLuggage;
    private int totalCapacity;
    private LocalDate dateOfProduction;
    private int transportationProviderId;
    protected BusquedaResultado busquedaResultado; // atributo para las tareas de busqueda


    public Vehicle(int id, String lock, String brand, int capacityWithoutLuggage, int capacityWithLuggage,
                   int totalCapacity, LocalDate dateOfProduction, int transportationProviderId) {
        super();
        this.id = id;
        this.lock = lock;
        this.brand = brand;
        this.capacityWithoutLuggage = capacityWithoutLuggage;
        this.capacityWithLuggage = capacityWithLuggage;
        this.totalCapacity = totalCapacity;
        this.dateOfProduction = dateOfProduction;
        this.transportationProviderId = transportationProviderId;
    }

    public Vehicle(String lock, String brand, int capacityWithoutLuggage, int capacityWithLuggage,
                   int totalCapacity, LocalDate dateOfProduction, int transportationProviderId) { // Constructor a nivel de logica
        this.lock = lock;
        this.brand = brand;
        this.capacityWithoutLuggage = capacityWithoutLuggage;
        this.capacityWithLuggage = capacityWithLuggage;
        this.totalCapacity = totalCapacity;
        this.dateOfProduction = dateOfProduction;
        this.transportationProviderId = transportationProviderId;
    }

    public Vehicle(String lock, String brand, int capacityWithoutLuggage, int capacityWithLuggage,
                   int totalCapacity, LocalDate dateOfProduction) { // Constructor Temporal
        this.lock = lock;
        this.brand = brand;
        this.capacityWithoutLuggage = capacityWithoutLuggage;
        this.capacityWithLuggage = capacityWithLuggage;
        this.totalCapacity = totalCapacity;
        this.dateOfProduction = dateOfProduction;
    }

    public void actualizarCampos(String lock, String brand, int capacityWithoutLuggage, int capacityWithLuggage,
                                 int totalCapacity, LocalDate dateOfProduction, int transportationProviderId) { // Metodo para actualizar los campos de la clase
        this.lock = lock;
        this.brand = brand;
        this.capacityWithoutLuggage = capacityWithoutLuggage;
        this.capacityWithLuggage = capacityWithLuggage;
        this.totalCapacity = totalCapacity;
        this.dateOfProduction = dateOfProduction;
        this.transportationProviderId = transportationProviderId;
    }

    public String getLock() {
        return lock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCapacityWithoutLuggage() {
        return capacityWithoutLuggage;
    }

    public void setCapacityWithoutLuggage(int capacityWithoutLuggage) {
        this.capacityWithoutLuggage = capacityWithoutLuggage;
    }

    public int getCapacityWithLuggage() {
        return capacityWithLuggage;
    }

    public void setCapacityWithLuggage(int capacityWithLuggage) {
        this.capacityWithLuggage = capacityWithLuggage;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public LocalDate getDateOfProduction() {
        return this.dateOfProduction;
    }

    public void setDateOfProduction(LocalDate dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }

    public int getTransportationProviderId() {
        return transportationProviderId;
    }

    public void setTransportationProviderId(int transportationProviderId) {
        this.transportationProviderId = transportationProviderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void insert() throws SQLException {
        this.id = VehicleDAO.getInstancie().insert(this);
    }

    public void insertIntoTransportModality(int idTransportModality) throws SQLException {
        VehicleDAO.getInstancie().insertIntoTransportModality(idTransportModality, this.id);
    }

    @Override
    public void update() throws SQLException {
        VehicleDAO.getInstancie().update(this);
    }

    @Override
    public void delete() throws SQLException {
        VehicleDAO.getInstancie().delete(this.id);
    }

    public void deleteFromTransportModality(int idTransportModality) throws SQLException {
        VehicleDAO.getInstancie().deleteIntoTransportModality(idTransportModality, this.id);
    }

    // Metodo para comprobar semejanza de nombre
    public boolean isSameName(String name) {
        boolean veredicto = false;
        String nameComparar = "";
        if (!name.equalsIgnoreCase("")) {
            for (int i = 0, j = 0, l = 0; i < this.lock.length() && !veredicto; i++) {

                nameComparar += this.lock.charAt(i);

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

    public String toString() { // Metodo para definir como se va a mostrar la informacion de la clase
        return this.lock;
    }
}
