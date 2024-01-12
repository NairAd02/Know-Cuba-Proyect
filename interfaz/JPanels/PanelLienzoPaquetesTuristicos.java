package JPanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JPanel;


public class PanelLienzoPaquetesTuristicos extends JPanel {


	private static final long serialVersionUID = 1L;

	public PanelLienzoPaquetesTuristicos() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
	}

	public void addPanelPaqueteTuristico(PanelPaqueteTuristico panelPaqueteTuristico){
		Component[] componentes = getComponents();
		PanelPaqueteTuristico ultimoElemento = null;
		int posicionExactaY = 0;

		if(componentes.length!=0){
			ultimoElemento = (PanelPaqueteTuristico) componentes[componentes.length-1];
			posicionExactaY = ultimoElemento.getY() + ultimoElemento.getHeight();
		}


		if(getComponentCount() == 0){
			panelPaqueteTuristico.setLocation(56, 55);

		}
		else{
			if(getComponentCount() % 3 == 0){
				panelPaqueteTuristico.setLocation(56, posicionExactaY + 50);
			}
			else{
				panelPaqueteTuristico.setLocation(ultimoElemento.getX() + 532, ultimoElemento.getY());
			}
		}

		add(panelPaqueteTuristico);

		if((getComponentCount()-1) % 3 == 0 && getComponentCount() != 1 ){

			componentes = getComponents();
			ultimoElemento = (PanelPaqueteTuristico) componentes[componentes.length-1];

			posicionExactaY = ultimoElemento.getY() + ultimoElemento.getHeight();

			if(posicionExactaY > (int)getPreferredSize().getHeight()){

				setPreferredSize(new Dimension((int)getPreferredSize().getWidth(), (int)getPreferredSize().getHeight() + (posicionExactaY - (int)getPreferredSize().getHeight())+ 50));
			}
		}
	}
}
