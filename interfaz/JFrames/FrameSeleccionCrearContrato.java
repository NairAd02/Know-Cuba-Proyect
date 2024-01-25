package JFrames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import JPanels.PanelGerenteCreacionContrato;
import logica.AccommodationContract;
import logica.CarrierContract;
import logica.Controller;
import logica.ServiceContract;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class FrameSeleccionCrearContrato extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSuperior;
	private JLabel lblX;
	private JPanel panelContenedorContratos;
	private JScrollPane scrollPane;
	private JPanel panelContratos;
	private JLabel lblServiceContract;
	private JLabel lblAccommodationContract;
	private JLabel lblCarrierContract;
	private JPanel panel;
	private PanelGerenteCreacionContrato panelGerenteCreacionContrato;
	private int mouseX, mouseY;
	private JPanel panelSalir;
	private JLabel lblNewLabel;


	public FrameSeleccionCrearContrato(PanelGerenteCreacionContrato p) {
		this.panelGerenteCreacionContrato = p;
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1035, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));


		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelSuperior = new JPanel();
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

		panelSuperior.setBackground(new Color(18, 95, 115));
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		setLocationRelativeTo(null);


		panelSuperior.setLayout(new BorderLayout(0, 0));

		panelSalir = new JPanel();

		panelSalir.setBackground(new Color(18, 95, 115));
		panelSuperior.add(panelSalir, BorderLayout.EAST);


		lblX = new JLabel("X");
		lblX.setForeground(SystemColor.textHighlightText);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FramePrincipal.getIntancie().setEnabled(true); // se habilita el frame principal
				cerrarFrame();
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
		panelSalir.add(lblX);
		
		lblNewLabel = new JLabel("   Select Contract Created");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		panelSuperior.add(lblNewLabel, BorderLayout.WEST);
		panelContenedorContratos = new JPanel();
		contentPane.add(panelContenedorContratos, BorderLayout.CENTER);
		panelContenedorContratos.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panelContenedorContratos.add(scrollPane, BorderLayout.CENTER);

		panelContratos = new JPanel();
		panelContratos.setBackground(SystemColor.inactiveCaptionBorder);
		scrollPane.setViewportView(panelContratos);
		panelContratos.setLayout(null);

		lblServiceContract = new JLabel("Service Contract");
		lblServiceContract.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblServiceContract.setHorizontalAlignment(SwingConstants.CENTER);
		lblServiceContract.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Controller.getInstancie().getTouristAgency().cantServicesProviders() != 0) { 
					FrameGerenteCreacionContratoServivio frameGerenteCreacionContratoServicio = new FrameGerenteCreacionContratoServivio(panelGerenteCreacionContrato, new ServiceContract());
					frameGerenteCreacionContratoServicio.setVisible(true);
					cerrarFrame(); // se cierra el frame actual
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblServiceContract.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblServiceContract.setBackground(new Color(6, 95, 115));
			}
		});
		lblServiceContract.setBounds(10, 27, 264, 41);
		lblServiceContract.setOpaque(true);
		lblServiceContract.setBackground(new Color(6, 95, 115));
		lblServiceContract.setForeground(SystemColor.textHighlightText);
		lblServiceContract.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblServiceContract.setFont(new Font("Dialog", Font.BOLD, 28));
		panelContratos.add(lblServiceContract);

		lblAccommodationContract = new JLabel("Accommodation Contract");
		lblAccommodationContract.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAccommodationContract.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccommodationContract.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Controller.getInstancie().getTouristAgency().cantAccommodationProviders() != 0) { 
					FrameGerenteCreacionContratoAlojamiento frameGerenteCreacionContratoAlojamiento = new FrameGerenteCreacionContratoAlojamiento(panelGerenteCreacionContrato, new AccommodationContract());
					frameGerenteCreacionContratoAlojamiento.setVisible(true);
					cerrarFrame(); // se cierra el frame actual
				}
				else {
					// Informar de que no existen proveedores con los que establecer contratos
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAccommodationContract.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAccommodationContract.setBackground(new Color(18, 95, 115));
			}
		});
		lblAccommodationContract.setBounds(299, 27, 418, 41);
		lblAccommodationContract.setBackground(new Color(18, 95, 115));
		lblAccommodationContract.setOpaque(true);
		lblAccommodationContract.setForeground(SystemColor.textHighlightText);
		lblAccommodationContract.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblAccommodationContract.setFont(new Font("Dialog", Font.BOLD, 28));
		panelContratos.add(lblAccommodationContract);

		lblCarrierContract = new JLabel("Carrier Contract");
		lblCarrierContract.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCarrierContract.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarrierContract.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Controller.getInstancie().getTouristAgency().cantTransportProviders() != 0) { 
					FrameGerenteCreacionContratoTransporte frameGerenteCreacionContratoTransporte = new FrameGerenteCreacionContratoTransporte(panelGerenteCreacionContrato, new CarrierContract());
					frameGerenteCreacionContratoTransporte.setVisible(true);
					cerrarFrame(); // se cierra el frame actual
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCarrierContract.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCarrierContract.setBackground(new Color(18, 95, 115));
			}
		});
		lblCarrierContract.setBounds(743, 27, 276, 41);
		lblCarrierContract.setOpaque(true);
		lblCarrierContract.setForeground(SystemColor.textHighlightText);
		lblCarrierContract.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblCarrierContract.setBackground(new Color(18, 95, 115));
		lblCarrierContract.setFont(new Font("Dialog", Font.BOLD, 28));
		panelContratos.add(lblCarrierContract);

		panel = new JPanel();
		panel.setBackground(new Color(18, 95, 115));
		contentPane.add(panel, BorderLayout.SOUTH);
	}

	private void cerrarFrame () {
		dispose();
	}


}
