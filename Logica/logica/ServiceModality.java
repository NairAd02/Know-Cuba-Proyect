package logica;

import java.time.LocalDate;

public class ServiceModality extends Modality{
	private Activity activity;
	private LocalDate releasedDate;
	private double price;


	public ServiceModality(int id, Contract contract, String typeOfModality, Activity activity, LocalDate releasedDate,
			double price) {
		super(id, contract, typeOfModality);
		this.activity = activity;
		this.releasedDate = releasedDate;
		this.price = price;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public LocalDate getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(LocalDate releasedDate) {
		this.releasedDate = releasedDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getActivityId () {
		return this.activity.getId();
	}

}
