package JPanels;

import javax.swing.JPanel;

import logica.CarrierContract;
import logica.TransportModality;

public interface AddTransportModalityOperations {
	public CarrierContract getCarrierContract ();
	public void cambioDePanel (JPanel panel);
	public TransportModality getTransporModality ();
	public JPanel getPanel ();
}
