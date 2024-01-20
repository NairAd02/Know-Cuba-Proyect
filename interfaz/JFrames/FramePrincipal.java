package JFrames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import JPanels.PanelGerenteAsociacionEmpresaProveedorAlojamiento;
import JPanels.PanelGerenteAsociacionEmpresaProveedorServicio;
import JPanels.PanelGerenteAsociacionEmpresaProveedorTransporte;
import JPanels.PanelGerenteCreacionContrato;
import JPanels.PanelGestionPaquetesTuristicos;
import JPanels.PanelGestionUsuarios;
import logica.Administrator;
import logica.Controller;
import logica.Manager;
import logica.PackageDesigner;
import logica.TouristPackage;
import utils.ConnectionDataBase;
import utils.Semaphore;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;

public class FramePrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static FramePrincipal frameGerente;
	private JPanel contentPane;
	private JLabel lblSeccionAssociationCompany;
	private JLabel lblSeccionContractCreation;
	private int mouseX, mouseY;
	private JPanel panelLateral;
	private JPanel panelSecciones;
	private JPanel panelNameAplication;
	private JLabel lblTitle;
	private JLabel lblX;
	private JPanel panelUser;
	private JLabel lblManager;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblCerrarSesion;
	private JLabel lblServiceProvider;
	private JLabel lblTransportationProvider;
	private JLabel lblAccommodationProvider;
	private JLabel lblContracts;
	private JLabel lblUserAdministration;
	private JLabel lblUsers;
	private JLabel lblTouristPackage;
	private JLabel lblSeccionTouristPackageDesing;
	

	private class CerrarPrograma extends Thread { // Hilo para cerrar el programa
		public void run () {
			synchronized (Semaphore.samaphore) {
				try {
					Semaphore.samaphore.wait(); // se pone en espera al hilo

					if (Controller.getInstancie().isConfirmacion()) {
						cerrarPrograma();
						Controller.getInstancie().setConfirmacion(false);
					}

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}
	}



	public static FramePrincipal getIntancie () {
		if (frameGerente == null)
			frameGerente = new FramePrincipal();

		return frameGerente;
	}
	
	public static void destruirInstancia () {
		frameGerente = null;
	}


	private void crearFrameDecisor () {
		FrameDecisor frameDecisor = new FrameDecisor(FramePrincipal.this, "Seguro que desea salir del programa");
		frameDecisor.setVisible(true);
		setEnabled(false); // se inhabilita el frame
	}

	private FramePrincipal() {
		//GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//GraphicsDevice device = env.getDefaultScreenDevice();
		//setBounds(0, 0, 390, 1080);
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(5, 150, 177));
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));

		setContentPane(contentPane);
		setLocationRelativeTo(null);
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 0, 0)));
		panelSuperior.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x= e.getXOnScreen();
				int y= e.getYOnScreen();

				setLocation(x - mouseX , y - mouseY );
			}
		});
		panelSuperior.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		contentPane.setLayout(new BorderLayout(0, 0));
		panelSuperior.setBackground(new Color(18, 95, 115));
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new BorderLayout(0, 0));


		lblX = new JLabel("X ");
		lblX.setForeground(SystemColor.textHighlightText);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CerrarPrograma cerrarProgramaThread = new CerrarPrograma(); // se crea el hilo
				cerrarProgramaThread.start(); // se ejecuta el hilo
				crearFrameDecisor();

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(SystemColor.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(SystemColor.textHighlightText);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 34));
		panelSuperior.add(lblX, BorderLayout.EAST);

		panelUser = new JPanel();
		panelUser.setBackground(new Color(18, 95, 115));
		panelSuperior.add(panelUser, BorderLayout.WEST);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FramePrincipal.class.getResource("/images/user.png")));
		panelUser.add(lblNewLabel_1);

		lblNewLabel = new JLabel(Controller.getInstancie().getUser().getUserName());
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 32));
		panelUser.add(lblNewLabel);

		panelLateral = new JPanel();
		panelLateral.setBackground(new Color(5, 150, 177));
		contentPane.add(panelLateral, BorderLayout.WEST);
		panelLateral.setLayout(new BorderLayout(0, 0));

		panelSecciones = new JPanel();
		panelSecciones.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
		panelSecciones.setBackground(new Color(18, 95, 115));
		panelLateral.add(panelSecciones, BorderLayout.CENTER);
		panelSecciones.setLayout(null);

		panelNameAplication = new JPanel();
		panelNameAplication.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
		panelNameAplication.setBackground(new Color(18, 95, 115));
		panelLateral.add(panelNameAplication, BorderLayout.NORTH);
		panelNameAplication.setLayout(new BorderLayout(0, 0));

		lblTitle = new JLabel("");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setIcon(new ImageIcon(FramePrincipal.class.getResource("/images/WhatsApp Image 2023-11-14 at 8.59.57 PM - copia.jpeg")));
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panelNameAplication.add(lblTitle);

		JPanel panelTitleAplication = new JPanel();
		panelTitleAplication.setBackground(new Color(18, 95, 115));
		panelNameAplication.add(panelTitleAplication, BorderLayout.SOUTH);
		panelTitleAplication.setLayout(new BoxLayout(panelTitleAplication, BoxLayout.Y_AXIS));

		lblManager = new JLabel("  KNOW CUBA  ");
		lblManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblManager.setForeground(SystemColor.textHighlightText);
		lblManager.setFont(new Font("Arial Black", Font.BOLD, 32));
		panelTitleAplication.add(lblManager);

		lblNewLabel_2 = new JLabel("\r\n");
		panelNameAplication.add(lblNewLabel_2, BorderLayout.NORTH);

		lblCerrarSesion = new JLabel("Cerrar Sesion");
		lblCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrarSesion.setIcon(new ImageIcon(FramePrincipal.class.getResource("/images/Home.png")));
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cerrarSesionPrograma();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		lblCerrarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrarSesion.setForeground(SystemColor.textHighlightText);
		lblCerrarSesion.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblCerrarSesion.setBounds(0, 792, 278, 46);
		panelSecciones.add(lblCerrarSesion);

		this.addSecciones();

		
		this.definirColorLabelsSecciones(); // se actualizan los colores de los labels se de seleccion de las secciones

	}

	private void addSecciones () {
		if (Controller.getInstancie().getUser() instanceof Administrator) { // si el usuario es administrador se añaden todas las secciones
			this.addSeccionCreateContract(); // se añade la seccion de creacion de contratos
			this.addSeccionAssociationCompany(); // se añade la seccion de asociasion con proveedores
			this.addSeccionTouristPackageDesign(); // se añade la seccion de diseño de paquetes turisticos
			this.addSeccionUsers(); // se añade la seccion de administracion de usarios
			this.contentPane.add(new PanelGerenteAsociacionEmpresaProveedorServicio(), BorderLayout.CENTER);
		}
		else if (Controller.getInstancie().getUser() instanceof Manager) { // si el usuario es manager se añaden las secciones de creacion de contrato y de asociacion con proveedores
			this.addSeccionCreateContract(); // se añade la seccion de creacion de contratos
			this.addSeccionAssociationCompany(); // se añade la seccion de asociasion con proveedores
			this.contentPane.add(new PanelGerenteAsociacionEmpresaProveedorServicio(), BorderLayout.CENTER);
		}
		else if (Controller.getInstancie().getUser() instanceof PackageDesigner) { // si el usuario es packageDisigner se añaden las secciones de gestión de paquetes turisticos
			this.addSeccionTouristPackageDesign(); // se añade la seccion de diseño de paquetes turisticos
			this.contentPane.add(new PanelGestionPaquetesTuristicos(), BorderLayout.CENTER);
		}
	}


	private void addSeccionCreateContract () {
		lblSeccionContractCreation = new JLabel("CONTRACT CREATION");
		lblSeccionContractCreation.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));

		lblSeccionContractCreation.setForeground(SystemColor.textHighlightText);
		lblSeccionContractCreation.setOpaque(true);
		lblSeccionContractCreation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeccionContractCreation.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSeccionContractCreation.setBackground(new Color(18, 95, 115));
		lblSeccionContractCreation.setBounds(0,30, 296, 38);
		panelSecciones.add(lblSeccionContractCreation);

		lblContracts = new JLabel("   - CONTRACTS");
		lblContracts.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteCreacionContrato()); // se cambia la seccion a la de creacion de contratos
				definirColorLabelsSecciones(); // se actualiza el estado de los botonoes de las secciones
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblContracts.setForeground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (!(contentPane.getComponent(contentPane.getComponentCount() - 1) instanceof PanelGerenteCreacionContrato))
					lblContracts.setForeground(SystemColor.textHighlightText);
			}
		});
		lblContracts.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblContracts.setOpaque(true);
		lblContracts.setHorizontalAlignment(SwingConstants.LEFT);
		lblContracts.setForeground(SystemColor.textHighlightText);
		lblContracts.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblContracts.setBorder(null);
		lblContracts.setBackground(new Color(18, 95, 115));
		lblContracts.setBounds(0, 79, 136, 38);
		panelSecciones.add(lblContracts);
	}

	private void addSeccionAssociationCompany () {
		lblSeccionAssociationCompany = new JLabel("ASSOCIATION COMPANY");
		lblSeccionAssociationCompany.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));
		lblSeccionAssociationCompany.setForeground(SystemColor.textHighlightText);
		lblSeccionAssociationCompany.setOpaque(true);
		lblSeccionAssociationCompany.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeccionAssociationCompany.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSeccionAssociationCompany.setBackground(new Color(18, 95, 115));
		lblSeccionAssociationCompany.setBounds(0,146, 296, 38);
		panelSecciones.add(lblSeccionAssociationCompany);

		lblServiceProvider = new JLabel("   - SERVICE PROVIDER");
		lblServiceProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteAsociacionEmpresaProveedorServicio()); // se cambia la seccion a la de provedor de servicios
				definirColorLabelsSecciones(); // se actualiza el estado de los botonoes de las secciones
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblServiceProvider.setForeground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (!(contentPane.getComponent(contentPane.getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorServicio))
					lblServiceProvider.setForeground(SystemColor.textHighlightText);
			}
		});
		lblServiceProvider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblServiceProvider.setOpaque(true);
		lblServiceProvider.setHorizontalAlignment(SwingConstants.LEFT);
		lblServiceProvider.setForeground(SystemColor.textHighlightText);
		lblServiceProvider.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblServiceProvider.setBorder(null);
		lblServiceProvider.setBackground(new Color(18, 95, 115));
		lblServiceProvider.setBounds(0, 195, 244, 38);
		panelSecciones.add(lblServiceProvider);

		lblTransportationProvider = new JLabel("   - TRANSPORTATION PROVIDER");
		lblTransportationProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteAsociacionEmpresaProveedorTransporte()); // se cambia la seccion a la de provedor de transporte
				definirColorLabelsSecciones(); // se actualiza el estado de los botonoes de las secciones
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblTransportationProvider.setForeground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (!(contentPane.getComponent(contentPane.getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorTransporte))
					lblTransportationProvider.setForeground(SystemColor.textHighlightText);
			}
		});
		lblTransportationProvider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblTransportationProvider.setOpaque(true);
		lblTransportationProvider.setHorizontalAlignment(SwingConstants.LEFT);
		lblTransportationProvider.setForeground(SystemColor.textHighlightText);
		lblTransportationProvider.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblTransportationProvider.setBorder(null);
		lblTransportationProvider.setBackground(new Color(18, 95, 115));
		lblTransportationProvider.setBounds(0, 244, 271, 38);
		panelSecciones.add(lblTransportationProvider);

		lblAccommodationProvider = new JLabel("   - ACCOMMODATION PROVIDER");
		lblAccommodationProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteAsociacionEmpresaProveedorAlojamiento()); // se cambia la seccion a la de provedor de alojamiento
				definirColorLabelsSecciones(); // se actualiza el estado de los botonoes de las secciones
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAccommodationProvider.setForeground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (!(contentPane.getComponent(contentPane.getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorAlojamiento))
					lblAccommodationProvider.setForeground(SystemColor.textHighlightText);
			}
		});
		lblAccommodationProvider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAccommodationProvider.setOpaque(true);
		lblAccommodationProvider.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccommodationProvider.setForeground(SystemColor.textHighlightText);
		lblAccommodationProvider.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblAccommodationProvider.setBorder(null);
		lblAccommodationProvider.setBackground(new Color(18, 95, 115));
		lblAccommodationProvider.setBounds(0, 293, 271, 38);
		panelSecciones.add(lblAccommodationProvider);
	}

	private void addSeccionTouristPackageDesign () {
		lblSeccionTouristPackageDesing = new JLabel("TOURIST PACKAGE DESINGN");
		lblSeccionTouristPackageDesing.setOpaque(true);
		lblSeccionTouristPackageDesing.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeccionTouristPackageDesing.setForeground(SystemColor.textHighlightText);
		lblSeccionTouristPackageDesing.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSeccionTouristPackageDesing.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));
		lblSeccionTouristPackageDesing.setBackground(new Color(18, 95, 115));
		lblSeccionTouristPackageDesing.setBounds(0, 360, 296, 38);
		panelSecciones.add(lblSeccionTouristPackageDesing);

		lblTouristPackage = new JLabel("   - TOURIST PACKAGES");
		lblTouristPackage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblTouristPackage.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGestionPaquetesTuristicos()); // se cambia la seccion a la de provedor de alojamiento
				definirColorLabelsSecciones(); // se actualiza el estado de los botonoes de las secciones
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblTouristPackage.setForeground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (!(contentPane.getComponent(contentPane.getComponentCount() - 1) instanceof PanelGestionPaquetesTuristicos))
					lblTouristPackage.setForeground(SystemColor.textHighlightText);
			}
		});
		lblTouristPackage.setOpaque(true);
		lblTouristPackage.setHorizontalAlignment(SwingConstants.LEFT);
		lblTouristPackage.setForeground(SystemColor.textHighlightText);
		lblTouristPackage.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblTouristPackage.setBorder(null);
		lblTouristPackage.setBackground(new Color(18, 95, 115));
		lblTouristPackage.setBounds(0, 409, 244, 38);
		panelSecciones.add(lblTouristPackage);
	}

	private void addSeccionUsers () {
		lblUserAdministration = new JLabel("USERS ADMINISTRATION");
		lblUserAdministration.setOpaque(true);
		lblUserAdministration.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserAdministration.setForeground(SystemColor.textHighlightText);
		lblUserAdministration.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblUserAdministration.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));
		lblUserAdministration.setBackground(new Color(18, 95, 115));
		lblUserAdministration.setBounds(0, 476, 296, 38);
		panelSecciones.add(lblUserAdministration);

		lblUsers = new JLabel("   - USERS");
		lblUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGestionUsuarios()); // se cambia la sección para la sección de usuarios
				definirColorLabelsSecciones(); // se actualiza el estado de los botonoes de las secciones
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblUsers.setForeground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (!(contentPane.getComponent(contentPane.getComponentCount() - 1) instanceof PanelGestionUsuarios))
					lblUsers.setForeground(SystemColor.textHighlightText);
			}
		});
		lblUsers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUsers.setOpaque(true);
		lblUsers.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsers.setForeground(SystemColor.textHighlightText);
		lblUsers.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblUsers.setBorder(null);
		lblUsers.setBackground(new Color(18, 95, 115));
		lblUsers.setBounds(0, 525, 244, 38);
		panelSecciones.add(lblUsers);


	}

	private void cerrarPrograma () {
		try {
			this.cerrarSesionUser();
			ConnectionDataBase.getConnectionDataBase().commit(); // se confirman todas las operaciones realizadas a la base de datos
			System.exit(0);
		} catch (SQLException e) {
			try {
				ConnectionDataBase.getConnectionDataBase().rollback(); // se cancelan todas las operaciones realizadas a la base de datos
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}
		
	}
	
	private void cerrarSesionUser () throws SQLException {
			Controller.getInstancie().getUser().cerrarConexion(); // se cierra sesión
	}
	
	private void cerrarSesionPrograma () {
		try {
			this.cerrarSesionUser();
			ConnectionDataBase.getConnectionDataBase().commit(); // se confirman todas las operaciones realizadas a la base de datos
			destruirInstancia(); // se destruye la instancia de este frame
			Controller.destruirInstancie(); // se destruye la instancia de la clase controladora
			this.abrirFrameLogin(); // se abre el frame login
			
		} catch (SQLException e) {
			try {
				ConnectionDataBase.getConnectionDataBase().rollback(); // se cancelan todas las operaciones realizadas a la base de datos
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			e.printStackTrace();
		}
	}
	
	private void abrirFrameLogin () {
		FrameLogin frameLogin = new FrameLogin();
		frameLogin.setVisible(true);
		dispose(); // se cierra el frame actual
		
	}

	public void cambiarSeccion (JPanel seccion) {
		this.contentPane.remove(this.contentPane.getComponentCount() - 1); // se remueve el ultimo panel (este panel va a ser una seccion)
		this.contentPane.add(seccion, BorderLayout.CENTER); // se adiciona la seccion
		this.repintarFrame(); // se repinta el frame
		this.definirColorLabelsSecciones();
		this.repintarFrame();
	}

	public void repintarFrame () {
		repaint();
		revalidate();
	}

	public void definirColorLabelsSecciones () {
		if (Controller.getInstancie().getUser() instanceof Administrator) { // User Administrator
			// se define el color de todas las secciones
			this.definirColorLabelSeccionAssociationCompany();
			this.definirColorLabelSeccionCreationContract();
			this.definirColorLabelSeccionTouristPackageDesing();
			this.definirColorLabelSeccionUserAdministration();
			this.definirColorLabelServicerProvider();
			this.definirColorLabelTransportProvider();
			this.definirColorLabelAccommodationProvider();
			this.definirColorLabelContract();
			this.definirColorLabelTouristPackage();
			this.definirColorLabelUser();	
		}
		else if (Controller.getInstancie().getUser() instanceof Manager) { // User Manager
			// se define el color de las secciones de asosacion con las empresas y creacion de contratos
			this.definirColorLabelSeccionAssociationCompany();
			this.definirColorLabelSeccionCreationContract();	
			this.definirColorLabelServicerProvider();
			this.definirColorLabelTransportProvider();
			this.definirColorLabelAccommodationProvider();
			this.definirColorLabelContract();
		}
		else if (Controller.getInstancie().getUser() instanceof PackageDesigner) {
			this.definirColorLabelTouristPackage();
		}
		

	}

	private void definirColorLabelSeccionAssociationCompany () {
		if (this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorServicio || 
				this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorTransporte || 
				this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorAlojamiento) // se esta seleccionada cualquiera de las secciones de asosacion de empresas
			this.lblSeccionAssociationCompany.setBackground(SystemColor.activeCaptionBorder);
		else
			lblSeccionAssociationCompany.setBackground(new Color(18, 95, 115));
	}

	private void definirColorLabelSeccionCreationContract () {
		if (this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGerenteCreacionContrato)  // se esta seleccionada la seccion creacion contrato
			this.lblSeccionContractCreation.setBackground(SystemColor.activeCaptionBorder);
		else
			lblSeccionContractCreation.setBackground(new Color(18, 95, 115));
	}

	private void definirColorLabelSeccionTouristPackageDesing () {
		if (this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGestionPaquetesTuristicos)  // se esta seleccionada la seccion creacion contrato
			this.lblSeccionTouristPackageDesing.setBackground(SystemColor.activeCaptionBorder);
		else
			this.lblSeccionTouristPackageDesing.setBackground(new Color(18, 95, 115));
	}

	private void definirColorLabelSeccionUserAdministration () {
		if (this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGestionUsuarios)  // se esta seleccionada la seccion creacion contrato
			this.lblUserAdministration.setBackground(SystemColor.activeCaptionBorder);
		else
			lblUserAdministration.setBackground(new Color(18, 95, 115));
	}

	private void definirColorLabelServicerProvider () {
		if (this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorServicio)  // se esta seleccionada la seccion serviceProvider
			lblServiceProvider.setForeground(SystemColor.activeCaptionBorder);
		else
			lblServiceProvider.setForeground(SystemColor.textHighlightText);

	}

	private void definirColorLabelTransportProvider () {
		if (this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorTransporte)  // se esta seleccionada la seccion transportProvider
			lblTransportationProvider.setForeground(SystemColor.activeCaptionBorder);
		else
			lblTransportationProvider.setForeground(SystemColor.textHighlightText);

	}

	private void definirColorLabelAccommodationProvider () {
		if (this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorAlojamiento)  // se esta seleccionada la seccion accommodationProvider
			lblAccommodationProvider.setForeground(SystemColor.activeCaptionBorder);
		else
			lblAccommodationProvider.setForeground(SystemColor.textHighlightText);

	}

	private void definirColorLabelContract () {
		if (this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGerenteCreacionContrato)  // se esta seleccionada la seccion creacion contrato
			lblContracts.setForeground(SystemColor.activeCaptionBorder);
		else
			lblContracts.setForeground(SystemColor.textHighlightText);

	}

	private void definirColorLabelTouristPackage () {
		if (this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGestionPaquetesTuristicos)  // se esta seleccionada la seccion creacion contrato
			lblTouristPackage.setForeground(SystemColor.activeCaptionBorder);
		else
			lblTouristPackage.setForeground(SystemColor.textHighlightText);

	}

	private void definirColorLabelUser () {
		if (this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGestionUsuarios)  // se esta seleccionada la seccion gestion de usuarios
			lblUsers.setForeground(SystemColor.activeCaptionBorder);
		else
			lblUsers.setForeground(SystemColor.textHighlightText);
	}

	
}
