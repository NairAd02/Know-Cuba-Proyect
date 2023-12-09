package logica;

public class Vehicle {
	private int id;
	private String lock;
	private int transportationProviderId;

	public Vehicle(int id,String lock, int transportationProviderId) {
		super();
		this.id = id;
		this.lock = lock;
		this.transportationProviderId = transportationProviderId;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public int getTransportationProviderId() {
		return transportationProviderId;
	}

	public void setTransportationProviderId(int transportationProviderId) {
		this.transportationProviderId = transportationProviderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
