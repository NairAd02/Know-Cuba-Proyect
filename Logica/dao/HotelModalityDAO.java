package dao;

import logica.HotelModality;
import utils.ConnectionDataBase;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotelModalityDAO implements HotelModalityDAOInterface {
    private static HotelModalityDAO hotelModalityDAO;
    private HashMap<Integer, HotelModality> cache; // Atributo para guardar en cache cada referencia creada

    // PATRON SINGLENTON
    private HotelModalityDAO() {
        this.cache = new HashMap<Integer, HotelModality>();
    }

    public static HotelModalityDAO getInstancie() {
        if (hotelModalityDAO == null)
            hotelModalityDAO = new HotelModalityDAO();

        return hotelModalityDAO;
    }

    @Override
    public int insert(HotelModality hotelModality) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_hotel_modality(?)}");
        // se definen los parámetros de la funcion
        cs.registerOutParameter(1, Types.INTEGER); // se registra el parametro de retorno
        cs.setString(2, hotelModality.getName());
        cs.execute(); // se ejecuta la llamada a la funcion
        int idInsertado = cs.getInt(1);
        cs.close(); // se cierra la llamada a la funcion

        return idInsertado;
    }

    @Override
    public void delete(int idHotelModality) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_hotel_modality(?)}");
        cs.setInt(1, idHotelModality); // se define el parámetros de la funcion
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        cs.close(); // se cierra la llamada a la funcion
        this.cache.remove(idHotelModality); // se elimina la modalidad de hotel de la base de datos
    }

    @Override
    public void update(HotelModality hotelModality) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_hotel_modality(?, ?)}");
        // Se definen los parametros de la funcion
        cs.setInt(1, hotelModality.getId());
        cs.setString(2, hotelModality.getName());
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        cs.close(); // se cierra la llamada a la funcion
    }

    @Override
    public HotelModality select(int idHotelModality) throws SQLException {
        HotelModality hotelModality = null;
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_hotel_modality(?)}");
        cs.setInt(1, idHotelModality); // se define el parametro de la funcion
        cs.execute(); // se ejecuta la consulta de llamada a la funcion

        if (cs.getResultSet().next()) // se situa el cursor
            hotelModality = mapEntity(cs);

        cs.close(); // se cierra la llamada a la funcion

        return hotelModality;
    }

    @Override
    public List<HotelModality> selectAll() throws SQLException {
        List<HotelModality> listHotelsModalitys = new ArrayList<HotelModality>();
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_hotel_modality()}");
        cs.execute(); // se ejecuta la consulta de llamada a la funcion

        while (cs.getResultSet().next()) {
            listHotelsModalitys.add(mapEntity(cs)); // se almacena en la lista la entidad mapeada a objeto
        }

        cs.close(); // se cierra la llamada a la funcion

        return listHotelsModalitys;
    }

    @Override
    public HotelModality mapEntity(CallableStatement cs) throws SQLException {
        HotelModality hotelModality = this.cache.get(cs.getResultSet().getInt("id"));

        if (hotelModality == null) { // si la entidad no se encuentra mapeada a objeto
            hotelModality = new HotelModality(cs.getResultSet().getInt("id"), cs.getResultSet().getString("name"));
            this.cache.put(hotelModality.getId(), hotelModality); // se mapea la entidad a objeto
        } else
            hotelModality.actualizarCampos(cs.getResultSet().getString("name"));

        return hotelModality;
    }

    @Override
    public List<HotelModality> selectIntoHotel(int idHotel) throws SQLException {
        List<HotelModality> listHotelsModalitys = new ArrayList<HotelModality>();
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_hotel_modality_from_hotel(?)}");
        cs.setInt(1, idHotel); // se define el parametro de la funcion
        cs.execute(); // se ejecuta la consulta de llamada a la funcion

        while (cs.getResultSet().next()) {
            listHotelsModalitys.add(mapEntity(cs)); // se almacena en la lista la entidad mapeada a objeto
        }

        cs.close(); // se cierra la llamada a la funcion

        return listHotelsModalitys;
    }

    @Override
    public void insertIntoHotel(int idHotel, int idHotelModality) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call insert_hotel_modality_into_hotel(?, ?)}");
        // Se definen los parametros de la funcion
        cs.setInt(1, idHotel);
        cs.setInt(2, idHotelModality);
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        cs.close(); // se cierra la llamada a la funcion
    }

    @Override
    public void deleteFromHotel(int idHotel, int idHotelModality) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_hotel_modality_from_hotel(?, ?)}");
        // Se definen los parametros de la funcion
        cs.setInt(1, idHotel);
        cs.setInt(2, idHotelModality);
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        cs.close(); // se cierra la llamada a la funcion
    }
}
