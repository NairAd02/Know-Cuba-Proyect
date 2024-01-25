package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.EstablishedRouteDAO;

public class EstablishedRoute extends TransportModality  {
    private String descriptionRout;
    private double costGoing;
    private double costLap;


    public EstablishedRoute(int id, Contract contract, String typeOfModality, ArrayList<Vehicle> vehicles, String typeTransportModality,
                            String descriptionRout, double costGoing, double costLap) { // constructor a nivel de base de datos
        super(id, contract, typeOfModality, vehicles, typeTransportModality);
        this.descriptionRout = descriptionRout;
        this.costGoing = costGoing;
        this.costLap = costLap;
    }

    public EstablishedRoute(Contract contract, ArrayList<Vehicle> vehicles,
                            String descriptionRout, double costGoing, double costLap) { // constructor a nivel de logica
        super(contract, vehicles);
        this.descriptionRout = descriptionRout;
        this.costGoing = costGoing;
        this.costLap = costLap;
        this.typeTransportModality = "Established Route";
    }

    public EstablishedRoute(ArrayList<Vehicle> vehicles,
                            String descriptionRout, double costGoing, double costLap) { // constructor a nivel de logica (para el proceso de creación del objeto)
        super(vehicles);
        this.descriptionRout = descriptionRout;
        this.costGoing = costGoing;
        this.costLap = costLap;
        this.typeTransportModality = "Established Route";
    }

    public EstablishedRoute() { // constructor para crear una instancia temporal
        super();
        this.typeTransportModality = "Established Route";
    }

    public void actualizarCampos(Contract contract, String typeOfModality, ArrayList<Vehicle> vehicles,
                                    String typeTransportModality, String descriptionRout, double costGoing, double costLap) { // Metodo para actualizar los atributos de la clase
        super.actualizarCampos(contract, typeOfModality, vehicles, typeTransportModality);
        this.descriptionRout = descriptionRout;
        this.costGoing = costGoing;
        this.costLap = costLap;
    }

    public String getDescriptionRout() {
        return descriptionRout;
    }

    public void setDescriptionRout(String descriptionRout) {
        this.descriptionRout = descriptionRout;
    }

    public double getCostGoing() {
        return costGoing;
    }

    public void setCostGoing(double costGoing) {
        this.costGoing = costGoing;
    }

    public double getCostLap() {
        return costLap;
    }

    public void setCostLap(double costLap) {
        this.costLap = costLap;
    }

    @Override
    public void insert() throws SQLException {
        this.id = EstablishedRouteDAO.getInstancie().insert(this);
        this.insertVehiclesIntoTransportModality(); // Se insertan todos los vehiculos asignados como parte de esta modalidad en la base de datos
    }

    @Override
    public void update() throws SQLException {
        EstablishedRouteDAO.getInstancie().update(this);
    }

    // Operaciones

    public double price() {
        return this.costGoing + this.costLap;
    }
    // Fin de Operaciones

}
