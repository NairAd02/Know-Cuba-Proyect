package JFrames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JPanels.PanelGerenteAsociacionEmpresa;
import JPanels.PanelGerenteAsociacionEmpresaProveedorAlojamiento;
import JPanels.PanelGerenteAsociacionEmpresaProveedorServicio;
import JPanels.PanelGerenteAsociacionEmpresaProveedorTransporte;
import JPanels.PanelGerenteCreacionContrato;
import dao.UserDAO;
import logica.Controller;
import utils.ConnectionDataBase;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
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
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Cursor;

public class FrameGerente extends JFrame {

	private static final long serialVersionUID = 1L;
	private static FrameGerente frameGerente;
	private JPanel contentPane;
	private JLabel lblSeccionAssociationCompany;
	private JLabel lblSeccionContractCreation;
	private int mouseX, mouseY;
	private JPanel panelLateral;
	private JPanel panelSecciones;
	private JPanel panelUser;
	private JLabel lblTitle;
	private JLabel lblX;
	private JPanel panelNameAplication;
	private JLabel lblManager;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblCerrarSesion;
	private JLabel lblServiceProvider;
	private JLabel lblTransportationProvider;
	private JLabel lblAccommodationProvider;
	private JLabel lblContracts;


	/**
	 * Launch the application.
	 */

	public static FrameGerente getIntancie () {
		if (frameGerente == null)
			frameGerente = new FrameGerente();

		return frameGerente;
	}

	private FrameGerente() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
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
				try {
					Controller.getInstancie().getUser().cerrarConexion(); // se cierra sesión
					ConnectionDataBase.getConnectionDataBase().commit(); // se confirman todas las operaciones realizadas a la base de datos
				} catch (SQLException e1) {
					try {
						ConnectionDataBase.getConnectionDataBase().rollback(); // se cancelan todas las operaciones realizadas a la base de datos
					} catch (SQLException e2) {

						e2.printStackTrace();
					}
					e1.printStackTrace();
				} 
				System.exit(0);
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

		panelNameAplication = new JPanel();
		panelNameAplication.setBackground(new Color(18, 95, 115));
		panelSuperior.add(panelNameAplication, BorderLayout.WEST);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FrameGerente.class.getResource("/images/user.png")));
		panelNameAplication.add(lblNewLabel_1);

		lblNewLabel = new JLabel("Fabio");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 32));
		panelNameAplication.add(lblNewLabel);

		panelLateral = new JPanel();
		panelLateral.setBackground(new Color(5, 150, 177));
		contentPane.add(panelLateral, BorderLayout.WEST);
		panelLateral.setLayout(new BorderLayout(0, 0));

		panelSecciones = new JPanel();
		panelSecciones.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
		panelSecciones.setBackground(new Color(18, 95, 115));
		panelLateral.add(panelSecciones, BorderLayout.CENTER);
		panelSecciones.setLayout(null);

		panelUser = new JPanel();
		panelUser.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
		panelUser.setBackground(new Color(18, 95, 115));
		panelLateral.add(panelUser, BorderLayout.NORTH);
		panelUser.setLayout(new BorderLayout(0, 0));

		lblTitle = new JLabel("");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setIcon(new ImageIcon(FrameGerente.class.getResource("/images/WhatsApp Image 2023-11-14 at 8.59.57 PM - copia.jpeg")));
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panelUser.add(lblTitle);

		JPanel panelTitleAplication = new JPanel();
		panelTitleAplication.setBackground(new Color(18, 95, 115));
		panelUser.add(panelTitleAplication, BorderLayout.SOUTH);
		panelTitleAplication.setLayout(new BoxLayout(panelTitleAplication, BoxLayout.Y_AXIS));

		lblManager = new JLabel("  KNOW CUBA  ");
		lblManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblManager.setForeground(SystemColor.textHighlightText);
		lblManager.setFont(new Font("Arial Black", Font.BOLD, 32));
		panelTitleAplication.add(lblManager);

		lblNewLabel_2 = new JLabel("\r\n");
		panelUser.add(lblNewLabel_2, BorderLayout.NORTH);

		lblSeccionAssociationCompany = new JLabel("ASSOCIATION-COMPANY");
		lblSeccionAssociationCompany.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));

		lblSeccionAssociationCompany.setForeground(SystemColor.textHighlightText);
		lblSeccionAssociationCompany.setOpaque(true);
		lblSeccionAssociationCompany.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeccionAssociationCompany.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSeccionAssociationCompany.setBackground(new Color(18, 95, 115));
		lblSeccionAssociationCompany.setBounds(0,79, 296, 38);
		panelSecciones.add(lblSeccionAssociationCompany);

		lblSeccionContractCreation = new JLabel("CONTRACT CREATION");
		lblSeccionContractCreation.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));

		lblSeccionContractCreation.setForeground(SystemColor.textHighlightText);
		lblSeccionContractCreation.setOpaque(true);
		lblSeccionContractCreation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeccionContractCreation.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSeccionContractCreation.setBackground(new Color(18, 95, 115));
		lblSeccionContractCreation.setBounds(0,318, 296, 38);
		panelSecciones.add(lblSeccionContractCreation);

		lblCerrarSesion = new JLabel("Cerrar Sesion");
		lblCerrarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrarSesion.setForeground(SystemColor.textHighlightText);
		lblCerrarSesion.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblCerrarSesion.setBounds(0, 792, 278, 46);
		panelSecciones.add(lblCerrarSesion);

		lblServiceProvider = new JLabel("   - SERVICE-PROVIDER");
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
		lblServiceProvider.setBounds(0, 128, 244, 38);
		panelSecciones.add(lblServiceProvider);

		lblTransportationProvider = new JLabel("   - TRANSPORTATION-PROVIDER");
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
		lblTransportationProvider.setBounds(0, 177, 271, 38);
		panelSecciones.add(lblTransportationProvider);

		lblAccommodationProvider = new JLabel("   - ACCOMMODATION-PROVIDER");
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
		lblAccommodationProvider.setBounds(0, 226, 271, 38);
		panelSecciones.add(lblAccommodationProvider);

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
		lblContracts.setBounds(0, 367, 136, 38);
		panelSecciones.add(lblContracts);

		this.contentPane.add(new PanelGerenteAsociacionEmpresaProveedorServicio(), BorderLayout.CENTER); // se añade el panel gerente asociocion empresa
		this.definirColorLabelsSecciones(); // se actualizan los colores de los labels se de seleccion de las secciones



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
		this.definirColorLabelSeccionAssociationCompany();
		this.definirColorLabelSeccionCreationContract();
		this.definirColorLabelServicerProvider();
		this.definirColorLabelTransportProvider();
		this.definirColorLabelAccommodationProvider();
		this.definirColorLabelContract();
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
}
