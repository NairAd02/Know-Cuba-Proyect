package JFrames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JPanels.PanelGerenteAsociacionEmpresa;
import JPanels.PanelGerenteCreacionContrato;
import logica.Controller;

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

public class FrameGerente extends JFrame {

	private static final long serialVersionUID = 1L;
	private static FrameGerente frameGerente;
	private JPanel contentPane;
	private JLabel lblSeccionAssociationCompany;
	private JLabel lblSeccionContractCreation;
	private JLabel lblX;
	private int mouseX, mouseY;


	/**
	 * Launch the application.
	 */

	public static FrameGerente getIntancie () {
		if (frameGerente == null)
			frameGerente = new FrameGerente();

		return frameGerente;
	}

	private FrameGerente() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 782);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x= e.getXOnScreen();
				int y= e.getYOnScreen();

				setLocation(x - mouseX , y - mouseY );
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(5, 150, 177));
		panel.setBounds(0, 0, 990, 65);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblManager = new JLabel("MANAGER");
		lblManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblManager.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblManager.setBounds(59, 16, 142, 32);
		panel.add(lblManager);

		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.controlText);
		separator.setBackground(SystemColor.controlText);
		separator.setBounds(189, 39, 801, 2);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(0, 39, 70, 2);
		panel.add(separator_1);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Controller.getInstancie().getUser().cerrarConexion();
				} catch (SQLException e1) {

					e1.printStackTrace();
				} // se cierra la sesión del usuario
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(SystemColor.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(SystemColor.black);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(952, 0, 38, 38);
		panel.add(lblX);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.controlText);
		separator_2.setBackground(SystemColor.controlText);
		separator_2.setBounds(0, 63, 990, 2);
		panel.add(separator_2);

		JPanel panelLateral = new JPanel();
		panelLateral.setBounds(0, 65, 279, 717);
		panelLateral.setBackground(new Color(5, 150, 177));
		contentPane.add(panelLateral);
		panelLateral.setLayout(null);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setForeground(SystemColor.controlText);
		separator_2_1.setBackground(SystemColor.controlText);
		separator_2_1.setBounds(277, 0, 2, 717);
		panelLateral.add(separator_2_1);

		lblSeccionAssociationCompany = new JLabel("ASSOCIATION-COMPANY");
		lblSeccionAssociationCompany.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteAsociacionEmpresa()); // se cambia la seccion por la seccion de asociacion de empresa
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSeccionAssociationCompany.setBackground(SystemColor.info);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				definirColorLabelsSecciones(); // se actualizan los colores de los labels se de seleccion de las secciones
			}
		});
		lblSeccionAssociationCompany.setForeground(new Color(0, 0, 0));
		lblSeccionAssociationCompany.setOpaque(true);
		lblSeccionAssociationCompany.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeccionAssociationCompany.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSeccionAssociationCompany.setBackground(new Color(0, 183, 194));
		lblSeccionAssociationCompany.setBounds(0, 358, 279, 45);
		panelLateral.add(lblSeccionAssociationCompany);

		lblSeccionContractCreation = new JLabel("CONTRACT CREATION");
		lblSeccionContractCreation.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteCreacionContrato()); // se cambia la seccion por la seccion de creacion de contrato
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSeccionContractCreation.setBackground(SystemColor.info);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				definirColorLabelsSecciones(); // se actualizan los colores de los labels se de seleccion de las secciones
			}
		});
		lblSeccionContractCreation.setForeground(new Color(0, 0, 0));
		lblSeccionContractCreation.setOpaque(true);
		lblSeccionContractCreation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeccionContractCreation.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSeccionContractCreation.setBackground(new Color(0, 183, 194));
		lblSeccionContractCreation.setBounds(0, 425, 279, 45);
		panelLateral.add(lblSeccionContractCreation);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(FrameGerente.class.getResource("/images/Imagen5.png")));
		lblNewLabel_4.setBounds(0, 0, 279, 717);
		panelLateral.add(lblNewLabel_4);

		this.contentPane.add(new PanelGerenteAsociacionEmpresa()); // se añade el panel gerente asociocion empresa
		this.definirColorLabelsSecciones(); // se actualizan los colores de los labels se de seleccion de las secciones

	}

	public void cambiarSeccion (JPanel seccion) {
		this.contentPane.remove(this.contentPane.getComponentCount() - 1); // se remueve el ultimo panel (este panel va a ser una seccion)
		this.contentPane.add(seccion); // se adiciona la seccion
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
			this.lblSeccionAssociationCompany.setBackground(SystemColor.info);
		else
			lblSeccionAssociationCompany.setBackground(new Color(0, 183, 194));
	}

	private void definirColorLabelSeccionCreationContract () {
		if (this.contentPane.getComponent(this.contentPane.getComponentCount() - 1) instanceof PanelGerenteCreacionContrato)  // se esta seleccionada la seccion creacion contrato
			this.lblSeccionContractCreation.setBackground(SystemColor.info);
		else
			lblSeccionContractCreation.setBackground(new Color(0, 183, 194));
	}
}
