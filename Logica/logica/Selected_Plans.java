package logica;

public class Selected_Plans {
	private int id;
	private double price_selected_plan;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice_selected_plan() {
		return price_selected_plan;
	}

	public void setPrice_selected_plan(double price_selected_plan) {
		this.price_selected_plan = price_selected_plan;
	}

	public Selected_Plans(double price_selected_plan) {
		this.price_selected_plan = price_selected_plan;
	}
}
