package JFrames;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import JPanels.LabelDesplazar;
import JPanels.PanelLienzoPaquetes;
import JPanels.PanelPaqueteTuristico;
import logica.Controller;
import logica.Dependent;
import logica.PackageDesigner;
import logica.TouristPackage;
import utils.ConnectionDataBase;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;


public class FramePaquetes extends JFrame {

	private static final long serialVersionUID = 1L;
	private static FramePaquetes framePaquetes;
	private JPanel contentPane;
	private JLabel lblDelete;
	private JLabel lblAnnadir;
	private JPanel panelOpciones;
	private JLabel lblTitleFrame;
	private int mouseX, mouseY;
	private JTextField textFieldProvince;
	private JPanel panelContenedorPaneles;
	private ArrayList<PanelLienzoPaquetes> panelesPaquetes;
	private JPanel panelDesplazamiento;
	private JLabel lblX;

	public static FramePaquetes getInstancie () {
		if (framePaquetes == null)
			framePaquetes = new FramePaquetes();

		return framePaquetes;
	}


	private FramePaquetes() {
		this.panelesPaquetes = new ArrayList<PanelLienzoPaquetes>();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 813);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 990, 782);
		contentPane.add(panel);
		panel.setLayout(null);

		panelOpciones = new JPanel();
		panelOpciones.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x= e.getXOnScreen();
				int y= e.getYOnScreen();

				setLocation(x - mouseX , y - mouseY );
			}
		});
		panelOpciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		panelOpciones.setLayout(null);
		panelOpciones.setBackground(new Color(5, 150, 177));
		panelOpciones.setBounds(0, 0, 990, 112);
		panel.add(panelOpciones);
		setLocationRelativeTo(null);
		lblTitleFrame = new JLabel("Dynamic");
		lblTitleFrame.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitleFrame.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTitleFrame.setBounds(10, 9, 275, 20);
		panelOpciones.add(lblTitleFrame);

		JLabel lblCostMax = new JLabel("Cost-Max :");
		lblCostMax.setForeground(SystemColor.info);
		lblCostMax.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostMax.setBounds(84, 61, 101, 20);
		panelOpciones.add(lblCostMax);

		JLabel lblCostomin = new JLabel("Cost-Min :");
		lblCostomin.setForeground(SystemColor.info);
		lblCostomin.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostomin.setBounds(323, 61, 96, 20);
		panelOpciones.add(lblCostomin);

		JLabel lblProvincia = new JLabel("Province :");
		lblProvincia.setForeground(SystemColor.info);
		lblProvincia.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvincia.setBounds(549, 61, 96, 20);
		panelOpciones.add(lblProvincia);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(1)));
		spinner.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spinner.setBounds(189, 61, 70, 20);
		panelOpciones.add(spinner);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(1)));
		spinner_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spinner_1.setBounds(425, 61, 70, 20);
		panelOpciones.add(spinner_1);

		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.controlText);
		separator.setBackground(SystemColor.controlText);
		separator.setBounds(180, 36, 810, 2);
		panelOpciones.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(0, 36, 70, 2);
		panelOpciones.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.controlText);
		separator_2.setBackground(SystemColor.controlText);
		separator_2.setBounds(0, 110, 990, 2);
		panelOpciones.add(separator_2);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Controller.getInstancie().getUser().cerrarConexion(); // se cierra la sesión del usuario
					ConnectionDataBase.commit(); // confirmar las operaciones realizadas
				} catch (SQLException e1) {
					try {
						ConnectionDataBase.roolback(); // cancelar las operaciones realizadas
					} catch (SQLException e2) { 				
						e2.printStackTrace();
					} 
					e1.printStackTrace();
				}
				System.exit(0); // Se finaliza la ejecucion del programa
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
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setForeground(new Color(0, 0, 0));
		lblX.setBounds(952, 0, 38, 38);
		panelOpciones.add(lblX);

		panelContenedorPaneles = new JPanel();
		panelContenedorPaneles.setBounds(0, 111, 990, 671);
		panel.add(panelContenedorPaneles);
		panelContenedorPaneles.setLayout(new BorderLayout(0, 0));

		panelDesplazamiento = new JPanel();
		panelDesplazamiento.setBounds(0, 780, 990, 33);
		contentPane.add(panelDesplazamiento);




		this.definirComponentes();
		this.actualizarPanelPaquetes(); // metodo para actualizar la informacion los paquetes turisticos en pantalla
		this.definirTexto();

	}

	private void definirComponentes () {
		if (Controller.getInstancie().getUser() instanceof PackageDesigner) {
			this.addButtonAdd();
			this.addButtonDelete();
		}
	}

	/*private void addP () {
		panelPaquetes = new JPanel();
		panelPaquetes.setBackground(new Color(5, 150, 177));
		panelPaquetes.setBounds(0, 112, 990, 670);
		this.panelContenedorPaneles.add(panelPaquetes);
		panelPaquetes.setLayout(new BoxLayout(panelPaquetes, BoxLayout.Y_AXIS));
		panelPaquetes.add(new PanelPaqueteTuristico());
		panelPaquetes.add(new PanelPaqueteTuristico());
		panelPaquetes.add(new PanelPaqueteTuristico());
	}*/

	private void definirTexto () {
		if (Controller.getInstancie().getUser() instanceof PackageDesigner)
			this.lblTitleFrame.setText("Package Designer");
		else if (Controller.getInstancie().getUser() instanceof Dependent)
			this.lblTitleFrame.setText("Dependent");
	}

	private void addButtonAdd () {
		lblAnnadir = new JLabel("ADD");
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameInformacionPaquete frameInfoPackage = new FrameInformacionPaquete(new TouristPackage());
				frameInfoPackage.setVisible(true);
				setEnabled(false); // se inhabilita el frame
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(825, 49, 155, 20);
		panelOpciones.add(lblAnnadir);
	}

	private void addButtonDelete () {
		lblDelete = new JLabel("DELETE");
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblDelete.setOpaque(true);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDelete.setBackground(SystemColor.info);
		lblDelete.setBounds(825, 70, 155, 20);
		panelOpciones.add(lblDelete);

		textFieldProvince = new JTextField();
		textFieldProvince.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		textFieldProvince.setBounds(643, 64, 142, 20);
		panelOpciones.add(textFieldProvince);
		textFieldProvince.setColumns(10);

	}

	public void actualizarPanelPaquetes () {
		this.reiniciarPanelPaquetes();
		ArrayList<TouristPackage> touristPackages = Controller.getInstancie().getTouristAgency().getTouristPackages(); // se obtienen todos los paquetes turisticos de la empresa;

		for (TouristPackage touristPackage : touristPackages) {
			this.addPanelPaqueteTuristico(new PanelPaqueteTuristico(touristPackage));
		}
	}

	private void reiniciarPanelPaquetes () {
		this.panelesPaquetes.clear();
		this.panelContenedorPaneles.removeAll();
		this.panelDesplazamiento.removeAll();
	}

	private void addPanelPaqueteTuristico (PanelPaqueteTuristico panelPaqueteTuristico) {
		if (!this.panelesPaquetes.isEmpty()) {
			PanelLienzoPaquetes panelLienzoPaquetes = this.panelesPaquetes.get(this.panelesPaquetes.size() - 1);
			if (panelLienzoPaquetes.getComponentCount() < 3)
				panelLienzoPaquetes.add(panelPaqueteTuristico);
			else
				this.crearNuevoPanelLiezoPaquetes(panelPaqueteTuristico);
		}
		else { // si esta vacio
			this.crearNuevoPanelLiezoPaquetes(panelPaqueteTuristico);
		}

		this.addPanelPaqueteTuristicoIntoPanelContenedorPaquetes(this.panelesPaquetes.get(this.panelesPaquetes.size() - 1));

	}

	private void addPanelPaqueteTuristicoIntoPanelContenedorPaquetes (PanelLienzoPaquetes panelLienzoPaquetes) {
		if (this.panelContenedorPaneles.getComponentCount() != 0)
			this.panelContenedorPaneles.remove(0);
		this.panelContenedorPaneles.add(panelLienzoPaquetes, BorderLayout.CENTER);
		this.repintarFrame();
	}

	public void ubicarPanelPaquetes (int pos) {
		this.addPanelPaqueteTuristicoIntoPanelContenedorPaquetes(this.panelesPaquetes.get(pos));
	}

	private void crearNuevoPanelLiezoPaquetes (PanelPaqueteTuristico panelPaqueteTuristico) {
		this.panelesPaquetes.add(new PanelLienzoPaquetes(panelPaqueteTuristico));
		this.addLabelDesplazar(this.panelesPaquetes.size());
	}

	private void addLabelDesplazar (int pos) {
		this.panelDesplazamiento.add(new LabelDesplazar(pos));
	}

	private void repintarFrame () {
		repaint();
		revalidate();
	}
}
