package JPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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

public class PanelGestionPaquetesTuristicos extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField textFieldBuscador;
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
		setBounds(0, 41, 712, 678);
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

		for (int i = 0; i < touristPackages.size(); i++) {
			if (touristPackages.get(i).verificarPaquete()) // Se comprueba si el paquete es valido (Se contiene al menos una modalidad)
				this.panelLienzoPaquetesTuristicos.addPanelPaqueteTuristico(new PanelPaqueteTuristico(touristPackages.get(i)));
			else {
				Controller.getInstancie().getTouristAgency().deleteTouristPackage(touristPackages.get(i)); // se elimina el paquete de la logica del negocio y de la base de datos*/
				i--; // se decrementa por la eliminacion
			}
		}

	}


	private void addButtons () {

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(86, 34, 68, 22);
		panelFiltros.add(lblName);

		lblAnnadir = new JLabel("");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAnnadir.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Plus.png")));
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

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

		textFieldBuscador = new JTextField();
		textFieldBuscador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				searchName = "";
				if(e.getKeyChar() != ''){ // si la key es distinta del boton delete
					searchName = textFieldBuscador.getText()+ e.getKeyChar();

				}
				else{
					searchName = textFieldBuscador.getText();
				}

				// actualizar panel
			}
		});
		textFieldBuscador.setColumns(10);
		panelFiltros.add(textFieldBuscador);
	}
}
