package logica;

import dao.HotelModalityDAO;

import java.sql.SQLException;

public class HotelModality implements DUILogic {
    private int id;
    private String name;

    public HotelModality(int id, String name) { // Constructor a nivel de base de datos
        this.id = id;
        this.name = name;
    }

    public HotelModality(String name) { // Constructor a nivel de logica
        this.name = name;
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

    @Override
    public void insert() throws SQLException {
      this.id = HotelModalityDAO.getInstancie().insert(this);
    }

    public void insertIntoHotel (int idHotel) throws SQLException {
        HotelModalityDAO.getInstancie().insertIntoHotel(idHotel, this.id);
    }

    @Override
    public void update() throws SQLException {
 HotelModalityDAO.getInstancie().update(this);
    }

    @Override
    public void delete() throws SQLException {
       HotelModalityDAO.getInstancie().delete(this.id);
    }

    public void deleteFromHotel (int idHotel) throws SQLException {
        HotelModalityDAO.getInstancie().deleteFromHotel(idHotel, this.id);
    }


}
