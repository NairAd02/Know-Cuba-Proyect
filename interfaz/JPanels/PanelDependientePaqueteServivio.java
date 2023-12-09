package JPanels;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;

public class PanelDependientePaqueteServivio extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelDependientePaqueteServivio() {
		setLayout(null);
		
		JLabel lblAtras = new JLabel("");
		lblAtras.setIcon(new ImageIcon(PanelDependientePaqueteServivio.class.getResource("/images/flecha.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(279, 452, 91, 38);
		add(lblAtras);
		
		JLabel lblService = new JLabel("SERVICE");
		lblService.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblService.setBounds(28, 11, 206, 30);
		add(lblService);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setOpaque(true);
		lblFondo.setBounds(0, 0, 650, 501);
		lblFondo.setBackground(new Color(5, 150, 177));
		add(lblFondo);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 84, 630, 359);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

	}

}
