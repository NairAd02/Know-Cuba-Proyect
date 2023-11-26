package JFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class FrameSalir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSalir frame = new FrameSalir();
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
	public FrameSalir() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(18, 95, 115));
		panel.setBounds(0, 0, 422, 201);
		contentPane.add(panel);
		
		JLabel lblYes = new JLabel("YES");
		lblYes.setOpaque(true);
		lblYes.setHorizontalAlignment(SwingConstants.CENTER);
		lblYes.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblYes.setBackground(SystemColor.info);
		lblYes.setBounds(55, 120, 138, 35);
		panel.add(lblYes);
		
		JLabel lblNo = new JLabel("NO");
		lblNo.setOpaque(true);
		lblNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNo.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblNo.setBackground(SystemColor.info);
		lblNo.setBounds(233, 120, 138, 35);
		panel.add(lblNo);
		
		JLabel lblAreYouSure = new JLabel("Are you sure you want to go out?");
		lblAreYouSure.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAreYouSure.setBounds(32, 54, 365, 30);
		panel.add(lblAreYouSure);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(384, 0, 38, 38);
		panel.add(lblX);
	}

}
