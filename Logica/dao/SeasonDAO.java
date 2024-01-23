package dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logica.Season;
import utils.ConnectionDataBase;

public class SeasonDAO implements SeasonDAOInterface {
    private static SeasonDAO seasonDAO;
    private HashMap<Integer, Season> cache;

    // PATRON SINGLENTON
    private SeasonDAO() {
        this.cache = new HashMap<Integer, Season>();
    }

    public static SeasonDAO getInstancie() {
        if (seasonDAO == null)
            seasonDAO = new SeasonDAO();

        return seasonDAO;
    }

    @Override
    public int insert(Season season) throws SQLException { // Metodo para la inserccion de una temporada en la base de datos
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_season(?, ?, ?, ?, ?, ?)}");
        // se definen los parámetros de la funcion
        cs.registerOutParameter(1, Types.INTEGER); // se registra el parametro de retorno
        cs.setString(2, season.getName());
        cs.setDate(3, Date.valueOf(season.getStartDate()));
        cs.setDate(4, Date.valueOf(season.getTerminationDate()));
        cs.setString(5, season.getDescription());
        cs.setString(6, season.getTypeOfSeason());
        cs.setInt(7, season.getAccommodationContractId());
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
        cs.close(); // se cierra la llamada a la funcion

        return idInsertado;
    }

    @Override
    public void delete(int idSeason) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_season(?)}");
        // se definen los parámetros de la funcion
        cs.setInt(1, idSeason);
        cs.executeQuery(); // se ejecuta la consulta de llamada a la funcion
        cs.close(); // se cierra la llamada a la funcion
        this.cache.remove(idSeason); // Se elimina la temporada de cache
    }

    @Override
    public void update(Season season) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_season(?, ?, ?, ?, ?, ?)}");
        // se definen los parámetros de la funcion
        cs.setString(1, season.getName());
        cs.setDate(2, Date.valueOf(season.getStartDate()));
        cs.setDate(3, Date.valueOf(season.getTerminationDate()));
        cs.setString(4, season.getDescription());
        cs.setString(5, season.getTypeOfSeason());
        cs.setInt(6, season.getId());
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        cs.close(); // se cierra la llamada a la funcion

    }


    @Override
    public List<Season> selectAll() throws SQLException {
        List<Season> listSeason = new ArrayList<Season>();
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_season()}");
        // se crea la lista de temporadas con la información obtenida de la base de datos
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        while (cs.getResultSet().next()) {
            listSeason.add(mapEntity(cs));
        }

        cs.close(); // se cierra la llamada a la funcion

        return listSeason;
    }

    @Override
    public List<Season> selectIntoAccommodationContract(int idAccommodationContract) throws SQLException { // metodo para obtener todas las temoporadas pertenecientes a un contrato
        List<Season> listSeason = new ArrayList<Season>();
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_seasons_accommodation_contract(?)}");
        cs.setInt(1, idAccommodationContract); // se define el parametro de la funcion
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        // se crea la lista de temporadas con la información obtenida de la base de datos
        while (cs.getResultSet().next()) {
            listSeason.add(mapEntity(cs));
        }

        cs.close(); // se cierra la llamada a la funcion

        return listSeason;
    }

    @Override
    public Season select(int idSeason) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_season(?)}");
        Season season = null;
        cs.setInt(1, idSeason); // se define el parametro de la funcion
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        if (cs.getResultSet().next()) // se situa el puntero
            season = mapEntity(cs);

        cs.close(); // se cierra la llamada a la funcion

        return season;
    }

    @Override
    public Season mapEntity(CallableStatement cs) throws SQLException {
        Season season = this.cache.get(cs.getResultSet().getInt("id"));

        if (season == null) {
            season = new Season(cs.getResultSet().getInt("id"), cs.getResultSet().getString("season_name"), cs.getResultSet().getDate("season_start_date").toLocalDate(),
                    cs.getResultSet().getDate("season_termination_date").toLocalDate(), cs.getResultSet().getString("season_description"),
                    cs.getResultSet().getString("type_of_season"), cs.getResultSet().getInt("accommodation_contract_id")); // se crea una nueva referencia de la temporada

            this.cache.put(season.getId(), season); // se alamacena en cache la referencia de esa temporada

        } else
            season.actualizarCampos(cs.getResultSet().getString("season_name"), cs.getResultSet().getDate("season_start_date").toLocalDate(),
                    cs.getResultSet().getDate("season_termination_date").toLocalDate(), cs.getResultSet().getString("season_description"),
                    cs.getResultSet().getString("type_of_season"), cs.getResultSet().getInt("accommodation_contract_id"));

        return season;
    }

}
