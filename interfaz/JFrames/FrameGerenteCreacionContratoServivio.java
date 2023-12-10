package JFrames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrameGerenteCreacionContratoServivio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableModalities;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGerenteCreacionContratoServivio frame = new FrameGerenteCreacionContratoServivio();
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
	public FrameGerenteCreacionContratoServivio() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 531);
		panel.setBackground(new Color(5, 150, 177));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblServiceContract = new JLabel("SERVICE CONTRACT");
		lblServiceContract.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblServiceContract.setBounds(27, 11, 229, 30);
		panel.add(lblServiceContract);
		
		JLabel lblStartDate = new JLabel("START DATE ");
		lblStartDate.setForeground(SystemColor.info);
		lblStartDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblStartDate.setBounds(59, 71, 119, 23);
		panel.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("END DATE ");
		lblEndDate.setForeground(SystemColor.info);
		lblEndDate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblEndDate.setBounds(247, 71, 98, 23);
		panel.add(lblEndDate);
		
		JLabel lblProvider = new JLabel("PROVIDER");
		lblProvider.setForeground(SystemColor.info);
		lblProvider.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvider.setBounds(418, 71, 98, 23);
		panel.add(lblProvider);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(59, 105, 119, 22);
		panel.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(230, 105, 119, 22);
		panel.add(dateChooser_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(391, 105, 152, 22);
		panel.add(comboBox);
		
		JLabel lblModalities = new JLabel("MODALITIES ");
		lblModalities.setForeground(SystemColor.info);
		lblModalities.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblModalities.setBounds(42, 166, 124, 30);
		panel.add(lblModalities);
		
		JPanel panelModalities = new JPanel();
		panelModalities.setBounds(23, 207, 553, 267);
		panel.add(panelModalities);
		panelModalities.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelModalities.add(scrollPane, BorderLayout.CENTER);
		
		tableModalities = new JTable();
		scrollPane.setColumnHeaderView(tableModalities);
		
		JLabel lblConfirm = new JLabel("CONFIRM");
		lblConfirm.setOpaque(true);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblConfirm.setBackground(SystemColor.info);
		lblConfirm.setBounds(182, 485, 235, 35);
		panel.add(lblConfirm);
		
		JLabel lblEliminar = new JLabel("DELETE");
		lblEliminar.setOpaque(true);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setBackground(SystemColor.info);
		lblEliminar.setBounds(421, 173, 155, 20);
		panel.add(lblEliminar);
		
		JLabel lblAnnadir = new JLabel("ADD");
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(247, 173, 155, 20);
		panel.add(lblAnnadir);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(562, 0, 38, 38);
		panel.add(lblX);
	}
}
