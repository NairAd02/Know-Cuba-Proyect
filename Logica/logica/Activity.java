package logica;

import java.time.LocalDate;

public class Activity {
	private int id;
	private LocalDate fecha;
	private String activity_description;
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getActivity_description() {
		return activity_description;
	}
	public void setActivity_description(String activity_description) {
		this.activity_description = activity_description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Activity(int id, LocalDate fecha, String activity_description) {
		this.id = id;
		this.fecha = fecha;
		this.activity_description = activity_description;
	}
}
