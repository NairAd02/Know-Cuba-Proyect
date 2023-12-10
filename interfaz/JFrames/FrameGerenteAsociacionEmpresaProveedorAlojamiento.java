package JFrames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrameGerenteAsociacionEmpresaProveedorAlojamiento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable tableRoomTypes;
	private JTable tableMealPlan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGerenteAsociacionEmpresaProveedorAlojamiento frame = new FrameGerenteAsociacionEmpresaProveedorAlojamiento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameGerenteAsociacionEmpresaProveedorAlojamiento() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(5, 150, 177));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAccommodationProvider = new JLabel("ACCOMMODATION PROVIDER");
		lblAccommodationProvider.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblAccommodationProvider.setBounds(27, 11, 323, 30);
		contentPane.add(lblAccommodationProvider);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(492, 0, 38, 38);
		contentPane.add(lblX);
		
		JLabel lblName = new JLabel("NAME :");
		lblName.setForeground(SystemColor.info);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(127, 65, 80, 23);
		contentPane.add(lblName);
		
		JLabel lblProvince = new JLabel("PROVINCE :");
		lblProvince.setForeground(SystemColor.info);
		lblProvince.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvince.setBounds(127, 104, 109, 23);
		contentPane.add(lblProvince);
		
		JLabel lblRoomTypes = new JLabel("ROOM TYPES");
		lblRoomTypes.setForeground(SystemColor.info);
		lblRoomTypes.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblRoomTypes.setBounds(27, 285, 118, 23);
		contentPane.add(lblRoomTypes);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(219, 66, 197, 20);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(258, 105, 158, 20);
		contentPane.add(textField_1);
		
		JPanel panelRoomTypes = new JPanel();
		panelRoomTypes.setBounds(14, 319, 243, 113);
		contentPane.add(panelRoomTypes);
		panelRoomTypes.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelRoomTypes.add(scrollPane, BorderLayout.CENTER);
		
		tableRoomTypes = new JTable();
		scrollPane.setColumnHeaderView(tableRoomTypes);
		
		JLabel lblAnnadir = new JLabel("ADD");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(155, 288, 38, 20);
		contentPane.add(lblAnnadir);
		
		JLabel lblEliminar = new JLabel("DELETE");
		lblEliminar.setOpaque(true);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setBackground(SystemColor.info);
		lblEliminar.setBounds(200, 288, 57, 20);
		contentPane.add(lblEliminar);
		
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(155, 455, 235, 35);
		contentPane.add(lblAdd);
		
		JPanel panelMealPlan = new JPanel();
		panelMealPlan.setBounds(271, 319, 243, 113);
		contentPane.add(panelMealPlan);
		panelMealPlan.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelMealPlan.add(scrollPane_1, BorderLayout.CENTER);
		
		tableMealPlan = new JTable();
		scrollPane_1.setColumnHeaderView(tableMealPlan);
		
		JLabel lblMealPlan = new JLabel("MEAL PLAN");
		lblMealPlan.setForeground(SystemColor.info);
		lblMealPlan.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblMealPlan.setBounds(284, 285, 118, 23);
		contentPane.add(lblMealPlan);
		
		JLabel lblAnnadir_1 = new JLabel("ADD");
		lblAnnadir_1.setOpaque(true);
		lblAnnadir_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir_1.setBackground(SystemColor.info);
		lblAnnadir_1.setBounds(412, 288, 38, 20);
		contentPane.add(lblAnnadir_1);
		
		JLabel lblEliminar_1 = new JLabel("DELETE");
		lblEliminar_1.setOpaque(true);
		lblEliminar_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar_1.setBackground(SystemColor.info);
		lblEliminar_1.setBounds(457, 288, 57, 20);
		contentPane.add(lblEliminar_1);
		
		JLabel lblHotelChail = new JLabel("HOTEL CHAIN :");
		lblHotelChail.setForeground(SystemColor.info);
		lblHotelChail.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblHotelChail.setBounds(127, 143, 138, 23);
		contentPane.add(lblHotelChail);
		
		JLabel lblHotelCategory = new JLabel("HOTEL CATEGORY :");
		lblHotelCategory.setForeground(SystemColor.info);
		lblHotelCategory.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblHotelCategory.setBounds(127, 182, 181, 23);
		contentPane.add(lblHotelCategory);
		
		JLabel lblDireccion = new JLabel("DIRECCION :");
		lblDireccion.setForeground(SystemColor.info);
		lblDireccion.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDireccion.setBounds(127, 221, 109, 23);
		contentPane.add(lblDireccion);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(359, 183, 57, 20);
		contentPane.add(spinner);
		
		textField_2 = new JTextField();
		textField_2.setBounds(330, 222, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(284, 144, 132, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
	}

}
