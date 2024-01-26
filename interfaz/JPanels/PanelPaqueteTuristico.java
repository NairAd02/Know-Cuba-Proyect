package JPanels;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;
import logica.TouristPackage;
import javax.swing.border.MatteBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class PanelPaqueteTuristico extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TouristPackage touristPackage;
	private JLabel lblCampoDiasDuracion;
	private JLabel lblName;
	private JLabel lblCampoName;
	private JLabel lblCampoTerminationDate;
	private JLabel lblCampoStartDate;
	private JLabel lblCampoCantPax;
	private JLabel lblCampoCosto;
	private JLabel lblCantPax_1;
	private JLabel lblCampoCantDisponibles;
	private SimpleAttributeSet modificadorDeTexto;
	private JLabel lblCarrito;
	private JTextPaneModificado textPaneNameTouristPackage;
	private boolean isSelected;
	private PanelGestionPaquetesTuristicos panelGestionPaquetesTuristicos;

	public void select () {
		this.isSelected = true;
		lblCarrito.setIcon(new ImageIcon(PanelPaqueteTuristico.class.getResource("/images/Diapositiva2.JPG")));
		setBackground(new Color(5, 150, 177));
		this.panelGestionPaquetesTuristicos.deselectedAllTouristPackage(PanelPaqueteTuristico.this);
		this.panelGestionPaquetesTuristicos.actualizarEstadoButtons();
	}

	public void deselect () {
		this.isSelected = false;
		lblCarrito.setIcon(new ImageIcon(PanelPaqueteTuristico.class.getResource("/images/PaqueteTuristico.jpg")));
		setBackground(new Color(18, 95, 115));
	}





	public TouristPackage getTouristPackage() {
		return touristPackage;
	}

	public void setTouristPackage(TouristPackage touristPackage) {
		this.touristPackage = touristPackage;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public PanelPaqueteTuristico(PanelGestionPaquetesTuristicos p ,TouristPackage t) {
		this.isSelected = false;

		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCarrito.setIcon(new ImageIcon(PanelPaqueteTuristico.class.getResource("/images/Diapositiva2.JPG")));
				setBackground(new Color(5, 150, 177));

			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (!isSelected) {
					lblCarrito.setIcon(new ImageIcon(PanelPaqueteTuristico.class.getResource("/images/PaqueteTuristico.jpg")));
					setBackground(new Color(18, 95, 115));
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				select();
			}
		});
		setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 0, 0)));
		this.touristPackage = t;
		this.panelGestionPaquetesTuristicos = p;
		setLayout(null);
		setBackground(new Color(18, 95, 115));

		lblName = new JLabel("Name :");
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(315, 11, 72, 23);
		add(lblName);

		lblCampoDiasDuracion = new JLabel("");
		lblCampoDiasDuracion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoDiasDuracion.setBounds(572, 178, 77, 23);
		add(lblCampoDiasDuracion);

		JLabel lblCosto = new JLabel("COSTO :");
		lblCosto.setForeground(SystemColor.textHighlightText);
		lblCosto.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCosto.setBounds(528, 178, 72, 23);
		add(lblCosto);

		lblCampoCosto = new JLabel(String.valueOf(this.touristPackage.price()));
		lblCampoCosto.setForeground(SystemColor.textHighlightText);
		lblCampoCosto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoCosto.setBounds(610, 178, 72, 23);
		add(lblCampoCosto);
		textPaneNameTouristPackage = new JTextPaneModificado();
		textPaneNameTouristPackage.setForeground(SystemColor.textHighlightText);
		textPaneNameTouristPackage.setEditable(false);
		textPaneNameTouristPackage.setFont(new Font("Roboto", Font.BOLD, 18));
		textPaneNameTouristPackage.setOpaque(false);
		textPaneNameTouristPackage.setBounds(386, 7, 363, 27);		
		add(textPaneNameTouristPackage);

		setSize(756, 231);

		JLabel lblDesde = new JLabel("Begin :");
		lblDesde.setForeground(SystemColor.textHighlightText);
		lblDesde.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDesde.setBounds(315, 56, 72, 23);
		add(lblDesde);

		JLabel lblHasta = new JLabel("Final :");
		lblHasta.setForeground(SystemColor.textHighlightText);
		lblHasta.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblHasta.setBounds(528, 56, 72, 23);
		add(lblHasta);

		JLabel lblCantPax = new JLabel("Cant PAX :");
		lblCantPax.setForeground(SystemColor.textHighlightText);
		lblCantPax.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCantPax.setBounds(315, 126, 101, 23);
		add(lblCantPax);

		lblCampoTerminationDate = new JLabel(this.touristPackage.getTerminationDate().toString());
		lblCampoTerminationDate.setForeground(SystemColor.textHighlightText);
		lblCampoTerminationDate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoTerminationDate.setBounds(603, 56, 143, 23);
		add(lblCampoTerminationDate);

		lblCampoStartDate = new JLabel(this.touristPackage.getStartDate().toString());
		lblCampoStartDate.setForeground(SystemColor.textHighlightText);
		lblCampoStartDate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoStartDate.setBounds(386, 56, 143, 23);
		add(lblCampoStartDate);

		lblCampoCantPax = new JLabel(String.valueOf(this.touristPackage.getCantMaxPax()));
		lblCampoCantPax.setForeground(SystemColor.textHighlightText);
		lblCampoCantPax.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoCantPax.setBounds(413, 126, 143, 23);
		add(lblCampoCantPax);

		lblCantPax_1 = new JLabel("Avaible :");
		lblCantPax_1.setForeground(SystemColor.textHighlightText);
		lblCantPax_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCantPax_1.setBounds(315, 178, 101, 23);
		add(lblCantPax_1);

		lblCampoCantDisponibles = new JLabel(String.valueOf(this.touristPackage.cantAviablePax()));
		lblCampoCantDisponibles.setForeground(SystemColor.textHighlightText);
		lblCampoCantDisponibles.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoCantDisponibles.setBounds(413, 178, 143, 23);
		add(lblCampoCantDisponibles);

		lblCarrito = new JLabel("");
		lblCarrito.setBorder(new MatteBorder(4, 0, 4, 4, (Color) new Color(0, 0, 0)));
		lblCarrito.setIcon(new ImageIcon(PanelPaqueteTuristico.class.getResource("/images/PaqueteTuristico.jpg")));
		lblCarrito.setBounds(6, 0, 287, 231);
		add(lblCarrito);

		modificadorDeTexto = new SimpleAttributeSet();

		this.construirPaqueteTuristico();
	}


	private void construirPaqueteTuristico(){

		if(this.touristPackage.getBusquedaResultado() != null)
			construirNombrePaqueteTuristico();
		else
			textPaneNameTouristPackage.setText(this.touristPackage.getName());
	}

	private void construirNombrePaqueteTuristico(){
		String name = this.touristPackage.getName();
		String textoAnnadir = null;

		for (int i = 0, j = 0; i < name.length(); i++) {
			j = i;
			StyleConstants.setBold(modificadorDeTexto, true);
			if(i != this.touristPackage.getBusquedaResultado().getInicio()){
				StyleConstants.setForeground(modificadorDeTexto, SystemColor.textHighlightText);
				textoAnnadir = String.valueOf(name.charAt(i));
			}
			else{
				StyleConstants.setForeground(modificadorDeTexto, SystemColor.activeCaptionBorder);
				textoAnnadir = this.touristPackage.getBusquedaResultado().getTextoBusqueda();
				i += textoAnnadir.length()-1;
			}

			try {
				textPaneNameTouristPackage.getDocument().insertString(j, textoAnnadir, modificadorDeTexto);
			} catch (BadLocationException e) {

				e.printStackTrace();
			}
		}
	}


}
