package JPanels;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class PanelLienzoPaquetes extends JPanel {

	private static final long serialVersionUID = 1L;


	public PanelLienzoPaquetes(PanelPaqueteTuristico panelPaqueteTuristico) {		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(panelPaqueteTuristico);
	}

}
