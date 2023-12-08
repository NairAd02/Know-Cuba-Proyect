package logica;

import java.time.LocalDate;

public class Season {
	private int id;
	private String name;
	private LocalDate startDate;
	private LocalDate terminationDate;
	private String description;
	private String typeOfSeason;
	private int accommodationContractId;

	public Season(int id, String name, LocalDate startDate,
			LocalDate terminationDate, String description,
			String typeOfSeason, int accommodationContractId ) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.terminationDate = terminationDate;
		this.description = description;
		this.typeOfSeason = typeOfSeason;
		this.accommodationContractId = accommodationContractId;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(LocalDate terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeOfSeason() {
		return typeOfSeason;
	}

	public void setTypeOfSeason(String typeOfSeason) {
		this.typeOfSeason = typeOfSeason;
	}

	public int getAccommodationContractId() {
		return accommodationContractId;
	}

	public void setAccommodationContractId(int accommodationContractId) {
		this.accommodationContractId = accommodationContractId;
	}
	
	
}
