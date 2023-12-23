package JFrames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JPanels.PanelGerenteAsociacionEmpresa;
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

public class FrameGerente extends JFrame {

	private static final long serialVersionUID = 1L;
	private static FrameGerente frameGerente;
	private JPanel contentPane;
	private JLabel lblSeccionAssociationCompany;
	private JLabel lblSeccionContractCreation;
	private int mouseX, mouseY;
	private JPanel panelLateral;
	private JPanel panelSecciones;
	private JPanel panelTitle;
	private JLabel lblTitle;
	private JLabel lblX;


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
		
		JLabel lblManager = new JLabel("MANAGER");
		lblManager.setForeground(SystemColor.textHighlightText);
		lblManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblManager.setFont(new Font("Arial Black", Font.PLAIN, 32));
		panelSuperior.add(lblManager, BorderLayout.WEST);
		
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

		panelLateral = new JPanel();
		panelLateral.setBackground(new Color(5, 150, 177));
		contentPane.add(panelLateral, BorderLayout.WEST);
		panelLateral.setLayout(new BorderLayout(0, 0));
		
		panelSecciones = new JPanel();
		panelSecciones.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
		panelSecciones.setBackground(new Color(18, 95, 115));
		panelLateral.add(panelSecciones, BorderLayout.CENTER);
		panelSecciones.setLayout(null);
		
		panelTitle = new JPanel();
		panelTitle.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
		panelTitle.setBackground(new Color(18, 95, 115));
		panelLateral.add(panelTitle, BorderLayout.NORTH);
		
		lblTitle = new JLabel("                            ");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panelTitle.add(lblTitle);

		lblSeccionAssociationCompany = new JLabel("ASSOCIATION-COMPANY");
		lblSeccionAssociationCompany.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblSeccionAssociationCompany.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteAsociacionEmpresa()); // se cambia la seccion por la seccion de asociacion de empresa
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSeccionAssociationCompany.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				definirColorLabelsSecciones(); // se actualizan los colores de los labels se de seleccion de las secciones
			}
		});
		lblSeccionAssociationCompany.setForeground(SystemColor.textHighlightText);
		lblSeccionAssociationCompany.setOpaque(true);
		lblSeccionAssociationCompany.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeccionAssociationCompany.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSeccionAssociationCompany.setBackground(new Color(18, 95, 115));
		lblSeccionAssociationCompany.setBounds(0,425, 293, 38);
		panelSecciones.add(lblSeccionAssociationCompany);

		lblSeccionContractCreation = new JLabel("CONTRACT CREATION");
		lblSeccionContractCreation.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblSeccionContractCreation.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteCreacionContrato()); // se cambia la seccion por la seccion de creacion de contrato
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSeccionContractCreation.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				definirColorLabelsSecciones(); // se actualizan los colores de los labels se de seleccion de las secciones
			}
		});
		lblSeccionContractCreation.setForeground(SystemColor.textHighlightText);
		lblSeccionContractCreation.setOpaque(true);
		lblSeccionContractCreation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeccionContractCreation.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSeccionContractCreation.setBackground(new Color(18, 95, 115));
		lblSeccionContractCreation.setBounds(0,495, 293, 38);
		panelSecciones.add(lblSeccionContractCreation);
		
		JTree tree = new JTree();
		tree.setBounds(25, 591, 72, 64);
		panelSecciones.add(tree);

		this.contentPane.add(new PanelGerenteAsociacionEmpresa(), BorderLayout.CENTER); // se añade el panel gerente asociocion empresa
		this.definirColorLabelsSecciones(); // se actualizan los colores de los labels se de seleccion de las secciones
		
		

	}

	public void cambiarSeccion (JPanel seccion) {
		this.contentPane.remove(this.contentPane.getComponentCount() - 1); // se remueve el ultimo panel (este panel va a ser una seccion)
		this.contentPane.add(seccion, BorderLayout.CENTER); // se adiciona la seccion
		this.repintarFrame(); // se repinta el frame
		this.definirColorLabelsSecciones();
	}

	public void repintarFrame () {
		repaint();
		revalidate();
	}

	public void definirColorLabelsSecciones () {
		this.definirColorLabelSeccionAssociationCompany();
		this.definirColorLabelSeccionCreationContract();
		this.repintarFrame();
	}

	private void definirColorLabelSeccionAssociationCompany () {
		if (this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresa) // se esta seleccionada la seccion asociacion empresa
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
}
