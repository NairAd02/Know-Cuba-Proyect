package JPanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import logica.TouristPackage;
import net.sf.jasperreports.engine.type.PenEnum;


public class PanelLienzoPaquetesTuristicos extends JPanel {


	private static final long serialVersionUID = 1L;

	public PanelLienzoPaquetesTuristicos() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
	}

	public void clearElements () { // Metodo para eliminar todos los componenetes del panel
		this.removeAll();
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
			panelPaqueteTuristico.setLocation(30, 55);

		}
		else{
			if(getComponentCount() % 2 == 0){
				panelPaqueteTuristico.setLocation(30, posicionExactaY + 50);
			}
			else{
				panelPaqueteTuristico.setLocation(ultimoElemento.getX() + ultimoElemento.getWidth() + 45, ultimoElemento.getY());
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

	public TouristPackage getFirstSelectElement () {
		TouristPackage touristPackage = null;

		for (int i = 0; i < getComponentCount() && touristPackage == null; i++) {
			PanelPaqueteTuristico panelPaq = (PanelPaqueteTuristico) getComponent(i);
			if (panelPaq.isSelected())
				touristPackage = panelPaq.getTouristPackage();
		}

		return touristPackage;
	}

	public ArrayList<TouristPackage> getSelectElements () {
		ArrayList<TouristPackage> touristPackage = new ArrayList<TouristPackage>();

		for (int i = 0; i < getComponentCount(); i++) {
			PanelPaqueteTuristico panelPaq = (PanelPaqueteTuristico) getComponent(i);
			if (panelPaq.isSelected())
				touristPackage.add(panelPaq.getTouristPackage());
		}

		return touristPackage;
	}

	public void deselectedAllPanelTouristPackage (PanelPaqueteTuristico panel) {
		for (int i = 0; i < getComponentCount(); i++) {
			PanelPaqueteTuristico panelPaq = (PanelPaqueteTuristico) getComponent(i);
			if (!panelPaq.equals(panel))
			panelPaq.deselect();			
		}
	}
	
	public void deselectedAllPanelTouristPackage () {
		for (int i = 0; i < getComponentCount(); i++) {
			PanelPaqueteTuristico panelPaq = (PanelPaqueteTuristico) getComponent(i);
			panelPaq.deselect();			
		}
	}
}
