package logica;

public class Hotel {
	private int id;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHotel_chain() {
		return hotel_chain;
	}

	public void setHotel_chain(String hotel_chain) {
		this.hotel_chain = hotel_chain;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getHotel_category() {
		return hotel_category;
	}

	public void setHotel_category(String hotel_category) {
		this.hotel_category = hotel_category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String address;
	public String hotel_chain;
	public String province;
	public String hotel_category;
	
	public Hotel(String name, String address, String hotel_chain,
			String province, String hotel_category) {
		super();
		this.name = name;
		this.address = address;
		this.hotel_chain = hotel_chain;
		this.province = province;
		this.hotel_category = hotel_category;
	}
}
