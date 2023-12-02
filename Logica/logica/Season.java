package logica;

import java.time.LocalDate;

public class Season {
	private int id;
	private String name;
	private LocalDate startDate;
	private LocalDate terminationDate;
	private String seasonDescription;
	private String typeOfSeason;
	
	public Season(int id, String name, LocalDate startDate,
			LocalDate terminationDate, String seasonDescription,
			String typeOfSeason) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.terminationDate = terminationDate;
		this.seasonDescription = seasonDescription;
		this.typeOfSeason = typeOfSeason;
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

	public String getSeasonDescription() {
		return seasonDescription;
	}

	public void setSeasonDescription(String seasonDescription) {
		this.seasonDescription = seasonDescription;
	}

	public String getTypeOfSeason() {
		return typeOfSeason;
	}

	public void setTypeOfSeason(String typeOfSeason) {
		this.typeOfSeason = typeOfSeason;
	}
	
}
