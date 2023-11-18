package logica;

import java.time.LocalDate;

public class Season {
	private int id;
	private String season_name;
	private LocalDate season_start_date;
	private LocalDate season_termination_date;
	private String season_description;
	private String type_of_contract;
	
	public Season(String season_name, LocalDate season_start_date,
			LocalDate season_termination_date, String season_description,
			String type_of_contract) {
		this.season_name = season_name;
		this.season_start_date = season_start_date;
		this.season_termination_date = season_termination_date;
		this.season_description = season_description;
		this.type_of_contract = type_of_contract;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSeason_name() {
		return season_name;
	}
	public void setSeason_name(String season_name) {
		this.season_name = season_name;
	}
	public LocalDate getSeason_start_date() {
		return season_start_date;
	}
	public void setSeason_start_date(LocalDate season_start_date) {
		this.season_start_date = season_start_date;
	}
	public LocalDate getSeason_termination_date() {
		return season_termination_date;
	}
	public void setSeason_termination_date(LocalDate season_termination_date) {
		this.season_termination_date = season_termination_date;
	}
	public String getSeason_description() {
		return season_description;
	}
	public void setSeason_description(String season_description) {
		this.season_description = season_description;
	}
	public String getType_of_contract() {
		return type_of_contract;
	}
	public void setType_of_contract(String type_of_contract) {
		this.type_of_contract = type_of_contract;
	}
	
}
