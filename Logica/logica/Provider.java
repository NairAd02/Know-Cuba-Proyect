package logica;



public class Provider {
	// Atributos estaticos para el hash
	public static final int serviceProvider = 0;
	public static final int transportationProvider = 1;
	public static final int accommodationProvider = 2;
	
	//fin 
	protected int id;
	protected String name;
	protected String province;
	
	public Provider(int id, String name, String province) {
		super();
		this.id = id;
		this.name = name;
		this.province = province;
	}
	
	public Provider (int id) { // CONSTRUCTOR PARA LAS BUSQUEDAS EN EL BINARYSEARCHTREE
		this.id = id;
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
	


}
