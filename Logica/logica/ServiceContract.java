package logica;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.ServiceContractDAO;
import dao.ServiceModalityDAO;
import utils.AusentFilter;
import utils.FiltersServiceModality;


public class ServiceContract extends Contract {


    public ServiceContract(int id, LocalDate startDate, LocalDate terminationDate, LocalDate reconciliationDate,
                           String description, Provider provider, boolean state, String typeOfContract,
                           double surcharge) throws SQLException { // Constructor a nivel de base de datos
        super(id, startDate, terminationDate, reconciliationDate, description, provider, state, typeOfContract,
                surcharge);

    }

    public ServiceContract(LocalDate startDate, LocalDate terminationDate,
                           String description, Provider provider, ArrayList<Modality> modalitys,
                           double surcharge) { // Constructor a nivel de logica
        super(startDate, terminationDate, description, provider, modalitys,
                surcharge);
        this.typeOfContract = "Service Contract";

    }

    public ServiceContract() { // Constructor temporal
        super();
    }

    // Operaciones logicas

    public ArrayList<Activity> getActivitiesServiceProvider() { // metodos para obtener todas las actiidades del provedor de servicios con el que se estableció contrato
        return ((ServiceProvider) this.provider).getActivities();
    }

    // Fin de Operaciones logicas


    // Metodos para el control de las modalidades


    public void updateServiceModality(ServiceModality serviceModality, Activity activity, LocalDate releaseDate, double price) throws SQLException {
        // se actualiza la informacion de la modalidad a nivel de logica
        this.updateServiceModalityLogic(serviceModality, activity, releaseDate, price);
        serviceModality.update(); // se actualiza la informacion de la modalidad en la base de datos
    }

    public void updateServiceModalityLogic(ServiceModality serviceModality, Activity activity, LocalDate releaseDate, double price) throws SQLException {
        // se actualiza la informacion de la modalidad a nivel de logica
        serviceModality.setActivity(activity);
        serviceModality.setReleasedDate(releaseDate);
        serviceModality.setPrice(price);
    }
    // Fin Metodos para el control de las modalidades

    @Override
    public void insert() throws SQLException {
        this.id = ServiceContractDAO.getInstancie().insert(this);
        this.insertModalitysIntoDataBase();
    }

    @Override
    public void update() throws SQLException {
        ServiceContractDAO.getInstancie().update(this);
    }

    // Metodos de carga

    @Override
    public void cargarModalities() throws SQLException {
        this.modalitys = new ArrayList<Modality>(ServiceModalityDAO.getInstancie().selectIntoServiceContract(this));
    }

    @Override
    public void actualizarDatos() throws SQLException {
        this.cargarModalities();
    }

    // Fin de Metodos de carga

    // Metodos para la Obtenecion de las Modalidades

    // Metodo de obtencion con Filtros aplicados
    public ArrayList<Modality> getModalitys(Activity activity, LocalDate releasedDateMin, LocalDate releasedDateMax, double precioMin, double precioMax) {
        ArrayList<Modality> modalitys = this.modalitys;
        // Filtro Actividad
        if (activity != null)
            modalitys = FiltersServiceModality.filterActivity(modalitys, activity); // se filtra por actividad

        // Filtro Fecha de Realizacion
        if (releasedDateMin != null && releasedDateMax != null)
            modalitys = FiltersServiceModality.filterReleasedDate(modalitys, releasedDateMin, releasedDateMax); // se filtra por el rango de fechas
        else if (releasedDateMin != null)
            modalitys = FiltersServiceModality.filterReleasedDate(modalitys, releasedDateMin, LocalDate.MAX); // se filtra por fecha minima
        else if (releasedDateMax != null)
            modalitys = FiltersServiceModality.filterReleasedDate(modalitys, LocalDate.MIN, releasedDateMax); // se filtra por fecha maxima

        // Filtro Precio
        if (precioMin != AusentFilter.spinnerField && precioMax != AusentFilter.spinnerField)
            modalitys = FiltersServiceModality.filterPrice(modalitys, precioMin, precioMax); // se filtra por el rango de precios
        else if (precioMin != AusentFilter.spinnerField)
            modalitys = FiltersServiceModality.filterPrice(modalitys, precioMin, Double.MAX_VALUE);
        else if (precioMax != AusentFilter.spinnerField)
            modalitys = FiltersServiceModality.filterPrice(modalitys, Double.MIN_VALUE, precioMax);

        return modalitys;
    }

    // Fin Metodos para la Obtenecion de las Modalidades

}
