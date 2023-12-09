package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class FrameInformacionPaquete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCreate;
	private JLabel lblReserve;
	private JLabel lblModalities;
	private JLabel lblAccommodationModalitys;
	private JLabel lblTransportModalitys;
	private JLabel lblServiceModalitys;
	private JPanel panel;
	private JLabel lblPackage;
	private JLabel lblPackage_1;

	
	public FrameInformacionPaquete() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 650, 501);
		panel.setBackground(new Color(5, 150, 177));
		contentPane.add(panel);
		panel.setLayout(null);

		lblModalities = new JLabel("MODALITIES : ");
		lblModalities.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblModalities.setBounds(10, 44, 151, 30);
		panel.add(lblModalities);

		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(612, 0, 38, 38);
		panel.add(lblX);

		lblAccommodationModalitys = new JLabel("");
		lblAccommodationModalitys.setIcon(new ImageIcon(FrameInformacionPaquete.class.getResource("/images/hotel1.png")));
		lblAccommodationModalitys.setOpaque(true);
		lblAccommodationModalitys.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccommodationModalitys.setBackground(new Color(18, 95, 115));
		lblAccommodationModalitys.setBounds(97, 127, 181, 87);
		panel.add(lblAccommodationModalitys);

		lblTransportModalitys = new JLabel("");
		lblTransportModalitys.setIcon(new ImageIcon(FrameInformacionPaquete.class.getResource("/images/carro3.png")));
		lblTransportModalitys.setOpaque(true);
		lblTransportModalitys.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransportModalitys.setBackground(new Color(18, 95, 115));
		lblTransportModalitys.setBounds(369, 127, 181, 87);
		panel.add(lblTransportModalitys);

		lblServiceModalitys = new JLabel("");
		lblServiceModalitys.setIcon(new ImageIcon(FrameInformacionPaquete.class.getResource("/images/actividad4.png")));
		lblServiceModalitys.setOpaque(true);
		lblServiceModalitys.setHorizontalAlignment(SwingConstants.CENTER);
		lblServiceModalitys.setBackground(new Color(18, 95, 115));
		lblServiceModalitys.setBounds(234, 250, 181, 87);
		panel.add(lblServiceModalitys);

		if (true) // si el usuario es trabajador
			this.addButtonCreate();
		else
			this.addButtonRerserve();


	}

	public void addButtonCreate () {
		lblCreate = new JLabel("CREATE");
		lblCreate.setOpaque(true);
		lblCreate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreate.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblCreate.setBackground(SystemColor.info);
		lblCreate.setBounds(207, 421, 235, 35);
		panel.add(lblCreate);
		
		lblPackage = new JLabel("PACKAGE");
		lblPackage.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblPackage.setBounds(10, 3, 113, 30);
		panel.add(lblPackage);
		
		lblPackage_1 = new JLabel("#");
		lblPackage_1.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblPackage_1.setBounds(131, 3, 57, 30);
		panel.add(lblPackage_1);
	}

	public void addButtonRerserve () {
		lblReserve = new JLabel("RESERVE");
		lblReserve.setOpaque(true);
		lblReserve.setHorizontalAlignment(SwingConstants.CENTER);
		lblReserve.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblReserve.setBackground(SystemColor.info);
		lblReserve.setBounds(207, 421, 235, 35);
		panel.add(lblReserve);
	}
}
