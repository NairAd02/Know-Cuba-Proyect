package JPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import JFrames.FrameAdvertencia;
import JFrames.FrameDecisor;
import JFrames.FrameInformacionPaquete;
import JFrames.FramePrincipal;
import logica.Controller;
import logica.Hotel;
import logica.Provider;
import logica.TouristPackage;
import modelosTablas.ModeloTablaAccommodationProvider;
import utils.ConnectionDataBase;
import utils.Semaphore;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;

public class PanelGestionPaquetesTuristicos extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel lblAnnadir;
	private JLabel lblDelete;
	private JLabel lblShowPlans;
	private String searchName;
	private JPanel panelContenedorTable;
	private JPanel panelOpciones;
	private JPanel panelBotones;
	private JPanel panelFiltros;
	private JPanel panelSuperior;
	private JLabel lblTitleSeccion;
	private JPanel panelPaquetesTuristicos;
	private JScrollPane scrollPane;
	private PanelLienzoPaquetesTuristicos panelLienzoPaquetesTuristicos;
	private JPanel panelFiltrosStartDate;
	private JLabel lblNewLabel;
	private JPanel panelMinMaxStartDate;
	private JLabel lblMin;
	private JPanel panelFilterName;
	private JPanel panelTextFieldName;
	private JTextField textFieldName;
	private JPanel panelFiltrosTerminationDate;
	private JLabel lblTerminationDate;
	private JPanel panelMinMaxTerminationDate;
	private JLabel lblMin_1;
	private JDateChooser dateChooserStratDateMin_2;
	private JLabel lblMax_1;
	private JDateChooser dateChooserStratDateMin_3;
	private JPanel panelFiltrosCantAviable;
	private JLabel lblCantAvailable;
	private JLabel lblMin_2;
	private JPanel panelMinMaxCantAvaible;
	private JLabel lblMin_3;
	private JSpinner spinner;
	private JLabel lblMin_4;
	private JSpinner spinner_1;
	private JPanel panelFiltrosPrice;
	private JLabel lblPrice;
	private JPanel panelMinMaxPrice;
	private JLabel lblMin_5;
	private JSpinner spinner_2;
	private JLabel lblMin_6;
	private JSpinner spinner_3;
	private JLabel lblNewLabel_1;

	private class Eliminar extends Thread { // Hilo para la eliminacion

		public void run () {
			synchronized (Semaphore.samaphore) { 
				try {
					Semaphore.samaphore.wait(); // se duerme al hilo hasta esperar la confirmacion del usuario
					if (Controller.getInstancie().isConfirmacion()) { // si el usuario dió el consentimiento de realizar la operación
						Controller.getInstancie().setConfirmacion(false); // se establece el estado de la confirmación por defecto
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}


	private void crearFrameDecisor () {
		FrameDecisor frameDecisor = new FrameDecisor(FramePrincipal.getIntancie(), "Seguro que desea eliminar los paquetes turisticos seleccionados seleccionados");
		frameDecisor.setVisible(true);
		FramePrincipal.getIntancie().setEnabled(false); // se inhabilita el frame principal
	}

	private void crearFrameNotificacion () {
		FrameAdvertencia frameAdvertencia = new FrameAdvertencia("Han sido elimanado correctamente los paquetes turisticos");
		frameAdvertencia.setVisible(true);
	}

	public PanelGestionPaquetesTuristicos() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});
		searchName = "";
		setBackground(SystemColor.inactiveCaptionBorder);
		setBounds(0, 41, 1244, 678);
		setLayout(new BorderLayout(0, 0));

		panelContenedorTable = new JPanel();
		add(panelContenedorTable, BorderLayout.CENTER);
		panelContenedorTable.setLayout(new BorderLayout(0, 0));

		panelOpciones = new JPanel();

		panelContenedorTable.add(panelOpciones, BorderLayout.NORTH);
		panelOpciones.setLayout(new BorderLayout(0, 0));
		panelOpciones.setBackground(new Color(18, 95, 115));

		panelFiltros = new JPanel();
		panelFiltros.setBackground(new Color(18, 95, 115));
		panelOpciones.add(panelFiltros, BorderLayout.WEST);

		panelBotones = new JPanel();
		panelBotones.setBackground(new Color(18, 95, 115));
		panelOpciones.add(panelBotones, BorderLayout.EAST);

		panelPaquetesTuristicos = new JPanel();
		panelContenedorTable.add(panelPaquetesTuristicos, BorderLayout.CENTER);
		panelPaquetesTuristicos.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panelPaquetesTuristicos.add(scrollPane, BorderLayout.CENTER);

		panelLienzoPaquetesTuristicos = new PanelLienzoPaquetesTuristicos();
		scrollPane.setViewportView(panelLienzoPaquetesTuristicos);

		panelSuperior = new JPanel();
		panelSuperior.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panelSuperior.setBackground(SystemColor.inactiveCaptionBorder);
		add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblTitleSeccion = new JLabel("Tourist Package");
		lblTitleSeccion.setOpaque(true);
		lblTitleSeccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleSeccion.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitleSeccion.setBorder(null);
		lblTitleSeccion.setBackground(SystemColor.inactiveCaptionBorder);
		panelSuperior.add(lblTitleSeccion);



		this.addButtons();
		try {
			this.actualizarPanelPaquetes();
			ConnectionDataBase.commit(); // se confirman las operaciones realizadas en la base de datos
		} catch (SQLException e1) {
			try {
				ConnectionDataBase.roolback(); // se cancelan las operaciones realizadas en la base de datos
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} 
			e1.printStackTrace();
		}
	}



	public void actualizarPanelPaquetes () throws SQLException {
		ArrayList<TouristPackage> touristPackages = Controller.getInstancie().getTouristAgency().getTouristPackages(); // se obtienen todos los paquetes turisticos de la empresa;
		this.panelLienzoPaquetesTuristicos.clearElements();
		for (int i = 0; i < touristPackages.size(); i++) {
			if (touristPackages.get(i).verificarPaquete()) // Se comprueba si el paquete es valido (Se contiene al menos una modalidad)
				this.panelLienzoPaquetesTuristicos.addPanelPaqueteTuristico(new PanelPaqueteTuristico(touristPackages.get(i)));
			else {
				Controller.getInstancie().getTouristAgency().deleteTouristPackage(touristPackages.get(i)); // se elimina el paquete de la logica del negocio y de la base de datos*/
				i--; // se decrementa por la eliminacion
			}
		}

		this.repintarPanel(); // se repinta la infromacion del panel
	}

	private void repintarPanel () {
		repaint();
		revalidate();
	}


	private void addButtons () {

		lblAnnadir = new JLabel("");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAnnadir.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Plus.png")));
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrameInformacionPaquete frameInformacionPaqueteTuristico = new FrameInformacionPaquete(PanelGestionPaquetesTuristicos.this, new TouristPackage());
				frameInformacionPaqueteTuristico.setVisible(true);
				FramePrincipal.getIntancie().setEnabled(false); // se inhabilita el frame principal
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAnnadir.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAnnadir.setBackground(new Color(18, 95, 115));
			}
		});
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(new Color(18, 95, 115));
		lblAnnadir.setBounds(245, 19, 68, 52);
		panelBotones.add(lblAnnadir);

		lblDelete = new JLabel("");
		lblDelete.setOpaque(true);
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDelete.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Trash.png")));
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblDelete.isEnabled()) {
					Eliminar eliminar = new Eliminar(); // se crea el nuevo hilo
					eliminar.start(); // se ejecuta el nuevo hilo
					crearFrameDecisor(); // se crea el frame decisor, donde el usuario dará su confirmación
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (lblDelete.isEnabled()) 
					lblDelete.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (lblDelete.isEnabled())
					lblDelete.setBackground(new Color(18, 95, 115));
			}
		});
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDelete.setBackground(new Color(18, 95, 115));
		lblDelete.setBounds(405, 19, 67, 52);
		panelBotones.add(lblDelete);
		lblShowPlans = new JLabel("");
		lblShowPlans.setOpaque(true);
		lblShowPlans.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblShowPlans.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Edit.png")));
		lblShowPlans.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblShowPlans.isEnabled()) {

				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (lblShowPlans.isEnabled())
					lblShowPlans.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (lblShowPlans.isEnabled())
					lblShowPlans.setBackground(new Color(18, 95, 115));
			}
		});
		lblShowPlans.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowPlans.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblShowPlans.setBackground(new Color(18, 95, 115));
		lblShowPlans.setBounds(564, 19, 67, 52);
		panelBotones.add(lblShowPlans);
		
		lblNewLabel_1 = new JLabel("                       ");
		panelFiltros.add(lblNewLabel_1);
		
		panelFilterName = new JPanel();
		panelFilterName.setBackground(new Color(18, 95, 115));
		panelFiltros.add(panelFilterName);
		panelFilterName.setLayout(new BorderLayout(0, 0));
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Dialog", Font.BOLD, 18));
		panelFilterName.add(lblName, BorderLayout.NORTH);
		
		panelTextFieldName = new JPanel();
		panelTextFieldName.setBackground(new Color(18, 95, 115));
		panelFilterName.add(panelTextFieldName, BorderLayout.SOUTH);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelTextFieldName.add(textFieldName);
		textFieldName.setColumns(10);
		
		panelFiltrosStartDate = new JPanel();
		panelFiltrosStartDate.setBackground(new Color(18, 95, 115));
		panelFiltros.add(panelFiltrosStartDate);
		panelFiltrosStartDate.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("Start Date");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		panelFiltrosStartDate.add(lblNewLabel, BorderLayout.NORTH);
		
		panelMinMaxStartDate = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelMinMaxStartDate.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelMinMaxStartDate.setBackground(new Color(18, 95, 115));
		panelFiltrosStartDate.add(panelMinMaxStartDate, BorderLayout.SOUTH);
		
		lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxStartDate.add(lblMin);
		
		JDateChooser dateChooserStratDateMin = new JDateChooser();
		dateChooserStratDateMin.setPreferredSize(new Dimension(120, 22));
		dateChooserStratDateMin.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxStartDate.add(dateChooserStratDateMin);
		
		JLabel lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxStartDate.add(lblMax);
		
		JDateChooser dateChooserStratDateMin_1 = new JDateChooser();
		dateChooserStratDateMin_1.setPreferredSize(new Dimension(120, 22));
		dateChooserStratDateMin_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxStartDate.add(dateChooserStratDateMin_1);
		
		panelFiltrosTerminationDate = new JPanel();
		panelFiltrosTerminationDate.setBackground(new Color(18, 95, 115));
		panelFiltros.add(panelFiltrosTerminationDate);
		panelFiltrosTerminationDate.setLayout(new BorderLayout(0, 0));
		
		lblTerminationDate = new JLabel("Termination Date");
		lblTerminationDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblTerminationDate.setForeground(SystemColor.textHighlightText);
		lblTerminationDate.setFont(new Font("Dialog", Font.BOLD, 18));
		panelFiltrosTerminationDate.add(lblTerminationDate, BorderLayout.NORTH);
		
		panelMinMaxTerminationDate = new JPanel();
		panelMinMaxTerminationDate.setBackground(new Color(18, 95, 115));
		panelFiltrosTerminationDate.add(panelMinMaxTerminationDate, BorderLayout.CENTER);
		
		lblMin_1 = new JLabel("Min:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxTerminationDate.add(lblMin_1);
		
		dateChooserStratDateMin_2 = new JDateChooser();
		dateChooserStratDateMin_2.setPreferredSize(new Dimension(120, 22));
		dateChooserStratDateMin_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxTerminationDate.add(dateChooserStratDateMin_2);
		
		lblMax_1 = new JLabel("Max:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxTerminationDate.add(lblMax_1);
		
		dateChooserStratDateMin_3 = new JDateChooser();
		dateChooserStratDateMin_3.setPreferredSize(new Dimension(120, 22));
		dateChooserStratDateMin_3.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxTerminationDate.add(dateChooserStratDateMin_3);
		
		panelFiltrosCantAviable = new JPanel();
		panelFiltrosCantAviable.setBackground(new Color(18, 95, 115));
		panelFiltros.add(panelFiltrosCantAviable);
		panelFiltrosCantAviable.setLayout(new BorderLayout(0, 0));
		
		lblCantAvailable = new JLabel("Cant Available");
		lblCantAvailable.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantAvailable.setForeground(SystemColor.textHighlightText);
		lblCantAvailable.setFont(new Font("Dialog", Font.BOLD, 18));
		panelFiltrosCantAviable.add(lblCantAvailable, BorderLayout.NORTH);
		
		panelMinMaxCantAvaible = new JPanel();
		panelMinMaxCantAvaible.setBackground(new Color(18, 95, 115));
		FlowLayout flowLayout_1 = (FlowLayout) panelMinMaxCantAvaible.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelFiltrosCantAviable.add(panelMinMaxCantAvaible, BorderLayout.SOUTH);
		
		lblMin_3 = new JLabel("Min:");
		lblMin_3.setForeground(SystemColor.textHighlightText);
		lblMin_3.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxCantAvaible.add(lblMin_3);
		
		spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension(70, 20));
		spinner.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxCantAvaible.add(spinner);
		
		lblMin_4 = new JLabel("Max:");
		lblMin_4.setForeground(SystemColor.textHighlightText);
		lblMin_4.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxCantAvaible.add(lblMin_4);
		
		spinner_1 = new JSpinner();
		spinner_1.setPreferredSize(new Dimension(70, 20));
		spinner_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxCantAvaible.add(spinner_1);
		
		panelFiltrosPrice = new JPanel();
		panelFiltrosPrice.setBackground(new Color(18, 95, 115));
		panelFiltros.add(panelFiltrosPrice);
		panelFiltrosPrice.setLayout(new BorderLayout(0, 0));
		
		lblPrice = new JLabel("Price");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setForeground(SystemColor.textHighlightText);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 18));
		panelFiltrosPrice.add(lblPrice, BorderLayout.NORTH);
		
		panelMinMaxPrice = new JPanel();
		panelMinMaxPrice.setBackground(new Color(18, 95, 115));
		panelFiltrosPrice.add(panelMinMaxPrice, BorderLayout.SOUTH);
		
		lblMin_5 = new JLabel("Min:");
		lblMin_5.setForeground(SystemColor.textHighlightText);
		lblMin_5.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxPrice.add(lblMin_5);
		
		spinner_2 = new JSpinner();
		spinner_2.setPreferredSize(new Dimension(70, 20));
		spinner_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxPrice.add(spinner_2);
		
		lblMin_6 = new JLabel("Max:");
		lblMin_6.setForeground(SystemColor.textHighlightText);
		lblMin_6.setFont(new Font("Dialog", Font.BOLD, 16));
		panelMinMaxPrice.add(lblMin_6);
		
		spinner_3 = new JSpinner();
		spinner_3.setPreferredSize(new Dimension(70, 20));
		spinner_3.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelMinMaxPrice.add(spinner_3);
		
	
	}
}
