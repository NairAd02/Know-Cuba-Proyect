package logica;

public class TransportationProvider {
	private String provider_name;

	public TransportationProvider(String provider_name) {
		super();
		this.provider_name = provider_name;
	}

	public String getProvider_name() {
		return provider_name;
	}

	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
}
