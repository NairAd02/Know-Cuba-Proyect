package JFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import JPanels.PanelGerenteCreacionContrato;
import JPanels.PanelReporteGeneral;
import logica.AccommodationContract;
import logica.CarrierContract;
import logica.Controller;
import logica.ServiceContract;

public class FrameSeleccionReporte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSuperior;
	private JLabel lblX;
	private JPanel panelContenedorContratos;
	private JScrollPane scrollPane;
	private JPanel panelContratos;
	private JLabel lblReporte2;
	private JLabel lblReporteListadoContratosConciliadoHoteles;
	private JLabel lblReporte3;
	private JPanel panel;
	private int mouseX, mouseY;
	private JPanel panelSalir;
	private JLabel lblNewLabel;
	private JLabel lblReporte4;
	private JLabel lblReporte5;
	private JLabel lblProgramaDelPaquete;
	private JLabel lblPlanDeIngresos;
	private HashMap mapa;

	private static final String reportsPath = "Reportes/";
	
	public FrameSeleccionReporte() {
		
		
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1035, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		mapa = new HashMap<>();


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
		
		lblNewLabel = new JLabel("   Select Report");
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

		lblReporte2 = new JLabel("List of seasons for hotel contracts ");
		lblReporte2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblReporte2.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporte2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PanelReporteGeneral pr = new PanelReporteGeneral(reportsPath + "Programa del paquete turistico.jasper",reportsPath + "SubreportSeason.jasper",reportsPath + "SubreportePlanHotelero.jasper", mapa, mapa);
				pr.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblReporte2.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblReporte2.setBackground(new Color(6, 95, 115));
			}
		});
		lblReporte2.setBounds(164, 42, 679, 41);
		lblReporte2.setOpaque(true);
		lblReporte2.setBackground(new Color(6, 95, 115));
		lblReporte2.setForeground(SystemColor.textHighlightText);
		lblReporte2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblReporte2.setFont(new Font("Dialog", Font.BOLD, 26));
		panelContratos.add(lblReporte2);

		lblReporteListadoContratosConciliadoHoteles = new JLabel("List of contracts reconciled with hotels");
		lblReporteListadoContratosConciliadoHoteles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblReporteListadoContratosConciliadoHoteles.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteListadoContratosConciliadoHoteles.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PanelReporteGeneral pr = new PanelReporteGeneral(reportsPath + "Listado de contratos conciliados con hoteless.jasper",reportsPath + "SubreportePlanHotelero.jasper", mapa);
				pr.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblReporteListadoContratosConciliadoHoteles.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblReporteListadoContratosConciliadoHoteles.setBackground(new Color(18, 95, 115));
			}
		});
		lblReporteListadoContratosConciliadoHoteles.setBounds(164, 125, 679, 41);
		lblReporteListadoContratosConciliadoHoteles.setBackground(new Color(18, 95, 115));
		lblReporteListadoContratosConciliadoHoteles.setOpaque(true);
		lblReporteListadoContratosConciliadoHoteles.setForeground(SystemColor.textHighlightText);
		lblReporteListadoContratosConciliadoHoteles.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblReporteListadoContratosConciliadoHoteles.setFont(new Font("Dialog", Font.BOLD, 26));
		panelContratos.add(lblReporteListadoContratosConciliadoHoteles);

		lblReporte3 = new JLabel("List of carrier contracts");
		lblReporte3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblReporte3.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporte3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PanelReporteGeneral pr = new PanelReporteGeneral(reportsPath + "Listado de contratos de transporte.jasper",reportsPath + "SubreporteVehiculos.jasper", mapa);
				pr.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblReporte3.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblReporte3.setBackground(new Color(18, 95, 115));
			}
		});
		lblReporte3.setBounds(164, 208, 679, 41);
		lblReporte3.setOpaque(true);
		lblReporte3.setForeground(SystemColor.textHighlightText);
		lblReporte3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblReporte3.setBackground(new Color(18, 95, 115));
		lblReporte3.setFont(new Font("Dialog", Font.BOLD, 26));
		panelContratos.add(lblReporte3);
		
		lblReporte4 = new JLabel("List of complementary service contracts");
		lblReporte4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblReporte4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PanelReporteGeneral pr = new PanelReporteGeneral(reportsPath + "Listado de contratos de servicios complementarios.jasper");
				pr.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblReporte4.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblReporte4.setBackground(new Color(18, 95, 115));
			}
		});
		lblReporte4.setOpaque(true);
		lblReporte4.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporte4.setForeground(SystemColor.textHighlightText);
		lblReporte4.setFont(new Font("Dialog", Font.BOLD, 26));
		lblReporte4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblReporte4.setBackground(new Color(18, 95, 115));
		lblReporte4.setBounds(164, 291, 679, 41);
		panelContratos.add(lblReporte4);
		
		lblReporte5 = new JLabel("List of active hotels");
		lblReporte5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblReporte5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameReporteElegirParametros frameElegirParametros = new FrameReporteElegirParametros(FrameSeleccionReporte.this);
				frameElegirParametros.setVisible(true);
				setEnabled(false); // se inhabilita el frame actual
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblReporte5.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblReporte5.setBackground(new Color(18, 95, 115));
			}
		});
		lblReporte5.setOpaque(true);
		lblReporte5.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporte5.setForeground(SystemColor.textHighlightText);
		lblReporte5.setFont(new Font("Dialog", Font.BOLD, 26));
		lblReporte5.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblReporte5.setBackground(new Color(18, 95, 115));
		lblReporte5.setBounds(164, 374, 679, 41);
		panelContratos.add(lblReporte5);
		
		lblProgramaDelPaquete = new JLabel("Tour package program");
		lblProgramaDelPaquete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblProgramaDelPaquete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PanelReporteGeneral pr = new PanelReporteGeneral(reportsPath + "Reporte del paquete turístico.jasper", reportsPath + "Subreporte Programacion de Actividades Diarias.jasper", mapa);
				pr.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblProgramaDelPaquete.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblProgramaDelPaquete.setBackground(new Color(18, 95, 115));
			}
		});
		lblProgramaDelPaquete.setOpaque(true);
		lblProgramaDelPaquete.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramaDelPaquete.setForeground(SystemColor.textHighlightText);
		lblProgramaDelPaquete.setFont(new Font("Dialog", Font.BOLD, 26));
		lblProgramaDelPaquete.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblProgramaDelPaquete.setBackground(new Color(18, 95, 115));
		lblProgramaDelPaquete.setBounds(164, 457, 679, 41);
		panelContratos.add(lblProgramaDelPaquete);
		
		lblPlanDeIngresos = new JLabel("Plan for revenue from the sale of tour packages");
		lblPlanDeIngresos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPlanDeIngresos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PanelReporteGeneral pr = new PanelReporteGeneral(reportsPath + "Plan de ingresos por concepto de venta de paquetes turísticos.jasper");
				pr.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblPlanDeIngresos.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblPlanDeIngresos.setBackground(new Color(18, 95, 115));
			}
		});
		lblPlanDeIngresos.setOpaque(true);
		lblPlanDeIngresos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlanDeIngresos.setForeground(SystemColor.textHighlightText);
		lblPlanDeIngresos.setFont(new Font("Dialog", Font.BOLD, 26));
		lblPlanDeIngresos.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblPlanDeIngresos.setBackground(new Color(18, 95, 115));
		lblPlanDeIngresos.setBounds(164, 540, 679, 41);
		panelContratos.add(lblPlanDeIngresos);

		panel = new JPanel();
		panel.setBackground(new Color(18, 95, 115));
		contentPane.add(panel, BorderLayout.SOUTH);
	}

	private void cerrarFrame () {
		dispose();
	}


}
