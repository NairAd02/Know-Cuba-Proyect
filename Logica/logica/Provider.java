package logica;

import java.sql.SQLException;

import java.util.ArrayList;
import dao.ProviderDAO;
import utils.BusquedaResultado;


public abstract class Provider implements DUILogic, LikeName{
	// Atributos estaticos para el hash
	public static final int serviceProvider = 0;
	public static final int transportationProvider = 1;
	public static final int accommodationProvider = 2;

	public static ArrayList<Integer> getKeys () {
		ArrayList<Integer> keys = new ArrayList<Integer>(3);
		keys.add(serviceProvider);
		keys.add(transportationProvider);
		keys.add(accommodationProvider);

		return keys;
	}

	//fin 
	protected int id;
	protected String name;
	protected String province;
	protected BusquedaResultado busquedaResultado; // atributo para las busquedas

	public Provider(int id, String name, String province) { // Constructor a nivel de base de datos
		this.id = id;
		this.name = name;
		this.province = province;
	}

	public Provider(String name, String province) { // Constructor a nivel de logica
		this.name = name;
		this.province = province;
	}

	public Provider(String name) { // Constructor temporal para filtros
		this.name = name;
	}


	public Provider () { // CONSTRUCTOR PARA LAS BUSQUEDAS EN EL BINARYSEARCHTREE
		this.id = -1;
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

	// Metodo para comprobar semejanza de nombre
	public boolean isSameName (String name) {
		boolean veredicto = false;
		String nameComparar = "";
		if(!name.equalsIgnoreCase("")){
			for (int i = 0, j = 0, l = 0; i < this.name.length() && !veredicto ; i++) {

				nameComparar += this.name.charAt(i);

				j++;
				if (j == name.length()){			
					if (name.equalsIgnoreCase(nameComparar)){
						veredicto = true;
						this.busquedaResultado = new BusquedaResultado(nameComparar, i - (j - 1), i);
					}
					else{
						nameComparar = "";
						this.busquedaResultado = null;
					}
					j = 0;
					i = l++;
				}            
			}
		}
		else{
			veredicto = true;
			this.busquedaResultado = null;
		}

		return veredicto;
	}

	@Override
	public void delete() throws SQLException {
		ProviderDAO.getInstancie().delete(this.id);
	}

	public String toString () { // Metodo para definir como se muestra la informacion de la clase
		return this.name;
	}

}
