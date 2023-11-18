package logica;

public class ServiceProvider {
	private String provider_name;
	private String province;

	public ServiceProvider(String provider_name, String province) {
		this.provider_name = provider_name;
		this.province = province;
	}

	public String getProvider_name() {
		return provider_name;
	}

	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
}

