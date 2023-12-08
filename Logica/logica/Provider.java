package logica;

public class Provider {
	private int id;
	private String name;
	private String province;
	
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
