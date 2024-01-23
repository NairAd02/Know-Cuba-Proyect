package logica;

import java.sql.SQLException;
import java.time.LocalDate;

import dao.SeasonDAO;
import utils.BusquedaResultado;

public class Season implements DUILogic, LikeName {
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate terminationDate;
    private String description;
    private String typeOfSeason;
    private int accommodationContractId;
    private BusquedaResultado busquedaResultado; // atributo para las tareas de busqueda

    public Season(int id, String name, LocalDate startDate,
                  LocalDate terminationDate, String description,
                  String typeOfSeason, int accommodationContractId) { // Constructor a nivel de base datos
        super();
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.terminationDate = terminationDate;
        this.description = description;
        this.typeOfSeason = typeOfSeason;
        this.accommodationContractId = accommodationContractId;
    }

    public Season(String name, LocalDate startDate,
                  LocalDate terminationDate, String description,
                  String typeOfSeason, int accommodationContractId) { // Constructor a nivel de logica
        super();
        this.name = name;
        this.startDate = startDate;
        this.terminationDate = terminationDate;
        this.description = description;
        this.typeOfSeason = typeOfSeason;
        this.accommodationContractId = accommodationContractId;
    }

    public Season(String name, LocalDate startDate,
                  LocalDate terminationDate, String description,
                  String typeOfSeason) { // Constructor a nivel de logica (para las tareas de inserccion temporales)
        super();
        this.name = name;
        this.startDate = startDate;
        this.terminationDate = terminationDate;
        this.description = description;
        this.typeOfSeason = typeOfSeason;
    }

    public void actualizarCampos(String name, LocalDate startDate,
                                 LocalDate terminationDate, String description,
                                 String typeOfSeason, int accommodationContractId) { // Metodo para actualizar los campos de la clase
        this.name = name;
        this.startDate = startDate;
        this.terminationDate = terminationDate;
        this.description = description;
        this.typeOfSeason = typeOfSeason;
        this.accommodationContractId = accommodationContractId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeOfSeason() {
        return typeOfSeason;
    }

    public void setTypeOfSeason(String typeOfSeason) {
        this.typeOfSeason = typeOfSeason;
    }

    public int getAccommodationContractId() {
        return accommodationContractId;
    }

    public void setAccommodationContractId(int accommodationContractId) {
        this.accommodationContractId = accommodationContractId;
    }

    @Override
    public void insert() throws SQLException {
        this.id = SeasonDAO.getInstancie().insert(this);
    }

    @Override
    public void update() throws SQLException {
        SeasonDAO.getInstancie().update(this);
    }

    @Override
    public void delete() throws SQLException {
        SeasonDAO.getInstancie().delete(this.id);
    }

    // Operaciones
    // Metodo para comprobar semejanza de nombre
    public boolean isSameName(String name) {
        boolean veredicto = false;
        String nameComparar = "";
        if (!name.equalsIgnoreCase("")) {
            for (int i = 0, j = 0, l = 0; i < this.name.length() && !veredicto; i++) {

                nameComparar += this.name.charAt(i);

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

    public boolean isEqualsType(String type) {
        return this.typeOfSeason.equalsIgnoreCase(type);
    }

    // Fin Metodo para comprobar semejanza de nombre
    // Fin de Operaciones


}
