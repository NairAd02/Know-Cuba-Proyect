package JFrames;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JSeparator;


public class FramePaquetes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDelete;
	private JLabel lblAnnadir;
	private JPanel panelOpciones;
	private JLabel lblRol;


	public FramePaquetes() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 782);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 990, 782);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panelOpciones = new JPanel();
		panelOpciones.setLayout(null);
		panelOpciones.setBackground(new Color(5, 150, 177));
		panelOpciones.setBounds(0, 0, 990, 112);
		panel.add(panelOpciones);
		
		lblRol = new JLabel("Dynamic");
		lblRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblRol.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblRol.setBounds(42, 11, 163, 39);
		panelOpciones.add(lblRol);
		
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		comboBox.setBounds(650, 61, 121, 20);
		panelOpciones.add(comboBox);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spinner.setBounds(189, 61, 70, 20);
		panelOpciones.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
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
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setForeground(new Color(0, 0, 0));
		lblX.setBounds(952, 0, 38, 38);
		panelOpciones.add(lblX);
		

		JPanel panelPaquetes = new JPanel();
		panelPaquetes.setLayout(null);
		panelPaquetes.setBackground(new Color(5, 150, 177));
		panelPaquetes.setBounds(0, 112, 990, 670);
		panel.add(panelPaquetes);
		
		if (true) { // si el usuario es trabajador
		this.addButtonAdd();
		this.addButtonDelete();
		}
	}
	
	public void addButtonAdd () {
		lblAnnadir = new JLabel("ADD");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(825, 49, 155, 20);
		panelOpciones.add(lblAnnadir);
	}
	
	public void addButtonDelete () {
		lblDelete = new JLabel("DELETE");
		lblDelete.setOpaque(true);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDelete.setBackground(SystemColor.info);
		lblDelete.setBounds(825, 70, 155, 20);
		panelOpciones.add(lblDelete);
	}
}
