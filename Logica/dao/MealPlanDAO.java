package dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logica.MealPlan;
import utils.ConnectionDataBase;

public class MealPlanDAO implements MealPlanDAOInterface {
    private static MealPlanDAO mealPlanDAO;
    private HashMap<Integer, MealPlan> cache; // Atributo para guardar en cache cada referencia creada

    // PATRON SINGLENTON
    private MealPlanDAO() {
        this.cache = new HashMap<Integer, MealPlan>();
    }

    public static MealPlanDAO getInstancie() {
        if (mealPlanDAO == null)
            mealPlanDAO = new MealPlanDAO();

        return mealPlanDAO;
    }

    @Override
    public int insert(MealPlan mealPlan) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{? = call insert_meal_plan(?)}");
        cs.setString(1, mealPlan.getName()); // se define el parametro de la funcion
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        int idInsertado = cs.getInt(1); // se obtiene el valor de retorno de la funcion
        cs.close(); // se cierra la llamada a la funcion

        return idInsertado;
    }

    @Override
    public void delete(int idMealPlan) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{delete_meal_plan(?)}");
        cs.setInt(1, idMealPlan); // se define el parametro de la funcion
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        cs.close(); // se cierra la llamada a la funcion
        this.cache.remove(idMealPlan); // se elimina el plan alimenticio de cache
    }

    @Override
    public void update(MealPlan mealPlan) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call update_meal_plan(?, ?)}");
        // se definen los parametros de la funcion
        cs.setInt(1, mealPlan.getId());
        cs.setString(2, mealPlan.getName());
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        cs.close(); // se cierra la llamada a la funcion
    }


    @Override
    public List<MealPlan> selectAll() throws SQLException {
        List<MealPlan> listMealPlans = new ArrayList<MealPlan>();
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_all_meal_plan()}");
        cs.execute(); // se ejecuta la consulta de llamada a la funcion

        while (cs.getResultSet().next()) {
            listMealPlans.add(mapEntity(cs));
        }

        cs.close(); // se cierra la llamada a la funcion

        return listMealPlans;
    }

    @Override
    public List<MealPlan> selectMealPlanIntoHotel(int idHotel) throws SQLException {
        List<MealPlan> listMealPlans = new ArrayList<MealPlan>();
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_meal_plans_hotel(?)}");
        cs.setInt(1, idHotel); // Se define el parametro de la funcion
        cs.execute(); // se ejecuta la consulta de llamada a la funcion

        while (cs.getResultSet().next()) {
            listMealPlans.add(mapEntity(cs));
        }

        cs.close(); // se cierra la llamada a la funcion

        return listMealPlans;
    }

    @Override
    public void insertIntoHotel(int idHotel, int idMealPlan) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call insert_meal_plan_hotel(?, ?)}");
        // Se definen los parametros de la funcion
        cs.setInt(1, idHotel);
        cs.setInt(2, idMealPlan);
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        cs.close(); // se cierra la llamada a la funcion
    }

    @Override
    public void deleteFromHotel(int idHotel, int idMealPlan) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call delete_meal_plan_hotel(?, ?)}");
        // Se definen los parametros de la funcion
        cs.setInt(1, idHotel);
        cs.setInt(2, idMealPlan);
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        cs.close(); // se cierra la llamada a la funcion
    }

    @Override
    public MealPlan select(int idMealPlan) throws SQLException {
        CallableStatement cs = ConnectionDataBase.getConnectionDataBase().prepareCall("{call get_meal_plan(?)}");
        MealPlan mealPlan = null;
        cs.setInt(1, idMealPlan); // se define el parametro de la funcion
        cs.execute(); // se ejecuta la consulta de llamada a la funcion
        if (cs.getResultSet().next()) // se situa el puntero
            mealPlan = mapEntity(cs);

        cs.close(); // se cierra la llamada a la funcion

        return mealPlan;
    }

    @Override
    public MealPlan mapEntity(CallableStatement cs) throws SQLException {
        MealPlan mealPlan = this.cache.get(cs.getResultSet().getInt("id"));

        if (mealPlan == null) {
            mealPlan = new MealPlan(cs.getResultSet().getInt("id"), cs.getResultSet().getString("meal_plan_name"));

            this.cache.put(mealPlan.getId(), mealPlan); // se alamacena en cache la referencia del plan alimenticio
        } else
            mealPlan.actualizarCampos(cs.getResultSet().getString("meal_plan_name"));

        return mealPlan;
    }

}
