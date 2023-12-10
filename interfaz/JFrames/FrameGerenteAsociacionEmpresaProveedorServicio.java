package JFrames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelosTablas.ModeloTablaActivies;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrameGerenteAsociacionEmpresaProveedorServicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable tableActivities;


	public FrameGerenteAsociacionEmpresaProveedorServicio() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(5, 150, 177));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblServiceProvider = new JLabel("SERVICE PROVIDER");
		lblServiceProvider.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblServiceProvider.setBounds(27, 11, 231, 30);
		contentPane.add(lblServiceProvider);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(427, 0, 38, 38);
		contentPane.add(lblX);
		
		JLabel lblName = new JLabel("NAME :");
		lblName.setForeground(SystemColor.info);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(90, 52, 80, 23);
		contentPane.add(lblName);
		
		JLabel lblProvince = new JLabel("PROVINCE :");
		lblProvince.setForeground(SystemColor.info);
		lblProvince.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvince.setBounds(90, 86, 109, 23);
		contentPane.add(lblProvince);
		
		JLabel lblActivities = new JLabel("ACTIVITIES");
		lblActivities.setForeground(SystemColor.info);
		lblActivities.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblActivities.setBounds(90, 130, 117, 23);
		contentPane.add(lblActivities);
		
		textField = new JTextField();
		textField.setBounds(180, 56, 197, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(219, 90, 158, 20);
		contentPane.add(textField_1);
		
		JPanel panelActivities = new JPanel();
		panelActivities.setBounds(87, 164, 290, 99);
		contentPane.add(panelActivities);
		panelActivities.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelActivities.add(scrollPane, BorderLayout.CENTER);
		
		tableActivities = new JTable();
		tableActivities.setModel(new ModeloTablaActivies());
		scrollPane.setViewportView(tableActivities);
		
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(115, 276, 235, 35);
		contentPane.add(lblAdd);
		
		JLabel lblAnnadir = new JLabel("ADD");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(220, 133, 71, 20);
		contentPane.add(lblAnnadir);
		
		JLabel lblEliminar = new JLabel("DELETE");
		lblEliminar.setOpaque(true);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setBackground(SystemColor.info);
		lblEliminar.setBounds(310, 133, 67, 20);
		contentPane.add(lblEliminar);
	}
}
