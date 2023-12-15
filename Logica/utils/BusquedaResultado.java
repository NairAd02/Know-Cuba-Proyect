package utils;

public class BusquedaResultado {
	private String textoBusqueda;
	private int inicio;
	private int finnal;


	public BusquedaResultado(String textoBusqueda, int inicio, int finnal) {
		super();
		this.textoBusqueda = textoBusqueda;
		this.inicio = inicio;
		this.finnal = finnal;
	}


	public String getTextoBusqueda() {
		return textoBusqueda;
	}


	public int getFinnal() {
		return finnal;
	}


	public void setFinnal(int finnal) {
		this.finnal = finnal;
	}


	public void setTextoBusqueda(String textoBusqueda) {
		this.textoBusqueda = textoBusqueda;
	}


	public int getInicio() {
		return inicio;
	}


	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

}
