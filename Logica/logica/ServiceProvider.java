package logica;

import java.util.ArrayList;

public class ServiceProvider extends Provider {
	private ArrayList<Activity> activities;

	public ServiceProvider(int id, String name, String province,
			ArrayList<Activity> activities) {
		super(id, name, province);
		this.activities = activities;
	}

	public ServiceProvider (int id) { // CONSTRUCTOR PARA LAS BUSQUEDAS EN EL BINARYSEARCHTREE
		super(id);
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

}

